/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import java.util.ArrayList;

/**
 *
 * @author josue
 */
public class If {
    String text="";
    String IDs="";
    Nodo b;
    String instruccionpy;
    String instruccionGo;
    String anterior_py;
    String anteriores_go;
    public If() {
    }
//    res_SI:a EXPRESION_logica:b res_ENTONCES:c RECURSIVE_INST:d res_FINSI:e
    public ArrayList<String> condicion(){
        ArrayList textos = new ArrayList();
        textos.add( py_If(b,instruccionpy));
        textos.add( go_If(b,instruccionGo));
        return (textos);
    }
    public String getExpression( Nodo nodo,String i){
        int k =0;
        String r="";
        for(int j =0 ; j<=nodo.hijos.size()-1; j++){
            getExpression(nodo.hijos.get(j), ""+i+k);
            k++;
        }
        
        if( !(nodo.lexema.equals("")) ){
            String nodoToken = nodo.lexema; 
            nodoToken = nodoToken.replace("\"", "");
            text+=nodoToken+" ";
//            text+=nodoToken+"";
            text = text.replace("\n", " ");
        }
        r = "";
        return text;
    }
    public String getcondition( Nodo nodo,String i){
        int k =0;
        String r="";
        for(int j =0 ; j<=nodo.hijos.size()-1; j++){
            r= r + getcondition(nodo.hijos.get(j), ""+i+k);
            k++;
        }
        
        if( !(nodo.lexema.equals("")) ){
            String nodoToken = nodo.lexema; 
            nodoToken = nodoToken.replace("\"", "");
            IDs+=nodoToken+" ";
            IDs = IDs.replace("\n", " ");
        }
        return IDs;
    }
    public String py_If(Nodo b, String d){
        String translate = ""; 
        String condicion = ""; 
        String texto = getExpression(b, "0");
        
        translate =anterior_py+ "\n    if ";
        if(texto.contains("menor_o_igual")){
             condicion = (texto.replace("menor_o_igual", "<="));
            translate+=condicion+":\n        "
                    +" "+d+"\n    ";
        }else if(texto.contains("mayor_o_igual")){
             condicion = (texto.replace("mayor_o_igual", ">="));
            translate+=condicion+":\n        "
                    +" "+d+"\n    ";
        }else if(texto.contains("mayor")){
             condicion = (texto.replace("mayor", ">"));
            translate+=condicion+":\n        "
                    +" "+d+"\n    ";
        }else if(texto.contains("menor")){
             condicion = (texto.replace("menor", "<"));
            translate+=condicion+":\n        "
                    +" "+d+"\n    ";
        }else if(texto.contains("es_igual")){
             condicion = (texto.replace("es_igual", "=="));
            translate+=condicion+":\n        "
                    +" "+d+"\n    ";
        }else if(texto.contains("es_diferente")){
             condicion = (texto.replace("es_diferente", "!="));
            translate+=condicion+":\n        "
                    +" "+d+"\n    ";
        }else if(texto.equals("")){
            translate+=texto+":\n        "
                    +" "+d+"\n    ";
        }
        IDs= "";
        text = "";
        return translate;
    }
    public String go_If(Nodo b,String d){
        String translate = ""; 
        String condicion = ""; 
        String texto = getExpression(b, "0");
//        String inst = getcondition(d, "0");
//        System.out.println(anteriores_go);
        translate =anteriores_go+ "\nif ";
        if(texto.contains("menor_o_igual")){
             condicion = (texto.replace("menor_o_igual", "<="));
            translate+=condicion+"{\n        "
                    +" "+d+"       \n    }\n";
        }else if(texto.contains("mayor_o_igual")){
             condicion = (texto.replace("mayor_o_igual", ">="));
            translate+=condicion+"{\n        "
                    +" "+d+"       \n    }\n";
        }else if(texto.contains("mayor")){
             condicion = (texto.replace("mayor", ">"));
            translate+=condicion+"{\n        "
                    +" "+d+"          }\n";
        }else if(texto.contains("menor")){
             condicion = (texto.replace("menor", "<"));
            translate+=condicion+"{\n        "
                    +" "+d+"       \n    }\n";
        }else if(texto.contains("es_igual")){
             condicion = (texto.replace("es_igual", "=="));
            translate+=condicion+"{\n        "
                    +" "+d+"       \n    }\n";
        }else if(texto.contains("es_diferente")){
             condicion = (texto.replace("es_diferente", "!="));
            translate+=condicion+"{\n        "
                    +" "+d+"       \n    }\n";
        }else if(texto.equals("")){
            translate+=texto+"{\n        ";
        }
        IDs= "";
        text = "";
//        go.mText(translate);
        return translate;
    }
    
}
