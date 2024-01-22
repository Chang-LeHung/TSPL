package org.example.vm.interpreter;

import org.example.bytecode.Instruction;
import org.example.ir.DefaultASTContext;
import org.example.vm.object.SPLFloatObject;
import org.example.vm.object.SPLObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultEval {
    private SPLObject[] evalStack;
    private int idx;
    private int top;
    private DefaultASTContext context;
    private List<Instruction> instructions;
    private Object[] constants;
    private Map<SPLObject, SPLObject> local;


    public DefaultEval(DefaultASTContext context) {
        this.context = context;
        evalStack = new SPLObject[instructions.size()];
        idx = 0;
        top = 0;
        local = new HashMap<>();
        instructions = context.getInstructions();
        Map<Object, Integer> obj = context.getConstants();
        for (Map.Entry<Object, Integer> entry : obj.entrySet()) {
            constants[entry.getValue()] = entry.getKey();
        }
    }

    public SPLObject evalFrame() {
        while (idx < instructions.size()) {
            switch (instructions.get(idx).getOpcode()) {
                case ADD -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.add(rhs);
                }
                case SUB -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.sub(rhs);
                }
                case MUL -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.mul(rhs);
                }
                case DIV -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.div(rhs);
                }
                case MOD -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.mod(rhs);
                }
                case EQ -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.eq(rhs);
                }
                case AND -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.and(rhs);
                }
                case CONDITIONAL_AND -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.conditionalAnd(rhs);
                }
                case CONDITIONAL_OR -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.conditionalOr(rhs);
                }
                case GT -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.gt(rhs);
                }
                case LE -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.le(rhs);
                }
                case LT -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.lt(rhs);
                }
                case GE -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.ge(rhs);
                }
                case NE -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.ne(rhs);
                }
                case NOT -> {
                    idx++;
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.not();
                }
                case LSHIFT -> {
                    idx ++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.lShift(rhs);
                }
                case RSHIFT -> {
                    idx ++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.rShift(rhs);
                }
                case LOAD_Var -> {
                    Instruction ins = instructions.get(idx);
                    idx++;



                }
            }
        }
    }
}
