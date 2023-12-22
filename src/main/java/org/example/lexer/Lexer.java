package org.example.lexer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    public class Token{
        public Object value;
        public TOKEN_TYPE token;

        public Token(Object value, TOKEN_TYPE token) {
            this.value = value;
            this.token = token;
            if (token == TOKEN_TYPE.IDENTIFIER) {
                String val = (String) value;
                switch (val) {
                    case "for" -> {
                        this.token = TOKEN_TYPE.FOR;
                    }
                    case "while" -> {
                        this.token = TOKEN_TYPE.WHILE;
                    }
                    case "do" -> {
                        this.token = TOKEN_TYPE.DO;
                    }
                    case "if" -> {
                        this.token = TOKEN_TYPE.IF;
                    }
                    case "else" -> {
                        this.token = TOKEN_TYPE.ELSE;
                    }
                    case "break" -> {
                        this.token = TOKEN_TYPE.BREAK;
                    }
                    case "continue" -> {
                        this.token = TOKEN_TYPE.CONTINUE;
                    }
                    case "import" -> {
                        this.token = TOKEN_TYPE.IMPORT;
                    }
                    case "return" -> {
                        this.token = TOKEN_TYPE.RETURN;
                    }
                    case "class" -> {
                        this.token = TOKEN_TYPE.CLASS;
                    }
                    case "true" -> {
                        this.token = TOKEN_TYPE.TRUE;
                    }
                    case "false" -> {
                        this.token = TOKEN_TYPE.FALSE;
                    }
                    case "in" -> {
                        this.token = TOKEN_TYPE.IN;
                    }
                    case "none" -> {
                        this.token = TOKEN_TYPE.NONE;
                    }
                }
            }
        }

        @Override
        public String toString() {
            return "Token{" +
                    "value:" + value +
                    ", token:" + token +
                    '}';
        }

        public boolean isOR() {return token == TOKEN_TYPE.OR;}
        public boolean isCONDITIONAL_OR() {return token == TOKEN_TYPE.CONDITIONAL_OR;}

        public boolean isAND() {return token == TOKEN_TYPE.AND;}

        public boolean isCONDITIONAL_AND() {return token == TOKEN_TYPE.CONDITIONAL_AND;}
        public boolean isNOT() {return token == TOKEN_TYPE.CONDITIONAL_NOT;}
        public boolean isEQ() {return token == TOKEN_TYPE.EQ;}
        public boolean isNE() {return token == TOKEN_TYPE.NE;}
        public boolean isLT() {return token == TOKEN_TYPE.LT;}
        public boolean isGT() {return token == TOKEN_TYPE.GT;}
        public boolean isLE() {return token == TOKEN_TYPE.LE;}
        public boolean isGE() {return token == TOKEN_TYPE.GE;}
        public boolean isXOR() {return token == TOKEN_TYPE.XOR;}
        public boolean isRSHIFT() {return token == TOKEN_TYPE.RSHIFT;}
        public boolean isLSHIFT() {return token == TOKEN_TYPE.LSHIFT;}
        public boolean isU_RSHIFT() {return token == TOKEN_TYPE.U_RSHIFT;}
        public boolean isU_LSHIFT() {return token == TOKEN_TYPE.U_LSHIFT;}
        public boolean isPLUS() {return token == TOKEN_TYPE.PLUS;}
        public boolean isMINUS() {return token == TOKEN_TYPE.MINUS;}
        public boolean isMUL() {return token == TOKEN_TYPE.MUL;}
        public boolean isDIV() {return token == TOKEN_TYPE.DIV;}
        public boolean isMOD() {return token == TOKEN_TYPE.MOD;}
        public boolean isTRUE_DIV() {return token == TOKEN_TYPE.TRUE_DIV;}
        public boolean isPOWER() {return token == TOKEN_TYPE.POWER;}
        public boolean isIDENTIFIER() {return token == TOKEN_TYPE.IDENTIFIER;}
        public boolean isASSIGN() {return token == TOKEN_TYPE.ASSIGN;}


    }
    public enum TOKEN_TYPE {
        IDENTIFIER, FLOAT, INT, PLUS, ASSIGN_ADD, ASSIGN_SUB, MINUS, ASSIGN_MUL, MUL, ASSIGN_POWER,
        POWER, DIV, ASSIGN_DIV, TRUE_DIV, MOD, ASSIGN_MOD, EQ, ASSIGN, GT, GE, LE, LT, ASSIGN_RSHIFT,
        ASSIGN_LSHIFT, ASSIGN_U_RSHIFT, U_RSHIFT, RSHIFT, ASSIGN_U_LSHIFT, U_LSHIFT, LSHIFT,
        ASSIGN_AND, AND, CONDITIONAL_AND, ASSIGN_OR, OR, CONDITIONAL_OR, NE, CONDITIONAL_NOT,
        LEFT_PARENTHESIS, RIGHT_PARENTHESIS, LEFT_BRACE, RIGHT_BRACE, XOR, ASSIGN_XOR,ASSIGN_INVERT,
        INVERT, DOT, COMMA, SEMICOLON, NEWLINE,
        FOR, WHILE, BREAK, CONTINUE, IF, ELSE, DO, CLASS, TRUE, FALSE, IMPORT, NONE, RETURN,
        IN;

    }

    public enum CHAR_TYPE{
        INIT, NUMBER, IDENTIFIER, PLUS, MINUS, MUL, DIV, MOD, ASSIGN, GT, LT, AND, OR, NOT,
        LPAREN, RPAREN, LBRACE, RBRACE, POWER, INVERT, COMMA, SEMICOLON, DOT, NEWLINE;
    }

    private List<Token> tokens;
    public BufferedReader reader;
    private StringBuilder sb;

    private int length;


    public Lexer(String filePath) throws FileNotFoundException {
        tokens = new ArrayList<>();
        reader = new BufferedReader(new FileReader(filePath));
        sb = new StringBuilder();
        length = 0;
    }

    private void readFile() throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
    }

    private char nextChar() {
        char c;
        if (length < sb.length()) {
            c =  sb.charAt(length);
            length++;
        } else {
            c = 0;
        }
        return c;
    }

    public List<Token> getTokens() {return tokens;}

    public void doParse() throws IOException {
        CHAR_TYPE state = CHAR_TYPE.INIT;
        readFile();
        char c = nextChar();
        StringBuilder builder = new StringBuilder();
        while(c != 0) {
            switch (state) {
                case INIT -> {
                    if (Character.isAlphabetic(c)) {
                        state = CHAR_TYPE.IDENTIFIER;
                    } else if (Character.isDigit(c)) {
                        state = CHAR_TYPE.NUMBER;
                    } else if (c == '+') {
                        state = CHAR_TYPE.PLUS;
                    } else if (c == '-') {
                        state = CHAR_TYPE.MINUS;
                    } else if (c == '*') {
                        state = CHAR_TYPE.MUL;
                    } else if (c == '/') {
                        state = CHAR_TYPE.DIV;
                    } else if (c == '%') {
                        state = CHAR_TYPE.MOD;
                    } else if (c == '=') {
                        state = CHAR_TYPE.ASSIGN;
                    } else if (c == '>') {
                        state = CHAR_TYPE.GT;
                    } else if (c == '<') {
                        state = CHAR_TYPE.LT;
                    } else if (c == '&') {
                        state = CHAR_TYPE.AND;
                    } else if (c == '|') {
                        state = CHAR_TYPE.OR;
                    } else if (c == '!') {
                        state = CHAR_TYPE.NOT;
                    } else if (c == '(') {
                        state= CHAR_TYPE.LPAREN;
                    } else if (c == ')') {
                        state = CHAR_TYPE.RPAREN;
                    } else if (c == '{') {
                        state = CHAR_TYPE.LBRACE;
                    } else if (c == '}') {
                        state = CHAR_TYPE.RBRACE;
                    } else if (c == '^') {
                        state = CHAR_TYPE.POWER;
                    } else if (c == '~') {
                        state = CHAR_TYPE.INVERT;
                    } else if (c == ',') {
                        state = CHAR_TYPE.COMMA;
                    } else if (c == ';') {
                        state = CHAR_TYPE.SEMICOLON;
                    } else if (c == '.') {
                        state = CHAR_TYPE.DOT;
                    } else if (c == '\n') {
                        state = CHAR_TYPE.NEWLINE;
                    } else {
                        if (c == ' ' || c == '\t' || c == '\r') {
                            c = nextChar();
                        }
                    }
                }
                case IDENTIFIER -> {
                    builder.append(c);
                    c = nextChar();
                    while (Character.isAlphabetic(c) || Character.isDigit(c) || c == '_') {
                        builder.append(c);
                        c = nextChar();
                    }
                    Token token = new Token(builder.toString(), TOKEN_TYPE.IDENTIFIER);
                    tokens.add(token);
                    state = CHAR_TYPE.INIT;
                    builder.delete(0, builder.length());
                }
                case NUMBER -> {
                    builder.append(c);
                    c = nextChar();
                    Token token;
                    while (Character.isDigit(c)) {
                        builder.append(c);
                        c = nextChar();
                    }
                    if (c == '.') {
                        builder.append(c);
                        c = nextChar();
                        while (Character.isDigit(c)) {
                            builder.append(c);
                            c = nextChar();
                        }
                        token = new Token(Float.parseFloat(builder.toString()), TOKEN_TYPE.FLOAT);
                    } else {
                        token = new Token(Integer.parseInt(builder.toString()), TOKEN_TYPE.INT);
                    }
                    state = CHAR_TYPE.INIT;
                    tokens.add(token);
                    builder.delete(0, builder.length());
                }
                case PLUS -> {
                    builder.append(c);
                    c = nextChar();
                    Token token;
                    if (c == '=') {
                        builder.append(c);
                        c = nextChar();
                        token = new Token("+=", TOKEN_TYPE.ASSIGN_ADD);
                    } else {
                        token = new Token("+", TOKEN_TYPE.PLUS);
                    }
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case MINUS -> {
                    builder.append(c);
                    c = nextChar();
                    Token token;
                    if (c == '=') {
                        builder.append(c);
                        c = nextChar();
                        token = new Token("-=", TOKEN_TYPE.ASSIGN_SUB);
                    } else {
                        token = new Token("-", TOKEN_TYPE.MINUS);
                    }
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case MUL -> {
                    builder.append(c);
                    c = nextChar();
                    Token token;
                    if (c == '=') {
                        builder.append(c);
                        c = nextChar();
                        token = new Token("*=", TOKEN_TYPE.ASSIGN_MUL);
                    } else if (c == '*'){
                        builder.append(c);
                        c = nextChar();
                        if (c == '=') {
                            builder.append(c);
                            c = nextChar();
                            token = new Token("**=", TOKEN_TYPE.ASSIGN_POWER);
                        } else {
                            token = new Token("**", TOKEN_TYPE.POWER);
                        }
                    } else {
                        token = new Token("*", TOKEN_TYPE.MUL);
                    }
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case DIV -> {
                    builder.append(c);
                    c = nextChar();
                    Token token;
                    if (c == '=') {
                        builder.append(c);
                        c = nextChar();
                        token = new Token("/=", TOKEN_TYPE.ASSIGN_DIV);
                    } else {
                        if (c == '/') {
                            builder.append(c);
                            c = nextChar();
                            token = new Token("//", TOKEN_TYPE.TRUE_DIV);
                        } else {
                            token = new Token("/", TOKEN_TYPE.DIV);
                        }
                    }
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case MOD -> {
                    builder.append(c);
                    c = nextChar();
                    Token token;
                    if (c == '=') {
                        builder.append(c);
                        c = nextChar();
                        token = new Token("%=", TOKEN_TYPE.ASSIGN_MOD);
                    } else {
                        token = new Token("%", TOKEN_TYPE.MOD);
                    }
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case ASSIGN -> {
                    builder.append(c);
                    c = nextChar();
                    Token token;
                    if (c == '=') {
                        builder.append(c);
                        c = nextChar();
                        token = new Token("==", TOKEN_TYPE.ASSIGN);
                    } else {
                        token = new Token("=", TOKEN_TYPE.EQ);
                    }
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case GT -> {
                    builder.append(c);
                    c = nextChar();
                    Token token;
                    if (c == '=') {
                        builder.append(c);
                        c = nextChar();
                        token = new Token(">=", TOKEN_TYPE.GE);
                    } else if (c == '>') {
                        builder.append(c);
                        c = nextChar();
                        if (c == '=') {
                            builder.append(c);
                            c = nextChar();
                            token = new Token(">>=", TOKEN_TYPE.ASSIGN_RSHIFT);
                        } else if (c == '>'){
                            builder.append(c);
                            c = nextChar();
                            if (c == '=') {
                                builder.append(c);
                                c = nextChar();
                                token = new Token(">>>=", TOKEN_TYPE.ASSIGN_U_RSHIFT);
                            } else {
                                token = new Token(">>>", TOKEN_TYPE.U_RSHIFT);
                            }
                        } else {
                            token = new Token(">>", TOKEN_TYPE.RSHIFT);
                        }
                    } else {
                        token = new Token(">", TOKEN_TYPE.GT);
                    }
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case LT -> {
                    builder.append(c);
                    c = nextChar();
                    Token token;
                    if (c == '=') {
                        builder.append(c);
                        c = nextChar();
                        token = new Token("<=", TOKEN_TYPE.LE);
                    } else if (c == '<') {
                        builder.append(c);
                        c = nextChar();
                        if (c == '=') {
                            builder.append(c);
                            c = nextChar();
                            token = new Token("<<=", TOKEN_TYPE.ASSIGN_LSHIFT);
                        } else if (c == '<'){
                            builder.append(c);
                            c = nextChar();
                            if (c == '=') {
                                builder.append(c);
                                c = nextChar();
                                token = new Token("<<<=", TOKEN_TYPE.ASSIGN_U_LSHIFT);
                            } else {
                                token = new Token("<<<", TOKEN_TYPE.U_LSHIFT);
                            }
                        } else {
                            token = new Token("<<", TOKEN_TYPE.LSHIFT);
                        }
                    } else {
                        token = new Token("<", TOKEN_TYPE.LT);
                    }
                    tokens.add(token);
                    builder.delete(0, builder.length() - 1);
                    state = CHAR_TYPE.INIT;
                }
                case AND -> {
                    builder.append(c);
                    c = nextChar();
                    Token token;
                    if (c == '=') {
                        builder.append(c);
                        c = nextChar();
                        token = new Token("&=", TOKEN_TYPE.ASSIGN_AND);
                    } else if (c == '&') {
                        builder.append(c);
                        c = nextChar();
                        token = new Token("&&", TOKEN_TYPE.CONDITIONAL_AND);
                    } else {
                        token = new Token("&", TOKEN_TYPE.AND);
                    }
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case OR -> {
                    builder.append(c);
                    c = nextChar();
                    Token token;
                    if (c == '=') {
                        builder.append(c);
                        c = nextChar();
                        token = new Token("|=", TOKEN_TYPE.ASSIGN_OR);
                    } else if (c == '|') {
                        builder.append(c);
                        c = nextChar();
                        token = new Token("||", TOKEN_TYPE.CONDITIONAL_OR);
                    } else {
                        token = new Token("|", TOKEN_TYPE.OR);
                    }
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case NOT -> {
                    builder.append(c);
                    c = nextChar();
                    Token token;
                    if (c == '=') {
                        builder.append(c);
                        c = nextChar();
                        token = new Token("!=", TOKEN_TYPE.NE);
                    } else {
                        token = new Token("!", TOKEN_TYPE.CONDITIONAL_NOT);
                    }
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case LPAREN -> {
                    builder.append(c);
                    c = nextChar();
                    Token token = new Token("(", TOKEN_TYPE.LEFT_PARENTHESIS);
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case RPAREN -> {
                    builder.append(c);
                    c = nextChar();
                    Token token = new Token(")", TOKEN_TYPE.RIGHT_PARENTHESIS);
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case LBRACE -> {
                    builder.append(c);
                    c = nextChar();
                    Token token = new Token("{", TOKEN_TYPE.LEFT_BRACE);
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case RBRACE -> {
                    builder.append(c);
                    c = nextChar();
                    Token token = new Token("{", TOKEN_TYPE.RIGHT_BRACE);
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case POWER -> {
                    builder.append(c);
                    c = nextChar();
                    Token token;
                    if (c == '=') {
                        builder.append(c);
                        c = nextChar();
                        token = new Token("^=", TOKEN_TYPE.ASSIGN_XOR);
                    } else {
                        token = new Token("^", TOKEN_TYPE.XOR);
                    }
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case INVERT -> {
                    builder.append(c);
                    c = nextChar();
                    Token token;
                    if (c == '=') {
                        builder.append(c);
                        c = nextChar();
                        token = new Token("~=", TOKEN_TYPE.ASSIGN_INVERT);
                    } else {
                        token = new Token("~", TOKEN_TYPE.INVERT);
                    }
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case DOT -> {
                    builder.append(c);
                    c = nextChar();
                    Token token = new Token(".", TOKEN_TYPE.DOT);
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case COMMA -> {
                    builder.append(c);
                    c = nextChar();
                    Token token = new Token(",", TOKEN_TYPE.COMMA);
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case SEMICOLON -> {
                    builder.append(c);
                    c = nextChar();
                    Token token = new Token(";", TOKEN_TYPE.SEMICOLON);
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
                case NEWLINE -> {
                    builder.append(c);
                    c = nextChar();
                    Token token = new Token("\n", TOKEN_TYPE.NEWLINE);
                    tokens.add(token);
                    builder.delete(0, builder.length());
                    state = CHAR_TYPE.INIT;
                }
            }
        }


    }
}
