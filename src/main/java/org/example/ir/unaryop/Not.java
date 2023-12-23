package org.example.ir.unaryop;

import org.example.bytecode.Instruction;
import org.example.bytecode.OpCode;
import org.example.ir.ASTContext;
import org.example.ir.Node;
import org.example.ir.binaryop.AbstractBinaryOp;

public class Not extends AbstractUnaryOp {
    public Not(Node node) {
        super(node);
    }

    @Override
    public void genCode(ASTContext context) {
        node.genCode(context);
        context.addInstruction(new Instruction(OpCode.NOT));
    }

}
