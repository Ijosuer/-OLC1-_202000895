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
"Imprimir"			return 'res_imprimir';

//----------Tipos de datos----------//
"int" 			return 'int';
"double" 		return 'double';
"boolean" 	return 'boolean';
"char" 			return 'char';
"string" 		return 'string';
"false" 		return 'false';
"true" 			return 'true';

"new"				return 'nnew';

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
	| ASIGNACION
	| INCREMENTO ';'
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
