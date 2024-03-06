/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package droledezebre;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author mae
 */
public class Jeu {

    private ArrayList<Joueur> listJoueur = new ArrayList<>();

    public void premierePosImpala(Plateau plateau1) {//premier placement de l'impala Jones 
        boolean repCorrect = false;
        while (repCorrect == false) {
            System.out.println(listJoueur.get(1).getPseudo() + " Où voulez vous placer Impala Jones pour ce premier tour? n'oubliez pas que ça doit être une case au bord du plateau! ");

            try {
                System.out.println("Donnez les coordonnées x (colonnes)de votre ImpalaJones, ");
                Scanner na = new Scanner(System.in);
                int x = na.nextInt();
                System.out.println("Donnez les coordonnées y(lignes) de votre ImplaJones ");
                Scanner va=new   Scanner(System.in);
                int y = va.nextInt();
                if ((x == 0 || y == 0 || x == 7 || y == 6) && (x <= 8 && y <= 7) && (x >= 0 && y >= 0) && !((x == 0 && y == 0) || (x == 7 && y == 0) || (x == 7 && y == 6) || (x == 0 && y == 6))) {
                    repCorrect = true;
                    ImpalaJones impala = new ImpalaJones();

                    plateau1.getPlateau()[y][x].setPion(impala);
                } else {
                    System.out.println("Entrez des coordonnées au bord du plateau");
                }
            } catch (Exception e) {
                System.out.println("Veuillez inscrire des entrées valides ");
            }

        }
    }

    public void init(Plateau plateau1, Jeu jeu1) { // creer le plateau et le jeu dans le main

        plateau1.init(choixCarte());
        for (int i = 0; i < 2; i++) { // initialisation des deux joueurs 
            Joueur j = new Joueur();
            String pseudo;
            pseudo = j.trouverPseudo();
            j.init(pseudo, listJoueur);
            listJoueur.add(j);
        }
        Random ra = new Random();//mélange les deuc joueur: premier tour aléatoire 
        int place = ra.nextInt(1) + 1;
        if (place == 2) {
            Joueur temp = listJoueur.get(2);
            listJoueur.add(1, listJoueur.get(1));
            listJoueur.add(0, temp);
        }
        premierePosImpala(plateau1);

        System.out.println(plateau1);

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
        int[] temp = new int[2];
        return (temp);
    }

    public Animaux demanderPionAJouer() { // methode qui demande au joueur quel pion il veut jouer 
        Animaux choixPion = new Animaux();
        return (choixPion);

    }

    public void jeu(Jeu jeu1, Plateau plateau1) {
        jeu1.init(plateau1, jeu1);
        ArrayList<int[]> choixCase = new ArrayList<>();
        while (!plateau1.plateauPlein()) {
            for (int i = 0; i < 2; i++) {
               
                System.out.println("C'est au tour de " + this.listJoueur.get(i).getPseudo());
                plateau1.voirPlateau();
                choixCase.clear();
                Animaux choixPion = new Animaux();
                choixPion = this.listJoueur.get(i).proposerPion();//propose au joueur la liste des pions qu'il peut jouer
                choixCase = plateau1.trouverChoixPion(choixPion);//trouver la liste des cases dispo
                int[] choixJoueur = new int[2];//première case pour x et l'autre pour y 
                choixJoueur = jeu1.proposerChoixCase(choixCase);// demande au joueur sur quelle case il veut poser son pion
                plateau1.poserPion(this.listJoueur.get(i), choixJoueur, choixPion);
                if (choixPion.getClass().getName().equals("Lion") || choixPion.getClass().getName().equals("Crocodile")) {
                    plateau1.verifAnimalPeureux(choixJoueur[0], choixJoueur[1]);
                }
                int nbDeplaImpala = plateau1.nbDeplaImpala();
                plateau1.deplacerImpala(nbDeplaImpala);
            }
        }

    }
}
