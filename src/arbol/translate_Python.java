/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import java.util.LinkedList;

/**
 *
 * @author josue
 */
public class translate_Python {
    public static LinkedList listaTokens;  
    public String texto;

    public translate_Python() {
    }
    
    public Object sacarToken(){
//        Este metodo saca el primer token de la lista
        try {
            return listaTokens.pop();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public void analizar(){
        System.out.println("");
    }
}
