/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package droledezebre;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mae
 */
public class Plateau {

    private Case[][] plateau = new Case[7][8];

    public Case[][] getPlateau() {
        return (this.plateau);
    }

    public String toString() {
        String retour = "";
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i == 0 && j == 0) || (i == 0 && j == 7) || (i == 6 && j == 0) || (i == 6 && j == 7)) {
                    retour = retour + "=> ";

                } else if (this.plateau[i][j].getPion() == null) {
                    if (i == 0 || j == 0 || i == 6 || j == 7) {
                        retour = retour + " X ";
                    } else {
                        retour = retour + " " + this.plateau[i][j].getTerrain() + " ";
                    }
                } else {
                    if (this.plateau[i][j].getPion().getIndicateur().equals("Imp")) {
                        retour = retour + "Imp";

                    } else {

                        retour = retour + this.plateau[i][j].getPion().getIndicateur() + this.plateau[i][j].getPion().getCouleur() + this.getPlateau()[i][j].getTerrain() + " ";
                    }
                }

                if (j == 7) {
                    retour = retour + "\n";
                }
            }
        }

        return (retour);
    }

    public void verifAnimalPeureux(int xLiCro, int yLiCro) { // sert à vérifier si il y a un zebre ou une gazelle à proximité du lion et à agir en consequence 

    }

    public void verifAnimalAgressif(int xGaZe, int yGaZe) { // verifie si il y a un animal agressif autour de la gazelle ou du zebre à poser et agit en consequence 

    }

    public void init(int carte) {

        if (carte == 1 || carte == 2) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    if (i == 0 || j == 0 || i == 6 || j == 7) {
                        this.plateau[i][j].setTerrain(0);
                    }
                }
            }
            if (carte == 1) {
                for (int lignes = 1; lignes < 4; lignes++) {
                    this.plateau[lignes][1].setTerrain(1);
                }
                this.plateau[4][1].setTerrain(2);
                for (int lignes = 2; lignes < 5; lignes++) {
                    this.plateau[lignes][2].setTerrain(2);
                }
                this.plateau[2][3].setTerrain(2);
                for (int colonnes = 1; colonnes < 5; colonnes++) {
                    this.plateau[5][colonnes].setTerrain(3);
                }
                this.plateau[4][3].setTerrain(3);
                this.plateau[3][3].setTerrain(3);
                this.plateau[3][4].setTerrain(3);
                for (int colonnes = 4; colonnes < 6; colonnes++) {
                    this.plateau[4][colonnes].setTerrain(4);
                }
                this.plateau[5][5].setTerrain(4);
                for (int colonnes = 2; colonnes < 5; colonnes++) {
                    this.plateau[1][colonnes].setTerrain(5);
                }
                for (int colonnes = 4; colonnes < 6; colonnes++) {
                    this.plateau[2][colonnes].setTerrain(5);
                }
                this.plateau[3][5].setTerrain(5);
                for (int lignes = 3; lignes < 6; lignes++) {
                    this.plateau[lignes][6].setTerrain(5);
                }
                for (int colonnes = 5; colonnes < 7; colonnes++) {
                    this.plateau[1][colonnes].setTerrain(6);
                }
                this.plateau[2][6].setTerrain(6);
                System.out.println("Vous avez choisi la Reserve du President.");
            } else if (carte == 2) {
                for (int lignes = 1; lignes < 4; lignes++) {
                    this.plateau[lignes][1].setTerrain(1);
                }
                for (int lignes = 3; lignes < 5; lignes++) {
                    this.plateau[lignes][2].setTerrain(1);
                }
                for (int lignes = 1; lignes < 3; lignes++) {
                    this.plateau[lignes][2].setTerrain(2);
                }
                for (int lignes = 2; lignes < 5; lignes++) {
                    this.plateau[lignes][3].setTerrain(2);
                }
                for (int colonnes = 4; colonnes < 6; colonnes++) {
                    this.plateau[4][colonnes].setTerrain(2);
                }
                for (int lignes = 4; lignes < 6; lignes++) {
                    this.plateau[lignes][1].setTerrain(3);
                }
                this.plateau[5][2].setTerrain(3);
                for (int colonnes = 3; colonnes < 7; colonnes++) {
                    this.plateau[1][colonnes].setTerrain(4);
                }
                this.plateau[2][4].setTerrain(4);
                for (int colonnes = 4; colonnes < 6; colonnes++) {
                    this.plateau[3][colonnes].setTerrain(4);
                }
                for (int colonnes = 5; colonnes < 7; colonnes++) {
                    this.plateau[2][colonnes].setTerrain(5);
                }
                this.plateau[3][6].setTerrain(5);
                this.plateau[4][6].setTerrain(6);
                for (int colonnes = 3; colonnes < 7; colonnes++) {
                    this.plateau[5][colonnes].setTerrain(6);
                }
                System.out.println("Vous avez choisi la Savane.");
            }
        } else {
            System.out.println("Erreur : cette carte n'existe pas"); // Cette erreur ne devrait jamais s'afficher en vu des contrôles à l'initialisation de "carte"
        }
    }

    public int nbDeplaImpala() {
        int choix = 0;

        while (choix != 1 && choix != 2 && choix != 3) {
            try {
                System.out.println("De combien de cases souhaitez vous déplacer Impala Jones (1, 2 ou 3)");
                Scanner scan = new Scanner(System.in);
                choix = scan.nextInt();
            } catch (Exception all) {
            }
            if (choix != 1 && choix != 2 && choix != 3) {
                System.out.println("Veuillez faire un choix valide");
            }
        }
        return choix;
    }

    public void deplacerImpala(int nbDepla) {

        int dx = 0, dy = 0;

        for (int lignes = 0; lignes < 7; lignes++) {
            for (int colonnes = 0; colonnes < 8; colonnes++) {
                if (this.plateau[lignes][colonnes].getPion() != null) {
                    if (this.plateau[lignes][colonnes].getPion().indicateur.equals("Imp")) {
                        dx = lignes;
                        dy = colonnes;
                        System.out.println("dx : " + dx + " /dy : " + dy);
                        this.plateau[dx][dy].setPion(null);
                    }
                }
            }
        }
        ImpalaJones imp = new ImpalaJones();
        if (dx == 0 && dy + nbDepla < 7) {
            this.plateau[dx][dy + nbDepla].setPion(imp);
        } else if (dx == 0 && dy + nbDepla >= 7) {
            this.plateau[-6 + dy + nbDepla][7].setPion(imp);
        } else if (dy == 7 && dx + nbDepla < 6) {
            this.plateau[dx + nbDepla][dy].setPion(imp);
        } else if (dy == 7 && dx + nbDepla >= 6) {
            this.plateau[6][(6 - nbDepla) + (6 - dx)].setPion(imp);
        } else if (dx == 6 && dy - nbDepla > 0) {
            this.plateau[dx][dy - nbDepla].setPion(imp);
        } else if (dx == 6 && dy - nbDepla <= 0) {
            this.plateau[5 + (dy - nbDepla)][0].setPion(imp);
        } else if (dy == 0 && dx - nbDepla > 0) {
            this.plateau[dx - nbDepla][0].setPion(imp);
        } else if (dy == 0 && dx - nbDepla <= 0) {
            this.plateau[0][1 - dx + nbDepla].setPion(imp);
        }
    }

    public boolean plateauPlein() {//méthode qui détermine si le plateau est plein ou non 
        return (false);
    }

    public ArrayList<int[]> trouverChoixPion() {// trouve la liste des coordonnées x et y des cases où le pion peut être placé 
        ArrayList<int[]> retour = new ArrayList<>();
        int[] coordoImp = new int[2];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.plateau[i][j].getPion() != null) {
                    if (this.plateau[i][j].getPion() instanceof ImpalaJones) {
                        coordoImp[0] = i;
                        coordoImp[1] = j;
                    }
                }
            }
        }
        
        if (coordoImp[0] == 0 || coordoImp[0] == 6) {
            for (int k = 1; k < 7; k++) {
                if (this.plateau[coordoImp[0]][k].getPion() == null) {
                    int[] tab = new int[]{k,coordoImp[1]};
                    retour.add(tab);
                }
            }
        } else if(coordoImp[1]==0||coordoImp[1]==7){
            for (int l = 1; l < 6; l++) {
                if (this.plateau[coordoImp[0]][l].getPion() == null) {
                    int[] tab = new int[]{coordoImp[0], l};
                    retour.add(tab);
                }
            }
        }
        return (retour);
    }

    public void poserPion(Joueur j, int[] choixJoueur, Animaux choixPion) {// methode qui pose le pion au bon endroit du plateau selon le joueur 
        //faire les verifs pour le zebre et ma gazelle  
        //appeler verifAnimalAgressif pour cela 
    }

    public void voirPlateau() {

        boolean entreeCorrect = false;
        while (!entreeCorrect) {
            try {
                System.out.println("Voulez vous voir le plateau? o/n");
                Scanner na = new Scanner(System.in);
                String rep = na.nextLine();
                if (rep.equals("o") || rep.equals("O")) {
                    System.out.println(this);
                    System.out.println("Maintenant veuillez choisir votre pion");
                    entreeCorrect = true;
                } else if (rep.equals("n") || rep.equals("N")) {
                    System.out.println("Veuillez donc choisir votre pion directement ");
                    entreeCorrect = true;
                } else {
                    System.out.println("Veuillez repondre par o pour oui et n pour non");
                    entreeCorrect = false;
                }
            } catch (Exception e) {
                System.out.println("Veuillez donner une entrée correct");
                entreeCorrect = false;
            }
        }
    }

    public Plateau() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                this.plateau[i][j] = new Case();
            }
        }
    }
}
