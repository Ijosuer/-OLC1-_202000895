/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author josue
 */
public class Errores {
    public String token;
    public String lexema;
    public int linea;
    public int column;
    public static LinkedList<Errores> listaErrores = new LinkedList<>();
    
    public Errores(String token, String lexema, int linea, int column){
       this.token = token;
       this.lexema = lexema;
       this.linea = linea;
       this.column = column;
    }
    public static void addError(String token, String lexema, int linea, int column){
        listaErrores.add(new Errores(token,lexema,linea,column));
    }
     public static void makeHTML(){
        String html = "<!DOCTYPE html>\n" +
"        <html lang=\"en\">\n" +
"        <head>\n" +
"        <title>Reporte Errores</title>\n" +
"        <meta charset=\"utf-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"        <link\n" +
"            href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"\n" +
"            rel=\"stylesheet\"\n" +
"            integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"\n" +
"            crossorigin=\"anonymous\"\n" +
"            />\n" +
"        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css\">\n" +
"        <script src=\"https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js\"></script>\n" +
"        <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\"></script>\n" +
"        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js\"></script>\n" +
"        </head>\n" +
"        <body style=\"background-color: rgb(243, 240, 235);\">\n" +
"        <div class=\"container\">\n" +
"        <br>\n" +
"        <h2 class=\"text-center\" style=\"font-weight: bold; color: rgb(36, 36, 179);\">Tabla de Errores</h2>\n" +
"        <br>\n" +
"        <h6 style=\"font-weight:600 ;\">A continuacion se presentan tabla de errores lexicos encontrados en el lenguaje:</h6>\n" +
"        <table class=\"table table-hover\">\n" +
"            <thead class=\"thead-dark\">\n" +
"        <tr>\n" +
"        <th class=\"text-center\">Descripcion</th>\n" +
"        <th class=\"text-center\">fila</th>\n" +
"        <th class=\"text-center\">COLUMNA</th>\n" +
"        </tr>\n" +
"        </thead>\n" +
"        <tbody>\n";
        for (Errores listaErrore : listaErrores) {
         
        
        html+=
"        <!-- ASI SE CREA UNA FILA -->\n" +
"        <tr>\n" +
"            <td class=\"text-center\">";html+=listaErrore.lexema;html+="</td>\n" +
"            <td class=\"text-center\">";html+=listaErrore.linea;html+="</td>\n" +
"            <td class=\"text-center\">";html+=listaErrore.column;html+="</td>\n" +
"        </tr>   \n";
        };
        html+=
"        </tbody>\n" +
"        </table>             \n" +
"        </body>\n" +
"        <hr>\n" +
"        <footer>\n" +
"            <h5 style=\"text-align: right; font-weight: bolder;\">Josue Gramajo - 202000895</h5>\n" +
"            <h6 style=\"text-align: right; font-weight: bold;\">Reporte generado:"+java.time.LocalDate.now()+"</h6>\n" +
"        </footer>\n" +
"        </html>\n" +
"        ";
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try{
            fichero = new FileWriter("Errores_Lexicos.html");
            escritor = new PrintWriter(fichero);
            escritor.println(html);
            escritor.close();
            fichero.close();
            html = "";
            listaErrores.clear();
        } catch (Exception e) {
            System.out.println("error en generar html");
            e.printStackTrace();
        }    
     }
}
