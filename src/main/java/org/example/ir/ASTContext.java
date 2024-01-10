package org.example.ir;

import org.example.bytecode.Instruction;

import java.util.List;

public interface ASTContext {


  void addInstruction(Instruction instruction);

  List<Instruction> getInstructions();
  Integer addConstant(Object constant);
}
