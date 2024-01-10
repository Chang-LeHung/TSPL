package org.example.ir;

import java.util.ArrayList;
import java.util.List;

public class Block extends Node{

    private List<Node> blocks;

    public Block() {
        blocks = new ArrayList<Node>();
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
