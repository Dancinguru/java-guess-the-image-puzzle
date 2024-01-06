package com.pro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {

    public static List<Puzzle> takenPuzzles = new ArrayList<Puzzle>();
    public static List<Puzzle> restPuzzles = new ArrayList<Puzzle>();

    public static int row;
    public static int column;
    /**
     * api to get data from txt file and set the Puzzle List
     * @return List<Puzzle> puzzles;
     */
    public static List<Puzzle> setPuzzles() {

        List<Puzzle> puzzles = new ArrayList<Puzzle>();
        InputStream inputStream = Util.class.getResourceAsStream("4x4.txt");
        Scanner scanner = new Scanner(inputStream);
        int index = 0;
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            String[] sdata = data.split(" ");
            if(sdata.length == 2) {
                row = Integer.parseInt(sdata[0]);
                column = Integer.parseInt(sdata[1]);
                continue;
            };
            Puzzle puzzle = new Puzzle();

            for (String s: sdata
                 ) {
                puzzle.add(Integer.parseInt(s));
            }
            puzzle.add(index);
            index ++;
            puzzles.add(puzzle);
        }
        scanner.close();
        restPuzzles = puzzles;
        return puzzles;
    }

    /**
     * api to get candidate puzzle from rest puzzles with index,
     * @param index
     * @return List<Puzzle> candidates
     */
    public static List<Puzzle> findCandidatePuzzles(int index) {
        List<Puzzle> resultPuzzles = new ArrayList<Puzzle>();
        int val1 = 0;
        int val2 = 0;
        if(index == 0) {
            val1 = 0;
            val2 = 0;
        }
        if (0 < index && index < column ) {
            val1 = takenPuzzles.get(index - 1).getRight();
            val2 = 0;
        }

        if(index > column - 1 && index % column == 0) {
            val1 = 0;
            val2 = takenPuzzles.get(index - column).getBottom();
        }

        if(index > column && index % column > 0 ) {
            val1 = takenPuzzles.get(index - 1).getRight();
            val2 = takenPuzzles.get(index - column).getBottom();
        }

        for (Puzzle puzzle: restPuzzles) {
            for (int i = 0; i < 4 ; i ++ ) {
                if((index % column == column - 1)) {
                    if(isCandidatePuzzleOnRight(puzzle, val1, val2)) {
                        resultPuzzles.add(puzzle);
                        break;
                    }
                }

                else if((index > (row - 1) * column - 1)) {
                    if(isCandidatePuzzleOnBottom(puzzle, val1, val2)) {
                        resultPuzzles.add(puzzle);
                        break;
                    }
                }

                else if(isCandidatePuzzle(puzzle, val1, val2)) {
                    resultPuzzles.add(puzzle);
                    break;
                }
                puzzle.rotate();
            }
        }

        return resultPuzzles;
    }

    /**
     * api to check if a puzzle can be a candidate using left and top value
     * @param puzzle
     * @param val1
     * @param val2
     * @return  true or false
     */
    public static boolean isCandidatePuzzle(Puzzle puzzle, int val1, int val2) {
        if(puzzle.getLeft() == val1 && puzzle.getTop() == val2)
            return true;
        return false;
    }
    public static boolean isCandidatePuzzleOnRight(Puzzle puzzle, int left, int top) {
        if(puzzle.getLeft() == left && puzzle.getTop() == top && puzzle.getRight() == 0)
            return true;
        return false;
    }
    public static boolean isCandidatePuzzleOnBottom(Puzzle puzzle, int left, int top) {
        if(puzzle.getLeft() == left && puzzle.getTop() == top && puzzle.getBottom() == 0)
            return true;
        return false;
    }

    public static Puzzle rotateCandidateUntilFix(int n, Puzzle puzzle) {
        int v1 = 0;
        int v2 = 0;
        if(n > 0)
            v1 = takenPuzzles.get(n - 1).getRight();
        if(n > column - 1)
            v2 = takenPuzzles.get(n - column).getBottom();
        for(int i = 0 ; i < 4; i ++ ){
            if(puzzle.getLeft() == v1 && puzzle.getTop() == v2)
                break;

            puzzle.rotate();
        }

        return puzzle;
    }

    public static boolean selectCandidate(int n) {
        if(takenPuzzles.size() != n)
            return false;

        List<Puzzle> candidates = new ArrayList<Puzzle>();
        candidates = findCandidatePuzzles(n);
        if(candidates.size() == 0) {
            return false;
        }

        for (int j = 0; j < candidates.size() ; j ++ ) {
            Puzzle p = candidates.get(j);
            p = rotateCandidateUntilFix(n, p);
            takenPuzzles.add(p);
            restPuzzles.remove(p);

            if(n == row * column - 1) {
                printResult(takenPuzzles);
                return false;
            }

            if(!selectCandidate(n+1)) {
                takenPuzzles.remove(takenPuzzles.size() - 1);
                restPuzzles.add(p);
            }
        }
        if( n > 0) {
            selectCandidate(n - 1);
        }

        return false;
    }

    public static void printResult(List<Puzzle> puzzleList) {
        for (int i = 0; i < row; i++) {
            int n = i * column;
            System.out.println(takenPuzzles.get(n).getIndex() + 1 + " " + (takenPuzzles.get(n + 1).getIndex() + 1) + " " + (takenPuzzles.get(n + 2).getIndex() + 1) + " " + (takenPuzzles.get(n + 3).getIndex() + 1));
        }
    }
}

