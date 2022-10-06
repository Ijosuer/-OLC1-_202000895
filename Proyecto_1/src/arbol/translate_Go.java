/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import static arbol.translate_Python.mainText;
import static arbol.translate_Python.space;

/**
 *
 * @author josue
 */
public class translate_Go {
    public static String mainText = "package main\n"
            + "import \"fmt\"\n\n"
            + "func main(){\n";
    public static String code_text="";
    public static String lastText = "}\n";
    public static String funcionesTxt = "";

    public translate_Go() {
    }
    
    public void mText(String txt){
//        System.out.println(txt);
        code_text += txt;
    }
    
    public void fText(){
        mainText += code_text+lastText+funcionesTxt;
    }
    
    public void clean(){
        mainText = "package main\n"
            + "import \"fmt\"\n\n"
            + "func main(){\n";
        code_text ="";
        funcionesTxt = "";
    }
}
