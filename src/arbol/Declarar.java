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
import arbol.translate_Python;
import arbol.translate_Go;
import java.util.ArrayList;
public class Declarar {
public translate_Python py = new translate_Python();
public translate_Go go = new translate_Go();
    
public Declarar() {
    }
String text="";
String IDs="";


Nodo b;Nodo tipo; Nodo E; 
    public ArrayList<String> declarar(){
        ArrayList textos = new ArrayList();
        textos.add( py_Declarar(b,tipo,E,"0"));
    textos.add( go_Declarar(b,tipo,E,"0"));
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
            String nodoToken = nodo.lexema; 
            nodoToken = nodoToken.replace("\"", "");
            text+=nodoToken;
            text = text.replace("\n", " ");
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
    
//res_INGRESAR:a LISTID:b res_COMO:c TIPO:d res_CONVALOR:e EXPRESION_NUM:f tk_PTCOMA:g
    public String py_Declarar(Nodo b, Nodo tipo, Nodo nodo, String i){
        String translate = "";
        String id = "";
        getExpression(nodo,i);
//        String id = b.hijos.get(0).lexema;
        id = getIDs(b,i);
        String[] var = id.split(",");
        String type = tipo.hijos.get(0).token;
        if(type == "res_NUM"){
//            translate = id+" = "+text+"\n";
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
        }else if (type == "res_CADENA"){
            if(var.length>1){
                for (String element : var) {
                    if(element == var[var.length-1]){
                    translate += element+" = \""+text+"\"\n";
                    }else{
                    translate += element+" = \""+text+"\"\n    ";
                    }
                }   
            }else{
                    translate += id+" = \""+text+"\"\n";
            }
        }else{
            if (var.length > 1){
                for (String element : var) {
                    if(element == var[var.length-1]){
                        if(text.equals("falso")){
                            translate +=  element+" = False\n";
                        }else{
                            System.out.println(text);
                            translate += element+" = True \n";
                        }
                    }else{
                        if(text.equals("falso")){
                            translate +=  element+" = False\n    ";
                        }else{
                            System.out.println(text);
                            translate += element+" = True \n    "    ;
                        }
                    }
                }
            }else{
                    if(text.equals("falso")){
                            translate +=  id+" = False\n";
                        }else if(text.equals("verdadero")){
                            System.out.println(text);
                            translate += id+" = True \n";
                        }else{
                            if(text.contains("es_igual")){
                            text = (text.replace("es_igual", "=="));
                            translate +=id+" = "+text+"\n";
                            }else if(text.contains("es_diferente")){
                            text = (text.replace("es_diferente", "!="));
                            translate +=id+" = "+text+"\n";
                            }else if(text.contains("mayor_o_igual")){
                            text = (text.replace("mayor_o_igual", ">="));
                            translate +=id+" = "+text+"\n";
                            }else if(text.contains("menor_o_igual")){
                            text = (text.replace("menor_o_igual", "<="));
                            translate +=id+" = "+text+"\n";
                            }else if(text.contains("mayor")){
                            text = (text.replace("mayor", ">"));
                            translate +=id+" = "+text+"\n";
                            }else if(text.contains("menor")){
                            text = (text.replace("menor", "<"));
                            translate +=id+" = "+text+"\n";
                            }
                        }
            }
        }
        
        text = "";
        IDs = "";
        return translate;
//        translate = "";
//        py.mText(translate);
//        System.out.println(translate);
    }
    
    public String go_Declarar(Nodo b, Nodo tipo, Nodo nodo, String i){
        String translate = "";
        String id = "";
        getExpression(nodo,i);
//        String id = b.hijos.get(0).lexema;
        id = getIDs(b,i);
        String[] var = id.split(",");
        String type = tipo.hijos.get(0).token;
        if(type == "res_NUM"){
//            translate = id+" = "+text+"\n";
        if (var.length > 1){
            for (String element : var) {
                if(element == var[var.length-1]){
                translate +="var "+element+" = "+text+"\n";
                }else{
                translate +="var "+ element+" = "+text+"\n    ";
                }
            }
        }else{
                translate += "var "+id+" = "+text+"\n";
        }
        }else if (type == "res_CADENA"){
            if(var.length>1){
                for (String element : var) {
                    if(element == var[var.length-1]){
                    translate += "var "+element+" = \""+text+"\"\n";
                    }else{
                    translate += "var "+element+" = \""+text+"\"\n    ";
                    }
                }   
            }else{
                    translate += "var "+id+" = \""+text+"\"\n";
            }
        }else{
            if (var.length > 1){
                for (String element : var) {
                    if(element == var[var.length-1]){
                        if(text.equals("falso")){
                            translate +=  "var "+element+" = false\n";
                        }else{
                            System.out.println(text);
                            translate += "var "+element+" = frue \n";
                        }
                    }else{
                        if(text.equals("falso")){
                            translate +=  "var "+element+" = false\n    ";
                        }else{
                            System.out.println(text);
                            translate += "var "+element+" = true \n    "    ;
                        }
                    }
                }
            }else{
                    if(text.equals("falso")){
                            translate +="var"+ id+" = false\n";
                        }else if(text.equals("verdadero")){
                            translate +="var"+id+" = true \n";
                        }else{
                            if(text.contains("es_igual")){
                            text = (text.replace("es_igual", "=="));
                            translate +="var"+id+" = "+text+"\n";
                            }else if(text.contains("es_diferente")){
                            text = (text.replace("es_diferente", "!="));
                            translate +="var"+id+" = "+text+"\n";
                            }else if(text.contains("mayor_o_igual")){
                            text = (text.replace("mayor_o_igual", ">="));
                            translate +="var"+id+" = "+text+"\n";
                            }else if(text.contains("menor_o_igual")){
                            text = (text.replace("menor_o_igual", "<="));
                            translate +="var"+id+" = "+text+"\n";
                            }else if(text.contains("mayor")){
                            text = (text.replace("mayor", ">"));
                            translate +="var"+id+" = "+text+"\n";
                            }else if(text.contains("menor")){
                            text = (text.replace("menor", "<"));
                            translate +="var"+id+" = "+text+"\n";
                            }
                        }
            }
        }
        
        text = "";
        IDs = "";
        return translate;
    }
}
