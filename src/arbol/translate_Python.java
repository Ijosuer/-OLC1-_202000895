/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author josue
 */
public class translate_Python {
    public static String mainText = "def main():\n";
    public static String text;
    public static String space = "    ";
    public final String mainlastText = "\nif __name__ == \"__main__\":\n" +space+"main()";
    
    public translate_Python() {
    
    }
    public void mText(String txt){
        mainText += space+txt;
    }
    public void makeFile(){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("prueba.txt");
            pw = new PrintWriter(fichero);
           mainText += mainlastText;
           pw.println(mainText);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    public void clean(){
        mainText = "def main():\n";
        
}
    
}
