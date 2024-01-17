package org.example.ir.vals;

import org.example.ir.ASTContext;
import org.example.ir.Node;

public class Float extends Node {
    private final float value;
    private final Integer oparg;

    public Float(float value, Integer oparg) {
        this.value = value;
        this.oparg = oparg;
    }

    @Override
    public void genCode(ASTContext context) {

    }
}
