package org.example.bytecode;

public class Instruction {

  public final OpCode opcode;
  public int arg;

  public Instruction(OpCode opcode, int arg) {
    this.opcode = opcode;
    this.arg = arg;
  }

  public Instruction(OpCode opcode) {
    this.opcode = opcode;
    this.arg = 0;
  }

  public OpCode getOpcode() {
    return opcode;
  }

  public int getArg() {
    return arg;
  }

  public void updateArg(int arg) {
    this.arg = arg;
  }

  @Override
  public String toString() {
    return "Instruction{" +
        "opcode=" + opcode +
        ", arg=" + arg +
        '}';
  }
}
