package com.pro;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    List<Integer> properties = new ArrayList<Integer>();

    public Puzzle() {
        properties = new ArrayList<>();
    }

    public Puzzle(int left, int top, int right, int bottom, int index) {
        this.properties.add(left);
        this.properties.add(right);
        this.properties.add(top);
        this.properties.add(bottom);
        this.properties.add(index);
    }

    public void rotate() {
        int tmp = getLeft();
        setLeft(getTop());
        setTop(getRight());
        setRight(getBottom());
        setBottom(tmp);
    }

    public void add (int x) {
        properties.add(x);
    }

    public void print() {
        System.out.println(properties.get(0) + " " + properties.get(1) + " " + properties.get(2) + " " + properties.get(3) + " " + properties.get(4));
    }
    public int getLeft() {
        return properties.get(0);
    }

    public void setLeft(int left) {
        properties.set(0, left);
    }

    public int getTop() {
        return properties.get(1);
    }

    public void setTop(int top) {
        properties.set(1, top);
    }

    public int getRight() {
        return properties.get(2);
    }

    public void setRight(int right) {
        properties.set(2, right);
    }

    public int getBottom() {
        return properties.get(3);
    }

    public void setBottom(int bottom) {
        properties.set(3, bottom);
    }

    public int getIndex() {
        return properties.get(4);
    }
}
