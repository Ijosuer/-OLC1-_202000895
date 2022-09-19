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
//     *******************************
     int i = 0;
     Nodo aux;
//     -------------------------
     String recursivo_py = "";
     String recursivo_go = "";
//     -------------------------
     
     
    public void instrucciones(Nodo nodo){
        for(Nodo instruccion : nodo.hijos){
            
            if(instruccion.token == "INSTRUCCIONES"){
                for(Nodo i : instruccion.hijos){
                    if(i.token.equals("CONDICION")){
                        System.out.println("VIENE IF");
                        for (Nodo inside : i.hijos) {
                            if(inside.token.equals("E")){
                                    condi.b = inside;
                                    aux = inside;
//                                System.out.println("res_SI");
                            }else if(inside.token.equals("RECURSIVE_INST")){
                                    recursive_instruction(inside);
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
                    }else  if(i.token.equals("PRINTLN")){
                        for (Nodo inside : i.hijos) {
                            if(inside.token.equals("E")){
                                for (Nodo j : inside.hijos) {
                                    if(j.token.equals("tk_CADENA")){
                                        textos = prin.print(j.lexema, true);
                                        texto_py = textos.get(0).toString();
                                        texto_go = textos.get(1).toString();
                                        py.mText(texto_py);
                                        go.mText(texto_go);
                                    }
                                }
                            }
                        }
                    }else if(i.token.equals("DECLARACION")){
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
//                        ------RECURSIVIDAD------
                    }else if(instruccion.token == "INSTRUCCIONES"){
                            instrucciones(instruccion);
                    } 
                }
            }
        }
    }
//    -/-/-/-/-/-/--/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-
//    -/-/-/-/-/-/--/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-
    public void recursive_instruction(Nodo nodo){
        for(Nodo instruccion : nodo.hijos){
//            IMPRIMIR
            if(instruccion.token.equals("PRINTLN")){
                for(Nodo i : instruccion.hijos){
                    if(i.token.equals("E")){
                        for (Nodo inside : i.hijos) {
                            if(inside.token.equals("tk_CADENA")){
                                System.out.println(inside.lexema);
                                textos = prin.print(inside.lexema, true);
                                recursivo_py+= textos.get(0).toString();
                                recursivo_go+= textos.get(1).toString();
                            }  
                        }
                    }
                }
//                IF
            }else if(instruccion.token.equals("CONDICION")){
                        for (Nodo inside : instruccion.hijos) {
                            if(inside.token.equals("E")){
                                condi.b = inside;
                                System.out.println("res_SI");
                        }else if(inside.token.equals("RECURSIVE_INST")){
                                recursive_instruction(inside);
//                                condi.d = recursivo;
                                    condi.instruccionGo = recursivo_go;
                                    condi.instruccionpy = recursivo_py;
                                    textos = condi.condicion();
                                    texto_py = textos.get(0).toString();
                                    texto_go = textos.get(1).toString();
                                    recursivo_go = texto_go;
                                    recursivo_py = texto_py;
//                                    py.mText(texto_py);
//                                    go.mText(texto_go);
                        }else if (inside.token.equals("res_FINSI")){
//                            texto_py = texto_go = "";                                                                                   
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
//                ------------RECURSIVIDAD DE INSTRUCCIONES---------
            }else if(instruccion.token.equals("RECURSIVE_INST")){
                recursive_instruction(instruccion);
            }
            
        }
    }
}
