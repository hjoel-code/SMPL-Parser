<<<<<<< HEAD
/* Specification for ECOLI tokens */

// user customisations
package smpl.lang;

import smpl.sys.*;
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

lineTerminator = \r|\n|\r\n
inputChar = [^\r\n]

lineComment = "//" {inputChar}* {lineTerminator}?
commentContent = ([^*] | \*+ [^\*])*
blockComment = "/*" {commentContent}* + "*/" 
comment = {lineComment} | {blockComment}

num = [0-9]
alpha = [A-Za-z_]
symbols = ["!""?""~""+""/""^"".""%""<"">""=""-""*"]
alphanum = ({alpha}|{symbols}|{num})

binary = #b[01]+ 
hex = #[0-9a-fA-F]+

%%
<YYINITIAL>	"."	{ //. on a line by itself is EOF
			  return new Symbol(sym.EOF);}

<YYINITIAL>    {nl} {
                        //skip newline, but reset char counter
                        yychar = 0;
                      }
<YYINITIAL>    {comment}  { // ignore comments
                      }


<YYINITIAL>    {ws} { // ignore whitespace
                      }

<YYINITIAL>    "," { return new Symbol(sym.COMMA);}
<YYINITIAL>    ";" { return new Symbol(sym.SEMI);}
<YYINITIAL>    ":" { return new Symbol(sym.COLON);}
<YYINITIAL>    "(" { return new Symbol(sym.LPAREN);}
<YYINITIAL>    ")" { return new Symbol(sym.RPAREN);}
<YYINITIAL>    "[" { return new Symbol(sym.LBRACKET);}
<YYINITIAL>    "]" { return new Symbol(sym.RBRACKET);}
<YYINITIAL>    "{" { return new Symbol(sym.LPAREN);}
<YYINITIAL>    "}" { return new Symbol(sym.RPAREN);}
<YYINITIAL>    "?" { return new Symbol(sym.QUESTION);}

<YYINITIAL>    if|IF { return new Symbol(sym.IF);}
<YYINITIAL>    else|ELSE { return new Symbol(sym.ELSE);}
<YYINITIAL>    def|DEF { return new Symbol(sym.DEF);}
<YYINITIAL>    proc|PROC { return new Symbol(sym.PROC);}

<YYINITIAL>    ":=" { return new Symbol(sym.ASSIGN); }
<YYINITIAL>    "<"|">"|"<="|">="|"="|"!="  { return new Symbol(sym.CMP, yytext()); }

<YYINITIAL>    "+" { return new Symbol(sym.PLUS); }
<YYINITIAL>    "-" { return new Symbol(sym.MINUS); }
<YYINITIAL>    "*" { return new Symbol(sym.TIMES); }
<YYINITIAL>    "/" { return new Symbol(sym.DIV); }
<YYINITIAL>    "%" { return new Symbol(sym.MOD); }
<YYINITIAL>    "^" { return new Symbol(sym.CARET); }
<YYINITIAL>    "&" { return new Symbol(sym.AMPERSAND); }
<YYINITIAL>    "|" { return new Symbol(sym.PIPE); }
<YYINITIAL>    "~" { return new Symbol(sym.TILDE); }

<YYINITIAL>    and|AND { return new Symbol(sym.AND); }
<YYINITIAL>    or|OR { return new Symbol(sym.OR); }
<YYINITIAL>    not|NOT { return new Symbol(sym.NOT); }

<YYINITIAL>    "@" { return new Symbol(sym.CONCAT); }

<YYINITIAL>    "#t" { return new Symbol(sym.TRUE); }
<YYINITIAL>    "#f" { return new Symbol(sym.FALSE); }
<YYINITIAL>    "#e" { return new Symbol(sym.NIL); }
<YYINITIAL>    {binary}|{hex} { return new Symbol(sym.BINARYHEX); }



<YYINITIAL>	{num}+{alpha}{alphanum}*|{alpha}{alphanum}* {
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}

<YYINITIAL>  {num}+ {
	       		// INTEGER
	       		return new Symbol(sym.INTEGER, new Integer(yytext()));
	       	}

<YYINITIAL>     0?"."{num}+ {
			// FRACTION
			return new Symbol(sym.FRACTION, new Double(yytext()));
		}

<YYINITIAL>	{num}+"."{num}+ {
			// REAL no. used for defining frames
			return new Symbol(sym.REAL, new Double(yytext()));
		}

<YYINITIAL>	\" {
			yybegin(STRING);
		}

<YYINITIAL>	. {
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}

<STRING>	\" {
			yybegin(YYINITIAL);
		}

<STRING>	[^\"]* {
			// constant string
			// System.out.println(yytext());
			return new Symbol(sym.STRING, yytext());
		}
=======
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


>>>>>>> SMPL
