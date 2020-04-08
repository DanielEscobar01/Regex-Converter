/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SyntaxTree;



/**
 *
 * @author danielescobar
 */
public class treeNode<T> implements Comparable<treeNode>{
    
    private treeNode izquierda, derecha;
    private boolean isLeaf;
    private T id;
    private T regex;
    private int numerotreeNode;
   
    public treeNode(T regex) {
        this.regex = regex;
        this.izquierda= new treeNode();
        this.derecha = new treeNode();
        
        
    }

    public treeNode(){
        
        
    }
    public treeNode getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(treeNode izquierda) {
        this.izquierda = izquierda;
    }

    public treeNode getDerecha() {
        return derecha;
    }

    public void setDerecha(treeNode derecha) {
        this.derecha = derecha;
    }

    public boolean isIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public T getRegex() {
        return regex;
    }

    public void setRegex(T regex) {
        this.regex = regex;
    }

    public int getNumerotreeNode() {
        return numerotreeNode;
    }

    public void setNumerotreeNode(int numerotreeNode) {
        this.numerotreeNode = numerotreeNode;
    }
    
    

    @Override
    public String toString() {
        String regexd = ""+numerotreeNode;
        return regexd;
    }

    
    public String preOrder()
    {
        String res = "";
         
            if (id!=null)
                res += this.id;
            if (izquierda.getId()!=null)
                res += this.izquierda.preOrder();
           
            if (derecha.getId()!=null)
                res += this.derecha.preOrder();
           
            return res;
     
    }
   
    
    public String postOrder() {

        String res="";

        if(this.izquierda.getId()!=null)
            res+=""+this.izquierda.postOrder();
        
        if(this.id!=null)
            res+=this.id+"";
        
        if(this.derecha.getId()!=null)
            res+=this.derecha.postOrder()+"";
        
        return res;
    }


    @Override
    public int compareTo(treeNode o) {
        return Integer.compare(numerotreeNode, o.getNumerotreeNode());
    }

   
    
    

}
