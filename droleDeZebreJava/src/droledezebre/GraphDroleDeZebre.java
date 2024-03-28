/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package droledezebre;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;

/**
 *
 * @author mae
 */
public class GraphDroleDeZebre extends javax.swing.JFrame {

    private static JLabel[][] cases = new JLabel[7][8];
    private static JButton[] mainJoueurImgPion = new JButton[5];
    private static JLabel[] mainJoueurValeur = new JLabel[5];
    private static Jeu jeu1 = new Jeu();
    public static int choixCarte;// se récupère via une pop up choix carte 

    /**
     * Creates new form GraphDroleDeZebre
     */
    public GraphDroleDeZebre() {
        initComponents();
        
        JLayeredPane fond = getLayeredPane();
        fond.setSize(1400, 1300);
       
        JLabel fondLabel = new JLabel();
        fondLabel.setIcon(new ImageIcon(".\\images\\img\\fond.jpg"));
        fondLabel.setBounds(0, 0, fond.getWidth(),fond.getHeight());
        JLabel platLabel = new JLabel();
        platLabel.setIcon(new ImageIcon(".\\images\\img\\plateau1.jpg"));
        platLabel.setBounds(400, 200, 600, 600);

        
        fond.setLayer(fondLabel, 1);
        fond.add(fondLabel, Integer.valueOf(1));
        fond.setLayer(platLabel, 2);
        fond.add(platLabel, Integer.valueOf(2));
        
        fond.setVisible(true);

        int Horiz, Vert;
        Horiz = 410;
        Vert = 275;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) { // on créé notre plateau

                cases[i][j] = new JLabel();

                cases[i][j].setLocation(Horiz, Vert);// on pose le Jlabel à 300px à l'horizontal et à 300 px à la verticale et on avance de 70 px à chaque label vers la droite 

                cases[i][j].setSize(65, 65);

                Horiz = Horiz + 65;

                if (j == 7) { // des qu'on arrive en bout de ligne on descend de 70 px aussi pour rester proportionnel
                    Vert = Vert + 65;
                    Horiz = 410;
                }
                cases[i][j].setVerticalAlignment(SwingConstants.CENTER);// affiche le texte au milieu de la case 
                cases[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                // code inspiré de https://stackoverflow.com/questions/39798401/how-to-make-a-border-fit-around-a-jlabel
                cases[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                // on change la bordure en noir avec la méthode setBorder de la classe JComponent qui est extends par la classe JLabel 
                fond.setLayer(cases[i][j], 3);
                fond.add(cases[i][j], 3);
                fond.repaint();
            }

        }
        cases[1][1].setIcon(new ImageIcon(".\\images\\img\\r_cro.gif"));
        Horiz = 400;
        Vert = 130;
        for (int k = 0; k < 5; k++) {
            this.mainJoueurImgPion[k] = new JButton();
            this.mainJoueurImgPion[k].setLocation(Horiz, Vert);
            this.mainJoueurImgPion[k].setSize(65, 65);

            Horiz = Horiz + 65;

            this.mainJoueurImgPion[k].setVerticalAlignment(SwingConstants.CENTER);// affiche le texte au milieu de la case 
            this.mainJoueurImgPion[k].setHorizontalAlignment(SwingConstants.CENTER);
            // code inspiré de https://stackoverflow.com/questions/39798401/how-to-make-a-border-fit-around-a-jlabel
            this.mainJoueurImgPion[k].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
            // on change la bordure en noir avec la méthode setBorder de la classe JComponent qui est extends par la classe JLabel 
            fond.setLayer(this.mainJoueurImgPion[k], 3);
            fond.add(this.mainJoueurImgPion[k], 3);
            fond.repaint();
        }

        Horiz = 400;
        Vert = 195;
        for (int k = 0; k < 5; k++) {
            mainJoueurValeur[k] = new JLabel();
            mainJoueurValeur[k].setLocation(Horiz, Vert);
            mainJoueurValeur[k].setSize(65, 65);
           
            Horiz = Horiz + 65;
            
            mainJoueurValeur[k].setVerticalAlignment(SwingConstants.CENTER);// affiche le texte au milieu de la case 
            mainJoueurValeur[k].setHorizontalAlignment(SwingConstants.CENTER);
            // code inspiré de https://stackoverflow.com/questions/39798401/how-to-make-a-border-fit-around-a-jlabel
            mainJoueurValeur[k].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
            // on change la bordure en noir avec la méthode setBorder de la classe JComponent qui est extends par la classe JLabel 
            fond.setLayer(mainJoueurValeur[k], 3);
            fond.add(mainJoueurValeur[k], 3);
            fond.repaint();
           
        }
        
       
        menuDebut.setSize(fond.getWidth(), fond.getHeight());
        menuDebut.setVisible(true);
        menuDebut.setAlwaysOnTop(true);

    }

     /*public void initJeuInterface(int choixCarte) {//les pseudos se récupèrent directement dans le liste de joueurs de jeu des qu'on les demande
        jeu1.getPlateauDejeu().init(choixCarte);
        Random ra = new Random();//mélange les deux joueurs: premier tour aléatoire 
        int place = ra.nextInt(1) + 1;
        if (place == 2) {
            Joueur temp = jeu1.getListJoueur().get(2);
            jeu1.getListJoueur().add(1, jeu1.getListJoueur().get(1));
            jeu1.getListJoueur().add(0, temp);
        }
        // jeu1.getPlateauDejeu().premierePosImpala(jeu1);
        System.out.println(jeu1.getListJoueur().get(0).getPseudo());
        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0:
                    mainJoueurImgPion[0].setIcon(new ImageIcon(".\\images\\img\\" + jeu1.getListJoueur().get(0).getCouleur() + "_gaz.gif"));
                    break;
                case 1:
                    mainJoueurImgPion[1].setIcon(new ImageIcon(".\\images\\img\\" + jeu1.getListJoueur().get(0).getCouleur() + "_zeb.gif"));
                    break;
                case 2:
                    mainJoueurImgPion[2].setIcon(new ImageIcon(".\\images\\img\\" + jeu1.getListJoueur().get(0).getCouleur() + "_lio.gif"));
                    break;
                case 3:
                    mainJoueurImgPion[3].setIcon(new ImageIcon(".\\images\\img\\" + jeu1.getListJoueur().get(0).getCouleur() + "_ele.gif"));
                    break;
                case 4:
                    mainJoueurImgPion[4].setIcon(new ImageIcon(".\\images\\img\\" + jeu1.getListJoueur().get(0).getCouleur() + "_cro.gif"));
                    break;
            }
        }
    }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuDebut = new javax.swing.JFrame();

        javax.swing.GroupLayout menuDebutLayout = new javax.swing.GroupLayout(menuDebut.getContentPane());
        menuDebut.getContentPane().setLayout(menuDebutLayout);
        menuDebutLayout.setHorizontalGroup(
            menuDebutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        menuDebutLayout.setVerticalGroup(
            menuDebutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1450, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1105, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GraphDroleDeZebre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphDroleDeZebre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphDroleDeZebre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphDroleDeZebre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* GraphDroleDeZebre graph = new GraphDroleDeZebre();
       
        
        graph.initJeuInterface(1);*/
        
        /* Create and display the form */
        
        System.out.println("TEST ");
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphDroleDeZebre().setVisible(true);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame menuDebut;
    // End of variables declaration//GEN-END:variables
}
