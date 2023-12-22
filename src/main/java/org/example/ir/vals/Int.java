package org.example.ir.vals;

import org.example.ir.Node;

public class Int extends Node {
    private int value;
    public Int(int value) {
        this.value = value;
    }

    @Override
    public void genCode() {
        System.out.println("LOAD_CONST" + value);
    }

    @Override
    public String toString() {
        return value + "";
    }
}
