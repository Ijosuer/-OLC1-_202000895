/**
 * Ejemplo mi primer proyecto con Jison utilizando Nodejs en Ubuntu
 */

/* Definición Léxica */
%lex

%options case-insensitive

%%

//Expresiones Regulares de comentarios
(\/\/(.)*) {console.log('comentario una linea')}
\/\*[\s\S]*?\*\/ {console.log('comentario multiLinea')}

"Evaluar"			return 'res_evaluar';
"print"			return 'res_print';

//----------Tipos de datos----------//
"int" 			return 'int';
"double" 		return 'double';
"boolean" 	return 'boolean';
"char" 			return 'char';
"string" 		return 'string';
"false" 		return 'false';
"true" 			return 'true';

"new"				return 'nnew';

// SENTENCIA IF
"if"				return 'if';
"else"			return 'else';
"elif"			return 'elif';

// SENTENCIA SWITCH
"switch"		return 'switch';
"default"		return 'defolt';
"case"			return 'case';

// -----CICLOS -----//
"while"			return 'while';
"for"				return 'for';
"do"				return 'do';
"until"			return 'until';
"break"			return 'break';
"continue"			return 'continue';

// -----FUNCIONES -----//
"return"		return 'return';
"void"			return 'void';

//----------Signos encontrados en el lenguaje----------//
";"					return ';';
":"					return ':';
","					return ',';
"("					return '(';
")"					return ')';
"["					return '[';
"]"					return ']';
"{"					return '{';
"}"					return '}';

"++"				return '++';
"--"				return '--';
"+"					return '+';
"-"					return '-';
"*"					return '*';
"/"					return '/';
"^"					return '^';
"%"					return '%';
"?"					return '?';
"=="				return '==';
"="					return '=';
">="				return '>=';
">"					return '>';
"<="				return '<=';
"<"					return '<';
"!="				return '!=';
"!"					return '!';
"||"				return '||';
"&&"				return '&&';
//Expresiones para tipos de datos
[0-9]+("."[0-9]+)?\b  	return 'DECIMAL';
[0-9]+\b								return 'ENTERO';
\"[^"]+\" 							return 'CADENA';

//Expresion para un ID
["_"0-9A-Za-z]*\b					return 'ID';

/* Espacios en blanco */
[ \r\t]+			{}
\n					{}

<<EOF>>				return 'EOF';

.					{ console.error('Este es un error léxico: ' + yytext + ', en la linea: ' + yylloc.first_line + ', en la columna: ' + yylloc.first_column); }
/lex

/* Asociación de operadores y precedencia */
%left '?'
%left '||' 
%left '&&'
%right '!'
%left '<' '>' '<=' '>=' '==' '!='
%left '+' '-' 
%left '*' '/' '%'
%left '^'
%left U-	//Nivel 0 (mas importante)

%start INI

%% /* Definición de la gramática */

INI
	: INSTRUCCIONES EOF 
;

INSTRUCCIONES
	: INSTRUCCION INSTRUCCIONES
	| INSTRUCCION
	| error { console.error('Este es un error sintáctico: ' + yytext + ', en la linea: ' + this._$.first_line + ', en la columna: ' + this._$.first_column); }
;

INSTRUCCION
	: DECLARACION
	| res_print '(' EXPRESION ')' ';'
	| ASIGNACION
	| INCREMENTO ';'
	| IF 
	| SWITCH
	| FOR
	| WHILE
	| DOWHILE
	| DOUNTIL
	| METODO
	| FUNCION
	| CALL
	//| TERNARIO
	 
;

DECLARACION 
	: TIPO LISTID ';' {console.log('Declarando: '+$2);}
	| TIPO LISTID '=' EXPRESION ';' {console.log('Declarando: '+$2+'con valor: '+$4);} 
	| VECT_T1
	| VECT_T2
;

ASIGNACION 
	: ID '=' EXPRESION ';' {console.log('asignando '+$1+' con valor: '+$3);} 
	| MODIF_VEC {console.log('asignando nuevo valor a '+$1);} 
;

TERNARIO
	:	EXPRESION '?' EXPRESION ':' EXPRESION  {console.log($1)}
;

//---------------VECTORES---------------//
//----------TIPO 1 ----------//
VECT_T1
	: TIPO '[' ']' ID '=' 'nnew' TIPO '[' EXPRESION ']' ';' {console.log('vector tipo 1.1');}
	| TIPO '[' ']' '[' ']' ID '=' 'nnew' TIPO '[' EXPRESION ']' '[' EXPRESION ']' ';' {console.log('vector tipo 1.2');}
;

//----------TIPO 2 ----------//
VECT_T2
	: TIPO '[' ']' ID '='  '{' LISTAVALORES '}' ';' {console.log('vector tipo 2.1');}
	| TIPO '[' ']' '[' ']' ID '='  '{' '{' LISTAVALORES '}' ',' '{' LISTAVALORES '}' '}' ';' {console.log('vector tipo 2.2 ');}
;

//---------------ACCESO VECTORES---------------//
ACCESO_VEC
	: ID '[' EXPRESION ']'
	| ID '[' EXPRESION ']' '[' EXPRESION ']' 
;
//---------------MODIFICAR VECTORES---------------//
MODIF_VEC
	: ACCESO_VEC '=' EXPRESION ';'
;

//---------------SENTENCIAS DE CONTROL---------------//
//---------------IF---------------//
IF
	: 'if' '(' EXPRESION ')' '{' INSTRUCCIONES '}' {console.log('IF SIMPLE');}
	| 'if' '(' EXPRESION ')' '{' INSTRUCCIONES '}' ELSEIF {console.log('ELIF ');}
	| 'if' '(' EXPRESION ')' '{' INSTRUCCIONES '}' 'else' '{' INSTRUCCIONES '}' {console.log('IF CON ELSE')}
	| 'if' '(' EXPRESION ')' '{' INSTRUCCIONES '}' ELSEIF 'else' '{' INSTRUCCIONES '}' {console.log('ELIF Y ELSE')}
;

ELSEIF
	: ELSEIF 'elif' '(' EXPRESION ')' '{' INSTRUCCIONES '}'
	| 'elif' '(' EXPRESION ')' '{' INSTRUCCIONES '}'
;

//---------------SWITCH---------------//
SWITCH
	: 'switch' '(' EXPRESION ')' '{' SWITCH_  'defolt' ':' INSTRUCCIONES '}' {console.log('switch con defolt')}
	| 'switch' '(' EXPRESION ')' '{' SWITCH_ '}'														 {console.log('switch sin defolt')}
	| 'switch' '(' EXPRESION ')' '{' 'defolt' ':' INSTRUCCIONES '}' 				 {console.log('switch solo defolt')}
;

SWITCH_
	: SWITCH_ 'case' EXPRESION ':' INSTRUCCIONES 
	| 'case' EXPRESION ':' INSTRUCCIONES
;

//---------------SENTENCIAS CICLICAS---------------//
WHILE
	: 'while' '(' EXPRESION ')' '{' INSTRUCCIONES '}' {console.log('WHILE');}
;

FOR
	: 'for' '(' DECLARACION EXPRESION ';' INCREMENTO ')' '{' INSTRUCCIONES '}'  			{console.log('FOR CON DEC');}
	| 'for' '(' ASIGNACION  EXPRESION ';' INCREMENTO ')' '{' INSTRUCCIONES '}'  			{console.log('FOR CON ASI');}
	| 'for' '(' DECLARACION EXPRESION ';' ID '=' EXPRESION ')' '{' INSTRUCCIONES '}'  {console.log('FOR CON DECx2');}
	| 'for' '(' ASIGNACION  EXPRESION ';' ID '=' EXPRESION ')' '{' INSTRUCCIONES '}'  {console.log('FOR CON ASIx2');}
;

DOWHILE
	: 'do' '{' INSTRUCCIONES '}' 'while' '(' EXPRESION ')' ';'	{console.log('do while (: ')}
;

DOUNTIL
	: 'do' '{' INSTRUCCIONES '}' 'until' '(' EXPRESION ')' ';'	{console.log('do until (: ')}
;

//BREAK*
//CONTINUE*

//---------------FUNCIONES---------------//
FUNCION
	: ID '(' LISTP ')' ':' TIPO '{' INSTRUCCIONES '}' {console.log('Funcion: '+$1+' declarada')}
	| ID '(' ')' ':' TIPO '{' INSTRUCCIONES '}' {console.log('Funcion sin parametros: '+$1+' declarada')}
;
// REUTURN *

METODO
	: ID '(' LISTP ')' ':' 'void' '{' INSTRUCCIONES '}' {console.log('Metodo con void: '+$1+' declarado')}
	| ID '(' LISTP ')' '{' INSTRUCCIONES '}' {console.log('Metodo sin void: '+$1+' declarado')}
	| ID '(' ')' ':' 'void' '{' INSTRUCCIONES '}' {console.log('Metodo con void sin parametros: '+$1+' declarado')}
	| ID '(' ')' '{' INSTRUCCIONES '}' {console.log('Metodo sin void ni apramtros: '+$1+' declarado')}
;

CALL
	:	ID '(' LISTP ')' ';' {console.log('llamando funcion '+$1+' con parametros')}
	|	ID '(' ')' ';' {console.log('llamando funcion '+$1)}
;
//Pendiente que especifiquen this
INCREMENTO
	: ID '++'  {$$=$1;  console.log('aumentando la variable: '+$1);} 
	| ID '--'  {$$=$1;  console.log('decremento la variable: '+$1);} 
;


TIPO
	: int 		{$$=$1;}
	| char 		{$$=$1;}
	| string	{$$=$1;}
	| double 	{$$=$1;}
	| boolean {$$=$1;}
;

LISTID
	: LISTID ',' ID {$$ += ", "+$3+" ";}
	| ID {$$ = $1;}
;

LISTAVALORES
	: LISTAVALORES ',' EXPRESION {$$ += ", "+$3+" ";}
	| EXPRESION {$$ = $1;}
;

LISTP
	: LISTP ',' TIPO ID {$$ += ", "+$3+" ";}
	| TIPO ID {$$ = $1;}
;

EXPRESION
	: '-' EXPRESION %prec U-		{ $$ = $2 *-1; }
	| EXPRESION '+' EXPRESION		{ $$ = $1 + $3; }
	| EXPRESION '-' EXPRESION		{ $$ = $1 - $3; }
	| EXPRESION '*' EXPRESION		{ $$ = $1 * $3; }
	| EXPRESION '/' EXPRESION		{ $$ = $1 / $3; }
	| EXPRESION '^' EXPRESION		{	$$ = $1 ** $3; }
	| EXPRESION '%' EXPRESION		{	$$ = $1 % $3; }
	| EXPRESION '<' EXPRESION			{if($1 < $3){console.log('Es menor')}else{console.log('noes')};}
	| EXPRESION '<=' EXPRESION		{}
	| EXPRESION '>=' EXPRESION		{}
	| EXPRESION '>' EXPRESION			{$$=`${$1}${$3}`;}
	| EXPRESION '==' EXPRESION		{}
	| EXPRESION '!=' EXPRESION		{}
	| EXPRESION '&&' EXPRESION		{}
	| EXPRESION '||' EXPRESION		{}
	| '!' EXPRESION								{}
	| TERNARIO										{$$=$1;}
	| INCREMENTO										{$$=$1;}
	| ACCESO_VEC										{$$=$1;}
	| ENTERO										{ $$ = Number($1); }
	| DECIMAL										{ $$ = Number($1); }
	| CADENA										{ $$ = ($1); }
	| ID												{ $$ = ($1); }
	| true											{ $$ = ($1); }
	| false											{ $$ = ($1); }
	| '(' EXPRESION ')'					{ $$ = $2; }
;
