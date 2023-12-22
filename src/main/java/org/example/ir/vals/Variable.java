package org.example.ir.vals;

import org.example.ir.Node;

public class Variable extends Node {
    private String name;
    public Variable(String name) {
        this.name = name;
    }

    public void genCode() {
        System.out.println("LOAD_VAL " + name);

    }

    @Override
    public String toString() {
        return name;
    }
}
