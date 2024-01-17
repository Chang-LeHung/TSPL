package org.example.ir;

import org.example.bytecode.Instruction;
import org.example.bytecode.OpCode;

import java.io.ObjectStreamException;
import java.util.*;

public class DefaultASTContext implements ASTContext {
  private final List<Instruction> instructions;
  private Map<Object, Integer> constants;

  private int breakAddress;
  private int continueAddress;

  public DefaultASTContext() {

    instructions = new ArrayList<>();
    constants = new HashMap<>();
    breakAddress = -1;
    continueAddress = -1;
  }

  public int getBreakAddress() {
    return breakAddress;
  }

  public void setBreakAddress(int breakAddress) {
    this.breakAddress = breakAddress;
  }

  public int getContinueAddress() {
    return continueAddress;
  }

  public void setContinueAddress(int continueAddress) {
    this.continueAddress = continueAddress;
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
    for (int i = 0; i < instructions.size(); i++) {
      Instruction instruction = instructions.get(i);
      if (instruction.getOpcode() == OpCode.JUMP_FALSE || instruction.getOpcode() == OpCode.JUMP_UNCON_FORWARD || instruction.getOpcode() == OpCode.JUMP_TRUE ||
      instruction.getOpcode() == OpCode.JUMP_ABSOLUTE || instruction.getOpcode() == OpCode.MAKE_FUNCTION) {
        System.out.println(i + " " + instruction.getOpcode() + " " + instruction.getArg());
      } else {
        System.out.println(i + " " + instruction.getOpcode() + " " + obj[instruction.getArg()]);
      }
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

  public Map<Object, Integer> getConstants() {
    return constants;
  }
}

