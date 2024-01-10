package org.example.ir.vals;

import org.example.bytecode.Instruction;
import org.example.bytecode.OpCode;
import org.example.ir.ASTContext;
import org.example.ir.Node;

public class Variable extends Node {
    private String name;
    private Integer oparg;

    @Override
    public void genCode(ASTContext context) {
        context.addInstruction(new Instruction(OpCode.LOAD_Var, oparg));
    }

    public Variable(String name, Integer oparg) {
        this.name = name;
        this.oparg = oparg;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
