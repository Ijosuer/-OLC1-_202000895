package arbol;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author josue
 */
public class Instruccion {
//|     -----------mandar-------------
    public translate_Python py = new translate_Python();
    public translate_Go go = new translate_Go();    
//|----------------------------------------|
     ArrayList textos = new ArrayList();
     String texto_py = "";
     String texto_go = "";
//     *******************************
     Imprimir prin = new Imprimir();
     Declarar declarar = new Declarar();
     Asignar asignar = new Asignar();
     If condi = new If();
     Para para = new Para();
     While mientras = new While();
     Hasta repetir = new Hasta();
     Funcion funcion = new Funcion();
     Metodo metodo = new Metodo();
     Ejecutar ejecutar = new Ejecutar();
//     *******************************
     int i = 0;
     Nodo aux;
     Nodo iterator;
     Nodo tmp;
//     -------------------------
     String recursivo_py = "";
     String recursivo_go = "";
//     -------------------------
     
     
    public void instrucciones(Nodo nodo){
        for(Nodo instruccion : nodo.hijos){
            
            if(instruccion.token == "INSTRUCCIONES"){
                for(Nodo i : instruccion.hijos){
                    if (i != null){
                        
//                    IF
                    if(i.token.equals("CONDICION")){
//                        System.out.println("VIENE IF");
                        for (Nodo inside : i.hijos) {
                            if(inside.token.equals("E")){
                                    condi.b = inside;
                                    aux = inside;
//                                System.out.println("res_SI");
                            }else if(inside.token.equals("RECURSIVE_INST")){
                                    recursive_instruction(inside);
                                    condi.anteriores_go = "";
                                    condi.anterior_py = "";
                                    condi.instruccionGo = recursivo_go;
                                    condi.instruccionpy = recursivo_py;
                                    recursivo_go = "";
                                    recursivo_py = "";
                                    condi.b = aux;
                                    textos = condi.condicion();
                                    texto_py = textos.get(0).toString();
                                    texto_go = textos.get(1).toString();
                                    py.mText(texto_py);
                                    go.mText(texto_go);
                            }  
                        }
//                    FUNCION
                    }else if(i.token.equals("FUNCION")){
                        for (Nodo inside : i.hijos) {
                            if(inside.token.equals("ID")){
                                    funcion.b = inside;
                            }else if(inside.token.equals("LISTP")){
                                    funcion.listp = inside;
                            }else if(inside.token.equals("TIPO")){
                                    for (Nodo hijo : inside.hijos) {
                                        if(hijo.token.equals("res_NUM")){
                                            funcion.tipo = " int ";
                                        }else if(hijo.token.equals("res_CADENA")){
                                            funcion.tipo = " string ";
                                        }else if(hijo.token.equals("res_BOOLEAN")){
                                            funcion.tipo = " boolean ";
                                        }else{
                                            funcion.tipo = " char ";
                                        }
                                }
                            }else if(inside.token.equals("RECURSIVE_INST")){
                                    recursive_instruction(inside);
                                    funcion.instruccionGo = recursivo_go;
                                    funcion.instruccionpy = recursivo_py;
                                    recursivo_go = "";
                                    recursivo_py = "";
                            }else if(inside.token.equals("RETURN")){
                                    for (Nodo retorno : inside.hijos) {
                                        if (retorno.token.equals("E")){
                                        funcion.reto = retorno;
                                        }
                                    }
                                    textos = funcion.funcion();
                                    texto_py = textos.get(0).toString();
                                    texto_go = textos.get(1).toString();
                                    py.funcionTxt = texto_py;
                                    go.funcionesTxt = texto_go;
                            }  
                        }
//                    METODO
                    }else if(i.token.equals("METODO")){
                        for (Nodo inside : i.hijos) {
                            if(inside.token.equals("ID")){
                                    metodo.b = inside;
                            }else if(inside.token.equals("LISTP")){
                                    metodo.listp = inside;
                            }else if(inside.token.equals("RECURSIVE_INST")){
                                    recursive_instruction(inside);
                                    metodo.instruccionGo = recursivo_go;
                                    metodo.instruccionpy = recursivo_py;
                                    recursivo_go = "";
                                    recursivo_py = "";
                                    textos = metodo.metodo();
                                    texto_py = textos.get(0).toString();
                                    texto_go = textos.get(1).toString();
                                    py.funcionTxt = texto_py;
                                    go.funcionesTxt = texto_go;
                            }  
                        }
//                        EEJCUTAR
                    }else if(i.token.equals("EJECUTAR")){
                        for (Nodo inside : i.hijos) {
                            if(inside.token.equals("ID")){
                                    ejecutar.b = inside;
                            }else if(inside.token.equals("LISTP")){
                                    ejecutar.listp = inside;
                                    recursive_instruction(inside);
                                    ejecutar.instruccionGo = recursivo_go;
                                    ejecutar.instruccionpy = recursivo_py;
                                    recursivo_go = "";
                                    recursivo_py = "";
                                    textos = ejecutar.ejecutar();
                                    texto_py = textos.get(0).toString();
                                    texto_go = textos.get(1).toString();
                                    py.mText(texto_py); 
                                    go.mText(texto_go);
                            }  
                        }
//                    PARA
                    }else if(i.token.equals("FOR")){
                        for (Nodo inside : i.hijos) {
                            if(inside.token.equals("LISTID")){
                                    para.b = inside;
                            }else if(inside.token.equals("E")){
                                for (Nodo hijo : inside.hijos) {
                                    if (inside.hijos.size() == 1){
                                        para.d = hijo;
                                    }else{
                                        para.f = inside;
                                        break;
                                    }
                                }
                            }else if(inside.token.equals("RECURSIVE_INST")){
                                if (inside.hijos.isEmpty()){
                                    para.instruccionGo = "//null\n";
                                    para.instruccionpy = "#null\n     ";
                                    recursivo_go = "";
                                    recursivo_py = "";
                                    textos = para.para();
                                    texto_py = textos.get(0).toString();
                                    texto_go = textos.get(1).toString();
                                    py.mText(texto_py);
                                    go.mText(texto_go);
                                }else{
                                    recursive_instruction(inside);
                                    para.instruccionGo = recursivo_go;
                                    para.instruccionpy = recursivo_py;
                                    recursivo_go = "";
                                    recursivo_py = "";
                                    textos = para.para();
                                    texto_py = textos.get(0).toString();
                                    texto_go = textos.get(1).toString();
                                    py.mText(texto_py);
                                    go.mText(texto_go);
                                }
                            }  
                        }
                    }else if(i.token.equals("WHILE")){
//                    WHILE
                        for (Nodo inside : i.hijos) {
                            if(inside.token.equals("E")){
                                    mientras.b = inside;
                            }else if(inside.token.equals("RECURSIVE_INST")){
                                if (inside.hijos.isEmpty()){
                                    mientras.instrucciongo = "//null\n";
                                    mientras.instruccionpy = "#null\n     ";
                                    recursivo_go = "";
                                    recursivo_py = "";
                                    textos = mientras.mientras();
                                    texto_py = textos.get(0).toString();
                                    texto_go = textos.get(1).toString();
                                    py.mText(texto_py);
                                    go.mText(texto_go);
                                }else{
                                    recursive_instruction(inside);
                                    mientras.instrucciongo = recursivo_go;
                                    mientras.instruccionpy = recursivo_py;
                                    recursivo_go = "";
                                    recursivo_py = "";
                                    textos = mientras.mientras();
                                    texto_py = textos.get(0).toString();
                                    texto_go = textos.get(1).toString();
                                    py.mText(texto_py);
                                    go.mText(texto_go);
                                }
                            }  
                        }
                    }else if(i.token.equals("REPETIR")){
//                    DO WHILE
                        for (Nodo inside : i.hijos) {
                            if(inside.token.equals("E")){
                                    repetir.b = inside;
                                    textos = repetir.repetir();
                                    texto_py = textos.get(0).toString();
                                    texto_go = textos.get(1).toString();
                                    py.mText(texto_py);
                                    go.mText(texto_go);
                            }else if(inside.token.equals("RECURSIVE_INST")){
                                if (inside.hijos.isEmpty()){
                                    repetir.instrucciongo = "//null\n";
                                    repetir.instruccionpy = "#null\n     ";
                                    recursivo_go = "";
                                    recursivo_py = "";
                                    
                                }else{
                                    recursive_instruction(inside);
                                    repetir.instrucciongo = recursivo_go;
                                    repetir.instruccionpy = recursivo_py;
                                    recursivo_go = "";
                                    recursivo_py = "";
                                }
                            }  
                        }
                    }
                    else  if(i.token.equals("PRINTLN") || i.token.equals("PRINT")){
//                        System.out.println(iterator);
//                        System.out.println(i);
                        if(iterator == i ){
//                            System.out.println("entra break");
                            break;
                        }
                        tmp = i;
                        iterator = i;
                        for (Nodo inside : i.hijos) {
                            if(inside.token.equals("E")){
                                for (Nodo j : inside.hijos) {
                                    if(j.token.equals("tk_CADENA")){
                                        textos = prin.print(j.lexema, true);
                                        texto_py = textos.get(0).toString();
                                        texto_go = textos.get(1).toString();
                                        py.mText(texto_py);
                                        go.mText(texto_go);
                                    }else if(j.token.equals("ID")){
                                        textos = prin.print(j.lexema, false);
                                        texto_py = textos.get(0).toString();
                                        texto_go = textos.get(1).toString();
                                        py.mText(texto_py);
                                        go.mText(texto_go);
                                        texto_go = "";
                                        texto_py = "";
                                        
                                    }
                                }
                            }
                        }
//                        DECLARAR
                    }else if(i.token.equals("DECLARACION")){
//                        System.out.println(i);
                        for (Nodo inside : i.hijos) {
                            if(inside.token.equals("LISTID")){
                                 declarar.b = inside;
                            }else if(inside.token.equals("TIPO")){
                                declarar.tipo = inside;
                            }else if(inside.token.equals("E")){
                                declarar.E = inside;
                                textos = declarar.declarar();
                                texto_py = textos.get(0).toString();
                                texto_go = textos.get(1).toString();
                                py.mText(texto_py);
                                go.mText(texto_go);
                            }
                        }
                    }else if(i.token.equals("ASIGNA")){
                        for (Nodo inside : i.hijos) {
                            if(inside.token.equals("LISTID")){
                                 asignar.a = inside;
                            }else if(inside.token.equals("E")){
                                asignar.E = inside;
                                textos = asignar.asignar();
                                texto_py = textos.get(0).toString();
                                texto_go = textos.get(1).toString();
                                py.mText(texto_py);
                                go.mText(texto_py);
                            }
                        }
//                        ------RECURSIVIDAD------
                    }else if(instruccion.token == "INSTRUCCIONES"){
                            instrucciones(instruccion);
                    } 
                    }
                }
            }
        }
    }
//    -/-/-/-/-/-/--/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-
//    -/-/-/-/-/-/--/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-
    public void recursive_instruction(Nodo nodo){
        for(Nodo instruccion : nodo.hijos){
            if (instruccion != null){
                
//            IMPRIMIR
            if(instruccion.token.equals("PRINTLN") || instruccion.token.equals("PRINT")){
                for(Nodo i : instruccion.hijos){
                    if(i.token.equals("E")){
                        for (Nodo inside : i.hijos) {
                            if(inside.token.equals("tk_CADENA")){
//                                System.out.println(inside.lexema);
                                textos = prin.print(inside.lexema, true);
                                recursivo_py += textos.get(0).toString();
                                recursivo_go += textos.get(1).toString();
                            }else{
                                textos = prin.print(inside.lexema, false);
                                recursivo_py += textos.get(0).toString();
                                recursivo_go += textos.get(1).toString();
                            }  
                        }
                    }
                }
//                        DECLARACION
            }else if(instruccion.token.equals("DECLARACION")){
                        for (Nodo inside : instruccion.hijos) {
                            if(inside.token.equals("LISTID")){
                                 declarar.b = inside;
                            }else if(inside.token.equals("TIPO")){
                                declarar.tipo = inside;
                            }else if(inside.token.equals("E")){
                                declarar.E = inside;
                                textos = declarar.declarar();
                                recursivo_py += textos.get(0).toString();
                                recursivo_go += textos.get(1).toString();
                            }
                        }
//                        ASIGNA
            }else if(instruccion.token.equals("ASIGNA")){
                        for (Nodo inside : instruccion.hijos) {
                            if(inside.token.equals("LISTID")){
                                 asignar.a = inside;
                            }else if(inside.token.equals("E")){
                                asignar.E = inside;
                                textos = asignar.asignar();
                                recursivo_py += textos.get(0).toString();
                                recursivo_go += textos.get(1).toString();
                            }
                        }
//                IF
            }else if(instruccion.token.equals("CONDICION")){
                    for (Nodo inside : instruccion.hijos) {
                        if(inside.token.equals("E")){
                                condi.b = inside;
//                                System.out.println("res_SI");
                            }else if(inside.token.equals("RECURSIVE_INST")){
                                condi.anteriores_go= recursivo_go;
                                condi.anterior_py = recursivo_py;
                                recursive_instruction(inside);
//                                condi.d = recursivo;
                                    condi.instruccionGo = recursivo_go;
                                    condi.instruccionpy = recursivo_py;
                                    textos = condi.condicion();
                                    texto_py = textos.get(0).toString();
                                    texto_go = textos.get(1).toString();
                                    recursivo_go = texto_go;
                                    recursivo_py = texto_py;
                        }else if (inside.token.equals("res_FINSI")){
                            texto_py = texto_go = "";                                                                                   
                        } 
                    }
            }else if(instruccion.token.equals("FOR")){
                    for (Nodo inside : instruccion.hijos) {
                        if(inside.token.equals("LISTID")){
                                para.b = inside;
                            }else if(inside.token.equals("E")){
                                for (Nodo hijo : inside.hijos) {
                                    if(inside.hijos.size() == 1){
                                        para.d = hijo;
                                    }else{
                                        para.f = inside;
                                        break;
                                    }
                                }
                            }else if(inside.token.equals("RECURSIVE_INST")){
                                if(inside.hijos.isEmpty()){
                                    para.instruccionGo = "// null";
                                    para.instruccionpy = "# null\n    ";
                                    textos = para.para();
                                    texto_py = textos.get(0).toString();
                                    texto_go = textos.get(1).toString();
                                    recursivo_go = texto_go;
                                    recursivo_py = texto_py;
                                }else{
                                recursive_instruction(inside);
                                    para.instruccionGo = recursivo_go;
                                    para.instruccionpy = recursivo_py;
                                    textos = para.para();
                                    texto_py = textos.get(0).toString();
                                    texto_go = textos.get(1).toString();
                                    recursivo_go = texto_go;
                                    recursivo_py = texto_py;
                                }
                        }
                    }
            }else if(instruccion.token.equals("WHILE")){
                    for (Nodo inside : instruccion.hijos) {
                             if(inside.token.equals("E")){
                                 mientras.b = inside;
                            }else if(inside.token.equals("RECURSIVE_INST")){
                                if(inside.hijos.isEmpty()){
                                    mientras.instrucciongo = "// null";
                                    mientras.instruccionpy = "# null\n    ";
                                    textos = mientras.mientras();
                                    texto_py = textos.get(0).toString();
                                    texto_go = textos.get(1).toString();
                                    recursivo_go = texto_go;
                                    recursivo_py = texto_py;
                                }else{
                                recursive_instruction(inside);
                                    mientras.instrucciongo = recursivo_go;
                                    mientras.instruccionpy = recursivo_py;
                                    textos = mientras.mientras();
                                    texto_py = textos.get(0).toString();
                                    texto_go = textos.get(1).toString();
                                    recursivo_go = texto_go;
                                    recursivo_py = texto_py;
                                }
                        }
                    }
//                ------------RECURSIVIDAD DE INSTRUCCIONES---------
            }else if(instruccion.token.equals("RECURSIVE_INST")){
                recursive_instruction(instruccion);
            }
            
            }
        }
    }
}
