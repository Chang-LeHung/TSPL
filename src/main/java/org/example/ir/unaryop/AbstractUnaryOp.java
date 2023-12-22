package org.example.ir.unaryop;

import org.example.ir.Node;

public abstract class AbstractUnaryOp extends Node{
    protected Node node;

    public AbstractUnaryOp(Node node) {
        this.node = node;
    }

}
