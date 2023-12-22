package org.example.ir.binaryop;

import org.example.ir.Node;

public class Mul extends AbstractBinaryOp{
    public Mul(Node left, Node right) {
        super(left, right);
    }

    @Override
    public void genCode() {
        left.genCode();
        right.genCode();
        System.out.println('*');
    }

    @Override
    public String toString() {
        return "Mul -> " + left.toString() + '\n' + "Mul -> " + right.toString();
    }
}
