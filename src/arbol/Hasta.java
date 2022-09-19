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
public class Hasta {

    Nodo b;
//    String d;
    String text ="";
//    ----------
    String instruccionpy;
    String instrucciongo;
    public Hasta() {
    }
   public ArrayList<String> repetir(){
        ArrayList textos = new ArrayList();
        textos.add( py_do(b,instruccionpy));
        textos.add( go_do(b,instrucciongo));
        return (textos);
   }
   
   public String py_do(Nodo b,String d){
        String translate = ""; 
        String idk = ""; 
        idk = getExpression(b, "0");
        String[] condi = idk.split(",");
        
//        String inst = getcondition(d, "0");
        
        String var1 =condi[0];
        String condicion =condi[1];
        String var2 =condi[2];
        text = "";
        translate = "while (";
        
        if(condicion.equals("mayor")){
            translate+=var1+" > "+var2+")):\n        "
                    +"\n        "+d+"\n    ";return translate;
        }else if(condicion.equals("menor")){
            translate+=var1+" < "+var2+"):\n        "
                    +"\n        "+d+"\n    ";return translate;
        }else if(condicion.equals("es_igual")){
            translate+=var1+" == "+var2+"):\n        "
                    +"\n        "+d+"    ";return translate;
        }else if(condicion.equals("menor_o_igual")){
            translate+=var1+" <= "+var2+"):\n        "
                    +"\n        "+d+"\n    ";return translate;
        }else if(condicion.equals("mayor_o_igual")){
            translate+=var1+" >= "+var2+"):\n        "
                    +"\n        "+d+"\n    ";return translate;
        }else if(condicion.equals("es_diferente")){
            translate+=var1+" != "+var2+"):\n        "
                    +"\n        "+d+"\n    ";return translate;
        }else if(condicion.equals("")){
            translate+=var1+"):\n        "
                    +"\n        "+d+"\n    ";return translate;
        }
        return translate;
   }
    
   public String go_do(Nodo b, String d){
        String translate = ""; 
        String idk = ""; 
        idk = getExpression(b, "0");
        String[] condi = idk.split(",");
        
//        String inst = getcondition(d, "0");
        
        String var1 =condi[0];
        String condicion =condi[1];
        String var2 =condi[2];
        text = "";
        translate = "for true {\n    "+d+"\n    ";
        
        if(condicion.equals("mayor")){
            translate+="if ("+var1+" > "+var2+")){\n        "
                    +"break \n }\n";return translate;
        }else if(condicion.equals("menor")){
            translate+="if ("+var1+" < "+var2+"){\n        "
                    +"break \n }\n";return translate;
        }else if(condicion.equals("es_igual")){
            translate+="if ("+var1+" == "+var2+"){\n        "
                    +"break \n }\n";return translate;
        }else if(condicion.equals("menor_o_igual")){
            translate+="if ("+var1+" <= "+var2+"){\n        "
                    +"break \n \n}";return translate;
        }else if(condicion.equals("mayor_o_igual")){
            translate+="if ("+var1+" >= "+var2+"){\n        "
                    +"break \n }\n";return translate;
        }else if(condicion.equals("es_diferente")){
            translate+="if ("+var1+" != "+var2+"){\n        "
                    +"break \n }\n";return translate;
        }else if(var2.equals("")){
            translate+="if ("+var1+"){\n        "
                    +"break \n }  \n";return translate;
        }
       return translate;
       
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
            text+=nodoToken+",";
//            text+=nodoToken+"";
            text = text.replace("\n", " ");
        }
        r = "";
        return text;
    }
}
