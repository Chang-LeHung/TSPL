package org.example.ir.stmt.func;

import org.example.bytecode.Instruction;
import org.example.bytecode.OpCode;
import org.example.ir.ASTContext;
import org.example.ir.Node;

import java.util.List;

public class FuncDef extends Node {
    private String funcName;
    private int idxFunc;
    private int idxFuncName;
    private List<Node> defaults;

    public FuncDef(String funcName, int idxFunc, int idxFuncName, List<Node> defaults) {
        this.funcName = funcName;
        this.idxFunc = idxFunc;
        this.idxFuncName = idxFuncName;
        this.defaults = defaults;
    }

    @Override
    public void genCode(ASTContext context) {
        context.addInstruction(new Instruction(OpCode.LOAD_CONST, idxFunc));
        context.addInstruction(new Instruction(OpCode.MAKE_FUNCTION, defaults.size()));
    }
}
