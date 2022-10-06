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
public class TEST {
  public static void main(String[] args) {
    String myStr = " _operaciones1Basica_ mayor_o_igual (_operaciones1Basica_)*(8+5)";
//    String find = "es_igual";
//    boolean i = myStr.contains(find);
    if(myStr.contains("es_igual")){
    System.out.println(myStr.replace("es_igual", "=="));
    }else if(myStr.contains("es_diferente")){
    System.out.println(myStr.replace("es_diferente", "!="));
    }else if(myStr.contains("mayor_o_igual")){
    System.out.println(myStr.replace("mayor_o_igual", ">="));
    }else if(myStr.contains("menor_o_igual")){
    System.out.println(myStr.replace("menor_o_igual", "<="));
    }else if(myStr.contains("mayor")){
    System.out.println(myStr.replace("mayor", ">"));
    }else if(myStr.contains("menor")){
    System.out.println(myStr.replace("menor", "<"));
    }
  }
}

