package org.example.ir.binaryop;

import org.example.ir.Node;

public class Minus extends AbstractBinaryOp{
    public Minus(Node left, Node right) {
        super(left, right);
    }

    @Override
    public void genCode() {
        left.genCode();
        right.genCode();
        System.out.println('-');
//        System.out.println("Sub -> " + left);
//        System.out.println("Sub -> " + right);
    }

    @Override
    public String toString() {
        return "Sub";
    }
}
