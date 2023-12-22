package org.example.ir.unaryop;

import org.example.ir.Node;
import org.example.ir.binaryop.AbstractBinaryOp;

public class Not extends AbstractUnaryOp {
    public Not(Node node) {
        super(node);
    }

    public void genCode() {
        node.genCode();
        System.out.println("!");
    }

}
