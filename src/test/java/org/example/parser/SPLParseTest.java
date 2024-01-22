package org.example.parser;

import org.example.ir.DefaultASTContext;
import org.example.ir.Node;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class SPLParseTest {
    @Test
    public void test01() throws IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("arithmetic/add.spl");
        SPLParser splParser = new SPLParser(resource.getPath());
        Node node = splParser.buildAST();
        DefaultASTContext context = splParser.getContext();
//        System.out.println(node.toString());
        node.genCode(context);
        System.out.println(context.getInstructions());
        context.printInstructions();
        System.out.println(context.getConstants());
    }

    @Test
    public void test02() {
        int a = 1;
        if (a == 1) {
            a = 2;
        } else if (a == 2) {
            a =3;
        } else {
            a = 4;
        }
    }

}
