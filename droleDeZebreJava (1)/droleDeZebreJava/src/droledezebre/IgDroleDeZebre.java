/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package droledezebre;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Utilisateur
 */
public class IgDroleDeZebre extends javax.swing.JFrame {

    private static JLabel[][] cases = new JLabel[7][8];
    private static JLabel[] mainJoueurImgPion = new JLabel[5];
    private static JLabel[] mainJoueurValeur = new JLabel[5];
    private static JLabel infosPartie = new JLabel();
    private static Jeu jeu = new Jeu();
    private static int[] coordoImp = new int[2];
    private static boolean bordPlatactive, plateauActive, listPionActive;//sert à savoir quels boutons on peut cliquer 

    private JLayeredPane fond = getLayeredPane();

    /**
     * Creates new form IgDroleDeZebre
     */
    public IgDroleDeZebre() {
        initComponents();
        // Affiche le menu principal
        frameMenuPrincipal.setVisible(true);
        frameMenuPrincipal.toFront();
        frameMenuPrincipal.setSize(frameMenuPrincipal.getPreferredSize());

        // Initialise certains composants graphiques de la fenêtre de Jeu (notamment le fond sur le jLayeredPane)
        fond.setSize(1800, 1600);
        JLabel fondLabel = new JLabel();
        fondLabel.setIcon(new ImageIcon(".\\images\\img\\fond.jpg"));
        fondLabel.setBounds(0, 0, fond.getWidth(), fond.getHeight());
        fond.setLayer(fondLabel, 1);
        fond.add(fondLabel, Integer.valueOf(1));
        fond.setVisible(true);
        bordPlatactive = true;
        plateauActive = false;
        listPionActive = false;
    }

    // méthode qui servira à initialiser le bon plateau et créera le tableau visuel du plateau
    // choix = 1 : réserve, choix = 2 : savane
    public void initCarte(int choix) {
        JLabel platLabel = new JLabel();
        if (choix == 1) {
            platLabel.setIcon(new ImageIcon(".\\images\\img\\plateau1.jpg"));
        } else if (choix == 2) {
            platLabel.setIcon(new ImageIcon(".\\images\\img\\plateau0.jpg"));
        }
        platLabel.setBounds(300, 175, 540, 471);
        fond.setLayer(platLabel, 2);
        fond.add(platLabel, Integer.valueOf(2));

        int Horiz, Vert;
        Horiz = 308;
        Vert = 175;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) { // on créé notre plateau

                cases[i][j] = new JLabel();

                if (!((i == 0 && j == 0) || (i == 6 && j == 0) || (i == 6 && j == 7) || (i == 0 && j == 7))) {
                    if (i == 0 || j == 0 || i == 6 || j == 7) {
                        cases[i][j].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if (bordPlatactive) {
                                    for (int i = 0; i < 7; i++) {
                                        for (int j = 0; j < 8; j++) {
                                            if (cases[i][j].equals(e.getSource())) {
                                                coordoImp[0] = i;
                                                coordoImp[1] = j;// a garder? 
                                                ImpalaJones impala = new ImpalaJones();
                                                jeu.getPlateauDejeu().getPlateau()[i][j].setPion(impala);
                                                cases[i][j].setIcon(new ImageIcon(".\\images\\img\\impalajones.gif"));//des que l'on clique sur une case du bord du plateau on pose impala
                                            }
                                        }

                                    }
                                }
                                bordPlatactive = false;//on empêche de cliquer sur le bord du plateau
                                listPionActive = true;//on permet de choisir le pion 
                                
                                echangerJoueur(); // on echange les joueurs dans la liste pour passer au tour d'apres 
                                chargerListPion();
                                infosPartie.setText(jeu.getListJoueur().get(0).getPseudo() + " : Vous pouvez maintenant choisir votre pion");

                            }
                        }
                        );

                    } else {
                        cases[i][j].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {

                                infosPartie.setText("J'ai réussi");
                            }
                        });

                    }
                }

                cases[i][j].setLocation(Horiz, Vert);
                cases[i][j].setSize(
                        60, 60);
                if (j
                        == 6) {
                    Horiz += 62;
                } else {
                    Horiz += 62 + j * 2;
                }

                if (j
                        == 7) {
                    Horiz = 308;
                    if (i == 5) {
                        Vert += 65;
                    } else {
                        Vert += 65 + i * 2;
                    }
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
        Horiz = 308;
        Vert = 80;
        for (int k = 0; k < 5; k++) {
            this.mainJoueurImgPion[k] = new JLabel();
            mainJoueurImgPion[k].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (listPionActive) {// avant de faire l'action on regarde si on est au bon moment de la partie pour le faire 
                        infosPartie.setText("J'ai réussi pions");
                    }

                }
            });

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
            //mainJoueurImgPion[k].setDisabledIcon(mainJoueurImgPion[k].getIcon());
            //mainJoueurImgPion[k].setEnabled(false); // pour griser les boutons ???
        }

        Horiz = 308;
        Vert = 145;
        for (int k = 0; k < 5; k++) {
            mainJoueurValeur[k] = new JLabel();

            mainJoueurValeur[k].setLocation(Horiz, Vert);
            mainJoueurValeur[k].setSize(65, 30);

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
        infosPartie.setText("Informations sur la partie ");
        infosPartie.setSize(platLabel.getWidth(), 60);
        infosPartie.setLocation(308, 15);
        infosPartie.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        fond.setLayer(infosPartie, 3);
        fond.add(infosPartie, 3);
        fond.repaint();
    }

    public void echangerJoueur() {
        Joueur temp = jeu.getListJoueur().get(1);
        jeu.getListJoueur().set(1, jeu.getListJoueur().get(1));
        jeu.getListJoueur().set(0, temp);
    }

    public void initJeuInterface(int choixCarte) {//les pseudos se récupèrent directement dans le liste de joueurs de jeu des qu'on les demande
        jeu.getPlateauDejeu().init(choixCarte);
        Random ra = new Random();//mélange les deux joueurs: premier tour aléatoire 
        int place = ra.nextInt(1) + 1;
        if (place == 2) {
            echangerJoueur();
        }
        // jeu1.getPlateauDejeu().premierePosImpala(jeu1);
        chargerListPion();
        
    }
    public void chargerListPion(){
        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0:
                    mainJoueurImgPion[0].setIcon(new ImageIcon(".\\images\\img\\" + jeu.getListJoueur().get(0).getCouleur() + "_gaz.gif"));
                    break;
                case 1:
                    mainJoueurImgPion[1].setIcon(new ImageIcon(".\\images\\img\\" + jeu.getListJoueur().get(0).getCouleur() + "_zeb.gif"));
                    break;
                case 2:
                    mainJoueurImgPion[2].setIcon(new ImageIcon(".\\images\\img\\" + jeu.getListJoueur().get(0).getCouleur() + "_lio.gif"));
                    break;
                case 3:
                    mainJoueurImgPion[3].setIcon(new ImageIcon(".\\images\\img\\" + jeu.getListJoueur().get(0).getCouleur() + "_ele.gif"));
                    break;
                case 4:
                    mainJoueurImgPion[4].setIcon(new ImageIcon(".\\images\\img\\" + jeu.getListJoueur().get(0).getCouleur() + "_cro.gif"));
                    break;
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameMenuPrincipal = new javax.swing.JFrame();
        panelFondMenuPrincipal = new javax.swing.JPanel();
        buttonNouvellePartie = new javax.swing.JButton();
        buttonChargerPartie = new javax.swing.JButton();
        buttonQuitterJeu = new javax.swing.JButton();
        labelLogoDdzMenuPrincipal = new javax.swing.JLabel();
        labelNomsMenuPrincipal = new javax.swing.JLabel();
        labelCreditsMenuPrincipal = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        frameChoixPseudo = new javax.swing.JFrame();
        panelFondChoixPseudo = new javax.swing.JPanel();
        jTextFieldPseudoJ1 = new javax.swing.JTextField();
        jTextFieldPseudoJ2 = new javax.swing.JTextField();
        labelDemandeChoixPseudo = new javax.swing.JLabel();
        buttonChoixPseudoValider = new javax.swing.JButton();
        labelChoixPseudoJ1 = new javax.swing.JLabel();
        labelChoixPseudoJ2 = new javax.swing.JLabel();
        frameChoixCarte = new javax.swing.JFrame();
        panelFondChoixCarte = new javax.swing.JPanel();
        labelDemandeChoixCarte = new javax.swing.JLabel();
        labelChoixCarteSavane = new javax.swing.JLabel();
        labelChoixCarteReservePresident = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        frameMenuPrincipal.setTitle("Menu - Drôles de Zèbres");

        panelFondMenuPrincipal.setBackground(new java.awt.Color(0, 153, 255));

        buttonNouvellePartie.setText("Nouvelle Partie");
        buttonNouvellePartie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonNouvellePartie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonNouvellePartieMouseClicked(evt);
            }
        });

        buttonChargerPartie.setText("Charger Partie");
        buttonChargerPartie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        buttonQuitterJeu.setText("Quitter");
        buttonQuitterJeu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonQuitterJeu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonQuitterJeuMouseClicked(evt);
            }
        });

        labelLogoDdzMenuPrincipal.setIcon(new ImageIcon(".\\images\\img\\logo_ddz.gif"));

        labelNomsMenuPrincipal.setText("Par Jonathan Adam et Maé Dugoua-Jacques");

        labelCreditsMenuPrincipal.setText("Jeu original par Bruno Cathala");

        jLabel3.setText("Illustrateurs: Philippe Briones & Sébastien Lamirand");

        javax.swing.GroupLayout panelFondMenuPrincipalLayout = new javax.swing.GroupLayout(panelFondMenuPrincipal);
        panelFondMenuPrincipal.setLayout(panelFondMenuPrincipalLayout);
        panelFondMenuPrincipalLayout.setHorizontalGroup(
            panelFondMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondMenuPrincipalLayout.createSequentialGroup()
                .addGroup(panelFondMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondMenuPrincipalLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(panelFondMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNomsMenuPrincipal)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondMenuPrincipalLayout.createSequentialGroup()
                                .addComponent(labelCreditsMenuPrincipal)
                                .addGap(30, 30, 30))))
                    .addGroup(panelFondMenuPrincipalLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(panelFondMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonNouvellePartie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonChargerPartie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelLogoDdzMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelFondMenuPrincipalLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(buttonQuitterJeu))
                    .addGroup(panelFondMenuPrincipalLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        panelFondMenuPrincipalLayout.setVerticalGroup(
            panelFondMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondMenuPrincipalLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(labelLogoDdzMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(buttonNouvellePartie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonChargerPartie)
                .addGap(39, 39, 39)
                .addComponent(buttonQuitterJeu)
                .addGap(42, 42, 42)
                .addComponent(labelNomsMenuPrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCreditsMenuPrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameMenuPrincipalLayout = new javax.swing.GroupLayout(frameMenuPrincipal.getContentPane());
        frameMenuPrincipal.getContentPane().setLayout(frameMenuPrincipalLayout);
        frameMenuPrincipalLayout.setHorizontalGroup(
            frameMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        frameMenuPrincipalLayout.setVerticalGroup(
            frameMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        frameChoixPseudo.setTitle("Choix Pseudos - Drôles de Zèbres");

        panelFondChoixPseudo.setBackground(new java.awt.Color(0, 155, 255));

        jTextFieldPseudoJ1.setText("Joueur 1");

        jTextFieldPseudoJ2.setText("Joueur 2");

        labelDemandeChoixPseudo.setText("Veuillez entrer le pseudo des Joueurs");

        buttonChoixPseudoValider.setText("Valider");
        buttonChoixPseudoValider.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonChoixPseudoValider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonChoixPseudoValiderMouseClicked(evt);
            }
        });

        labelChoixPseudoJ1.setText("Joueur 1 : ");

        labelChoixPseudoJ2.setText("Joueur 2 : ");

        javax.swing.GroupLayout panelFondChoixPseudoLayout = new javax.swing.GroupLayout(panelFondChoixPseudo);
        panelFondChoixPseudo.setLayout(panelFondChoixPseudoLayout);
        panelFondChoixPseudoLayout.setHorizontalGroup(
            panelFondChoixPseudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondChoixPseudoLayout.createSequentialGroup()
                .addGroup(panelFondChoixPseudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondChoixPseudoLayout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(labelDemandeChoixPseudo))
                    .addGroup(panelFondChoixPseudoLayout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(buttonChoixPseudoValider))
                    .addGroup(panelFondChoixPseudoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelFondChoixPseudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPseudoJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelChoixPseudoJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(151, 151, 151)
                        .addGroup(panelFondChoixPseudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelChoixPseudoJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldPseudoJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFondChoixPseudoLayout.setVerticalGroup(
            panelFondChoixPseudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondChoixPseudoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(labelDemandeChoixPseudo)
                .addGap(31, 31, 31)
                .addGroup(panelFondChoixPseudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelChoixPseudoJ1)
                    .addComponent(labelChoixPseudoJ2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondChoixPseudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPseudoJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPseudoJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonChoixPseudoValider)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout frameChoixPseudoLayout = new javax.swing.GroupLayout(frameChoixPseudo.getContentPane());
        frameChoixPseudo.getContentPane().setLayout(frameChoixPseudoLayout);
        frameChoixPseudoLayout.setHorizontalGroup(
            frameChoixPseudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondChoixPseudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        frameChoixPseudoLayout.setVerticalGroup(
            frameChoixPseudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondChoixPseudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        frameChoixCarte.setTitle("Choix Carte - Drôles de Zèbres");

        panelFondChoixCarte.setBackground(new java.awt.Color(0, 153, 255));

        labelDemandeChoixCarte.setText("Sur quelle carte souhaitez-vous jouer ?");

        labelChoixCarteSavane.setIcon(new ImageIcon(".\\images\\img\\miniplateau0.jpg"));
        labelChoixCarteSavane.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelChoixCarteSavane.setPreferredSize(new java.awt.Dimension(120, 100));
        labelChoixCarteSavane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelChoixCarteSavaneMouseClicked(evt);
            }
        });

        labelChoixCarteReservePresident.setIcon(new ImageIcon(".\\images\\img\\miniplateau1.jpg"));
        labelChoixCarteReservePresident.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelChoixCarteReservePresident.setPreferredSize(new java.awt.Dimension(120, 100));
        labelChoixCarteReservePresident.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelChoixCarteReservePresidentMouseClicked(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("La Réserve du Président");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("La Savane");

        javax.swing.GroupLayout panelFondChoixCarteLayout = new javax.swing.GroupLayout(panelFondChoixCarte);
        panelFondChoixCarte.setLayout(panelFondChoixCarteLayout);
        panelFondChoixCarteLayout.setHorizontalGroup(
            panelFondChoixCarteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondChoixCarteLayout.createSequentialGroup()
                .addGroup(panelFondChoixCarteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondChoixCarteLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(labelDemandeChoixCarte))
                    .addGroup(panelFondChoixCarteLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(panelFondChoixCarteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelFondChoixCarteLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(labelChoixCarteReservePresident, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(180, 180, 180)
                        .addGroup(panelFondChoixCarteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelChoixCarteSavane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        panelFondChoixCarteLayout.setVerticalGroup(
            panelFondChoixCarteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondChoixCarteLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(labelDemandeChoixCarte)
                .addGap(31, 31, 31)
                .addGroup(panelFondChoixCarteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelChoixCarteReservePresident, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelChoixCarteSavane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondChoixCarteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameChoixCarteLayout = new javax.swing.GroupLayout(frameChoixCarte.getContentPane());
        frameChoixCarte.getContentPane().setLayout(frameChoixCarteLayout);
        frameChoixCarteLayout.setHorizontalGroup(
            frameChoixCarteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondChoixCarte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        frameChoixCarteLayout.setVerticalGroup(
            frameChoixCarteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondChoixCarte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1800, 1600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelChoixCarteSavaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelChoixCarteSavaneMouseClicked
        // Chargement de la savane
        initCarte(2);
        initJeuInterface(2);
        bordPlatactive = true;
        infosPartie.setText(jeu.getListJoueur().get(0).getPseudo()+" : Veuillez choisir où vous voulez placer Impala Jones\n Il doit être au bord !");
        frameChoixCarte.setVisible(false);
        this.setVisible(true);
        this.toFront();

    }//GEN-LAST:event_labelChoixCarteSavaneMouseClicked

    private void buttonQuitterJeuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonQuitterJeuMouseClicked
        System.exit(0);
    }//GEN-LAST:event_buttonQuitterJeuMouseClicked

    private void labelChoixCarteReservePresidentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelChoixCarteReservePresidentMouseClicked
        // Chargement de la réserve du Président
        initCarte(1);
        initJeuInterface(1);
        bordPlatactive = true;
        infosPartie.setText(jeu.getListJoueur().get(0).getPseudo()+" :Veuillez choisir où vous voulez placer Impala Jones. Il doit être au bord !");
        frameChoixCarte.setVisible(false);
        this.setVisible(true);
        this.toFront();

    }//GEN-LAST:event_labelChoixCarteReservePresidentMouseClicked

    private void buttonNouvellePartieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonNouvellePartieMouseClicked
        frameMenuPrincipal.setVisible(false);
        frameChoixPseudo.setVisible(true);
        frameChoixPseudo.setSize(frameChoixPseudo.getPreferredSize());
        frameChoixPseudo.toFront();
    }//GEN-LAST:event_buttonNouvellePartieMouseClicked

    private void buttonChoixPseudoValiderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonChoixPseudoValiderMouseClicked
        jeu.getListJoueur().add(new Joueur("v", jTextFieldPseudoJ1.getText()));
        jeu.getListJoueur().add(new Joueur("r", jTextFieldPseudoJ2.getText()));

        // Ajoute les jetons aux mains des joueurs
        for (int i = 0; i < 2; i++) {
            jeu.getListJoueur().get(i).setMain(jeu.getListJoueur().get(i).trouverMainJoueur());
        }
        frameChoixPseudo.setVisible(false);
        frameChoixCarte.setVisible(true);
        frameChoixCarte.setSize(frameChoixCarte.getPreferredSize());
        frameChoixCarte.toFront();
    }//GEN-LAST:event_buttonChoixPseudoValiderMouseClicked

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
            java.util.logging.Logger.getLogger(IgDroleDeZebre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IgDroleDeZebre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IgDroleDeZebre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IgDroleDeZebre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IgDroleDeZebre().setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonChargerPartie;
    private javax.swing.JButton buttonChoixPseudoValider;
    private javax.swing.JButton buttonNouvellePartie;
    private javax.swing.JButton buttonQuitterJeu;
    private javax.swing.JFrame frameChoixCarte;
    private javax.swing.JFrame frameChoixPseudo;
    private javax.swing.JFrame frameMenuPrincipal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextFieldPseudoJ1;
    private javax.swing.JTextField jTextFieldPseudoJ2;
    private javax.swing.JLabel labelChoixCarteReservePresident;
    private javax.swing.JLabel labelChoixCarteSavane;
    private javax.swing.JLabel labelChoixPseudoJ1;
    private javax.swing.JLabel labelChoixPseudoJ2;
    private javax.swing.JLabel labelCreditsMenuPrincipal;
    private javax.swing.JLabel labelDemandeChoixCarte;
    private javax.swing.JLabel labelDemandeChoixPseudo;
    private javax.swing.JLabel labelLogoDdzMenuPrincipal;
    private javax.swing.JLabel labelNomsMenuPrincipal;
    private javax.swing.JPanel panelFondChoixCarte;
    private javax.swing.JPanel panelFondChoixPseudo;
    private javax.swing.JPanel panelFondMenuPrincipal;
    // End of variables declaration//GEN-END:variables
}
