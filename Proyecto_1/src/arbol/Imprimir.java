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
public class Imprimir {
    public Imprimir() {
    }
//    public translate_Python py = new translate_Python();
//    public translate_Go go = new translate_Go();
    String txt;
//    PRINT::= res_IMPRIMIR:a  PRINT_:b tk_PTCOMA:c
    public ArrayList<String> print(String a, boolean flag){
        
        ArrayList textos = new ArrayList();
        textos.add( py_Print(a,flag));
        textos.add( go_Print(a,flag));
        return (textos);
    }
    public String py_Print(String a, boolean flag){
        if (flag == true){
        txt = ("print(\'"+a+"\')\n");
        return(txt);
//            System.out.println(txt);

        }else{
        txt = ("print("+a+")\n");
        return(txt);
        }
    }
    public String returnPrint(){
        return "SIMON EMPTY";
    }
    public String go_Print(String a,boolean flag){
        if (flag == true){
//        String txt = ("fmt.Println(\""+a+"\")\n");
//        go.mText(txt);
        return ("fmt.Println(\""+a+"\")\n");
        }else{
        String txt = ("fmt.Println("+a+")\n");
//        go.mText(txt);
        return txt;
        }
    }
}
