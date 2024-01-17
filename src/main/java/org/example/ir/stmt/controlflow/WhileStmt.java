package org.example.ir.stmt.controlflow;

import org.example.bytecode.Instruction;
import org.example.bytecode.OpCode;
import org.example.ir.ASTContext;
import org.example.ir.DefaultASTContext;
import org.example.ir.Node;

public class WhileStmt extends Node {
    private Node condition;
    private Node whileBlock;

    public WhileStmt(Node condition, Node whileBlock) {
        this.condition = condition;
        this.whileBlock = whileBlock;
    }

    @Override
    public void genCode(ASTContext context) {
        //记录上一个循环中的break和continue地址
        int lastBreak = context.getBreakAddress();
        int lastContinue = context.getContinueAddress();


        int before = context.getInstructions().size();
        condition.genCode(context);
        Instruction jump = new Instruction(OpCode.JUMP_FALSE, 0);
        context.addInstruction(jump);

        DefaultASTContext newContext = new DefaultASTContext();
        whileBlock.genCode(newContext);
        int current = context.getInstructions().size() + newContext.getInstructions().size();
        context.setContinueAddress(current);
        context.setBreakAddress(current + 1);


        whileBlock.genCode(context);
        context.addInstruction(new Instruction(OpCode.JUMP_UNCON_FORWARD, before));
        int jumpTo = context.getInstructions().size();
        jump.updateArg(jumpTo);

        //将上一个循环的break和continue复原
        context.setContinueAddress(lastContinue);
        context.setBreakAddress(lastBreak);
    }
}
