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
public class Imprimir {
    public Imprimir() {
    }
    public translate_Python py = new translate_Python();
//    PRINT::= res_IMPRIMIR:a  PRINT_:b tk_PTCOMA:c
    public void print(String a){
        py_Print(a);
        go_Print(a);
    }
    public void py_Print(String a){
        System.out.println("llega");
        String txt = ("print(\'"+a+"\')\n");
        py.mText(txt);

    }
    public void go_Print(String a){
        System.out.println("fmt.Print(\'"+a+"\')");
    }
        
}
