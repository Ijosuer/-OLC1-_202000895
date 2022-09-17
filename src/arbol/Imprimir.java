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
public class Imprimir {
    public Imprimir() {
    }
    public translate_Python py = new translate_Python();
    public translate_Go go = new translate_Go();
    String txt;
//    PRINT::= res_IMPRIMIR:a  PRINT_:b tk_PTCOMA:c
    public void print(String a, boolean flag){
        py_Print(a,flag);
        go_Print(a,flag);
    }
    public void py_Print(String a, boolean flag){
        if (flag == true){
        txt = ("print(\'"+a+"\')\n");
        py.mText(txt);
        }else{
        txt = ("print("+a+")\n");
        py.mText(txt);
        }
    }
    public String returnPrint(){
        return "SIMON EMPTY";
    }
    public void go_Print(String a,boolean flag){
        if (flag == true){
        String txt = ("fmt.Println(\""+a+"\")\n");
        go.mText(txt);
        }else{
        String txt = ("fmt.Println("+a+")\n");
        go.mText(txt);
        }
    }
}
