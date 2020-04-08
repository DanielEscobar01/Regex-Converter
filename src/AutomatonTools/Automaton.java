/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutomatonTools;

import InterfaceGui.GUI;
import java.util.ArrayList;
import java.util.HashSet;


/**
 *
 * @author danielescobar
 */
/**
 * Clase automata finito
 *
 * @author danielescobar
 */
public class Automaton {

    private int numberStates;

    //compuesto por un estado inicial
    private State inicial;
    //en general deberia ser un arreglo de conjuntos finales
    //pero de acuerdo al algoritmo de thomson, siempre 
    //se mantiene un unico estado de aceptacion/final
    private final ArrayList<State> aceptacion;
    //array de estados
    private final ArrayList<State> estados;
    // alfabeto del autómata, hash para no tener elementos repetidos
    private HashSet alfabeto;
    //atributo para saber si es Deterministico o No 
    private String tipo;
    
    /**
     * Constructor vacio
     */
    public Automaton() {
        this.estados = new ArrayList();
        this.aceptacion = new ArrayList();
        this.alfabeto = new HashSet();
        

    }

    public int numberOfStates() {
        System.out.println(this.numberStates);
        return this.estados.size();
    }

    /**
     * Accesor del estado inicial del autómata
     *
     * @return State
     */
    public State getStateInicial() {
        return inicial;
    }

    /**
     * Mutador del estado inicial del autómata
     *
     * @param inicial State inicial
     */
    public void setStateInicial(State inicial) {
        this.inicial = inicial;
    }

    /**
     * Accesor del estado de aceptacion o final del autómata
     *
     * @return State
     */
    public ArrayList<State> getStatesAceptacion() {
        return aceptacion;
    }

    /**
     * Mutador del estado final o aceptacion del autómata
     *
     * @param fin State final
     */
    public void addStatesAceptacion(State fin) {
        this.aceptacion.add(fin);
    }

    /**
     * Obtener los estados del autómata
     *
     * @return Array de States
     */
    public ArrayList<State> getStates() {
        return estados;
    }

    public State getStates(int index) {
        return estados.get(index);
    }

    /**
     * Agregar un estado al autómata
     *
     * @param estado estructura de estado
     */
    public void addStates(State estado) {
        this.estados.add(estado);
    }

    /**
     * Mostrar los atributos del autómata
     *
     * @return String
     */
    public HashSet getAlfabeto() {
        return alfabeto;
    }

    /**
     * Metodo para definir el alfabeto del automata a partir de la expresion
     * regular
     *
     * @param regex
     */
    public void createAlfabeto(String regex) {
        for (Character ch : regex.toCharArray()) {

            if (ch != '|' && ch != '.' && ch != '*'&& ch != GUI.EPSILON_CHAR) {
                this.alfabeto.add(Character.toString(ch));
            }
        }
    }

    public void setAlfabeto(HashSet alfabeto) {
        this.alfabeto = alfabeto;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public State getInicial() {
        return inicial;
    }

    public void setInicial(State inicial) {
        this.inicial = inicial;
    }

}
