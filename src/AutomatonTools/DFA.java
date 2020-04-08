/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutomatonTools;

/**
 *
 * @author danielescobar
 */

import InterfaceGui.GUI;
import SyntaxTree.syntaxTree;
import SyntaxTree.treeNode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

/**
 * Clase que construye un AFD a partir de un AF
 * @author danielescobar
 */
public class DFA {
    
    private Automaton afd;
    private Automaton afdDirecto;
    private Automaton afdMinimo;
    private final HashMap resultadoFollowPos;
  
    
    public DFA(){
        this.resultadoFollowPos = new HashMap();
        afd = new Automaton();
    }
    
    
    /**
     * Método general que crea el AFD de forma directa
     * @param arbolSintactico 
     */
    public void creacionDirecta(syntaxTree arbolSintactico){
        
        //colocar numeracion a los nodos hojas
        generarNumeraciontreeNodes(arbolSintactico);
        
        ArrayList<treeNode> arraytreeNodes = arbolSintactico.getArraytreeNodes();      
        
        for (int i = 0;i<arraytreeNodes.size();i++){
            if (arraytreeNodes.get(i).getId().equals("*")||arraytreeNodes.get(i).getId().equals("."))
                followPos(arraytreeNodes.get(i));
        }
        crearStates(arbolSintactico);  
    }
    
    /**
     * Método para verificar si el nodo puede generar el simbolo de operacion
     * @param expresion
     * @return true si lo puede generar, false si no
     */
    public boolean nullable(treeNode expresion){
        if (expresion.getId().equals(GUI.EPSILON))
            return true;
        //cerradura de kleene siempre retorna verdadero
        if (expresion.getId().equals("*"))
            return true;
        //cuando es or, se verifica cada una las hojas del nodo
        else if (expresion.getId().equals("|"))
            return (nullable(expresion.getIzquierda()))||(nullable(expresion.getDerecha()));
        //cuando es concatenacion solo si los dos nodos son true, devuelve true
        else if (expresion.getId().equals("."))
            return (nullable(expresion.getIzquierda()))&&(nullable(expresion.getDerecha()));
          //verificar si es una hoja terminal
        else if (expresion.isIsLeaf()==true)
            return false;
      
        
        //valor por default a regresar
        return false;
        
    }
    
    /**
     * Devuelve  una lista de elementos que contiene la primera posicion del nodo
     * @param nodoEval
     * @return ArrayList con el resultado
     */
    public TreeSet firstPos(treeNode nodoEval){
        TreeSet resultado = new TreeSet();
        //regresar i en caso de que sea epsilon, regresa vacio
        if (nodoEval.getId().equals(GUI.EPSILON))
            return resultado;
        //en caso de que sea una hoja regresa el nodo i en el arreglo
        if (nodoEval.isIsLeaf()){
            resultado.add(nodoEval);
            //return resultado;
        }
        //en caso del OR hace la union de firstPos de los nodos hijos
        else if (nodoEval.getId().equals("|")){
           resultado.addAll(firstPos(nodoEval.getIzquierda()));
           resultado.addAll(firstPos(nodoEval.getDerecha()));
           return resultado;
           
        }
        /*en el caso de la concatenacion primero revisa el nullable y
        despues realiza la union */
        else if (nodoEval.getId().equals(".")){
            if (nullable(nodoEval.getIzquierda())){
                resultado.addAll(firstPos(nodoEval.getIzquierda()));
                resultado.addAll(firstPos(nodoEval.getDerecha()));
            }
            else{
                resultado.addAll(firstPos(nodoEval.getIzquierda()));
            }
        }
        //en el caso de la cerradura de kleene regresa firstPos del nodo hijo izquierdo
        else if (nodoEval.getId().equals("*")){
            resultado.addAll(firstPos(nodoEval.getIzquierda()));
        }
        
        return resultado;
    }
    
    /**
     * Metodo que retorna una lista con los elementos de la operacion
     * last pos del nodo especificado
     * @param nodoEval
     * @return ArrayList con el resultado
     */
    public ArrayList lastPos(treeNode nodoEval){
        ArrayList resultado = new ArrayList();
        
        if (nodoEval.getId().equals(GUI.EPSILON))
            return resultado;
        
        if (nodoEval.isIsLeaf()){
           resultado.add(nodoEval);
           return resultado;
        }
        else if (nodoEval.getId().equals("*")){
            resultado.addAll(lastPos(nodoEval.getIzquierda()));
        }
        else if (nodoEval.getId().equals("|")){
            resultado.addAll(lastPos(nodoEval.getIzquierda()));
            resultado.addAll(lastPos(nodoEval.getDerecha()));
        }
        else if (nodoEval.getId().equals(".")){
            if (nullable(nodoEval.getDerecha())){
                
                resultado.addAll(lastPos(nodoEval.getIzquierda()));
                resultado.addAll(lastPos(nodoEval.getDerecha()));
            }
            else{
                resultado.addAll(lastPos(nodoEval.getDerecha()));
            }
        }
        
        return resultado;
    }
    
    /**
     * metodo para calcular el follow pos de cada hoja terminal del árbol
     * @param nodoEval
     * 
     */
    public void followPos(treeNode nodoEval){
        //por definicion follow pos aplica para cerradura de kleene y concatenacion
       
        
        //si es cerradura de kleen
        if (nodoEval.getId().equals("*")){
            
            //según el algoritmo primero verificamos el lastPos
            ArrayList<treeNode> lastPosition = lastPos(nodoEval);
            //el follow pos del lastPos incluye todo lo que este en el first pos
            //del kleen
            
            //por lo tanto se necesita el firstPos del kleen
            TreeSet<treeNode> firstPosition = firstPos(nodoEval);
              
            for(int j=0;j<lastPosition.size();j++){
                int numero = lastPosition.get(j).getNumerotreeNode();

                if(resultadoFollowPos.containsKey(numero)){
                    //si ya la tiene, es agregar
                    firstPosition.addAll((Collection)resultadoFollowPos.get(numero));
                    //this.Sort_Set((LinkedList<Integer>)SiguientePos.get(numero));
                }
               
                    //si no la tiene, es poner
                    resultadoFollowPos.put(numero,firstPosition);
                   
            }
        }
        //si es concatenación
        else if (nodoEval.getId().equals(".")){
            /*según el algoritmo el follow pos del cada posicion del last pos
            del hijo izquierdo debe incluir el el first pos del hijo derecho*/
            
            //obtener el lastPos del hijo izquierdo
            ArrayList<treeNode> lastPosition = lastPos(nodoEval.getIzquierda());
            //obtener el fistPos del lado derecho
            TreeSet<treeNode> firstPosition = firstPos(nodoEval.getDerecha());
            
            //usamos el last pos del hijo izquierdo 
            for (int i = 0;i<lastPosition.size();i++){
                int numero = (int) lastPosition.get(i).getNumerotreeNode();
                //le agregamos el first pos del hijo derecho [merge si ya existe]
                if (resultadoFollowPos.containsKey(numero)){
                    firstPosition.addAll((Collection) resultadoFollowPos.get(numero));//merge 
                }
                resultadoFollowPos.put(numero, firstPosition);
                firstPosition = firstPos(nodoEval.getDerecha());
            }
            
        }
        
       
    }
    
    /**
     * Método para numerar los nodos hoja del árbol sintáctico
     * @param arbol 
     */
    private void generarNumeraciontreeNodes(syntaxTree arbol){
        ArrayList<treeNode> arraytreeNodes = arbol.getArraytreeNodes();
        int index = 1;
        for (int i = 0 ;i<arraytreeNodes.size();i++){
            if (arraytreeNodes.get(i).isIsLeaf()){
                arraytreeNodes.get(i).setNumerotreeNode(index);
                index++;
            }
        }
        arbol.setArraytreeNodes(arraytreeNodes);
        
    }
    
    /**
     * Método que crea el nuevo automata a partir de follow pos
     * @param arbolSintactico 
     */
     public void crearStates(syntaxTree arbolSintactico){
        Automaton afd_result = new Automaton();
        afd_result.setTipo("AFD DIRECTO");
        
        definirAlfabeto(afd_result, arbolSintactico);
        //el estado inicial se crear a partir del first pos de la raiz
        State inicial = new State(0);
        TreeSet<treeNode> resultadoInicial = firstPos(arbolSintactico.getRoot());
        afd_result.setStateInicial(inicial);
        afd_result.addStates(inicial);
        
        //variable para marcar los estados ya creados
        ArrayList<ArrayList<TreeSet>> estadosCreados = new ArrayList();
        //se convierte el resultado del firstPos a arrayList
        ArrayList conversionInicial = new ArrayList(resultadoInicial);
        
        estadosCreados.add(conversionInicial);
        
         for (treeNode temp: (ArrayList<treeNode>)conversionInicial){
            if (temp.getId().equals("#"))
                afd_result.addStatesAceptacion(inicial);
        }
        
        int indexEstadoInicio=0;
        int indexEstados=1;
        //La cola sirve para evaluar los nodos nuevos creados
        Queue<ArrayList> cola = new LinkedList();
        cola.add(conversionInicial);
        
        while(!cola.isEmpty()){
            
            //se evalua el arreglo de nodos
            ArrayList<treeNode> actual = cola.poll();
            boolean control = true;
            for (String letra: (HashSet<String>)afd_result.getAlfabeto()){
                
                
                
                //arreglo temporal para hacer merge del resultado del followPos
                ArrayList temporal = new ArrayList();
                
                for (treeNode n: actual){
                    if (n.getId().equals(letra)){
                        temporal.addAll((TreeSet<treeNode>) resultadoFollowPos.get(n.getNumerotreeNode()));

                    }
                   
                    
                }
                if (control){
                //termina el merge
               // System.out.println(estadosCreados);
                //System.out.println(temporal);
                //si el resultado del merge no existe, se crea un nuevo estao
                if (!estadosCreados.contains(temporal)){

                    State siguiente = new State(indexEstados);
                    indexEstados++;
                    State estadoAnterior = afd_result.getStates(indexEstadoInicio);
                    
                    estadoAnterior.setTransitions(new Transition(estadoAnterior,siguiente,letra));
                    afd_result.addStates(siguiente);

                    cola.add(temporal);
                    estadosCreados.add(temporal);
                   
                    //verificar si tiene el # que define el estado de aceptacion
                    for (treeNode temp: (ArrayList<treeNode>)temporal){
                        if (temp.getId().equals("#")&&!afd_result.getStatesAceptacion().contains(siguiente))
                            afd_result.addStatesAceptacion(siguiente);
                    }
                }
                else{//si ya existe, se procede a poner transiciones
                   
                    State estadoAnterior = afd_result.getStates(indexEstadoInicio);
                    State estadoSiguiente = afd_result.getStates(estadosCreados.indexOf(temporal));
                    estadoAnterior.setTransitions(new Transition(estadoAnterior,estadoSiguiente,letra));
                }
                }
           //  System.out.println(afd_result);   
            }
            indexEstadoInicio++;
            
        }
        
        //afd_result = quitarEstadosTrampa(afd_result);
        
        System.out.println(afd_result);
        this.afdDirecto=afd_result;
        
    }
    
   
    /**
     * Método para definir el alfabeto del automata a partir del árbol sináctico
     * @param afd
     * @param arbol 
     */
    public void definirAlfabeto(Automaton afd, syntaxTree arbol){
      HashSet alfabeto = new HashSet();
      String expresion = arbol.getRoot().postOrder();
      for (Character ch: expresion.toCharArray()){
          if (ch!='*'&&ch!='.'&&ch!='|'&&ch!='#'&&ch!=GUI.EPSILON_CHAR){
              alfabeto.add(Character.toString(ch));
          }
      }
      afd.setAlfabeto(alfabeto);

  }
    
     /**
     * Retornar el AFD creado
     * @return Autoamta generado
     */
    public Automaton getAfd() {
        return afd;
    }
    
    /**
     * Retornar el AFD Directo
     * @return tipo Automaton
     */
    public Automaton getAfdDirecto(){
        return this.afdDirecto;
    }
    
   
    
  

}