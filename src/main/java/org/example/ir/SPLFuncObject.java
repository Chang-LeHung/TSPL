package org.example.ir;

import org.example.bytecode.Instruction;
import org.example.vm.interpreter.DefaultEval;
import org.example.vm.object.SPLObject;
import org.example.vm.object.SPLStringObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SPLFuncObject extends SPLObject {
    private final List<String> parameters;
    private final String funName;
    private final List<Instruction> instructions;
    private final DefaultASTContext context;
    private final Node block;
    private final Map<Object, Integer> constants;

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

    @Override
    public SPLObject call(SPLObject... rhs) {
        if (rhs.length == parameters.size()) {
            var locals = new HashMap<SPLObject, SPLObject>();
            for (int i = 0; i < parameters.size(); i++) {
                locals.put(new SPLStringObject(parameters.get(i)), rhs[i]);
            }
            return new DefaultEval(context, locals).evalFrame();
        }
        throw new RuntimeException("vm crashes");
    }

    public String getFunName() {
        return funName;
    }
}
