package org.example.ir.unaryop;

import org.example.ir.Node;

public class Invert extends AbstractUnaryOp{
    public Invert(Node node) {
        super(node);
    }

    @Override
    public void genCode() {
        node.genCode();
        System.out.println("~");
    }
}
