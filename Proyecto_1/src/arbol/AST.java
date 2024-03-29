/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author josue

import java.io.PrintWriter;
/**
 *
 * @author sandr
 */
public class AST {
    public Nodo raiz;
    
    public AST(Nodo raiz){
        this.raiz = raiz;
        
    }
    
     public void GraficarSintactico(){
        String grafica = "digraph asdfsadfasdfsdf{\n\n" + GraficaNodos(this.raiz, "0") + "\n\n}";        
        GenerarDot(grafica);
    }
    
    private String GraficaNodos(Nodo nodo, String i){
        int k=0; 
        String r = "";
        if(nodo == null){
            return "";
        }
        String nodoTerm = nodo.token;
        nodoTerm = nodoTerm.replace("\"", "");
        r= "node" + i + "[label = \"" + nodoTerm + "\"];\n";
        
        for(int j =0 ; j<=nodo.hijos.size()-1; j++){
            r = r + "node" + i + " -> node" + i + k + "\n";
            r= r + GraficaNodos(nodo.hijos.get(j), ""+i+k);
            k++;
        }
        
        if( !(nodo.lexema.equals("")) ){
            String nodoToken = nodo.lexema; 
            
            nodoToken = nodoToken.replace("\"", "");
            r += "node" + i + "c[label = \"" + nodoToken + "\"];\n";
            r += "node" + i + " -> node" + i + "c\n";
        }
        
        return r;
    }
    
    private void GenerarDot(String cadena){
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try{
            fichero = new FileWriter("Arbol_Sintactico.dot");
            escritor = new PrintWriter(fichero);
            escritor.println(cadena);
            escritor.close();
            fichero.close();
            
            /* Exiten varias formas de generar el .dot
              A continuacion se presentan dos metodos reportar() y GenerarJpg() los cuales ambos hacen lo mismo generan la imagen del .dot
              Prueben ambos y solo llamar el metodo que abra la imagen .jpg el otro metodo comentarlo
              -- Si les genera la imagen con ambos metodos dejen del de su preferencia y comentar el otro metodo 
              
              La imagen y el .dot se guardan dentro de la carpeta del proyecto
            */
            
            //GenerarJpg();
            reportar();
            
        } catch (Exception e) {
            System.out.println("error en generar dot");
            e.printStackTrace();
        }
    }
    
      
    public void reportar() throws IOException {
        
        String file_input_path = "C:\\Users\\josue\\Documents\\NetBeansProjects\\OLC1_Proyecto1\\Arbol_Sintactico.dot";
        String do_path = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        
        String file_get_path =  "C:\\Users\\josue\\Documents\\NetBeansProjects\\OLC1_Proyecto1\\Arbol_Sintactico.jpg" ;
        try {
            ProcessBuilder pBuilder;
            pBuilder = new ProcessBuilder(do_path, "-Tjpg", "-o", file_get_path, file_input_path);
            pBuilder.redirectErrorStream(true);
            pBuilder.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}