/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGui;

import AutomatonTools.Automaton;
import AutomatonTools.DFA;
import AutomatonTools.State;
import AutomatonTools.Transition;
import AutomatonTools.expressionConverter;
import SyntaxTree.syntaxTree;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danielescobar
 */
public class GUI extends javax.swing.JFrame {
    public static String EPSILON = "ε";    
    public static char EPSILON_CHAR = EPSILON.charAt(0);
    Automaton automataMomentaneo;

  
    public GUI() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn = new javax.swing.JButton();
        regularExp = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        strEvaluate = new javax.swing.JTextField();
        evaluate = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        accepted = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.green);
        setResizable(false);

        btn.setBackground(new java.awt.Color(153, 255, 153));
        btn.setText("Create Automaton");
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMouseClicked(evt);
            }
        });

        regularExp.setText("(a|b)*abb");
        regularExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regularExpActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Regular Expression:");

        table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table);

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 3, 18)); // NOI18N
        jLabel2.setText("AUTOMATON CREATED BY REGULAR EXPRESSION.");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("String to evaluate:");

        evaluate.setBackground(new java.awt.Color(153, 255, 153));
        evaluate.setText("Evaluate String");
        evaluate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                evaluateMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Your string is:");

        jButton1.setBackground(new java.awt.Color(153, 255, 153));
        jButton1.setText("Refresh");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(regularExp, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(btn))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(accepted)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(strEvaluate, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(evaluate)
                                        .addGap(12, 12, 12))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel2)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(regularExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(strEvaluate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(evaluate))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accepted))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMouseClicked
       
       String b = regularExp.getText().toUpperCase();
       if(b.equals("")){//controlo que no se entre alguna expresion
       JOptionPane.showMessageDialog(null, "Enter an expression", "Error", JOptionPane.WARNING_MESSAGE);
       }else{
        boolean twoParts = doubleExpressions(b);
        System.out.println(twoParts);
        if(!twoParts){ 
        while (b.contains(")+") || b.contains(")*")) {
                while (b.contains(")+")) {
                    b = distribute(b, '+');
                }
                while (b.contains(")*")) {
                    b = distribute(b, '*');
                } 
        }}
        System.out.println(b);
        expressionConverter convert = new expressionConverter();
        b = convert.infixToPostfix(b);
        DFA AFD = new DFA();
        String c = b+"#.";
        syntaxTree syntaxTree = new syntaxTree(); 
        syntaxTree.buildTree(c); 
        AFD.creacionDirecta(syntaxTree);
        Automaton aFinal = AFD.getAfdDirecto();
        automataMomentaneo = AFD.getAfdDirecto();
        String columns[] = new String[aFinal.getAlfabeto().size() + 2]; //Creacion columnas + 1, 1. Label Ultima. Aceptacion
        columns[0] = "States/Input"; // Columna 1 Label
        columns[aFinal.getAlfabeto().size() + 1] = "Status"; // Columna ultima aceptacion
        Object[] alfabeto = aFinal.getAlfabeto().toArray();
        //alfabeto=(String[]) AFD.getAfdDirecto().getAlfabeto().toArray();
        for (int i = 0; i < columns.length - 2; i++) {
            columns[i + 1] = alfabeto[i].toString();
            System.out.println(columns[i]);
        } // Hasta aqui funciona - columns ya tiene los datos correctos
       
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        for (int i = 0; i < columns.length; i++) {
           modelo.addColumn(columns[i]);
        }// Ya agregue las columnas a la tabla.
       
        ArrayList<Transition> trans;
        int states = aFinal.getStates().size();
        for (int i = 0; i < states; i++) {
            String datas[] = new String[columns.length];
            datas[0] = String.valueOf(i);
            datas[datas.length-1] = "0";
            trans = aFinal.getStates(i).getTransitions();
            
            for (int j = 0; j < trans.size(); j++) {
                datas[j+1] = trans.get(j).getFin().getId().toString();
            }
            ArrayList<State> acept = aFinal.getStatesAceptacion();
            for (int t = 0; t < acept.size(); t++) {
                if (acept.get(t).getId().toString().equals(datas[0])) {
                    datas[columns.length - 1] = "1";
                } else {
                    if(datas[datas.length-1].equals("1")){}
                    else{
                    datas[columns.length - 1] = "0";
                    }
                }
            }
            modelo.addRow(datas);
        }
        btn.setVisible(false);
        }
    }//GEN-LAST:event_btnMouseClicked

    private void evaluateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_evaluateMouseClicked
        String a = strEvaluate.getText().toUpperCase();
        if (a.equals("") && (automataMomentaneo == null) || !a.equals("") && (automataMomentaneo == null)){
        JOptionPane.showMessageDialog(null, "First, you have to create an Automaton", "Error", JOptionPane.WARNING_MESSAGE);//Controla que no se halla creado el automata aun
        }else{
        boolean malo = false;
        boolean valido = false;
        for (int i = 0; i < a.length(); i++) {
            if (!insideAlphabet(a.charAt(i))) {
                malo = true;
            }
        }
        if (!malo) {
            State inicio = automataMomentaneo.getStateInicial();
            State last = inicio;
            System.out.println(a);
            for (int i = 0; i < a.length(); i++) {
                Character x = a.charAt(i);
                last = last.getTransitionWith(x);
                System.out.println("Voy en estado" + last.getId().toString());
            }
            String idLast = last.getId().toString();
            for (int i = 0; i < automataMomentaneo.getStatesAceptacion().size(); i++) {
                System.out.println(automataMomentaneo.getStatesAceptacion().get(i).getId().toString() + " contra " + idLast);
                if (Integer.parseInt(idLast) == Integer.parseInt(automataMomentaneo.getStatesAceptacion().get(i).getId().toString())) {
                    valido = true;
                }
            }
        }
        if (valido) {
            accepted.setText("Valid string");
        } else {
            accepted.setText("Invalid string");
        }
        }
    }//GEN-LAST:event_evaluateMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        regularExp.setText("");
        table.setModel(new DefaultTableModel());
        automataMomentaneo = null;
        btn.setVisible(true);
        accepted.setText("");
        strEvaluate.setText("");
    }//GEN-LAST:event_jButton1MouseClicked

    private void regularExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regularExpActionPerformed
        // TODO add your handling code here:
        System.out.println("aaa");
    }//GEN-LAST:event_regularExpActionPerformed

    // Returns True if a Character is inside the Alphabet
    public boolean insideAlphabet(Character a) {
        boolean status = false;
        Object[] alfabeto = automataMomentaneo.getAlfabeto().toArray();
        Character[] alfabetoo = new Character[automataMomentaneo.getAlfabeto().toArray().length];
        for (int i = 0; i < alfabeto.length; i++) {
            String auxiliar;
            Character c;
            auxiliar = (String) alfabeto[i];
            c = auxiliar.charAt(0);
            alfabetoo[i] = c;
        }
        for (int i = 0; i < alfabetoo.length; i++) {
            int compareOneTwo = Character.compare(alfabetoo[i], a);
            if (compareOneTwo == 0) {
                status = true;
                return status;
            }
        }
        return status;
    }
    
    // Metodo para controlar el ingreso de expresiones en las cuales una parte de la expresion se componga de dos o mas caracteres. 
    // Ejemplos : (00|11)* , (go|goto|too|on)* , ((1|01)*|1)+ ...
    // Si se compone de dos caracteres como es el caso de (01) en la expresion ((1|01)*|1)+ retornara falso puesto que solo hay un simbolo compuesto de dos o mas caracteres en toda la expresion.
    // En los demas ejemplos hay mas de un simbolo compuesto de dos o mas caracteres caso en el cual el metodo retornara verdadero.
    public static Boolean doubleExpressions(String b){
        
        
        int plus = 0;
        int local = 0;
        if(b.contains("|")){
        for(int i = 0;i< b.length();i++){
            if(b.charAt(i)== '(')  continue;
            if(b.charAt(i) != '(' && b.charAt(i) != '+' && b.charAt(i) != '*' && b.charAt(i)!= '|' && b.charAt(i)!= ')'){
            plus++;
            }
            //System.out.println("La posicion es  "+i+"   local es "+local+"   Plus es  "+plus);
            if(b.charAt(i)== ')') plus = 0;
            if(b.charAt(i)=='|') plus = 0;
            if(plus == 2) local++;
        }
        
        if (plus >= 2 && !b.contains(")+"))  return true; 
        if (local >= 2 ) return true;
        }
        
        
        return false;
    
 
    }
    
    
    
    //Metodo para dsitribuir los operadores + y * para determinadas expresiones que lo requieren, estas son las que tengan combinacion de operadores (*+)
    //Ejemplos : (1*01*)+ , ((1|01)*|1)+ ...
    //No es necesario este metodo cuando NO HAY combinacion de operadores (+*) por Ejemplo: (00|11)* , (a|b)*abb, (0|1)* , (go|goto|too|on)*ontoo  
    public static String distribute(String b, char w) {
        int pos = -1;
        char z;
        if (w == '+') {
            z = '*';
        } else {
            z = '+';
        }
        int pos2 = -1;
        int paren = 1;
        char v[] = b.toCharArray();
        for (int i = 0; i < v.length; i++) {
            if (v[i] == ')') {
                if (v[i + 1] == w) {
                    pos = i;
                    i = v.length;
                }
            }
        }
        for (int i = pos - 1; i >= 0; i--) {
            switch (v[i]) {
                case ')':
                    paren++;
                    break;
                case '(':
                    paren--;
                    if (paren == 0) {
                        pos2 = i;
                    }
                    break;
            }
        }
        int ast = 0;
        if (pos2 != -1) {
            for (int i = pos2 + 1; i < pos; i++) {
                if (v[i] != '|' && v[i] != '.' && v[i] != '(' && v[i] != ')') {
                    ast++;
                }
            }
        }
        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < v.length ; i++) {
            if (i > pos2 && i < pos && v[i] != '|' && v[i] != '.' && v[i] != '(' && v[i] != ')') {
                q.add(v[i]);
                if (v[i + 1] != z) {
                    q.add(w);
                }
            } else {
                if (i != pos + 1) {
                    if (v[i] != '.') {
                        q.add(v[i]);
                    }
                }
            }
        }
        char v2[] = new char[v.length + ast];
        int i = 0;
        while (!q.isEmpty()) {
            char s = q.remove();
            v2[i] = s;
            i++;
        }
        b = new String(v2);
        
        return b;
    }

    public static void main(String args[]) {
    
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accepted;
    private javax.swing.JButton btn;
    private javax.swing.JButton evaluate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField regularExp;
    private javax.swing.JTextField strEvaluate;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
