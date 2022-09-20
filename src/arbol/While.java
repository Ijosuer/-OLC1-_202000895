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
//res_MIENTRAS:a EXPRESION_logica:b res_HACER:c RECURSIVE_INST:d res_FINMIENTRAS:e
public class While {

    Nodo b;
    String d;
    String text ="";
//    ----------
    String instruccionpy;
    String instrucciongo;
    public While() {
    }
   public ArrayList<String> mientras(){
        ArrayList textos = new ArrayList();
        textos.add( py_while(b,instruccionpy));
        textos.add( while_go(b,instrucciongo));
        return (textos);
   }
   
   public String py_while(Nodo b,String d){
        String translate = ""; 
        String idk = ""; 
        idk = getExpression(b, "0");
        String logico = getlogic(idk);
        translate = "while (";
        translate+= logico+"):\n            "+d+"\n     ";
       text="";
       return translate;
   }
    
   public String while_go(Nodo b, String d){
        String translate = ""; 
        String idk = ""; 
        idk = getExpression(b, "0");
        String logico = getlogic(idk);
        translate = "for true {\n    ";
        translate+="if !("+logico+"){\n    "
                +"break\n}\n  "+d+"}\n";
        
       return translate;
       
   }
    public String getlogic(String texto){
        String condicion = "";
        if(texto.contains("menor_o_igual")){
             condicion = (texto.replace("menor_o_igual", "<="));
             return condicion;
        }else if(texto.contains("mayor_o_igual")){
             condicion = (texto.replace("mayor_o_igual", ">="));
             return condicion;
        }else if(texto.contains("mayor")){
             condicion = (texto.replace("mayor", ">"));
             return condicion;
        }else if(texto.contains("menor")){
             condicion = (texto.replace("menor", "<"));
             return condicion;
        }else if(texto.contains("es_igual")){
             condicion = (texto.replace("es_igual", "=="));
             return condicion;
        }else if(texto.contains("es_diferente")){
             condicion = (texto.replace("es_diferente", "!="));
             return condicion;
        }else if(texto.equals("")){
             return condicion;
            
        }
        return condicion;
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
            text = text.replace("\n", " ");
        }
        r = "";
        return text;
    }
}
