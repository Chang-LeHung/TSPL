package org.example.ir.vals;

import org.example.bytecode.Instruction;
import org.example.bytecode.OpCode;
import org.example.ir.ASTContext;
import org.example.ir.Node;

public class Int extends Node {
    private int value;
    private Integer oparg;
    public Int(int value, Integer oparg) {
        this.value = value;
        this.oparg = oparg;
    }

    @Override
    public void genCode(ASTContext context) {
        context.addInstruction(new Instruction(OpCode.LOAD_CONST, oparg));
    }



    @Override
    public String toString() {
        return value + "";
    }
}
