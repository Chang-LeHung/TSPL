package org.example.ir;

import org.example.bytecode.Instruction;
import org.example.bytecode.OpCode;

public class Break extends Node{
    private int absoluteAddr;

    public void setAbsoluteAddr(int absoluteAddr) {
        this.absoluteAddr = absoluteAddr;
    }

    public Break() {
        this.absoluteAddr = -1;
    }

    @Override
    public void genCode(ASTContext context) {
        context.addInstruction(new Instruction(OpCode.JUMP_ABSOLUTE, absoluteAddr));
    }
}
