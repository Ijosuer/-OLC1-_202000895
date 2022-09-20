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
public class Ejecutar {
    String text="";
    String IDs="";
    Nodo b;
    Nodo listp;
    String listparam = "";
    String instruccionpy;
    String instruccionGo;
    public Ejecutar() {
    }
//      res_EJECUTAR:a IDENTIFICADOR:b tk_PARIZQ:c tk_PARDER:d tk_PTCOMA:
    public ArrayList<String> ejecutar(){
        ArrayList textos = new ArrayList();
        textos.add( py_ejecutar(b,instruccionpy));
        textos.add( go_ejecutar(b,instruccionGo));
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
    
    public String py_ejecutar(Nodo b, String d){
        String translate = "";
        String texto = b.lexema;
        text = "";
        String lista = "";
        if (listp == null){
            lista = "";
        }else{
        lista = getParam(listp, "0");
        }
        translate ="    "+texto+"( "+lista+" )\n";
        IDs= "";
        text = "";
        return translate;
    }
    
    public String go_ejecutar(Nodo b,String d){
       String translate = "";
        String texto = b.lexema;
        text = "";
        String lista = "";
        if (listp == null){
            lista = "";
        }else{
        lista = getParam(listp, "0");
        }
        translate ="\n"+texto+"("+lista+")\n";//pendiente parametros
        IDs= "";
        text = "";
        return translate;
    }
    
  
}
