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
        fondLabel.setBounds(0, 0, fond.getWidth(), fond.getHeight());
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
        newGamebutton = new javax.swing.JButton();
        frameChoixCarte = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonCarte1 = new javax.swing.JButton();
        buttonCarte2 = new javax.swing.JButton();
        choixNom = new javax.swing.JFrame();
        pseudo1 = new javax.swing.JTextField();
        pseudo2 = new javax.swing.JTextField();
        soumettrePseudoButton = new javax.swing.JButton();

        newGamebutton.setText("newGame");
        newGamebutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newGamebuttonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menuDebutLayout = new javax.swing.GroupLayout(menuDebut.getContentPane());
        menuDebut.getContentPane().setLayout(menuDebutLayout);
        menuDebutLayout.setHorizontalGroup(
            menuDebutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuDebutLayout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(newGamebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
        );
        menuDebutLayout.setVerticalGroup(
            menuDebutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuDebutLayout.createSequentialGroup()
                .addContainerGap(189, Short.MAX_VALUE)
                .addComponent(newGamebutton)
                .addGap(88, 88, 88))
        );

        jLabel1.setText("carte2");

        jLabel2.setText("carte1");

        buttonCarte1.setText("buttonCarte1");
        buttonCarte1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCarte1MouseClicked(evt);
            }
        });
        buttonCarte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCarte1ActionPerformed(evt);
            }
        });

        buttonCarte2.setText("buttonCarte2");
        buttonCarte2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCarte2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout frameChoixCarteLayout = new javax.swing.GroupLayout(frameChoixCarte.getContentPane());
        frameChoixCarte.getContentPane().setLayout(frameChoixCarteLayout);
        frameChoixCarteLayout.setHorizontalGroup(
            frameChoixCarteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameChoixCarteLayout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(frameChoixCarteLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(buttonCarte1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonCarte2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        frameChoixCarteLayout.setVerticalGroup(
            frameChoixCarteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameChoixCarteLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(frameChoixCarteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameChoixCarteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCarte1)
                    .addComponent(buttonCarte2))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pseudo1.setText("pseudo1");

        pseudo2.setText("pseudo2");

        soumettrePseudoButton.setText("submitt");
        soumettrePseudoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                soumettrePseudoButtonMouseClicked(evt);
            }
        });
        soumettrePseudoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soumettrePseudoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout choixNomLayout = new javax.swing.GroupLayout(choixNom.getContentPane());
        choixNom.getContentPane().setLayout(choixNomLayout);
        choixNomLayout.setHorizontalGroup(
            choixNomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(choixNomLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(pseudo1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addComponent(pseudo2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
            .addGroup(choixNomLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(soumettrePseudoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        choixNomLayout.setVerticalGroup(
            choixNomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(choixNomLayout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(choixNomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pseudo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pseudo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addComponent(soumettrePseudoButton)
                .addContainerGap(94, Short.MAX_VALUE))
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

    private void newGamebuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newGamebuttonMouseClicked
        // TODO add your handling code here:
        menuDebut.setAlwaysOnTop(false);
        menuDebut.setVisible(false);
        frameChoixCarte.setVisible(true);
        frameChoixCarte.setSize(menuDebut.getWidth(), menuDebut.getHeight());
        frameChoixCarte.setAlwaysOnTop(true);
    }//GEN-LAST:event_newGamebuttonMouseClicked

    private void buttonCarte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCarte1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCarte1ActionPerformed

    private void buttonCarte1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCarte1MouseClicked
        // TODO add your handling code here:
        choixCarte = 1;
        frameChoixCarte.setAlwaysOnTop(false);
        frameChoixCarte.setVisible(false);
        choixNom.setVisible(true);
        choixNom.setSize(menuDebut.getWidth(), menuDebut.getHeight());
        choixNom.setAlwaysOnTop(true);

    }//GEN-LAST:event_buttonCarte1MouseClicked

    private void buttonCarte2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCarte2MouseClicked
        // TODO add your handling code here:
        choixCarte = 2;
        frameChoixCarte.setAlwaysOnTop(false);
        frameChoixCarte.setVisible(false);
        choixNom.setVisible(true);
        choixNom.setSize(menuDebut.getWidth(), menuDebut.getHeight());
        choixNom.setAlwaysOnTop(true);
    }//GEN-LAST:event_buttonCarte2MouseClicked

    private void soumettrePseudoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soumettrePseudoButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soumettrePseudoButtonActionPerformed

    private void soumettrePseudoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_soumettrePseudoButtonMouseClicked
        // TODO add your handling code here:
        jeu1.getListJoueur().add(new Joueur("v", pseudo1.getText()));
        jeu1.getListJoueur().add(new Joueur("r", pseudo2.getText()));
        for (int i = 0; i < 2; i++) {
            jeu1.getListJoueur().get(i).setMain(jeu1.getListJoueur().get(i).trouverMainJoueur());
        }
        choixNom.setAlwaysOnTop(false);
        choixNom.setVisible(false);
        this.setVisible(true);
    }//GEN-LAST:event_soumettrePseudoButtonMouseClicked

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphDroleDeZebre().setVisible(false);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCarte1;
    private javax.swing.JButton buttonCarte2;
    private javax.swing.JFrame choixNom;
    private javax.swing.JFrame frameChoixCarte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JFrame menuDebut;
    private javax.swing.JButton newGamebutton;
    private javax.swing.JTextField pseudo1;
    private javax.swing.JTextField pseudo2;
    private javax.swing.JButton soumettrePseudoButton;
    // End of variables declaration//GEN-END:variables
}
