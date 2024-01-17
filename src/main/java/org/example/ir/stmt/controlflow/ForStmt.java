package org.example.ir.stmt.controlflow;


import org.example.bytecode.Instruction;
import org.example.bytecode.OpCode;
import org.example.ir.ASTContext;
import org.example.ir.DefaultASTContext;
import org.example.ir.Node;

public class ForStmt extends Node {
    private Node initializer;
    private Node condition;
    private Node increment;
    private Node forBlock;

    public ForStmt(Node initializer, Node condition, Node increment, Node forBlock) {
        this.initializer = initializer;
        this.condition = condition;
        this.increment = increment;
        this.forBlock = forBlock;
    }

    @Override
    public void genCode(ASTContext context) {
        //记录上一个循环中的break和continue地址
        int lastBreak = context.getBreakAddress();
        int lastContinue = context.getContinueAddress();
        initializer.genCode(context);
        int conditionOffset = context.getInstructions().size();
        condition.genCode(context);
        Instruction jump = new Instruction(OpCode.JUMP_FALSE, 0);
        context.addInstruction(jump);

        // 先得到forBlock和increment的字节码的数量，在forBlock的genCode时进行赋值
        int before = context.getInstructions().size();
        DefaultASTContext newContext = new DefaultASTContext();
        forBlock.genCode(newContext);
        context.setContinueAddress(newContext.getInstructions().size() + before);
        increment.genCode(newContext);
        context.setBreakAddress(newContext.getInstructions().size() + before + 1);

        //正式生成字节码
        forBlock.genCode(context);
        increment.genCode(context);
        context.addInstruction(new Instruction(OpCode.JUMP_UNCON_FORWARD, conditionOffset));
        int jumpTo = context.getInstructions().size();
        jump.updateArg(jumpTo);

        //将上一个循环的break和continue复原
        context.setContinueAddress(lastContinue);
        context.setBreakAddress(lastBreak);
    }
}

