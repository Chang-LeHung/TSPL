package org.example.parser;

import org.example.ir.DefaultASTContext;
import org.example.ir.Node;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class SPLParseTest {
    @Test
    public void test01() throws IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("arithmetic/test01");
        SPLParser splParser = new SPLParser(resource.getPath());
        Node node = splParser.buildAST();
        DefaultASTContext context = splParser.getContext();
//        System.out.println(node.toString());
        node.genCode(context);
        System.out.println(context.getInstructions());
        context.printInstructions();
    }

}
