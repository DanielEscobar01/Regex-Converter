/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutomatonTools;

import java.util.ArrayList;

/**
 * Clase para simular un estado de un autómata
 *
 * @author danielescobar
 */
public class State<T> {

    //atributos
    //identificador del estado
    private T id;
    //transiciones del estado
    private ArrayList<Transition> transiciones = new ArrayList();

    /**
     * Constructor
     *
     * @param id identificador
     * @param transiciones transiciones
     */
    public State(T id, ArrayList<Transition> transiciones) {
        this.id = id;
        this.transiciones = transiciones;
    }

    public State getTransitionWith(Character c) {
        ArrayList<Transition> trans = getTransitions();
        for (int j = 0; j < trans.size(); j++) {
            String auxiliar;
            Character ch;
            auxiliar = trans.get(j).getSimbolo().toString();
            ch = auxiliar.charAt(0);
            int compareOneTwo = Character.compare(c, ch);
            if (compareOneTwo == 0) {
                return trans.get(j).getFin();
            }
        }
        return null;
    }

    /**
     * Constructor de un estado con solo el identificador
     *
     * @param identificador identificador
     */
    public State(T identificador) {
        this.id = identificador;

    }

    public T fin(int i) {
        return (T) transiciones.get(i).getFin().getId();
    }

    /**
     * Accesor del atributo identificador
     *
     * @return id
     */
    public T getId() {
        return id;
    }

    /**
     * Mutador del atributo identificador
     *
     * @param id identificador
     */
    public void setId(T id) {
        this.id = id;
    }

    /**
     * Accesor del atributo transiciones
     *
     * @return array de transiciones
     */
    public ArrayList<Transition> getTransitions() {

        return transiciones;
    }

    /**
     * Agregar transiciones al estado
     *
     * @param tran transicion
     */
    public void setTransitions(Transition tran) {
        this.transiciones.add(tran);
    }

    /**
     * Mostrar el estado
     *
     * @return String
     */
    @Override
    public String toString() {
        return this.id.toString();
    }

}
