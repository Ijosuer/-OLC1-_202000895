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
    public static String funcionTxt = "\n";
    public static String text;
    public static String space = "    ";
    public final String mainlastText = "\nif __name__ == \"__main__\":\n" +space+"main()";
    
    public translate_Python() {
    
    }
    public void mText(String txt){
        mainText += space+txt;
    }
    public String ftext(){
        return mainText + funcionTxt + mainlastText;
    }
    public void clean(){
        mainText = "def main():\n";
        funcionTxt = "\n";
        
}
    
}
