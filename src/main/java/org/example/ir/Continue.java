package org.example.ir;

import org.example.bytecode.Instruction;
import org.example.bytecode.OpCode;

public class Continue extends Node{
    private int absoluteAddr;

    public void setAbsoluteAddr(int absoluteAddr) {
        this.absoluteAddr = absoluteAddr;
    }

    public Continue() {
        this.absoluteAddr = -1;
    }

    @Override
    public void genCode(ASTContext context) {
        context.addInstruction(new Instruction(OpCode.JUMP_ABSOLUTE, absoluteAddr));

    }
}
