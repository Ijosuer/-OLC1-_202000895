%{
        var cadena="";
        var lista_Errores=[];
        const TIPO_ERROR        = require('./controller/Enums/Tipo_Error');
        const ERRORES            = require("./controller/Ambito/S_Error")
%}

/* Definición Léxica */

%lex

%options case-insensitive

%%

//Expresiones Regulares de comentarios
(\/\/(.)*) {console.log('comentario una linea')}
\/\*[\s\S]*?\*\/ {console.log('comentario multiLinea')}

// "Evaluar"			return 'res_evaluar';
"print"			return 'res_print';
"println"			return 'res_println';

//----------Tipos de datos----------//
"int" 			return 'int';
"double" 		return 'double';
"boolean" 	return 'boolean';
"char" 			return 'char';
"string" 		return 'string';
"false" 		return 'false';
"true" 			return 'true';

"new"				return 'nnew';
"pop"				return 'pop';
"push"			return 'push';
"run"				return 'run';

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

// -----casteos -----//
"toupper"			return 'toupper';
"tolower"			return 'tolower';
"round"				return 'round';
"length"			return 'length';
"typeof"			return 'typeof';
"tostring"		return 'tostring';
"tochararray"	return 'tochararray';


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
"."					return '.';
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
["\'"]([^"\'"])?["\'"]  return 'caracter'
//Expresion para un ID
["_"0-9A-Za-z]*\b					return 'ID';

/* Espacios en blanco */
[ \r\t]+			{}
\n					{}

<<EOF>>				return 'EOF';

.					{ console.error('Este es un error léxico: ' + yytext + ', en la linea: ' + yylloc.first_line + ', en la columna: ' + yylloc.first_column); }
/lex
%{
        
	const TIPO_OPERACION	= require('./controller/Enums/TipoOperacion'); 
	const TIPO_VALOR 	= require('./controller/Enums/TipoValor');
	const TIPO_DATO		= require('./controller/Enums/TipoDato');
	const INSTRUCCION	= require('./controller/Instruccion/Instruccion');
%}
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
	: INSTRUCCIONES EOF {var a={'errores':lista_Errores,'arbol':$1}; lista_Errores=[]; return a;}
	 | error ptyComa    
;

INSTRUCCIONES
	: INSTRUCCIONES INSTRUCCION {$1.push($2); $$ = $1;} 
	| INSTRUCCION								{$$=[$1];}
	| error { console.error('Este es un error sintáctico: ' + yytext + ', en la linea: ' + this._$.first_line + ', en la columna: ' + this._$.first_column); }
;

INSTRUCCION
	: DECLARACION		{$$=$1} 
	| IMPRIMIR			{$$=$1}
	| ASIGNACION		{$$=$1}
	| INCREMENTO ';'{$$=$1}
	| IF 						{$$=$1}
	| SWITCH				{$$=$1}
	| FOR						{$$=$1}
	| WHILE					{$$=$1}
	| DOWHILE				{$$=$1}
	| DOUNTIL				{$$=$1}
	| METODO				{$$=$1}
	| FUNCION				{$$=$1}
	| CALL ';'			{$$=$1}
	| BREAK					{$$=$1}
	| CONTINUE			{$$=$1}
	| POP						{$$=$1}
	| PUSH					{$$=$1}
	//| TERNARIO
	 
;

DECLARACION 
	: TIPO LISTID ';' {$$ = INSTRUCCION.nuevaDECLARACION($2, null, $1, this._$.first_line, (this._$.first_column+1));}
	| TIPO LISTID '=' EXPRESION ';' {$$ = INSTRUCCION.nuevaDECLARACION($2, $4, $1, this._$.first_line, (this._$.first_column+1));}
	| VECT_T1
	| VECT_T2
;

IMPRIMIR
	: 'res_print' '(' EXPRESION ')' ';' 	{ $$ = new INSTRUCCION.nuevoPRINT($3, this._$.first_line, (this._$.first_column+1));}
	| 'res_println' '(' CALL ')' ';' 		{console.log('print call: '+$3)}
	| 'res_println' '(' EXPRESION ')' ';' { $$ = new INSTRUCCION.nuevoPRINT($3, this._$.first_line, (this._$.first_column+1));}
;

ASIGNACION 
	: ID '=' EXPRESION ';' {$$ = INSTRUCCION.nuevaASIGNACION($1, $3, this._$.first_line, (this._$.first_column+1));}
	| MODIF_VEC {console.log('asignando nuevo valor a vector '+$1);} 
;

TERNARIO
	:	EXPRESION '?' EXPRESION ':' EXPRESION  {$$ = INSTRUCCION.nuevoOperacionTernario($1,$3,$5,this._$.first_line,this._$.first_column+1);}
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

POP
	: ID '.' 'pop' '(' ')' ';'
;

PUSH
	: ID '.' 'push' '(' EXPRESION ')' ';'
;
//---------------MODIFICAR VECTORES---------------//
MODIF_VEC
	: ACCESO_VEC '=' EXPRESION ';'
;

//---------------SENTENCIAS DE CONTROL---------------//
//---------------IF---------------//
IF
	: 'if' '(' EXPRESION ')' '{' INSTRUCCIONES '}' {$$ = new INSTRUCCION.nuevoIf($3, $6 , this._$.first_line,(this._$.first_column+1));}
	| 'if' '(' EXPRESION ')' '{' INSTRUCCIONES '}' ELSEIF {$$ = new INSTRUCCION.nuevoIf($3, $6 , $8, this._$.first_line,(this._$.first_column+1));}
	| 'if' '(' EXPRESION ')' '{' INSTRUCCIONES '}' 'else' '{' INSTRUCCIONES '}' { $$ = INSTRUCCION.nuevoELSEIF_Def($10, this._$.first_line,(this._$.first_column+1)); }
	| 'if' '(' EXPRESION ')' '{' INSTRUCCIONES '}' ELSEIF 'else' '{' INSTRUCCIONES '}' {console.log('ELIF Y ELSE')}
;

ELSEIF
	: ELSEIF 'elif' '(' EXPRESION ')' '{' INSTRUCCIONES '}' {$1.push($2); $$ = $1;}
	| 'elif' '(' EXPRESION ')' '{' INSTRUCCIONES '}'				{$$ = INSTRUCCION.nuevoListaELSEIF($1);}
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
	: 'while' '(' EXPRESION ')' '{' INSTRUCCIONES '}' {$$ = new INSTRUCCION.nuevoWhile($3, $6 , this._$.first_line,(this._$.first_column+1));}
;

FOR
	: 'for' '(' DECLARACION EXPRESION ';' INCREMENTO ')' '{' INSTRUCCIONES '}'  			{$$ = new INSTRUCCION.nuevoFOR($3,$4, $6,$9 , this._$.first_line,(this._$.first_column+1));}
	| 'for' '(' ASIGNACION  EXPRESION ';' INCREMENTO ')' '{' INSTRUCCIONES '}'  			{$$ = new INSTRUCCION.nuevoFOR($3,$4, $6,$9 , this._$.first_line,(this._$.first_column+1));}
	| 'for' '(' DECLARACION EXPRESION ';' ID '=' EXPRESION ')' '{' INSTRUCCIONES '}'  {$$ = new INSTRUCCION.nuevoFOR($3,$4, INSTRUCCION.nuevaASIGNACION($6, $8, this._$.first_line, (this._$.first_column+1)),$11 , this._$.first_line,(this._$.first_column+1));}
	| 'for' '(' ASIGNACION  EXPRESION ';' ID '=' EXPRESION ')' '{' INSTRUCCIONES '}'  {console.log('FOR CON ASIx2');}
;

DOWHILE
	: 'do' '{' INSTRUCCIONES '}' 'while' '(' EXPRESION ')' ';'	{$$ = new INSTRUCCION.nuevoDOWhile($7, $3 , this._$.first_line,(this._$.first_column+1));}
;

DOUNTIL
	: 'do' '{' INSTRUCCIONES '}' 'until' '(' EXPRESION ')' ';'	{$$ = new INSTRUCCION.nuevoDOWhile($7, $3 , this._$.first_line,(this._$.first_column+1));}
;

//BREAK
BREAK
	: 'break' ';' {$$ = new INSTRUCCION.nuevoBREAK(this._$.first_line, (this._$.first_column+1));}
;

//CONTINUE
CONTINUE
	: 'continue' ';' {$$ = new INSTRUCCION.nuevoCONTINUE(this._$.first_line, (this._$.first_column+1));}
;

//---------------CASTEOS---------------//
CASTEO
	: 'tolower' '(' EXPRESION ')'
	| 'toupper' '(' EXPRESION ')'
	| 'round' '(' EXPRESION ')'
	| 'length' '(' VECT_T1 ')'
	| 'length' '(' VECT_T2 ')'
	| 'length' '(' CADENA ')'
	| 'typeof' '(' EXPRESION ')'
	| 'tostring' '(' EXPRESION ')'
	| 'tochararray' '(' CADENA ')'
;

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

RETORNO
	: 'return' EXPRESION ';' {$$ = new INSTRUCCION.nuevoRETURN($2,this._$.first_line, (this._$.first_column+1));}
	| 'return' ';'{$$ = new INSTRUCCION.nuevoRETURN(null,this._$.first_line, (this._$.first_column+1));}
;

CALL
	:	ID '(' LISTP ')' {console.log('llamando funcion '+$1+' con parametros')}
	|	ID '(' ')' {console.log('llamando funcion '+$1)}
	|	'run' ID '(' LISTP ')' {console.log('llamando funcion '+$1)}
	|	'run' ID '(' ')' {console.log('llamando funcion '+$1)}
;
//Pendiente que especifiquen this
INCREMENTO
	: ID '++'  {$$ = INSTRUCCION.nuevaASIGNACION($1, INSTRUCCION.nuevaOperacionBinaria(INSTRUCCION.nuevoVALOR( $1, TIPO_VALOR.IDENTIFICADOR,this._$.first_line, (this._$.first_column+1)),INSTRUCCION.nuevoVALOR( 1, TIPO_VALOR.ENTERO, this._$.first_line, (this._$.first_column+1)),TIPO_OPERACION.SUMA, this._$.first_line, (this._$.first_column+1)), this._$.first_line, (this._$.first_column+1));} 
	| ID '--'  {$$ = INSTRUCCION.nuevaASIGNACION($1, INSTRUCCION.nuevaOperacionBinaria(INSTRUCCION.nuevoVALOR( $1, TIPO_VALOR.IDENTIFICADOR,this._$.first_line, (this._$.first_column+1)),INSTRUCCION.nuevoVALOR( 1, TIPO_VALOR.ENTERO, this._$.first_line, (this._$.first_column+1)),TIPO_OPERACION.SUMA, this._$.first_line, (this._$.first_column+1)), this._$.first_line, (this._$.first_column+1));}
;


TIPO
	: int 		{$$=$1;}
	| char 		{$$=$1;}
	| string	{$$=$1;}
	| double 	{$$=$1;}
	| boolean {$$=$1;}
;

LISTID
	: LISTID ',' ID {$1.push($3); $$ = $1;} 
	| ID {$$ = [$1];}
;

LISTAVALORES
	: LISTAVALORES ',' EXPRESION {$1.push($3); $$ = $1;} 
	| EXPRESION {$$ = [$1];}
;

LISTP
	: LISTP ',' TIPO ID {$1.push($3); $$ = $1;} 
	| TIPO ID {$$ = [$1];}
;

EXPRESION
	: '-' EXPRESION %prec U-			{$$ = INSTRUCCION.nuevaOperacionBinaria($1,$3,TIPO_OPERACION.NEGACION, this._$.first_line, (this._$.first_column+1));}
	| EXPRESION '+' EXPRESION			{$$ = INSTRUCCION.nuevaOperacionBinaria($1,$3,TIPO_OPERACION.SUMA, this._$.first_line, (this._$.first_column+1));}
	| EXPRESION '-' EXPRESION			{$$ = INSTRUCCION.nuevaOperacionBinaria($1,$3,TIPO_OPERACION.RESTA, this._$.first_line, (this._$.first_column+1));}
	| EXPRESION '*' EXPRESION			{$$ = INSTRUCCION.nuevaOperacionBinaria($1,$3,TIPO_OPERACION.MULTIPLICACION, this._$.first_line, (this._$.first_column+1));}
	| EXPRESION '/' EXPRESION			{$$ = INSTRUCCION.nuevaOperacionBinaria($1,$3,TIPO_OPERACION.DIVISION, this._$.first_line, (this._$.first_column+1));}
	| EXPRESION '^' EXPRESION			{$$ = INSTRUCCION.nuevaOperacionBinaria($1,$3,TIPO_OPERACION.POTENCIA, this._$.first_line, (this._$.first_column+1));}
	| EXPRESION '%' EXPRESION			{$$ = INSTRUCCION.nuevaOperacionBinaria($1,$3,TIPO_OPERACION.MODULO, this._$.first_line, (this._$.first_column+1));}
	| EXPRESION '<' EXPRESION			{$$ = INSTRUCCION.nuevaOperacionBinaria($1,$3, TIPO_OPERACION.MENOR,this._$.first_line,this._$.first_column+1);}
	| EXPRESION '<=' EXPRESION		{$$ = INSTRUCCION.nuevaOperacionBinaria($1,$3, TIPO_OPERACION.MENORIGUAL,this._$.first_line,this._$.first_column+1);}
	| EXPRESION '>=' EXPRESION		{$$ = INSTRUCCION.nuevaOperacionBinaria($1,$3, TIPO_OPERACION.MAYORIGUAL,this._$.first_line,this._$.first_column+1);}
	| EXPRESION '>' EXPRESION			{$$ = INSTRUCCION.nuevaOperacionBinaria($1,$3, TIPO_OPERACION.MAYOR,this._$.first_line,this._$.first_column+1);}
	| EXPRESION '==' EXPRESION		{$$ = INSTRUCCION.nuevaOperacionBinaria($1,$3, TIPO_OPERACION.IGUALIGUAL,this._$.first_line,this._$.first_column+1);}
	| EXPRESION '!=' EXPRESION		{$$ = INSTRUCCION.nuevaOperacionBinaria($1,$3, TIPO_OPERACION.DIFERENTE,this._$.first_line,this._$.first_column+1);}
	| EXPRESION '&&' EXPRESION		{$$ = INSTRUCCION.nuevaOperacionBinaria($1,$3, TIPO_OPERACION.AND,this._$.first_line,this._$.first_column+1);}
	| EXPRESION '||' EXPRESION		{$$ = INSTRUCCION.nuevaOperacionBinaria($1,$3, TIPO_OPERACION.OR,this._$.first_line,this._$.first_column+1);}
	| '!' EXPRESION								{$$ = INSTRUCCION.nuevaOperacionBinaria($1,$3, TIPO_OPERACION.NOT,this._$.first_line,this._$.first_column+1);}
	| TERNARIO										{$$=$1;}
	| INCREMENTO									{$$=$1;}
	| ACCESO_VEC									{$$=$1;}
	| CASTEO											{$$=$1;}
	| ENTERO											{$$ = INSTRUCCION.nuevoVALOR( Number($1), TIPO_VALOR.ENTERO, this._$.first_line, (this._$.first_column+1));}
	| DECIMAL											{$$ = INSTRUCCION.nuevoVALOR( Number($1), TIPO_VALOR.ENTERO, this._$.first_line, (this._$.first_column+1));}
	| CADENA											{$$ = INSTRUCCION.nuevoVALOR($1 , TIPO_VALOR.CADENA, this._$.first_line, (this._$.first_column+1));}
	| caracter										{$$ = INSTRUCCION.nuevoVALOR($1 , TIPO_VALOR.CARACTER, this._$.first_line, (this._$.first_column+1));}
	| ID													{$$ = INSTRUCCION.nuevoVALOR($1 , TIPO_VALOR.IDENTIFICADOR, this._$.first_line, (this._$.first_column+1));}
	| true												{$$ = INSTRUCCION.nuevoVALOR(true, TIPO_VALOR.BANDERA, this._$.first_line, (this._$.first_column+1));}
	| false												{$$ = INSTRUCCION.nuevoVALOR(false, TIPO_VALOR.BANDERA, this._$.first_line, (this._$.first_column+1));}
	| '(' EXPRESION ')'						{ $$ = $2; }
;
