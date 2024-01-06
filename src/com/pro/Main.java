package com.pro;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Puzzle> puzzles = new ArrayList<Puzzle>();

    public static void main(String[] args) {
        puzzles = Util.setPuzzles();
        Util.selectCandidate(0);
    }
}