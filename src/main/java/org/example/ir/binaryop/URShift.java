package org.example.ir.binaryop;

import org.example.ir.Node;

public class URShift extends AbstractBinaryOp{
    public URShift(Node left, Node right) {
        super(left, right);
    }

    @Override
    public void genCode() {
        left.genCode();
        right.genCode();
        System.out.println(">>>");
    }
}
