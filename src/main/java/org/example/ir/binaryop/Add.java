package org.example.ir.binaryop;

import org.example.ir.Node;

public class Add extends AbstractBinaryOp{
    public Add(Node left, Node right) {
        super(left, right);
    }

    @Override
    public void genCode() {
        left.genCode();
        right.genCode();
        System.out.println("+");
//        System.out.println("Add -> " + left);
//        System.out.println("Add -> " + right);

    }

    @Override
    public String toString() {
        return "Add";
    }
}
