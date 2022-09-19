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
public class Para {

    String text="";
    String exp="";
    String IDs="";
    boolean flag = false;
//  --.-...----.-.-.--.-.
    Nodo b;
    Nodo d;
    Nodo f;
    String instruccionpy = "";
    String instruccionGo = "";
    public Para() {
    }
//    res_PARA:a LISTID:b tk_ASIGNAFLECHA:c EXPRESION_NUM:d res_HASTA:e EXPRESION_NUM:f res_HACER:g RECURSIVE_INST:h res_FINPARA:i
    public  ArrayList<String> para(){
        ArrayList textos = new ArrayList();
        textos.add( py_For(b,d,f,"0"));
        textos.add(go_For(b,d,f,"0"));
        return (textos);
    }
    
    public String py_For(Nodo b,Nodo d,Nodo f,String i){
        String translate = "";
        String id = getIDs(b, i);
        if(flag == false){
            translate+="for i in "+id+" :\n        "+instruccionpy;
        }else{
            translate+="for i in range ("+id+") :\n        pass";
        }
        text = "";
        IDs = "";
        return (translate);
    }
    
    public String go_For(Nodo b,Nodo d,Nodo f,String i){
        String translate = "";
        String id = getIDs(b, i);
        String hasta = getExpression(d, i);
        String E = getGo(f,i);
        translate+="for "+id+" := "+hasta+";"+E+"{\n "+instruccionGo+" }\n";
        text = "";
        IDs = "";
        return (translate);
    }
    
    public String getGo( Nodo nodo,String i){
        int k =0;
        String r="";
        for(int j =0 ; j<=nodo.hijos.size()-1; j++){
            r= r + getGo(nodo.hijos.get(j), ""+i+k);
            k++;
        }
        if( !(nodo.lexema.equals("")) ){
                String nodoToken = nodo.lexema; 
                nodoToken = nodoToken.replace("\"", "");
                exp = exp.replace("\n", " ");
                exp+=nodoToken;
        }
        return exp;
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
                 text +=nodoToken;
                flag = true;
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
}
