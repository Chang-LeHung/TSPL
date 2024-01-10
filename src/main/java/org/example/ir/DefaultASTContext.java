package org.example.ir;

import org.example.bytecode.Instruction;

import java.io.ObjectStreamException;
import java.util.*;

public class DefaultASTContext implements ASTContext {
  private final List<Instruction> instructions;
  private Map<Object, Integer> constants;

  public DefaultASTContext() {

    instructions = new ArrayList<>();
    constants = new HashMap<>();
  }

  @Override
  public void addInstruction(Instruction instruction) {
    instructions.add(instruction);
  }

  @Override
  public List<Instruction> getInstructions() {
    return instructions;
  }

  public void printInstructions() {
    Object[] obj = new Object[constants.size()];
    for (Map.Entry<Object, Integer> entry : constants.entrySet()) {
      obj[entry.getValue()] = entry.getKey();
    }
    for (Instruction instruction : instructions) {
      System.out.println(instruction.getOpcode() + " " + obj[instruction.getArg()]);
    }
  }



  public Integer getConstant(Object constant) {
    return constants.get(constant);
  }
  public Integer addConstant(Object constant) {
    int value;
    if (!constants.containsKey(constant)) {
      value = constants.size();
      constants.put(constant, value);
    } else {
      value = constants.get(constant);
    }
    return value;
    }



}

