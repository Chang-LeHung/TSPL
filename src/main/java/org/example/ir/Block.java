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
            if (block instanceof Break brk) {
                brk.setAbsoluteAddr(context.getBreakAddress());
            } else if (block instanceof Continue cont) {
                cont.setAbsoluteAddr(context.getContinueAddress());
            }
            block.genCode(context);
        }

    }
    public void addBlock(Node block) {
        blocks.add(block);
    }
}
