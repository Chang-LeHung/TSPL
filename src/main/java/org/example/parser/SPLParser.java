package org.example.parser;

import org.example.bytecode.Instruction;
import org.example.ir.Block;
import org.example.ir.DefaultASTContext;
import org.example.ir.Node;
import org.example.ir.binaryop.*;
import org.example.ir.stmt.assignstmt.*;
import org.example.ir.stmt.controlflow.IfStmt;
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
    private DefaultASTContext context;

    public DefaultASTContext getContext() {
        return context;
    }

    public SPLParser(String filename) throws IOException {
        this.fileName = filename;
        Lexer lexer = new Lexer(filename);
        lexer.doParse();
        this.tokens = lexer.getTokens();
        this.offset = 0;
        this.context = new DefaultASTContext();
    }

    public Node buildAST() {
        if (root != null) {
            return root;
        }
        root = statement();
        return root;
    }

    public Node statement() {
        Lexer.Token token = tokens.get(offset);
        if (token.isIDENTIFIER() && tokens.get(offset + 1).isASSIGN()) {
            return assignment();
        } else if (token.isIF()){
            return ifStatement();
        } else {
            return disjunction();
        }
    }

    public Node ifStatement() {
        Node condition;
        Node thenBlock;
        Node elseBlock;
        if (tokens.get(offset).isIF() && tokens.get(offset + 1).isLEFT_PARENTHESIS()) {
            offset += 2;
            condition = disjunction();
            if (tokens.get(offset).isRIGHT_PARENTHESIS()) {
                offset++;
                iterateToEffectiveToken();
                thenBlock = block();
                if (tokens.get(offset).isELSE() && tokens.get(offset+ 1).isIF()) {
                    offset++;
                    elseBlock = ifStatement();
                } else if (tokens.get(offset).isELSE()) {
                    offset++;
                    elseBlock = block();
                } else {
                    elseBlock = null;
                }
                return new IfStmt(condition, thenBlock, elseBlock);
            }
        }
        return null;
    }

    public Node block() {
        if (tokens.get(offset).isLEFT_BRACE()) {
            offset++;
            iterateToEffectiveToken();
            Block blocks = new Block();
            while (!tokens.get(offset).isRIGHT_BRACE()) {
                Node n = statement();
                blocks.addBlock(n);
                iterateToEffectiveToken();
            }
            offset++;
            return blocks;
        } else {
            return statement();
        }
    }

    public Node assignment() {
        Lexer.Token token = tokens.get(offset);
        offset++;
        Lexer.Token sign = tokens.get(offset);
        offset++;
        switch (sign.token) {
            case ASSIGN -> {
                Node R = disjunction();
                String name = (String) token.value;
                int idx = context.addConstant(name);
                Variable var = new Variable(name, idx);
                AssignStmt assignStmt = new AssignStmt(var, R);
                return assignStmt;
            }
            case ASSIGN_ADD -> {
                Node R = disjunction();
                String name = (String) token.value;
                int idx = context.addConstant(name);
                Variable var = new Variable(name, idx);
                AddAssignStmt addAssignStmt = new AddAssignStmt(var, R);
                return addAssignStmt;
            }
            case ASSIGN_AND -> {
                Node R = disjunction();
                String name = (String) token.value;
                int idx = context.addConstant(name);
                Variable var = new Variable(name, idx);
                AndAssignStmt andAssignStmt = new AndAssignStmt(var, R);
                return andAssignStmt;
            }
            case ASSIGN_DIV -> {
                Node R = disjunction();
                String name = (String) token.value;
                int idx = context.addConstant(name);
                Variable var = new Variable(name, idx);
                DivAssignStmt divAssignStmt = new DivAssignStmt(var, R);
                return divAssignStmt;
            }
            case ASSIGN_LSHIFT -> {
                Node R = disjunction();
                String name = (String) token.value;
                int idx = context.addConstant(name);
                Variable var = new Variable(name, idx);
                LshiftAssignStmt lshiftAssignStmt = new LshiftAssignStmt(var, R);
                return lshiftAssignStmt;
            }
            case ASSIGN_MOD -> {
                Node R = disjunction();
                String name = (String) token.value;
                int idx = context.addConstant(name);
                Variable var = new Variable(name, idx);
                ModAssignStmt modAssignStmt = new ModAssignStmt(var, R);
                return modAssignStmt;
            }
            case ASSIGN_MUL -> {
                Node R = disjunction();
                String name = (String) token.value;
                int idx = context.addConstant(name);
                Variable var = new Variable(name, idx);
                MulAssignStmt mulAssignStmt = new MulAssignStmt(var, R);
                return mulAssignStmt;
            }
            case ASSIGN_OR -> {
                Node R = disjunction();
                String name = (String) token.value;
                int idx = context.addConstant(name);
                Variable var = new Variable(name, idx);
                OrAssignStmt orAssignStmt = new OrAssignStmt(var, R);
                return orAssignStmt;
            }
            case ASSIGN_POWER -> {
                Node R = disjunction();
                String name = (String) token.value;
                int idx = context.addConstant(name);
                Variable var = new Variable(name, idx);
                PowerAssignStmt powerAssignStmt = new PowerAssignStmt(var, R);
                return powerAssignStmt;
            }
            case ASSIGN_RSHIFT -> {
                Node R = disjunction();
                String name = (String) token.value;
                int idx = context.addConstant(name);
                Variable var = new Variable(name, idx);
                RshiftAssignStmt rshiftAssignStmt = new RshiftAssignStmt(var, R);
                return rshiftAssignStmt;
            }
            case ASSIGN_SUB -> {
                Node R = disjunction();
                String name = (String) token.value;
                int idx = context.addConstant(name);
                Variable var = new Variable(name, idx);
                SubAssignStmt subAssignStmt = new SubAssignStmt(var, R);
                return subAssignStmt;
            }
            case ASSIGN_U_LSHIFT -> {
                Node R = disjunction();
                String name = (String) token.value;
                int idx = context.addConstant(name);
                Variable var = new Variable(name, idx);
                ULshiftAssignStmt uLshiftAssignStmt = new ULshiftAssignStmt(var, R);
                return uLshiftAssignStmt;
            }
            case ASSIGN_XOR -> {
                Node R = disjunction();
                String name = (String) token.value;
                int idx = context.addConstant(name);
                Variable var = new Variable(name, idx);
                XorAssignStmt xorAssignStmt = new XorAssignStmt(var, R);
                return xorAssignStmt;
            }
        }
        return null;

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
            int idx = context.addConstant(name);
            node = new Variable(name, idx);
        } else {
            int value = (int) token.value;
            int idx = context.addConstant(value);
            node = new Int(value, idx);
        }
        return node;
    }

    private void iterateToEffectiveToken() {
        Lexer.Token token = tokens.get(offset);
        while (token.isNEWLINE() || token.isSEMICOLON()) {
            offset++;
            token = tokens.get(offset);
        }
        }


}
