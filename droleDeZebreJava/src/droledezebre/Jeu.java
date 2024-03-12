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

    public ArrayList<Joueur> getListJoueur() {
        return (this.listJoueur);
    }

    public void init(Plateau plateau1) { // creer le plateau et le jeu dans le main

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
        plateau1.premierePosImpala(this);

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

    public void jeu(Jeu jeu1, Plateau plateau1) {
        jeu1.init(plateau1);
        ArrayList<int[]> choixCase = new ArrayList<>();
        while (!plateau1.plateauPlein()) {
            for (int i = 0; i < 2; i++) {

                System.out.println("C'est au tour de " + this.listJoueur.get(i).getPseudo());
                plateau1.voirPlateau();
                choixCase.clear();
                Animal choixPion = this.listJoueur.get(i).proposerPion();//propose au joueur la liste des pions qu'il peut jouer
                choixCase = plateau1.trouverCoordonneesCasesDispo();//trouver la liste des cases dispo
                int[] choixJoueur = new int[2];//première case pour x et l'autre pour y 
                choixJoueur = jeu1.proposerChoixCase(choixCase);// demande au joueur sur quelle case il veut poser son pion
                plateau1.poserPion(this, i, choixJoueur, choixPion);
                System.out.println(plateau1);
                int nbDeplaImpala = plateau1.nbDeplaImpala();
                plateau1.deplacerImpala(nbDeplaImpala);
            }
        }

    }
}
