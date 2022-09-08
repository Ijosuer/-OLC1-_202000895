javac JLex/Form.java
java -jar jflex-full-1.7.0.jar A_lexico.jflex    
java -jar java-cup-11b.jar -parser Analizador_sintactico -symbols sym A_sintactico.cup

