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
public class Jeu implements EntiteJeu {

    private ArrayList<Joueur> listJoueur;

    public void init() {
        listJoueur.clear(); // dans le cas ou on fiat plusieurs parties d'affilé 
        Plateau plateau = new Plateau();
        plateau.init();
        for (int i = 0; i < 2; i++) { // initialisation des deux joueurs 
            Joueur j = new Joueur();
            j.init();
            listJoueur.add(j);
        }
        Random ra=new Random();
        int place =ra.nextInt(1)+1;
        if (place==2){
            Joueur temp=listJoueur.get(2);
            listJoueur.add(1,listJoueur.get(1));
            listJoueur.add(0,temp);
        }
        boolean repCorrect =false;
        while(repCorrect=false){
        System.out.println(listJoueur.get(1).getPseudo()+" Où voulez vous placer Impala Jones pour ce premier tour? n'oubliez pas que ça doit être une case au bord du plateau! ");
                System.out.println("Donnez les coordonnées x de votre ImpalaJones, ");
        try{
            Scanner na=new Scanner(System.in);
            int rep1=na.nextInt();
            
            if(rep1==0){
                repCorrect=true;
            }  
        }
        
        }
    }

    public void jeu() {

    }
}
