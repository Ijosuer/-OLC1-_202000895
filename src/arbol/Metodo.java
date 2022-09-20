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
public class Metodo {
    String text="";
    String IDs="";
    Nodo b;
    Nodo listp;
    String listparam = "";
    String instruccionpy;
    String instruccionGo;
    public Metodo() {
    }
//      res_METODO:a IDENTIFICADOR:b res_METODOCONP:c tk_PARIZQ:d LISTP:e tk_PARDER:f RECURSIVE_INST:g res_FINMETODO:h
    public ArrayList<String> metodo(){
        ArrayList textos = new ArrayList();
        textos.add( py_metodo(b,instruccionpy));
        textos.add( go_metodo(b,instruccionGo));
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
            text+=nodoToken;
//            text+=nodoToken+"";
            text = text.replace("\n", " ");
        }
        r = "";
        return text;
    }
    public String getParam( Nodo nodo,String i){
        int k =0;
        String r="";
        for(int j =0 ; j<=nodo.hijos.size()-1; j++){
            getParam(nodo.hijos.get(j), ""+i+k);
            k++;
        }
        if( !(nodo.lexema.equals("")) ){
            String nodoToken = nodo.lexema; 
            nodoToken = nodoToken.replace("\"", "");
            text+=nodoToken+" ";
            text = text.replace("\n", " ");
        }
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
    
    public String py_metodo(Nodo b, String d){
        String translate = "";
        String texto = getExpression(b, "0");
        text = "";
        String lista = "";
        if (listp == null){
            lista = "";
        }else{
        lista = getParam(listp, "0");
        lista = getlogicPy(lista);
        }
        translate ="\ndef "+texto+"( "+lista+" ):\n";
        translate+="    "+d+"\n    ";
        IDs= "";
        text = "";
        return translate;
    }
    
    public String go_metodo(Nodo b,String d){
       String translate = "";
        String texto = getExpression(b, "0");
        text = "";
        String lista = "";
        if (listp == null){
            lista = "";
        }else{
        lista = getParam(listp, "0");
        lista = getlogic(lista);
        }
        translate ="\nfunc "+texto+"("+lista+"){\n";//pendiente parametros
        translate+="    "+d;
        translate+="\n}\n";
        IDs= "";
        text = "";
        return translate;
    }
    
    public String getlogic(String texto){
        String condicion = "";
        texto = texto.toLowerCase();
        if(texto.contains("numero")){
             condicion = (texto.replace("numero", "int"));
             return condicion;
        }else if(texto.contains("CADENA")){
             condicion = (texto.replace("cadena", "String"));
             return condicion;
        }else if(texto.contains("CARACTER")){
             condicion = (texto.replace("char", "char"));
             return condicion;
        }
        return condicion;
    }
    public String getlogicPy(String texto){
        String condicion = "";
        texto = texto.toLowerCase();
        if(texto.contains("numero")){
             condicion = (texto.replace("numero", ""));
             return condicion;
        }else if(texto.contains("cadena")){
             condicion = (texto.replace("cadena", ""));
             return condicion;
        }else if(texto.contains("caracter")){
             condicion = (texto.replace("char", ""));
             return condicion;
        }else if(texto.contains("boolean")){
             condicion = (texto.replace("boolean", ""));
             return condicion;
        }
        return condicion;
    }
}
