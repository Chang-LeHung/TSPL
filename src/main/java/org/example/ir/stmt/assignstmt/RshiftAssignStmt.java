package org.example.ir.stmt.assignstmt;

import org.example.bytecode.Instruction;
import org.example.bytecode.OpCode;
import org.example.ir.ASTContext;
import org.example.ir.Node;
import org.example.ir.vals.Variable;

public class RshiftAssignStmt extends AbstractAssignStmt{
    public RshiftAssignStmt(Node l, Node r) {
        super(l, r);
    }
    public void genCode(ASTContext context) {
        int idx = context.addConstant(((Variable) l).getName());
//        l.genCode(context);
        r.genCode(context);
        context.addInstruction(new Instruction(OpCode.RSHIFT_ASSIGN, idx));
    }
}
