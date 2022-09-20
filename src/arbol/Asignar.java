/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;
import arbol.translate_Python;
import arbol.translate_Go;
import java.util.ArrayList;
/**
 *
 * @author josue
 */

public class Asignar {
    String text="";
    String IDs="";
    Nodo a;Nodo E;
    public Asignar() {
    }
    
//    LISTID:a tk_ASIGNAFLECHA:b EXPRESION_NUM:c tk_PTCOMA:d
    public ArrayList<String> asignar(){
        ArrayList textos = new ArrayList();
        textos.add( py_Asignar(a,E));
        textos.add( go_Asignar(a,E));
        return (textos);
    }
    public String getExpression( Nodo nodo,String i){
        int k =0;
        String r="";
        for(int j =0 ; j<=nodo.hijos.size()-1; j++){
            r= r + getExpression(nodo.hijos.get(j), ""+i+k);
            k++;
        }
        if( !(nodo.lexema.equals("")) ){
            if(nodo.token.equals("tk_cadena")){
                String nodoToken = nodo.lexema; 
                text+="\""+nodoToken+"\"";
            }else{
                String nodoToken = nodo.lexema; 
                nodoToken = nodoToken.replace("\"", "");
                text = text.replace("\n", " ");
                text+=nodoToken;
            }
        }
        return text;
    }
    public String getIDs( Nodo nodo,String i){
        int k =0;
        String r="";
        for(int j =0 ; j<=nodo.hijos.size()-1; j++){
            r= r + getIDs(nodo.hijos.get(j), ""+i+k);
            k++;
        }
        if( !(nodo.lexema.equals("")) ){
            String nodoToken = nodo.lexema; 
            nodoToken = nodoToken.replace("\"", "");
            IDs+=nodoToken;
            IDs = IDs.replace("\n", " ");
        }
        return IDs;
    }
    
    public String py_Asignar(Nodo a, Nodo E){
        String translate = "";
        String id = "";
        getExpression(E,"0");
        id = getIDs(a,"0");
        String[] var = id.split(",");
        if (var.length > 1){
            for (String element : var) {
                if(element == var[var.length-1]){
                translate += element+" = "+text+"\n";
                }else{
                translate += element+" = "+text+"\n    ";
                }
            }
        }else{
                translate += id+" = "+text+"\n";
        }
        IDs= "";
        text = "";
        return translate;
    }
    
    public String go_Asignar(Nodo a, Nodo E){
        String translate = "";
        getExpression(E,"0");
        String id = getIDs(a, "0");
        String [] var = id.split(",");
        if(var.length > 1){
            for (String element : var) {
                if(element == var[var.length-1]){
                    translate = element+" = "+text+"\n";
                }else{
                    translate = element+" = "+text+"\n";
                }
            }
        }else{
            translate += id+" = "+text+"\n";
        }
        IDs = "";
        text = "";
        return translate;
    }
}
