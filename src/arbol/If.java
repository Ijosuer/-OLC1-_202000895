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
    public Imprimir print = new Imprimir();
    String text="";
    String IDs="";
    Nodo b;
    String instruccionpy;
    String instruccionGo;
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
            text+=nodoToken+",";
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
        String idk = ""; 
        idk = getExpression(b, "0");
        String[] condi = idk.split(",");
        
//        String inst = getcondition(d, "0");
        
        String var1 =condi[0];
        String condicion =condi[1];
        String var2 =condi[2];
        text = "";
        translate = "if ";
        if(condicion.equals("mayor")){
            translate+=var1+" > "+var2+":\n        "
                    +"\n        "+d+"\n    ";return translate;
        }else if(condicion.equals("menor")){
            translate+=var1+" < "+var2+":\n        "
                    +"\n        "+d+"\n    ";return translate;
        }else if(condicion.equals("es_igual")){
            translate+=var1+" == "+var2+":\n        "
                    +"\n        "+d+"    ";return translate;
        }else if(condicion.equals("menor_o_igual")){
            translate+=var1+" <= "+var2+":\n        "
                    +"\n        "+d+"\n    ";return translate;
        }else if(condicion.equals("mayor_o_igual")){
            translate+=var1+" >= "+var2+":\n        "
                    +"\n        "+d+"\n    ";
        }else if(condicion.equals("es_diferente")){
            translate+=var1+" != "+var2+":\n        "
                    +"\n        "+d+"\n    ";
        }else if(condicion.equals("")){
            translate+=var1+":\n        "
                    +"\n        "+d+"\n    ";
        }
        IDs= "";
//        py.mText(translate);
        return translate;
    }
    public String go_If(Nodo b,String d){
        String translate = ""; 
        String idk = getExpression(b, "0");
        String[] condi = idk.split(",");
//        String inst = getcondition(d, "0");
        
        String var1 =condi[0];
        String condicion =condi[1];
        String var2 =condi[2];
        translate = "if ";
        if(condicion.equals("mayor")){
            translate+=var1+" > "+var2+"{\n        "
                    +" "+d+"          }\n";
        }else if(condicion.equals("menor")){
            translate+=var1+" < "+var2+"{\n        "
                    +" "+d+"       \n    }\n";
        }else if(condicion.equals("es_igual")){
            translate+=var1+" == "+var2+"{\n        "
                    +" "+d+"    }\n";return translate;
        }else if(condicion.equals("menor_o_igual")){
            translate+=var1+" <= "+var2+"{\n        "
                    +" "+d+"    }\n";return translate;
        }else if(condicion.equals("mayor_o_igual")){
            translate+=var1+" >= "+var2+"{\n        "
                    +" "+d+"           }\n";
        }else if(condicion.equals("es_diferente")){
            translate+=var1+" != "+var2+"{\n        "
                    +" "+d+"           }\n";
        }else if(var2.equals("")){
            translate+=var1+"{\n        "
                    +" "+d+"         }\n  ";
        }
        IDs= "";
        text = "";
//        go.mText(translate);
        System.out.println(translate);
        return translate;
    }
    
}
