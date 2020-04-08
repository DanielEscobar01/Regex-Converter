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
/**
 * Estructura de datos para modelar una transición de un autómata
 * @author danielescobar
 * @param <T>
 */
public class Transition<T> {
    
    //estado inicial de la transicion
    private State inicio;
    //estado final de las transiciones
    private State fin;
    //simbolo por el cual se realiza la transicion
    private T simbolo;
    
    /**
     * Constructor de una transicion
     * @param inicio State inicial
     * @param fin State final
     * @param simbolo simbolo string o character
     */
    public Transition(State inicio, State fin, T simbolo) {
        this.inicio = inicio;
        this.fin = fin;
        this.simbolo = simbolo;
    }
    
    
    /**
     * Accesor del estado inicial de la transicion
     * @return State
     */
    public State getInicio() {
        return inicio;
    }
    /**
     * Mutador del estado inicial de la transicion
     * @param inicio 
     */
    public void setInicio(State inicio) {
        this.inicio = inicio;
    }
    
    /**
     * Accesor del estado final de la transiciones
     * @return State
     */
    public State getFin() {
        return fin;
    }

    /**
     * Mutadro del estado final de la transicion
     * @param fin estado de final o aceptaion
     */
    public void setFin(State fin) {
        this.fin = fin;
    }
    /**
     * Obtener el simbolo de la transicion
     * @return String
     */
    public T getSimbolo() {
        return simbolo;
    }

    public String getFinal(){
        return fin.toString();
    }
    
    /**
     * Mutador del simbolo
     * @param simbolo simbolor string o character
     */
    public void setSimbolo(T simbolo) {
        this.simbolo = simbolo;
    }
    /**
     * Mostrar la transicion
     * @return String toString
     */
    @Override
    public String toString(){
        return fin.getId()+"";
    }
    

}

