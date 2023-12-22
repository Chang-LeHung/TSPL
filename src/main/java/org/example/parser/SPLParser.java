package org.example.parser;

import org.example.ir.Node;
import org.example.ir.binaryop.*;
import org.example.ir.unaryop.Invert;
import org.example.ir.unaryop.Neg;
import org.example.ir.unaryop.Not;
import org.example.ir.vals.Int;
import org.example.ir.vals.Variable;
import org.example.lexer.Lexer;

import java.io.IOException;

import java.util.List;

public class SPLParser {
    private Node root;
    private final List<Lexer.Token> tokens;
    private final String fileName;
    private int offset;

    public SPLParser(String filename) throws IOException {
        this.fileName = filename;
        Lexer lexer = new Lexer(filename);
        lexer.doParse();
        this.tokens = lexer.getTokens();
        this.offset = 0;
    }

    public Node buildAST() {
        if (root != null) {
            return root;
        }
        root = disjunction();
        return root;
    }

    private Node disjunction(){
        Node L = conjunction();
        while (offset < tokens.size() && tokens.get(offset).isCONDITIONAL_OR()){
            offset++;
            L = new ConditionOr(L, conjunction());
        }
        return L;
    }
    private Node conjunction() {
        Node L = inversion();
        while (offset < tokens.size() && tokens.get(offset).isCONDITIONAL_AND()) {
            offset++;
            L = new ConditionAnd(L, inversion());
        }
        return L;
    }
    private Node inversion() {
        if (offset < tokens.size() && tokens.get(offset).isNOT()){
            offset++;
            return new Not(inversion());
        }
        return comparison();
    }
    private Node comparison() {
        Node L = bitwiseOr();
        if (offset < tokens.size()) {
            Lexer.Token token = tokens.get(offset);
            switch (token.token) {
                case EQ ->{
                    offset++;
                    Node R = bitwiseOr();
                    L = new Equal(L, R);

                }
                case NE -> {
                    offset++;
                    Node R = bitwiseOr();
                    L = new NotEqual(L, R);
                }
                case LT -> {
                    offset++;
                    Node R = bitwiseOr();
                    L = new LessThan(L, R);
                }
                case LE -> {
                    offset++;
                    Node R = bitwiseOr();
                    L = new LessOrEqual(L, R);
                }
                case GT -> {
                    offset++;
                    Node R = bitwiseOr();
                    L = new GreaterThan(L, R);
                }
                case GE -> {
                    offset++;
                    Node R = bitwiseOr();
                    L = new GreaterOrEqual(L, R);
                }
            }
        }
        return L;
    }

    private Node bitwiseOr() {
        Node L = bitwiseXor();
        while (offset < tokens.size() && tokens.get(offset).isOR()) {
            offset++;
            L = new Or(L, bitwiseXor());
        }
        return L;
    }

    private Node bitwiseXor() {
        Node L = bitwiseAnd();
        while (offset < tokens.size() && tokens.get(offset).isXOR()) {
            offset++;
            L = new Xor(L, bitwiseAnd());
        }
        return L;
    }
    private Node bitwiseAnd() {
        Node L = bitwiseShift();
        while (offset < tokens.size() && tokens.get(offset).isAND()) {
            offset++;
            L = new And(L, bitwiseShift());
        }
        return L;
    }
    private Node bitwiseShift() {
        Node L = sum();
        while (offset < tokens.size() && (tokens.get(offset).isRSHIFT() || tokens.get(offset).isLSHIFT() ||
                tokens.get(offset).isU_RSHIFT())) {
            Lexer.Token token = tokens.get(offset);
            switch (token.token) {
                case RSHIFT -> {
                    offset++;
                    Node R = sum();
                    L = new RShift(L, R);
                }
                case LSHIFT -> {
                    offset++;
                    Node R = sum();
                    L = new LShift(L, R);
                }
                case U_RSHIFT -> {
                    offset++;
                    Node R = sum();
                    L = new URShift(L, R);
                }
            }
        }
        return L;
    }
    private Node sum() {
        Node L = term();
        while (offset < tokens.size() && (tokens.get(offset).isPLUS() || tokens.get(offset).isMINUS())) {
            if (tokens.get(offset).isPLUS()) {
                offset++;
                Node R = term();
                L = new Add(L, R);
            } else {
                offset++;
                Node R = term();
                L = new Minus(L, R);
            }
        }
        return L;
    }
    private Node term() {
        Node L = factor();
        while (offset < tokens.size() && (tokens.get(offset).isMUL() || tokens.get(offset).isDIV()
        || tokens.get(offset).isMOD() || tokens.get(offset).isTRUE_DIV())) {
            Lexer.Token token = tokens.get(offset);
            switch (token.token) {
                case MUL -> {
                    offset++;
                    Node R = factor();
                    L = new Mul(L, R);
                }
                case DIV -> {
                    offset++;
                    Node R = factor();
                    L = new Div(L, R);
                }
                case MOD -> {
                    offset++;
                    Node R = factor();
                    L = new Mod(L, R);
                }
                case TRUE_DIV -> {
                    offset++;
                    Node R = factor();
                    L = new TrueDiv(L, R);
                }
            }
        }
        return L;
    }
    private Node factor() {
        Lexer.Token token = tokens.get(offset);
        Node L;
        switch (token.token) {
            case INVERT -> {
                offset++;
                L = new Invert(factor());
            }
            case PLUS -> {
                offset++;
                L = factor();
            }
            case MINUS -> {
                offset++;
                L = new Neg(factor());
            }
            default -> {
                L = power();
            }
        }
        return L;
    }
    private Node power() {
        Node L = primary();
        if (offset < tokens.size() && tokens.get(offset).isPOWER()) {
            offset++;
            L = new Power(L,factor());
        }
        return L;
    }
    private Node primary() {
        return atom();
    }
    private Node atom() {
        Lexer.Token token = tokens.get(offset);
        offset++;
        Node node;
        if (token.isIDENTIFIER()) {
            String name = (String) token.value;
            node = new Variable(name);
        } else {
            int value = (int) token.value;
            node = new Int(value);
        }
        return node;
    }


}
