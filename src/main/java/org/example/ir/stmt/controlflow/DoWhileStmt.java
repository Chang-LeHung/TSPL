package org.example.ir.stmt.controlflow;

import org.example.bytecode.Instruction;
import org.example.bytecode.OpCode;
import org.example.ir.ASTContext;
import org.example.ir.DefaultASTContext;
import org.example.ir.Node;

public class DoWhileStmt extends Node {
    private Node condition;
    private Node doWhileBlock;

    public DoWhileStmt(Node condition, Node doWhileBlock) {
        this.condition = condition;
        this.doWhileBlock = doWhileBlock;
    }

    @Override
    public void genCode(ASTContext context) {
        //记录上一个循环中的break和continue地址
        int lastBreak = context.getBreakAddress();
        int lastContinue = context.getContinueAddress();

        int before = context.getInstructions().size();
        DefaultASTContext newContext = new DefaultASTContext();
        doWhileBlock.genCode(newContext);
        context.setContinueAddress(newContext.getInstructions().size() + before);
        condition.genCode(newContext);
        context.setBreakAddress(newContext.getInstructions().size() + before + 1);

        doWhileBlock.genCode(context);
        condition.genCode(context);
        context.addInstruction(new Instruction(OpCode.JUMP_TRUE, before));

        //将上一个循环的break和continue复原
        context.setContinueAddress(lastContinue);
        context.setBreakAddress(lastBreak);
    }
}
