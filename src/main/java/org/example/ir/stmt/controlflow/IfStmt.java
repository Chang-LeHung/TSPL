package org.example.ir.stmt.controlflow;

import org.example.bytecode.Instruction;
import org.example.bytecode.OpCode;
import org.example.ir.ASTContext;
import org.example.ir.Node;

public class IfStmt extends Node {
    private Node condition;
    private Node thenBlock;
    private Node elseBlock;

    public IfStmt(Node condition, Node thenBlock, Node elseBlock) {
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
    }

    @Override
    public void genCode(ASTContext context) {
        condition.genCode(context);
        Instruction jump_false = new Instruction(OpCode.JUMP_FALSE, 0);
        context.addInstruction(jump_false);
        thenBlock.genCode(context);
//        int thenAfter = context.getInstructions().size();
//        jump_false.updateArg(thenAfter - 1);
        if (elseBlock!= null) {
            Instruction jump = new Instruction(OpCode.JUMP_UNCON_FORWARD, 0);
            context.addInstruction(jump);
            int thenAfter = context.getInstructions().size();
            jump_false.updateArg(thenAfter);
            elseBlock.genCode(context);
            int elseAfter = context.getInstructions().size();
            jump.updateArg(elseAfter);
        } else {
            int thenAfter = context.getInstructions().size();
            jump_false.updateArg(thenAfter);
        }
        }

}
