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
symbols = ["#""+""/""-""?""!""."]
alphanum = ({alpha}|{num}|{symbols})
binary = [0*1*]*[1*0*]*
hex = [0-9a-fA-F]

lineTerminator = \r|\n|\r\n
inputChar = [^\r\n]

lineComment =  "//" {inputChar}* {lineTerminator}?
commentContent =  ([^*] | \*+ [^\*])*
blockComment =  "/*" {commentContent}* + "*/" 
comment =  {lineComment} | {blockComment}

%%
<YYINITIAL> "."                       { //. on a line by itself is EOF
                                        return new Symbol(sym.EOF);}

<YYINITIAL>    {nl}                   {
                                        //skip newline, but reset char counter
                                        yychar = 0;
                                      }


<YYINITIAL>    {comment}              {
                                      }


<YYINITIAL>    {ws}                   { // ignore whitespace 
                                      }

<YYINITIAL>    ,                      { return new Symbol(sym.COMMA);}
<YYINITIAL>    ;                      { return new Symbol(sym.SEMI);}
<YYINITIAL>    :                      { return new Symbol(sym.COLON);}

<YYINITIAL>    "{"                    { return new Symbol(sym.LCURL);}
<YYINITIAL>    "}"                    { return new Symbol(sym.RCURL);}
<YYINITIAL>    "("                    { return new Symbol(sym.LPAREN);}
<YYINITIAL>    ")"                    { return new Symbol(sym.RPAREN);}
<YYINITIAL>    "["                    { return new Symbol(sym.LBRACKET);}
<YYINITIAL>    "]"                    { return new Symbol(sym.RBRACKET);}

<YYINITIAL>   "[:"                    { return new Symbol( sym.LBCOLON ); }
<YYINITIAL>   ":]"                    { return new Symbol( sym.RBCOLON ); }
<YYINITIAL>   "?"                     { return new Symbol( sym.QUES ); }
<YYINITIAL>   "@"                     { return new Symbol( sym.CONCAT ); }


// Keywords
<YYINITIAL>   call              { return new Symbol( sym.CALL ); }
<YYINITIAL>   pair               { return new Symbol( sym.PAIR ); }
<YYINITIAL>   cdr                 { return new Symbol( sym.CDR ); }
<YYINITIAL>   car                 { return new Symbol( sym.CAR ); }
<YYINITIAL>   list               { return new Symbol( sym.LIST ); }
<YYINITIAL>   size               { return new Symbol( sym.SIZE ); }
<YYINITIAL>   print             { return new Symbol( sym.PRINT ); }
<YYINITIAL>   println           { return new Symbol( sym.PRINTLN ); }
<YYINITIAL>   eqv               { return new Symbol( sym.EQV ); }
<YYINITIAL>   equal               { return new Symbol( sym.EQUAL ); }
<YYINITIAL>   substr               { return new Symbol( sym.SUBSTR ); }
<YYINITIAL>   lazy               { return new Symbol( sym.LAZY ); }
<YYINITIAL>   ref               { return new Symbol( sym.REF ); }
<YYINITIAL>   read               { return new Symbol( sym.READ ); }
<YYINITIAL>   readint               { return new Symbol( sym.READINT ); }
<YYINITIAL>   case               { return new Symbol( sym.CASE ); }


<YYINITIAL>    if                  { return new Symbol(sym.IF);}
<YYINITIAL>    then              { return new Symbol(sym.THEN);}
<YYINITIAL>    else              { return new Symbol(sym.ELSE);}
<YYINITIAL>    def                { return new Symbol(sym.DEF);}
<YYINITIAL>    proc              { return new Symbol(sym.PROC);}


<YYINITIAL>    ":="                   { return new Symbol(sym.ASSIGN); }
<YYINITIAL>    "+"                    { return new Symbol(sym.PLUS); }
<YYINITIAL>    "-"                    { return new Symbol(sym.MINUS); }
<YYINITIAL>    "*"                    { return new Symbol(sym.TIMES); }
<YYINITIAL>    "/"                    { return new Symbol(sym.DIV); }
<YYINITIAL>    "%"                    { return new Symbol(sym.MOD); }


<YYINITIAL>   "<="|">="|"!="|"="|">"|"<"    { return new Symbol(sym.RATIONAL, yytext()); }
<YYINITIAL>   and|or                 { return new Symbol(sym.LOGIC, yytext()); }

<YYINITIAL>   not                       { return new Symbol(sym.NOT); }


<YYINITIAL>   ("+"|"-")?{num}+                  { return new Symbol(sym.INTEGER, Integer.valueOf(yytext())); }
<YYINITIAL>   ("+"|"-")?{num}+"."{num}+         { return new Symbol( sym.REAL, Double.valueOf(yytext()) ); }
<YYINITIAL>   "#t" | "#f"             { return new Symbol( sym.BOOL, yytext()); }


<YYINITIAL>   "#e"             	{ return new Symbol( sym.EMPTY, yytext()); }


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

          

<YYINITIAL>   {num}+{alpha}{alphanum}*|{alpha}{alphanum}*     { return new Symbol(sym.VAR, yytext()); }


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
<STRING>       \"                     { yybegin(YYINITIAL); }
<STRING>       [^\"]*                 { return new Symbol(sym.STRING, yytext()); }
