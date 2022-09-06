package analizadores;
import java_cup.runtime.*;

%%

//directrices

%public 
%class Analizador_Lexico
%cupsym Simbolos
%cup
%char
%column
%full
%ignorecase
%line
%unicode

%init{ 
    yyline = 1; 
    yychar = 1; 
%init} 


CARACTER   =   [\']([^\t\'\"\n]|(\\\")|(\\n)|(\\\')|(\\t))?[\']  
BLANCOS=[ \r\t]+
CADENA = [\"]([^\"]|(\\\"))*[\"]

D=[0-9]+
DD=[0-9]+("."[  |0-9]+)?
ID=["_"A-Za-z]+["_"0-9A-Za-z]*
COMENTUNILINEA      =   ("//".*\r\n)|("//".*\n)|("//".*\r)
COMENTMULTILINEA    =   "/*""/"*([^*/]|[^*]"/"|"*"[^/])*"*"*"*/"

%%

{COMENTUNILINEA} {} 
{COMENTMULTILINEA} {}  
{CADENA} {System.out.println("Reconocio tk:CADENA lexema:"+yytext());return new Symbol(sym.tk_CADENA,yyline,yychar, (yytext()).substring(1,yytext().length()-1));} 


"inicio"               {System.out.println("Reconocio RES:INICIO lexema:"+yytext()); return new Symbol(sym.res_INICIO,yyline,yychar,yytext());} 
"fin"            {System.out.println("Reconocio RES:FIN lexema:"+yytext());return new Symbol(sym.res_FIN,yyline,yychar,yytext());} 


"ingresar"          {System.out.println("Reconocio RES:INGRESAR lexema:"+yytext());return new Symbol(sym.res_INGRESAR,yyline,yychar, yytext());} 
"como"          {System.out.println("Reconocio RES:COMO lexema:"+yytext());return new Symbol(sym.res_COMO,yyline,yychar, yytext());} 
"con_valor"          {System.out.println("Reconocio RES:CON_VAL lexema:"+yytext());return new Symbol(sym.res_CONVALOR,yyline,yychar, yytext());} 
"numero"            {System.out.println("Reconocio RES:NUMERO lexema:"+yytext());return new Symbol(sym.res_NUMERO,yyline,yychar, yytext());} 
"caracter"          {System.out.println("Reconocio RES:CARACTER lexema:"+yytext());return new Symbol(sym.res_CARACTER,yyline,yychar,yytext());} 
"cadena"          {System.out.println("Reconocio RES:CADENA lexema:"+yytext());return new Symbol(sym.res_CADENA,yyline,yychar,yytext());} 
"boolean"          {System.out.println("Reconocio RES:BOOLEAN lexema:"+yytext());return new Symbol(sym.res_BOOLEAN,yyline,yychar,yytext());} 
"verdadero"          {System.out.println("Reconocio RES:BOOLEAN lexema:"+yytext());return new Symbol(sym.res_VERDADERO,yyline,yychar,yytext());} 
"falso"          {System.out.println("Reconocio RES:BOOLEAN lexema:"+yytext());return new Symbol(sym.res_FALSO,yyline,yychar,yytext());} 


"si"                {System.out.println("Reconocio RES:SI") ;return new Symbol(sym.res_SI,yyline,yychar, yytext());} 
"entonces"                {return new Symbol(sym.res_ENTONCES,yyline,yychar, yytext());} 
"o_si"              {return new Symbol(sym.res_ELSEIF,yyline,yychar, yytext());}
"de_lo_contrario"              {return new Symbol(sym.res_ELSE,yyline,yychar, yytext());}
"fin_si"                {return new Symbol(sym.res_FINSI,yyline,yychar, yytext());} 


"para"          {System.out.println("Reconocio RES:PARA");return new Symbol(sym.res_PARA,yyline,yychar, yytext());} 
"hasta"          {return new Symbol(sym.res_HASTA,yyline,yychar, yytext());} 
"con_incremental"          {return new Symbol(sym.res_CONINCREM,yyline,yychar, yytext());} 
"fin_para"          {return new Symbol(sym.res_FINPARA,yyline,yychar, yytext());} 
"mientras"          {System.out.println("Reconocio RES:WHILE"); return new Symbol(sym.res_MIENTRAS,yyline,yychar, yytext());} 
"fin_mientras"          {return new Symbol(sym.res_FINMIENTRAS,yyline,yychar, yytext());} 
"repetir"          {System.out.println("Reconocio RES:REPETIR") ;return new Symbol(sym.res_REPETIR,yyline,yychar, yytext());} 
"hasta_que"          {return new Symbol(sym.res_HASTAQ,yyline,yychar, yytext());} 


"retornar"          {return new Symbol(sym.res_RETORNAR,yyline,yychar, yytext());} 


"metodo"          {System.out.println("Reconocio RES:METODO") ;return new Symbol(sym.res_METODO,yyline,yychar, yytext());} 
"metodo_con_parametros"          {return new Symbol(sym.res_METODOCONP,yyline,yychar, yytext());} 
"fin_metodo"          {return new Symbol(sym.res_FINMETODO,yyline,yychar, yytext());} 


"funcion"          {System.out.println("Reconocio RES:FUNCION"); return new Symbol(sym.res_FUNCION,yyline,yychar, yytext());} 
"fin_funcion"          {return new Symbol(sym.res_FINFUNCION,yyline,yychar, yytext());} 


"ejectuar"          {return new Symbol(sym.res_EJECUTAR,yyline,yychar, yytext());} 


"imprimir"          {System.out.println("Reconocio RES:IMPRIMIR") ;return new Symbol(sym.res_IMPRIMIR,yyline,yychar, yytext());} 
"imprimir_nl"          {return new Symbol(sym.res_IMPRIMIRNL,yyline,yychar, yytext());} 


"segun"          {System.out.println("Reconocio RES:SEGUN") ;return new Symbol(sym.res_SEGUN,yyline,yychar, yytext());} 
"hacer"          {System.out.println("Reconocio RES:DO") ; return new Symbol(sym.res_HACER,yyline,yychar, yytext());} 
"fin_segun"                {return new Symbol(sym.res_FINSEGUN,yyline,yychar, yytext());} 

";" {System.out.println("Reconocio token:<tk_PTCOMA>lexema:"+yytext());return new Symbol(sym.tk_PTCOMA,yyline,yychar, yytext());} 
"," {System.out.println("Reconocio token:<tk_COMA>");return new Symbol(sym.tk_COMA,yyline,yychar, yytext());} 
"{" {System.out.println("Reconocio token:<tk_LLAVIZQ>"+yytext());return new Symbol(sym.tk_LLAVIZQ,yyline,yychar, yytext());} 
"}" {System.out.println("Reconocio token:<tk_LLAVDER> lexema:"+yytext());return new Symbol(sym.tk_LLAVDER,yyline,yychar, yytext());} 
"(" {System.out.println("Reconocio token:<tk_PARIZQ>lexema:"+yytext());return new Symbol(sym.tk_PARIZQ,yyline,yychar, yytext());} 
")" {System.out.println("Reconocio token:<tk_PARDER>lexema:"+yytext());return new Symbol(sym.tk_PARDER,yyline,yychar, yytext());} 
"+" {System.out.println("Reconocio token:<tk_MAS>lexema:"+yytext());return new Symbol(sym.tk_MAS,yyline,yychar, yytext());} 
"-" {System.out.println("Reconocio token:<tk_MENOS>lexema:"+yytext());return new Symbol(sym.tk_MENOS,yyline,yychar, yytext());}  
"*" {System.out.println("Reconocio token:<tk_POR>lexema:"+yytext());return new Symbol(sym.tk_POR,yyline,yychar, yytext());} 
"/" {System.out.println("Reconocio token:<tk_DIVIDIR>lexema:"+yytext());return new Symbol(sym.tk_DIVIDIDO,yyline,yychar, yytext());} 
"&" {return new Symbol(sym.tk_CONCAT,yyline,yychar, yytext());} 
"<" {System.out.println("Reconocio token:<tk_MENQ>lexema:"+yytext());return new Symbol(sym.tk_MENQUE,yyline,yychar, yytext());} 
">" {System.out.println("Reconocio token:<tk_MAYQ>lexema:"+yytext());return new Symbol(sym.tk_MAYQUE,yyline,yychar, yytext());} 
"=" {System.out.println("Reconocio token:<tk_IGUAL>lexema:"+yytext());return new Symbol(sym.tk_IGUAL,yyline,yychar, yytext());} 

"++" {System.out.println("Reconocio token:<tk_MASMAS>lexema:"+yytext());return new Symbol(sym.tk_INCREMENTO,yyline,yychar, yytext());} 
"--" {System.out.println("Reconocio token:<tk_MENOSMENOS>lexema:"+yytext());return new Symbol(sym.tk_DECREMENTO,yyline,yychar, yytext());} 


"->" {System.out.println("Reconocio token:<tk_ASIGFLECHA>lexema:"+yytext());return new Symbol(sym.tk_ASIGNAFLECHA,yyline,yychar, yytext());} 

\n {yychar=1;}

{BLANCOS} {}
{CARACTER}  {System.out.println("Reconocio token:<tk_CARACTER>lexema:"+yytext());return new Symbol(sym.CARACTER,yyline,yychar, yytext());} 
{ID}        {System.out.println("Reconocio token:<tk_ID>lexema:"+yytext());return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());} 
{D}         {System.out.println("Reconocio token:<tk_ENTERO>lexema:"+yytext());return new Symbol(sym.ENTERO,yyline,yychar, yytext());} 
{DD}        {return new Symbol(sym.DECIMAL,yyline,yychar, yytext());}

. {
    System.err.println("Este es un error lexico: "+yytext()+", en la linea: "+yyline+", en la columna: "+yychar);
}





