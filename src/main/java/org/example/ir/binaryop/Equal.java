package org.example.ir.binaryop;

import org.example.bytecode.Instruction;
import org.example.bytecode.OpCode;
import org.example.ir.ASTContext;
import org.example.ir.Node;

public class Equal extends AbstractBinaryOp{
    public Equal(Node left, Node right) {
        super(left, right);
    }

    @Override
    public void genCode(ASTContext context) {
        left.genCode(context);
        right.genCode(context);
        context.addInstruction(new Instruction(OpCode.EQ));
    }

    @Override
    public String toString() {
        return "Equal{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
