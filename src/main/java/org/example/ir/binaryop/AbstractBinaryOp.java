package org.example.ir.binaryop;

import org.example.ir.Node;

public abstract class AbstractBinaryOp extends Node {
    protected final Node left;
    protected final Node right;
    public AbstractBinaryOp(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public abstract void genCode();

}
