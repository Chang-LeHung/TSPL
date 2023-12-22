package org.example.ir.binaryop;

import org.example.ir.Node;

public class LessThan extends AbstractBinaryOp{
    public LessThan(Node left, Node right) {
        super(left, right);
    }

    @Override
    public void genCode() {
        left.genCode();
        right.genCode();
        System.out.println("<");
    }
}
