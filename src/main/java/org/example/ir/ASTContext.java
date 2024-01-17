package org.example.ir;

import org.example.bytecode.Instruction;

import java.util.List;

public interface ASTContext {


  void addInstruction(Instruction instruction);

  List<Instruction> getInstructions();
  Integer addConstant(Object constant);

  int getBreakAddress();
  int getContinueAddress();

  void setBreakAddress(int breakAddress);
  void setContinueAddress(int continueAddress);
}
