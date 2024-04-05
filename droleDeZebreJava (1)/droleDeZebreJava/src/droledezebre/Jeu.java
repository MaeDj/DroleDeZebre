/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package droledezebre;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;

/**
 *
 * @author mae
 */
public class Jeu {

    private ArrayList<Joueur> listJoueur = new ArrayList<>(); //la liste des deux joueurs de la partie avec leurs attributs 
    private Plateau plateauDeJeu = new Plateau(); // Plateau sur lequel la partie se fait 

    public ArrayList<Joueur> getListJoueur() {
        return (this.listJoueur);
    }
    

    public Plateau getPlateauDejeu() {//interface
        return (this.plateauDeJeu);
    }

    public void setPlateauDejeu(Plateau plateau) {//à supprimer=> pour tester compter point 
        this.plateauDeJeu = plateau;
    }

    public void init() { // créé le plateau et le jeu dans le main

        this.plateauDeJeu.init(choixCarte());
        for (int i = 0; i < 2; i++) { // initialisation des deux joueurs 
            Joueur j = new Joueur();
            String pseudo;
            pseudo = j.trouverPseudo();
            j.init(pseudo, listJoueur);
            listJoueur.add(j);
        }
        Random ra = new Random();//mélange les deux joueurs: premier tour aléatoire 
        int place = ra.nextInt(1) + 1;
        if (place == 2) {
            Joueur temp = listJoueur.get(2);
            listJoueur.add(1, listJoueur.get(1));
            listJoueur.add(0, temp);
        }
        this.plateauDeJeu.premierePosImpala(this); // Le premier joueur de la liste choisi où il veut poser l'impala pour le premier tour 

        System.out.println(this.plateauDeJeu);

    }

    public int choixCarte() { //Demande au joueur la carte qu'il veut utiliser et récupérer un indicateur utilisé dans les autres méthodes 
        int choix = 0;

        while (choix != 1 && choix != 2) {
            try {
                System.out.println("Quelle carte souhaitez vous choisir ? \nLa Reserve du President(1) ou La Savane(2)");
                Scanner scan = new Scanner(System.in);
                choix = scan.nextInt();
            } catch (Exception all) {
            }
            if (choix != 1 && choix != 2) {
                System.out.println("Veuillez faire un choix valide");
            }
        }
        return choix;
    }

    public int[] proposerChoixCase(ArrayList<int[]> tabRep) {//demande à l'utilisateur quelle case il veut jouer parmis la liste proposée 
        int[] retour = new int[2];
        System.out.println("Voici les cases que vous pouvez choisir");
        for (int i = 0; i < tabRep.size(); i++) {
            System.out.println((i + 1) + ": ligne:" + (tabRep.get(i)[0] + 1) + " colonne:" + (tabRep.get(i)[1] + 1));
        }
        boolean bonneReponse = false;
        while (!bonneReponse)
        try {
            Scanner scanner = new Scanner(System.in);
            int rep = scanner.nextInt();

            retour[0] = tabRep.get(rep - 1)[0];
            retour[1] = tabRep.get(rep - 1)[1];
            bonneReponse = true;
        } catch (Exception e) {
            System.out.println("Veuillez entrer une valeur valide");

        }
        return (retour);
    }

    public ArrayList<Joueur> compterPoints() { // methode qui sert à compter les points à la fin de la partie
        int nbPtAjoute0;
        int nbPtAjoute1;// nombre de points que le joueur peut hypothétiquement gagner sur un secteur 
        int nbPions0; // nombre de pions sur le secteur sur chaque joueurs
        int nbPions1;
        for (int i = 1; i < 7; i++) {// on parcours secteur par secteur 
            nbPtAjoute0 = 0;
            nbPtAjoute1 = 0;
            nbPions0 = 0;
            nbPions1 = 0;
            for (int j = 0; j < 7; j++) {// on parcours tout le plateau

                for (int k = 0; k < 8; k++) {
                    if (this.plateauDeJeu.getPlateau()[j][k].getTerrain() == i) { // si c'est le secteur que l'on recherche 
                        for (int l = 0; l < 2; l++) { //on passe d'un joueur à l'autre 
                            if (this.listJoueur.get(l).getCouleur().equals(this.plateauDeJeu.getPlateau()[j][k].getPion().getCouleur())) {//si c'est la couleur du joueur que l'on parcourt on regarde si on compte les points 
                                boolean animalCache = false; // on verifie que le pion compte
                                Animal pion = (Animal) this.plateauDeJeu.getPlateau()[j][k].getPion();
                                if (pion instanceof Gazelle) {
                                    Gazelle gazelle = (Gazelle) pion;
                                    if (gazelle.getCache()) {
                                        animalCache = true;
                                    }
                                } else if (pion instanceof Zebre) {
                                    Zebre zebre = (Zebre) pion;
                                    if (zebre.getCache()) {
                                        animalCache = true;
                                    }
                                }
                                switch (l) {
                                    case 0:

                                        nbPions0 += 1;
                                        break;
                                    case 1:

                                        nbPions1 += 1;
                                        break;
                                }
                                if (!animalCache) {// si l'animal n'est pas caché on compte les points, sinon on ne fait rien et on passe au terrain suivant 
                                    switch (l) {
                                        case 0:
                                            nbPtAjoute0 += pion.getPts();

                                            break;
                                        case 1:
                                            nbPtAjoute1 += pion.getPts();

                                            break;
                                    }
                                }

                            }
                        }
                    }

                }

            }
            if (nbPions1 > nbPions0) { // on vérifie qui gagne les points du terrain 
                this.listJoueur.get(1).setNbPoint(this.listJoueur.get(1).getNbPoint() + nbPtAjoute1);
            } else if (nbPions0 > nbPions1) {
                this.listJoueur.get(0).setNbPoint(this.listJoueur.get(0).getNbPoint() + nbPtAjoute0);
            } else { // sans indications on prend le parti de ne pas compter les points des secteurs où il y a égalité 
                System.out.println("Vous etes à egalite sur le secteur " + i + " : aucun des points ne comptent");
            }
        }
        for (int n = 0; n < 2; n++) {// on attribut l'inauguration 
            if (this.listJoueur.get(n).getInauguration()) {
                this.listJoueur.get(n).setNbPoint(this.listJoueur.get(n).getNbPoint() + 5);
                System.out.println(this.listJoueur.get(n).getPseudo() + " ,grace a votre point inauguration vous remportez 5 pts de plus !");
            }
        }
        ArrayList<Joueur> retour = new ArrayList<>(); // détermine le ou les gagnants 
        if (this.listJoueur.get(0).getNbPoint() > this.listJoueur.get(1).getNbPoint()) {
            retour.add(this.listJoueur.get(0));
        } else if (this.listJoueur.get(1).getNbPoint() > this.listJoueur.get(0).getNbPoint()) {
            retour.add(this.listJoueur.get(1));
        } else {
            for (int m = 0; m < 2; m++) {
                retour.add(this.listJoueur.get(m));
            }
        }
        return (retour);
    }

    public void jeu() { // Méthode qui met en pratique toute la mécanique du jeu 
        this.init();
        ArrayList<int[]> choixCase = new ArrayList<>();//ensemble des cases disponibles à chaque tour 
        boolean inauguFaite = false;
        while (!this.plateauDeJeu.plateauPlein()) { // on arrête la partie uniquement si le plateau est plein
            for (int i = 0; i < 2; i++) {
                if (!this.listJoueur.get(i).getMain().isEmpty()) { //le joueur ne joue que si il a encore des pions, couvre le cas du lion
                    System.out.println("C'est au tour de " + this.listJoueur.get(i).getPseudo());
                    this.plateauDeJeu.voirPlateau();
                    choixCase.clear(); // on nettoie la liste pour ne pas voir les résultats précédents 
                    Animal choixPion = this.listJoueur.get(i).proposerPion();//propose au joueur la liste des pions qu'il peut jouer
                    choixCase = this.plateauDeJeu.trouverCoordonneesCasesDispo();//trouver la liste des cases dispo
                    int[] choixJoueur = new int[2];//première case pour x et l'autre pour y 
                    choixJoueur = this.proposerChoixCase(choixCase);// demande au joueur sur quelle case il veut poser son pion
                    this.plateauDeJeu.poserPion(this, i, choixJoueur, choixPion);
                    if (this.listJoueur.get(0).getInauguration() == false && this.listJoueur.get(1).getInauguration() == false) { //on ne vérifie le cas de l'inauguration que si aucun des deux joueurs ne l'a déjà 
                        this.plateauDeJeu.inauguration(choixJoueur, this.listJoueur.get(i));
                    }
                    System.out.println(this.plateauDeJeu);
                    this.plateauDeJeu.deplacementImpala();//on termine par déplacer l'impala 
                }
            }
        }
        ArrayList<Joueur> gagnants = new ArrayList(); // a la fin de la partie on compte les points 
        gagnants = this.compterPoints();// on met les gagnants dans une arrayList pour couvrir le cas de l'égalité entre deux joueurs 
        if (gagnants.size() == 1) {
            System.out.println("Le gagnant est " + gagnants.get(0).getPseudo() + " avec " + gagnants.get(0).getNbPoint() + " pts");
        } else {
            System.out.println("Les gagnants sont à egalite:");
            for (int j = 0; j < gagnants.size(); j++) {
                System.out.println(gagnants.get(j).getPseudo() + " avec " + gagnants.get(j).getNbPoint() + " pts");
            }
        }

    }
}
