
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package analizadores;

import java_cup.runtime.Symbol;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class Analizador_sintactico extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public Analizador_sintactico() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public Analizador_sintactico(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Analizador_sintactico(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\033\000\002\002\004\000\002\002\005\000\002\005" +
    "\003\000\002\005\006\000\002\005\006\000\002\005\003" +
    "\000\002\005\003\000\002\005\003\000\002\005\003\000" +
    "\002\005\003\000\002\005\003\000\002\005\003\000\002" +
    "\005\003\000\002\005\003\000\002\003\007\000\002\004" +
    "\005\000\002\004\005\000\002\004\005\000\002\004\005" +
    "\000\002\004\011\000\002\006\004\000\002\006\011\000" +
    "\002\006\011\000\002\006\011\000\002\007\011\000\002" +
    "\007\011\000\002\007\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\030\000\004\013\004\001\002\000\032\003\017\015" +
    "\021\020\010\021\022\022\013\026\011\027\012\031\015" +
    "\032\020\037\016\045\014\047\007\001\002\000\004\002" +
    "\006\001\002\000\004\002\001\001\002\000\004\014\ufff5" +
    "\001\002\000\004\014\ufff9\001\002\000\004\014\ufff6\001" +
    "\002\000\004\014\ufff8\001\002\000\004\014\ufffc\001\002" +
    "\000\004\063\030\001\002\000\004\014\uffff\001\002\000" +
    "\004\014\ufffa\001\002\000\004\014\ufff4\001\002\000\004" +
    "\063\025\001\002\000\004\014\ufffb\001\002\000\004\014" +
    "\ufff7\001\002\000\004\014\024\001\002\000\004\002\000" +
    "\001\002\000\004\072\026\001\002\000\004\064\027\001" +
    "\002\000\004\014\ufffe\001\002\000\004\072\031\001\002" +
    "\000\004\064\032\001\002\000\004\014\ufffd\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\030\000\004\002\004\001\001\000\004\005\022\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Analizador_sintactico$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Analizador_sintactico$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Analizador_sintactico$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



    //ESte es para llamar
    public static void print(Object s){
        String newOne = s.toString();
        System.out.println("print(\""+newOne+"\")");
    }
    /**
     * Método al que se llama automáticamente ante algún error sintactico.
     **/ 
    public void syntax_error(Symbol s){ 
            System.err.println("Error Sintáctico en la Línea " + (s.left) +" Columna "+s.right+ ". No se esperaba este componente: " +s.value+"."); 
    } 
    /**
     * Método al que se llama en el momento en que ya no es posible una recuperación de errores.
     **/ 
    public void unrecovered_syntax_error(Symbol s) throws java.lang.Exception{ 
            System.err.println("Error síntactico irrecuperable en la Línea " + (s.left)+ " Columna "+s.right+". Componente " + s.value + " no reconocido."); 
    }  


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$Analizador_sintactico$actions {
  private final Analizador_sintactico parser;

  /** Constructor */
  CUP$Analizador_sintactico$actions(Analizador_sintactico parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$Analizador_sintactico$do_action_part00000000(
    int                        CUP$Analizador_sintactico$act_num,
    java_cup.runtime.lr_parser CUP$Analizador_sintactico$parser,
    java.util.Stack            CUP$Analizador_sintactico$stack,
    int                        CUP$Analizador_sintactico$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Analizador_sintactico$result;

      /* select the action based on the action number */
      switch (CUP$Analizador_sintactico$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= INICIO EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-1)).value;
		RESULT = start_val;
              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-1)), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Analizador_sintactico$parser.done_parsing();
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // INICIO ::= res_INICIO BEGIN res_FIN 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("INICIO",0, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // BEGIN ::= res_INGRESAR 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("BEGIN",3, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // BEGIN ::= res_IMPRIMIR tk_PARIZQ tk_CADENA tk_PARDER 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-1)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-1)).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-1)).value;
		 print(a); 
              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("BEGIN",3, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-3)), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // BEGIN ::= res_IMPRIMIRNL tk_PARIZQ tk_CADENA tk_PARDER 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("BEGIN",3, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-3)), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // BEGIN ::= res_HACER 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("BEGIN",3, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // BEGIN ::= res_SI 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("BEGIN",3, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // BEGIN ::= res_MIENTRAS 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("BEGIN",3, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // BEGIN ::= res_PARA 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("BEGIN",3, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // BEGIN ::= res_REPETIR 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("BEGIN",3, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // BEGIN ::= res_SEGUN 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("BEGIN",3, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // BEGIN ::= res_METODO 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("BEGIN",3, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // BEGIN ::= res_FUNCION 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("BEGIN",3, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // BEGIN ::= error 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("BEGIN",3, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // I ::= res_INGRESAR IDENTIFICADOR res_COMO I_ tk_PTCOMA 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("I",1, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-4)), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // I_ ::= res_NUMERO res_CONVALOR ENTERO 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("I_",2, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // I_ ::= res_NUMERO res_CONVALOR DECIMAL 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("I_",2, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // I_ ::= res_CADENA res_CONVALOR tk_CADENA 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("I_",2, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // I_ ::= res_BOOLEAN res_CONVALOR res_VERDADERO 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("I_",2, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // I_ ::= res_INGRESAR IDENTIFICADOR res_COMO res_BOOLEAN res_CONVALOR res_VERDADERO tk_PTCOMA 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("I_",2, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-6)), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // LLAMA ::= LLAMA LLAMA_ 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("LLAMA",4, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-1)), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // LLAMA ::= res_INGRESAR IDENTIFICADOR res_COMO res_BOOLEAN res_CONVALOR res_VERDADERO tk_PTCOMA 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("LLAMA",4, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-6)), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // LLAMA ::= res_INGRESAR IDENTIFICADOR res_COMO res_CADENA res_CONVALOR tk_CADENA tk_PTCOMA 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("LLAMA",4, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-6)), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // LLAMA ::= res_INGRESAR IDENTIFICADOR res_COMO res_NUMERO res_CONVALOR ENTERO tk_PTCOMA 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("LLAMA",4, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-6)), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // LLAMA_ ::= res_INGRESAR IDENTIFICADOR res_COMO res_NUMERO res_CONVALOR ENTERO tk_PTCOMA 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("LLAMA_",5, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-6)), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // LLAMA_ ::= res_INGRESAR IDENTIFICADOR res_COMO res_CADENA res_CONVALOR tk_CADENA tk_PTCOMA 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("LLAMA_",5, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.elementAt(CUP$Analizador_sintactico$top-6)), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // LLAMA_ ::= error 
            {
              Object RESULT =null;

              CUP$Analizador_sintactico$result = parser.getSymbolFactory().newSymbol("LLAMA_",5, ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador_sintactico$stack.peek()), RESULT);
            }
          return CUP$Analizador_sintactico$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$Analizador_sintactico$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$Analizador_sintactico$do_action(
    int                        CUP$Analizador_sintactico$act_num,
    java_cup.runtime.lr_parser CUP$Analizador_sintactico$parser,
    java.util.Stack            CUP$Analizador_sintactico$stack,
    int                        CUP$Analizador_sintactico$top)
    throws java.lang.Exception
    {
              return CUP$Analizador_sintactico$do_action_part00000000(
                               CUP$Analizador_sintactico$act_num,
                               CUP$Analizador_sintactico$parser,
                               CUP$Analizador_sintactico$stack,
                               CUP$Analizador_sintactico$top);
    }
}

}
