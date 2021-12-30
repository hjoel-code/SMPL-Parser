/* Specification for ECOLI tokens */

// user customisations
package smpl.lang;

import smpl.lang.sym;
import java_cup.runtime.*;

%%

%cup
%public

%class SMPLLexer

%type java_cup.runtime.Symbol

%eofval{
  return new Symbol(sym.EOF);
%eofval}

%state STRING
%state BINARY
%state HEX
%state UNICODE
%state CHAR

%char
%line

%{
    public int getChar()
    {
  return yychar + 1;
    }

    public int getLine()
    {
  return yyline + 1;
    }

    public String getText()
    {
  return yytext();
    }
%}
      
nl = [\n\r]

cc = [\b\f]|{nl}

ws = ({cc}|[\t" "])

num = [0-9]
alpha = [A-Za-z_]
alphanum = ({alpha}|{num})
binary = [0*1*]*[1*0*]*
hex = [0-9a-fA-F]

%%
<YYINITIAL> "."                       { //. on a line by itself is EOF
                                        return new Symbol(sym.EOF);}

<YYINITIAL>    {nl}                   {
                                        //skip newline, but reset char counter
                                        yychar = 0;
                                      }
<YYINITIAL>    \//.*{nl}                  { // ignore line comments
                                      }

<YYINITIAL>    \/*.* \*\/              { // ignore line comments
                                      }


<YYINITIAL>    {ws}                   { // ignore whitespace 
                                      }

<YYINITIAL>    ,                      { return new Symbol(sym.COMMA);}
<YYINITIAL>    ;                      { return new Symbol(sym.SEMI);}
<YYINITIAL>    :                      { return new Symbol(sym.COLON);}
<YYINITIAL>    "("                    { return new Symbol(sym.LPAREN);}
<YYINITIAL>    ")"                    { return new Symbol(sym.RPAREN);}
<YYINITIAL>    "["                    { return new Symbol(sym.LBRACKET);}
<YYINITIAL>    "]"                    { return new Symbol(sym.RBRACKET);}
<YYINITIAL>    ":="                    { return new Symbol(sym.ASSIGN); }

<YYINITIAL>    "+"                    { return new Symbol(sym.PLUS); }
<YYINITIAL>    "-"                    { return new Symbol(sym.MINUS); }
<YYINITIAL>    "*"                    { return new Symbol(sym.TIMES); }
<YYINITIAL>    "/"                    { return new Symbol(sym.DIV); }
<YYINITIAL>    "%"                    { return new Symbol(sym.MOD); }

<YYINITIAL>   "[:"                    { return new Symbol( sym.LBCOLON ); }
<YYINITIAL>   ":]"                    { return new Symbol( sym.RBCOLON ); }
<YYINITIAL>   "?"                     { return new Symbol( sym.QUES ); }


// Keywords
<YYINITIAL>   "pair"                  { return new Symbol( sym.PAIR ); }
<YYINITIAL>   "cdr"                   { return new Symbol( sym.CDR ); }
<YYINITIAL>   "car"                   { return new Symbol( sym.CAR ); }
<YYINITIAL>   "list"                  { return new Symbol( sym.LIST ); }
<YYINITIAL>   "size"                  { return new Symbol( sym.SIZE ); }
<YYINITIAL>   "print"                 { return new Symbol( sym.PRINT ); }

<YYINITIAL>   "<="|">="|"!="|"="|">"|"<" { return new Symbol(sym.RATIONAL, yytext()); }
<YYINITIAL>   "and"|"or"|"not"        { return new Symbol(sym.LOGIC, yytext()); }

<YYINITIAL>   {alpha}+{alphanum}*     { return new Symbol(sym.VAR, yytext()); }
<YYINITIAL>   {num}+                  { return new Symbol(sym.INTEGER, Integer.valueOf(yytext())); }
<YYINITIAL>   {num}+"."{num}+         { return new Symbol( sym.REAL, Double.valueOf(yytext()) ); }
<YYINITIAL>   "#t" | "#f"             { return new Symbol( sym.BOOL, yytext()); }

<YYINITIAL>   {alpha}+{alphanum}*     { return new Symbol(sym.VAR, yytext()); }

<YYINITIAL>   "#b"                    { yybegin(BINARY); }
<BINARY>      {binary}{1,32}          { 
                                        yybegin(YYINITIAL);
                                        return new Symbol(sym.BINARY, Integer.parseInt(yytext(), 2)); 
                                      }  

<YYINITIAL>   "#x"                    { yybegin(HEX); }
<HEX>         {hex}+                  { 
                                        yybegin(YYINITIAL);
                                        return new Symbol(sym.HEX, Integer.parseInt(yytext(), 16)); 
                                      } 

          

<YYINITIAL>   {alpha}+{alphanum}*     { return new Symbol(sym.VAR, yytext()); }


<YYINITIAL>    "#c"                   { yybegin(CHAR); }
<CHAR>         [^#]                   { 
                                        yybegin(YYINITIAL);
                                        return new Symbol(sym.CHAR, yytext()); 
                                      }

<YYINITIAL>    "#u"                   { yybegin(UNICODE); }
<UNICODE>      {hex}{4}               { 
                                        yybegin(YYINITIAL);
                                        return new Symbol(sym.UNICODE, yytext()); 
                                      }



<YYINITIAL>    \"                     { yybegin(STRING); }
<YYINITIAL>     .                     { throw new java.io.IOException("Unrecognised character: " + yytext()); }
<STRING>       [^\"]*                 { return new Symbol(sym.STRING, yytext()); }
<STRING>       \"                     { yybegin(YYINITIAL); }


