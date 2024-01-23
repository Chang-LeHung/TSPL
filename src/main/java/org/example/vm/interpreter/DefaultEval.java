package org.example.vm.interpreter;

import org.example.bytecode.Instruction;
import org.example.ir.DefaultASTContext;
import org.example.vm.object.*;


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
    private SPLObject[] constants;
    private Map<SPLObject, SPLObject> local;


    public DefaultEval(DefaultASTContext context) {
        this.context = context;
        instructions = context.getInstructions();
        evalStack = new SPLObject[instructions.size()];
        idx = 0;
        top = 0;
        local = new HashMap<>();
        Map<Object, Integer> obj = context.getConstants();
        constants = new SPLObject[obj.size()];
        for (Map.Entry<Object, Integer> entry : obj.entrySet()) {
            if (entry.getKey() instanceof Integer) {
                constants[entry.getValue()] = SPLLongObject.create((int)entry.getKey());
            } else if (entry.getKey() instanceof Double) {
                constants[entry.getValue()] = new SPLFloatObject((double) entry.getKey());
            } else if (entry.getKey() instanceof String) {
                constants[entry.getValue()] = new SPLStringObject((String) entry.getKey());
            }
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
                case POWER -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.pow(rhs);
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
                case OR -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.or(rhs);
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
                case U_RSHIFT -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.URShift(rhs);
                }
                case XOR -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    SPLObject lhs = evalStack[--top];
                    evalStack[top++] = lhs.xor(rhs);
                }
                case NEG -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    evalStack[top++] = rhs.neg();
                }
                case INVERT -> {
                    idx++;
                    SPLObject rhs = evalStack[--top];
                    evalStack[top++] = rhs.invert();
                }
                case ASSIGN -> {
                    Instruction ins = instructions.get(idx);
                    idx++;
                    int arg = ins.getArg();
                    SPLObject o = evalStack[--top];
                    local.put(constants[arg], o);
                }
                case ADD_ASSIGN -> {
                    Instruction ins = instructions.get(idx);
                    idx++;
                    int arg = ins.getArg();
                    SPLObject rhs = evalStack[--top];
                    SPLObject name = constants[arg];
                    SPLObject lhs = local.get(name);
                    local.put(name, lhs.add(rhs));
                }
                case SUB_ASSIGN -> {
                    Instruction ins = instructions.get(idx);
                    idx++;
                    int arg = ins.getArg();
                    SPLObject rhs = evalStack[--top];
                    SPLObject name = constants[arg];
                    SPLObject lhs = local.get(name);
                    local.put(name, lhs.sub(rhs));
                }
                case MUL_ASSIGN -> {
                    Instruction ins = instructions.get(idx);
                    idx++;
                    int arg = ins.getArg();
                    SPLObject rhs = evalStack[--top];
                    SPLObject name = constants[arg];
                    SPLObject lhs = local.get(name);
                    local.put(name, lhs.mul(rhs));
                }
                case DIV_ASSIGN -> {
                    Instruction ins = instructions.get(idx);
                    idx++;
                    int arg = ins.getArg();
                    SPLObject rhs = evalStack[--top];
                    SPLObject name = constants[arg];
                    SPLObject lhs = local.get(name);
                    local.put(name, lhs.div(rhs));
                }
                case MOD_ASSIGN -> {
                    Instruction ins = instructions.get(idx);
                    idx++;
                    int arg = ins.getArg();
                    SPLObject rhs = evalStack[--top];
                    SPLObject name = constants[arg];
                    SPLObject lhs = local.get(name);
                    local.put(name, lhs.mod(rhs));
                }
                case AND_ASSIGN -> {
                    Instruction ins = instructions.get(idx);
                    idx++;
                    int arg = ins.getArg();
                    SPLObject rhs = evalStack[--top];
                    SPLObject name = constants[arg];
                    SPLObject lhs = local.get(name);
                    local.put(name, lhs.and(rhs));
                }
                case LSHIFT_ASSIGN -> {
                    Instruction ins = instructions.get(idx);
                    idx++;
                    int arg = ins.getArg();
                    SPLObject rhs = evalStack[--top];
                    SPLObject name = constants[arg];
                    SPLObject lhs = local.get(name);
                    local.put(name, lhs.lShift(rhs));
                }
                case RSHIFT_ASSIGN -> {
                    Instruction ins = instructions.get(idx);
                    idx++;
                    int arg = ins.getArg();
                    SPLObject rhs = evalStack[--top];
                    SPLObject name = constants[arg];
                    SPLObject lhs = local.get(name);
                    local.put(name, lhs.rShift(rhs));
                }
                case OR_ASSIGN -> {
                    Instruction ins = instructions.get(idx);
                    idx++;
                    int arg = ins.getArg();
                    SPLObject rhs = evalStack[--top];
                    SPLObject name = constants[arg];
                    SPLObject lhs = local.get(name);
                    local.put(name, lhs.or(rhs));
                }
                case XOR_ASSIGN -> {
                    Instruction ins = instructions.get(idx);
                    idx++;
                    int arg = ins.getArg();
                    SPLObject rhs = evalStack[--top];
                    SPLObject name = constants[arg];
                    SPLObject lhs = local.get(name);
                    local.put(name, lhs.xor(rhs));
                }
                case LOAD_Var -> {
                    Instruction ins = instructions.get(idx);
                    idx++;
                    int arg = ins.getArg();
                    SPLObject s = constants[arg];
                    evalStack[top++] = local.get(s);
                }
                case LOAD_CONST -> {
                    Instruction ins = instructions.get(idx);
                    idx++;
                    int arg = ins.getArg();
                    evalStack[top++] = constants[arg];
                }
                case JUMP_FALSE -> {
                    Instruction ins = instructions.get(idx);
                    int arg = ins.getArg();
                    if (evalStack[--top] == SPLBoolObject.getFalse()) {
                        idx = arg;
                    } else {
                        idx++;
                    }
                }
                case JUMP_TRUE -> {
                    Instruction ins = instructions.get(idx);
                    int arg = ins.getArg();
                    if (evalStack[--top] == SPLBoolObject.getTrue()) {
                        idx = arg;
                    } else {
                        idx++;
                    }
                }
                case JUMP_UNCON_FORWARD, JUMP_ABSOLUTE -> {
                    Instruction ins = instructions.get(idx);
                    int arg = ins.getArg();
                    idx = arg;
                }


            }
        }
        return null;
    }
}
