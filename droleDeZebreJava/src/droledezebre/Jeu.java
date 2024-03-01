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

    private ArrayList<Joueur> listJoueur=new ArrayList<>();

    public void init() {
      
        Plateau plateau1 = new Plateau();
        plateau1.init();
        for (int i = 0; i < 2; i++) { // initialisation des deux joueurs 
            Joueur j=new Joueur();
            String pseudo;
            pseudo=j.trouverPseudo();
            j.init(pseudo,listJoueur); 
            listJoueur.add(j);
        }
        Random ra = new Random();
        int place = ra.nextInt(1) + 1;
        if (place == 2) {
            Joueur temp = listJoueur.get(2);
            listJoueur.add(1, listJoueur.get(1));
            listJoueur.add(0, temp);
        }
        boolean repCorrect = false;
        while (repCorrect == false) {
            System.out.println(listJoueur.get(1).getPseudo() + " Où voulez vous placer Impala Jones pour ce premier tour? n'oubliez pas que ça doit être une case au bord du plateau! ");

            try {
                System.out.println("Donnez les coordonnées x (colonnes)de votre ImpalaJones, ");
                Scanner na = new Scanner(System.in);
                int x = na.nextInt();
                System.out.println("Donnez les coordonnées y(lignes) de votre ImplaJones ");
                Scanner va=new Scanner(System.in);
                int y=va.nextInt();
                if ((x == 0||y==0)&&(x<=8&&y<=7)&&(x>=0&&y>=0)) {
                    repCorrect = true;
                    ImpalaJones impala=new ImpalaJones();
                    
                    plateau1.getPlateau()[y][x].setPion(impala);
                }
                else{
                    System.out.println("Entrez des coordonnées au bord du plateau");
                }
            }
            catch(Exception e){
                System.out.println("Veuillez inscrire des entrées valides ");
            }

        }
        System.out.println(plateau1);
    }

    public void jeu() {

    }
}
