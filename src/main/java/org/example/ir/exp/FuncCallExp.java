package org.example.ir.exp;

import org.example.bytecode.Instruction;
import org.example.bytecode.OpCode;
import org.example.ir.ASTContext;
import org.example.ir.Node;

import java.util.List;

public class FuncCallExp extends Node {
    private String funcName;
    private List<Node> args;

    public FuncCallExp(String funcName, List<Node> args) {
        this.funcName = funcName;
        this.args = args;
    }

    @Override
    public void genCode(ASTContext context) {
        for (Node arg : args) {
            arg.genCode(context);
        }
        context.addInstruction(new Instruction(OpCode.LOAD_NAME, context.addConstant(funcName)));
        context.addInstruction(new Instruction(OpCode.CALL, args.size()));

    }
}
