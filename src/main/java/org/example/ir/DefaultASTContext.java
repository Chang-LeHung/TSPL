package org.example.ir;

import org.example.bytecode.Instruction;

import java.util.ArrayList;
import java.util.List;

public class DefaultASTContext implements ASTContext {
  private final List<Instruction> instructions;

  public DefaultASTContext() {
    instructions = new ArrayList<>();
  }

  @Override
  public void addInstruction(Instruction instruction) {
    instructions.add(instruction);
  }

  @Override
  public List<Instruction> getInstructions() {
    return instructions;
  }
}
