package org.example.parser;

import org.example.ir.DefaultASTContext;
import org.example.ir.Node;
import org.example.vm.interpreter.DefaultEval;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class SPLParseTest {
    @Test
    public void test01() throws IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("arithmetic/defTest");
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
    public void testVM() throws IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("arithmetic/testBreak");
        SPLParser splParser = new SPLParser(resource.getPath());
        Node node = splParser.buildAST();
        DefaultASTContext context = splParser.getContext();
        node.genCode(context);
        DefaultEval defaultEval = new DefaultEval(context);
        defaultEval.evalFrame();
    }

    @Test
    public void testFuncDef() throws IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("arithmetic/defTest");
        SPLParser splParser = new SPLParser(resource.getPath());
        Node node = splParser.buildAST();
        DefaultASTContext context = splParser.getContext();
        node.genCode(context);
        context.printInstructions();
        DefaultEval defaultEval = new DefaultEval(context);
//        defaultEval.evalFrame();
    }

    @Test
    public void testPrint() throws IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("arithmetic/add.spl");
        SPLParser splParser = new SPLParser(resource.getPath());
        Node node = splParser.buildAST();
        DefaultASTContext context = splParser.getContext();
        node.genCode(context);
        context.printInstructions();
        DefaultEval defaultEval = new DefaultEval(context);
        defaultEval.evalFrame();
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
