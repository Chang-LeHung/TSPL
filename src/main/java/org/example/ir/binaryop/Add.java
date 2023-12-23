package org.example.ir.binaryop;

import org.example.bytecode.Instruction;
import org.example.bytecode.OpCode;
import org.example.ir.ASTContext;
import org.example.ir.Node;

public class Add extends AbstractBinaryOp{
    public Add(Node left, Node right) {
        super(left, right);
    }

    @Override
    public void genCode(ASTContext context) {
        left.genCode(context);
        right.genCode(context);
        context.addInstruction(new Instruction(OpCode.ADD));
//        System.out.println("Add -> " + left);
//        System.out.println("Add -> " + right);

    }

    @Override
    public String toString() {
        return "Add";
    }
}
