/**
 * Ejemplo mi primer proyecto con Jison utilizando Nodejs en Ubuntu
 */

/* Definición Léxica */
%lex

%options case-insensitive

%%

//Expresiones Regulares de comentarios
(\/\/(.)+\n) {console.log('comentario una linea')}
\/\*[\s\S]*?\*\/ {console.log('comentario multiLinea')}

"Evaluar"			return 'res_evaluar';
"Imprimir"			return 'res_imprimir';
//Tipos de datos
"int" 			return 'int';
"double" 		return 'double';
"boolean" 	return 'boolean';
"char" 			return 'char';
"string" 		return 'string';
"false" 		return 'false';
"true" 			return 'true';

";"					return ';';
","					return ',';
"("					return '(';
")"					return ')';
"["					return '[';
"]"					return ']';

"++"				return '++';
"--"				return '--';
"+"					return '+';
"-"					return '-';
"*"					return '*';
"/"					return '/';
"^"					return '^';
"%"					return '%';
"?"					return '?';
"="					return '=';

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

%left '+' '-' '%'
%left '*' '/' '^'
%left U-

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
	: res_evaluar '[' EXPRESION ']' ';' {
		console.log('El valor de la expresión es: ' + $3);
	}
	| res_imprimir CADENA ';' {
		console.log('console.log('+$2+')');
	}
	| DECLARACION
	| ASIGNACION
	| INCREMENTO
	
;

DECLARACION 
	: TIPO LISTID ';' {console.log('Declarando: '+$2);}
	| TIPO LISTID '=' EXPRESION ';' {console.log('Declarando: '+$2+'con valor: '+$4);} 
;

ASIGNACION 
	: ID '=' EXPRESION ';' {console.log('asignando '+$1+' con valor: '+$3);} 
;

//Pendiente que especifiquen this
INCREMENTO
	: ID '++' ';' {console.log('aumentando la variable: '+$1);} 
	| ID '--' ';' {console.log('decremento la variable: '+$1);} 
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

EXPRESION
	: '-' EXPRESION %prec U-		{ $$ = $2 *-1; }
	| EXPRESION '+' EXPRESION		{ $$ = $1 + $3; }
	| EXPRESION '-' EXPRESION		{ $$ = $1 - $3; }
	| EXPRESION '*' EXPRESION		{ $$ = $1 * $3; }
	| EXPRESION '/' EXPRESION		{ $$ = $1 / $3; }
	| EXPRESION '^' EXPRESION		{	$$ = $1 ** $3; }
	| EXPRESION '%' EXPRESION		{	$$ = $1 % $3; }
	| ENTERO										{ $$ = Number($1); }
	| DECIMAL										{ $$ = Number($1); }
	| CADENA										{ $$ = ($1); }
	| ID												{ $$ = ($1); }
	| true											{ $$ = ($1); }
	| false											{ $$ = ($1); }
	| '(' EXPRESION ')'					{ $$ = $2; }
;