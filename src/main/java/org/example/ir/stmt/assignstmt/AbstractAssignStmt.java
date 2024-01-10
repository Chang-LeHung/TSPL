package org.example.ir.stmt.assignstmt;

import org.example.bytecode.OpCode;
import org.example.ir.Node;

public abstract class AbstractAssignStmt extends Node{
    public Node l;
    public Node r;

    public AbstractAssignStmt(Node l, Node r) {
        this.l = l;
        this.r = r;
    }
}
