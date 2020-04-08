/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SyntaxTree;



import java.util.ArrayList;
import java.util.Stack;


/**
 * Clase que construye el árbol sintáctico
 * @author Pablo
 * @param <T> Generic
 */
public class syntaxTree<T> {

    private treeNode<T> root;       //nodo raiz del arbol
    private treeNode<T> actual;     //un nodo actual, sirve para despues definir el raiz
    private final Stack pila;         //en realidad es una cola, para meter nodos
    private ArrayList arraytreeNodes;//se guardan todos los nodos creados
    
    
   /**
    * Constructor 
    * inicializa la raiz del arbol
    */
    public syntaxTree(){
        this.arraytreeNodes = new ArrayList();
        this.pila = new Stack();
        this.root= new treeNode("");
    }

   /**
    * Método que construye el árbol a partir de una cadena 
    * @param cadenaEnPrefix
    */
    public void buildTree(String cadenaEnPrefix){
        
        this.root = new treeNode(cadenaEnPrefix);
        pila.add(this.root);
        buildPostFixTree((treeNode<T>) this.root);
        this.root=this.actual;
        
        

        
    }
    
    /**
     * Método que crea las ramas del árbol recursivamente
     * @param nodo utiliza un nodo actual 
     */
    private void buildPostFixTree(treeNode<T> nodo){
        String texto_postfix = (String) nodo.getRegex();
        char letra_inicial = texto_postfix.charAt(0);
       // System.out.println("letra inicial " + letra_inicial);
        //verificar si es un símbolo. Si lo es poner de una vez en la rama
        if(letra_inicial!='*'&&letra_inicial!='|'&&letra_inicial!='.'){
            String sub_cadena = texto_postfix.substring(1);
            //System.out.println(sub_cadena);
            treeNode nuevo = new treeNode((sub_cadena));
            nuevo.setId(""+letra_inicial);
            nuevo.setIsLeaf(true);
            arraytreeNodes.add(nuevo);
            
            //nuevo.setIsLeaf(true);
           
            pila.remove(this.root);
            pila.add(nuevo);
            buildPostFixTree(nuevo);
           
           
        }
         else//verificar que no sea terminal
            
        {
            //es un operador

            //si es un operador unario (como *)
            if(letra_inicial == '*'){
                //obtener un operador
                //se le asigna el nombre al nodo principal
                String sub_cadena = texto_postfix.substring(1);
                //System.out.println(sub_cadena);
                treeNode nuevo = new treeNode(sub_cadena);
                nuevo.setId((T) (""+letra_inicial));
                
                treeNode nodoPila = (treeNode)pila.pop();
                nuevo.setIzquierda(nodoPila);
                arraytreeNodes.add(nuevo);
                
                /*String sub_cadena = texto_prefix.substring(1);//falta validar...
                //print("subcadena: "+sub_cadena);
                nodo.setIzquierda(new treeNode(obtener_operando(sub_cadena)));
                //para generar recursivamente el nodo*/
                pila.add(nuevo);
                buildPostFixTree(nuevo);
               
           
            }

            //si es un operador unario (como |, concat)
            else if(letra_inicial=='|'||letra_inicial=='.'){
                //obtener dos operadores

                //se le asigna el nombre al nodo principal
               // nodo.setId((T) (""+letra_inicial));
               
               
                String sub_cadena = texto_postfix.substring(1);
                //System.out.println(sub_cadena);
               /* String primer_operando = this.obtener_operando(sub_cadena);
                String segundo_operando = this.obtener_operando(sub_cadena.substring(primer_operando.length()));*/
                treeNode nuevo = new treeNode(sub_cadena);
                nuevo.setId(""+letra_inicial);
              
                nuevo.setDerecha((treeNode) pila.pop());
               
                //nodo.setIzquierda(new treeNode(primer_operando));
                //para generar recursivamente el nodo hijo izquierdo
               
                //buildPostFix(nuevo.getIzquierda());
                if (!pila.isEmpty())
                    nuevo.setIzquierda((treeNode)pila.pop());
                else
                    nuevo.setIzquierda(nodo);
                //el hijo izquierdo dejarlo vacío...
                //odo.setDerecha(new treeNode(segundo_operando));
                //para generar recursivamente el nodo hijo izquierdo
               // buildPostFix(nuevo.getDerecha());
                 pila.add(nuevo);
                 arraytreeNodes.add(nuevo);
                 this.actual = nuevo;
                 if (!sub_cadena.isEmpty())
                    buildPostFixTree(nuevo);
                
            }
        }
        
        
        
        
    }

    public treeNode<T> getRoot() {
        return root;
    }

    public void setRoot(treeNode<T> root) {
        this.root = root;
    }
    
    public treeNode<T> getResultado() {
        return actual;
    }

    public void setResultado(treeNode<T> resultado) {
        this.actual = resultado;
    }

    public ArrayList getArraytreeNodes() {
        return arraytreeNodes;
    }

    public void setArraytreeNodes(ArrayList arraytreeNodes) {
        this.arraytreeNodes = arraytreeNodes;
    } 
}