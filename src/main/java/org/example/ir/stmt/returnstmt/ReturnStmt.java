package org.example.ir.stmt.returnstmt;

import org.example.bytecode.Instruction;
import org.example.bytecode.OpCode;
import org.example.ir.ASTContext;
import org.example.ir.Node;

public class ReturnStmt extends Node {
    private Node expression;

    public ReturnStmt(Node expression) {
        this.expression = expression;
    }

    @Override
    public void genCode(ASTContext context) {
        expression.genCode(context);
        context.addInstruction(new Instruction(OpCode.RETURN));


    }
}
