package org.example.lexer;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class LexerTest {

    public void testAssert(List<Lexer.Token> tokens, int idx, Lexer.TOKEN_TYPE type, Object value) {
        Assert.assertEquals(type, tokens.get(idx).token);
        Assert.assertEquals(value, tokens.get(idx).value);
    }
    @Test
    public void Test01() throws IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("arithmetic/test01");
        Lexer lexer = new Lexer(resource.getPath());
        lexer.doParse();
        List<Lexer.Token> tokens = lexer.getTokens();
        System.out.println(tokens);
        tokens = tokens.stream().filter(x->x.token!= Lexer.TOKEN_TYPE.NEWLINE)
                .collect(Collectors.toList());
        System.out.println(tokens);
        testAssert(tokens, 0, Lexer.TOKEN_TYPE.IDENTIFIER, "a");
        testAssert(tokens, 1, Lexer.TOKEN_TYPE.ASSIGN, "=");
        testAssert(tokens, 2, Lexer.TOKEN_TYPE.INT, 1);
        testAssert(tokens, 3, Lexer.TOKEN_TYPE.PLUS, "+");
        testAssert(tokens, 4, Lexer.TOKEN_TYPE.INT, 2);
        testAssert(tokens, 5, Lexer.TOKEN_TYPE.SEMICOLON, ";");
        testAssert(tokens, 6, Lexer.TOKEN_TYPE.IDENTIFIER, "b");
        testAssert(tokens, 7, Lexer.TOKEN_TYPE.ASSIGN, "=");
        testAssert(tokens, 8, Lexer.TOKEN_TYPE.INT, 3);
        testAssert(tokens, 9, Lexer.TOKEN_TYPE.MINUS, "-");
        testAssert(tokens, 10, Lexer.TOKEN_TYPE.INT, 4);
        testAssert(tokens, 11, Lexer.TOKEN_TYPE.SEMICOLON, ";");
    }

    @Test
    public void testBool() throws IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("arithmetic/bool.spl");
        Lexer lexer = new Lexer(resource.getPath());
        lexer.doParse();
        List<Lexer.Token> tokens = lexer.getTokens();
        System.out.println(tokens);
        tokens = tokens.stream().filter(x->x.token!= Lexer.TOKEN_TYPE.NEWLINE)
                .collect(Collectors.toList());
        System.out.println(tokens);
        testAssert(tokens, 0, Lexer.TOKEN_TYPE.IDENTIFIER, "a");
        testAssert(tokens, 1, Lexer.TOKEN_TYPE.ASSIGN, "=");
        testAssert(tokens, 2, Lexer.TOKEN_TYPE.IDENTIFIER, "True");
        testAssert(tokens, 3, Lexer.TOKEN_TYPE.IDENTIFIER, "b");
        testAssert(tokens, 4, Lexer.TOKEN_TYPE.ASSIGN, "=");
        testAssert(tokens, 5, Lexer.TOKEN_TYPE.IDENTIFIER, "False");
        testAssert(tokens, 6, Lexer.TOKEN_TYPE.IDENTIFIER, "c");
        testAssert(tokens, 7, Lexer.TOKEN_TYPE.ASSIGN, "=");
        testAssert(tokens, 8, Lexer.TOKEN_TYPE.IDENTIFIER, "a");
        testAssert(tokens, 9, Lexer.TOKEN_TYPE.CONDITIONAL_AND, "&&");
        testAssert(tokens, 10, Lexer.TOKEN_TYPE.IDENTIFIER, "b");
        testAssert(tokens, 11, Lexer.TOKEN_TYPE.IDENTIFIER, "a");
        testAssert(tokens, 12, Lexer.TOKEN_TYPE.ASSIGN, "=");
        testAssert(tokens, 13, Lexer.TOKEN_TYPE.TRUE, "true");
        testAssert(tokens, 14, Lexer.TOKEN_TYPE.IDENTIFIER, "b");
        testAssert(tokens, 15, Lexer.TOKEN_TYPE.ASSIGN, "=");
        testAssert(tokens, 16, Lexer.TOKEN_TYPE.FALSE, "false");
        testAssert(tokens, 17, Lexer.TOKEN_TYPE.IDENTIFIER, "c");
        testAssert(tokens, 18, Lexer.TOKEN_TYPE.ASSIGN, "=");
        testAssert(tokens, 19, Lexer.TOKEN_TYPE.IDENTIFIER, "a");
        testAssert(tokens, 20, Lexer.TOKEN_TYPE.CONDITIONAL_AND, "&&");
        testAssert(tokens, 21, Lexer.TOKEN_TYPE.IDENTIFIER, "b");

    }




}
