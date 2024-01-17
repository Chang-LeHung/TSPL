package org.example.ir;

import java.util.ArrayList;
import java.util.List;

public class ProgramBlock extends Node{
    private List<Node> blocks;

    public ProgramBlock() {
        this.blocks = new ArrayList<>();
    }

    @Override
    public void genCode(ASTContext context) {
        for (Node block : blocks) {
            block.genCode(context);
        }
    }

    public void addBlock(Node block) {
        blocks.add(block);
    }
}
