/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutomatonTools;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author danielescobar
 */
public class expressionConverter {
 
    private final Map<Character, Integer> precedenciaOperadores;

    
    public expressionConverter()
        {
		Map<Character, Integer> map = new HashMap<>();
		map.put('(', 1); // parentesis
		map.put('|', 2); // Union o or
		map.put('.', 3); // explicit concatenation operator
		map.put('?', 4); // | €
		map.put('*', 4); // kleene
		map.put('+', 4); // positivo
		precedenciaOperadores = Collections.unmodifiableMap(map);
              
	};
        
	/**
	 * Obtener la precedencia del caracter
	 * 
	 * @param c character
	 * @return corresponding precedence
	 */
	private Integer getPrecedencia(Character c) {
		Integer precedencia = precedenciaOperadores.get(c);
                //si obtiene un valor nulo retrona 6 por default
		return precedencia == null ? 6 : precedencia;
	}
    
    
    /**
	 * Convertir una expresión regular de notación infix a postfix.  
	 * 
	 * @param regex notacion infix 
	 * @return notacion postfix 
	 */
	public  String infixToPostfix(String regex) {
		String postfix = new String();
                //regex = abreviacionOr(regex);
                //regex = abreviacionAnd(regex);
		Stack<Character> stack = new Stack<>();

		String formattedRegEx = formatRegEx(regex);
                //System.out.println(formattedRegEx);
		for (Character c : formattedRegEx.toCharArray()) {
			switch (c) {
				case '(':
					stack.push(c);
					break;

				case ')':
					while (!stack.peek().equals('(')) {
						postfix += stack.pop();
					}
					stack.pop();
					break;

				default:
					while (stack.size() > 0) 
                                        {
						Character peekedChar = stack.peek();

						Integer peekedCharPrecedence = getPrecedencia(peekedChar);
						Integer currentCharPrecedence = getPrecedencia(c);

						if (peekedCharPrecedence >= currentCharPrecedence) 
                                                {
							postfix += stack.pop();
                                                       
						} 
                                                else 
                                                {
							break;
						}
					}
					stack.push(c);
					break;
			}

		}

		while (stack.size() > 0)
			postfix += stack.pop();

		return postfix;
	}
    
    
    /**
         * Insertar caracter en una posicion deseada
         * @param s string deseado
         * @param pos indice del caracter
         * @param ch caracter  o String deseado
         * @return nuevo string con el caracter deseado
         */
        private String insertCharAt(String s, int pos, Object ch){
            return s.substring(0,pos)+ch+s.substring(pos+1);
        }
    
    /**
         * Método para abreviar el operador de cerradura positiva
         * @param regex expresion regular (string)
         * @return expresion regular modificada sin el operador +
         */
        public String abreviaturaCerraduraPositiva(String regex){
            //sirve para buscar el '(' correcto cuando  hay () en medio
            // de la cerradura positiva
            int compare = 0; 
            
            for (int i = 0; i<regex.length();i++){
                 Character ch = regex.charAt(i);
                 
                if (ch.equals('+'))
                {
                    //si hay un ')' antes de un operador
                    //significa que hay que buscar el '(' correspondiente
                    if (regex.charAt(i-1) == ')'){
                        
                        int fixPosicion = i;
                        
                        while (fixPosicion != -1)
                        {
                            if (regex.charAt(fixPosicion)==')')
                            {
                               compare++;
                               
                            }
                            
                            if (regex.charAt(fixPosicion)=='(')
                            {
                                
                                compare--;
                                if (compare ==0)
                                    break;
                            }
                            
                            
                        fixPosicion--;
                        
                        }
                      
                        String regexAb = regex.substring(fixPosicion,i);
                        regex = insertCharAt(regex,i,regexAb+"*");
                        
                      
                    }
                    //si no hay parentesis, simplemente se inserta el caracter
                    else
                    {
                        regex = insertCharAt(regex,i,regex.charAt(i-1)+"*");
                    }
                    
                   
                }
                
            }
           
            //regex = balancearParentesis(regex);
            
            return regex;
        }
    
    
    public  String formatRegEx(String regex) {
                regex = regex.trim();
                regex = abreviaturaCerraduraPositiva(regex);
		String  regexExplicit = new String();
		List<Character> operadores = Arrays.asList('|', '?', '+', '*');
		List<Character> operadoresBinarios = Arrays.asList('|');
                
                
                //recorrer la cadena
		for (int i = 0; i < regex.length(); i++)
                {
                    Character c1 = regex.charAt(i);
                   
                    if (i + 1 < regex.length()) 
                    {
                        
                        Character c2 = regex.charAt(i + 1);
                        
                        regexExplicit += c1;
                        
                        //mientras la cadena no incluya operadores definidos, será una concatenación implicita
                        if (!c1.equals('(') && !c2.equals(')') && !operadores.contains(c2) && !operadoresBinarios.contains(c1))
                        {
                            regexExplicit += '.';
                           
                        }
                        
                    }
		}
		regexExplicit += regex.charAt(regex.length() - 1);
                

		return regexExplicit;
	}
        
}
