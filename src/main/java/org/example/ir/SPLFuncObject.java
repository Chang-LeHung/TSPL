package org.example.ir;

import org.example.bytecode.Instruction;

import java.util.List;
import java.util.Map;

public class SPLFuncObject {
    private List<String> parameters;
    private String funName;
    private List<Instruction> instructions;
    private DefaultASTContext context;
    private Node block;
    private Map<Object, Integer> constants;

    public SPLFuncObject(List<String> parameters, String funName, DefaultASTContext context, Node block) {
        this.parameters = parameters;
        this.funName = funName;
        this.context = context;
        this.block = block;
        this.block.genCode(this.context);
        instructions = this.context.getInstructions();
        constants = this.context.getConstants();
    }

    @Override
    public String toString() {
        return "SPLFuncObject{" +
                "parameters=" + parameters +
                ", funName='" + funName + '\'' +
                ", instructions=" + instructions +
                ", constants=" + constants +
                '}';
    }
}
