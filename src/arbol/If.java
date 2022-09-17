/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;
/**
 *
 * @author josue
 */
public class If {
    public translate_Python py = new translate_Python();
    public translate_Go go = new translate_Go();
    public Imprimir print = new Imprimir();
    String text="";
    String IDs="";

    public If() {
    }
//    res_SI:a EXPRESION_logica:b res_ENTONCES:c RECURSIVE_INST:d res_FINSI:e
    public void condicion(Nodo b, Nodo d){
        py_If(b,d);
//        go_If();
    }
    public String getExpression( Nodo nodo,String i){
        int k =0;
        String r="";
        for(int j =0 ; j<=nodo.hijos.size()-1; j++){
            r= r + getExpression(nodo.hijos.get(j), ""+i+k);
            k++;
        }
        
        if( !(nodo.lexema.equals("")) ){
            String nodoToken = nodo.lexema; 
            nodoToken = nodoToken.replace("\"", "");
            text+=nodoToken+",";
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
    public void py_If(Nodo b, Nodo d){
        String translate = ""; 
        String idk = getExpression(b, "0");
        String[] condi = idk.split(",");
        String inst = getcondition(d, "0");
        
        String var1 =condi[0];
        String condicion =condi[1];
        String var2 =condi[2];
        translate = "if ";
        if(condicion.equals("mayor")){
            translate+=var1+" > "+var2+":\n        #"
                    +"\n        pass\n    ";
        }else if(condicion.equals("menor")){
            translate+=var1+" < "+var2+":\n        #"
                    +"\n        pass\n    ";
        }else if(condicion.equals("es_igual")){
            translate+=var1+" = "+var2+":\n        #"
                    +"\n        pass\n    ";
        }else if(condicion.equals("menor_o_igual")){
            translate+=var1+" <= "+var2+":\n        #"
                    +"\n        pass\n    ";
        }else if(condicion.equals("mayor_o_igual")){
            translate+=var1+" >= "+var2+":\n        #"
                    +"\n        pass\n    ";
        }else if(condicion.equals("es_diferente")){
            translate+=var1+" != "+var2+":\n        #"
                    +"\n        pass\n    ";
        }else if(condicion.equals("")){
            translate+=var1+":\n        #"
                    +"\n        pass\n    ";
        }
        IDs= "";
        text = "";
        py.mText(translate);
    }
    
}
