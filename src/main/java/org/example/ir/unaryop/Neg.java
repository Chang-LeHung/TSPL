package org.example.ir.unaryop;

import org.example.ir.Node;

public class Neg extends AbstractUnaryOp{
    public Neg(Node node) {
        super(node);
    }

    @Override
    public void genCode() {
        node.genCode();
        System.out.println("-");
    }
}
