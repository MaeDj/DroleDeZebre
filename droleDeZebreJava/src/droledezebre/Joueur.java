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
public class Joueur {

    private String pseudo;
    private int nbPoint;
    private String couleur;
    private ArrayList<Animal> main; //ensemble des pions disponibles pour le joueur 
    private boolean inauguration; // nous avons choisi de représenter le pion inauguration sous forme de booleen pour des raisons de facilité 

    public boolean getInauguration() {
        return (this.inauguration);
    }

    public String getPseudo() {
        return (this.pseudo);
    }

    public String getCouleur() {
        return (this.couleur);
    }

    public ArrayList<Animal> getMain() {
        return (this.main);
    }

    public int getNbPoint() {
        return (this.nbPoint);
    }

    public void setInauguration(boolean a) {
        this.inauguration = a;
    }

    public void setNbPoint(int newPts) {
        this.nbPoint = newPts;
    }

    public String trouverCouleur(ArrayList listJoueur) { // trouve, par rappot à la liste de joueur de quelle couleur est chaque joueur 
        if (listJoueur.isEmpty()) {
            return ("v");
        } else {
            return ("r");
        }
    }

    public ArrayList<Animal> trouverMainJoueur() { // initialise la première main du joueur complète
        ArrayList<Animal> mainJoueur = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Gazelle gazelle = new Gazelle();
            gazelle.couleur = this.couleur;
            mainJoueur.add(gazelle);
        }
        for (int j = 0; j < 5; j++) {
            Zebre zebre = new Zebre();
            zebre.couleur = this.couleur;
            mainJoueur.add(zebre);
        }
        for (int k = 0; k < 2; k++) {
            Crocodile croco = new Crocodile();
            croco.couleur = this.couleur;
            mainJoueur.add(croco);
        }
        Elephant eleph = new Elephant();
        eleph.couleur = this.couleur;
        mainJoueur.add(eleph);

        Lion lion = new Lion();
        lion.couleur = this.couleur;
        mainJoueur.add(lion);

        return (mainJoueur);

    }

    public ArrayList<Animal> trouverPionsAProposer(int[] tab) {//trouve les pions que le joueur peut jouer dans sa main et combien il lui en reste. 
        // Le nombre de chaque pion est retourné par référence 
        ArrayList<Animal> pionAJouer = new ArrayList<>();
        for (int i = 0; i < this.getMain().size(); i++) {
            boolean presence = false;
            switch (this.getMain().get(i).getClass().getName()) {//trouve combien il y a chaque pion
                case "droledezebre.Gazelle":
                    tab[0] += 1;
                    break;
                case "droledezebre.Zebre":
                    tab[1] += 1;
                    break;
                case "droledezebre.Lion":
                    tab[2] += 1;
                    break;
                case "droledezebre.Elephant":
                    tab[3] += 1;
                    break;
                case "droledezebre.Crocodile":
                    tab[4] += 1;
                    break;
            }
            for (int k = 0; k < pionAJouer.size(); k++) {
                if (this.getMain().get(i).getIndicateur().equals(pionAJouer.get(k).getIndicateur())) {
                    presence = true;//vérifie qu'il n'y a pas déjà le pion dans la liste  
                }
            }
            if (presence == false) {
                pionAJouer.add(this.getMain().get(i));// si il n'est pas présent on l'ajoute dans la liste de retour

            }
        }
        return (pionAJouer);
    }

    public Animal proposerPion() { //propose au joueur quel pion il peut jouer
        int[] nbChaquePion = new int[5];
        for (int k = 0; k < 5; k++) {
            nbChaquePion[k] = 0;
        }
        ArrayList<Animal> pionAJouer = this.trouverPionsAProposer(nbChaquePion);
        System.out.println("Quel Pion voulez vous poser? \n");

        System.out.println("Vous pouvez poser:");
        for (int l = 0; l < pionAJouer.size(); l++) {

            switch (pionAJouer.get(l).getClass().getName()) {
                case "droledezebre.Gazelle":
                    System.out.println(l + 1 + ": " + " Gazelle " + "(" + nbChaquePion[0] + ")");
                    break;
                case "droledezebre.Zebre":
                    System.out.println(l + 1 + ": " + " Zebre " + "(" + nbChaquePion[1] + ")");
                    break;
                case "droledezebre.Lion":
                    System.out.println(l + 1 + ": " + " Lion " + "(" + nbChaquePion[2] + ")");
                    break;
                case "droledezebre.Elephant":
                    System.out.println(l + 1 + ": " + " Elephant " + "(" + nbChaquePion[3] + ")");
                    break;
                case "droledezebre.Crocodile":
                    System.out.println(l + 1 + ": " + " Crocodile " + "(" + nbChaquePion[4] + ")");
                    break;
            }

        }
        boolean bonneReponse = false;
        while (bonneReponse == false) {
            try {
                System.out.println("Selectionnez le numero du pion que vous voulez jouer ");
                Scanner sc = new Scanner(System.in);
                int rep = sc.nextInt();
                bonneReponse = true;
                this.getMain().remove(pionAJouer.get(rep - 1));
                return (pionAJouer.get(rep - 1));
            } catch (Exception e) {
                System.out.println("Veuillez entrer le numero du pion que vous voulez jouer");

            }
        }

        return (null);
    }

    public void init(String pseudo, ArrayList listJoueur) {//nous avons choisi de ne pas utiliser de constructeur car nous utilisions des méthodes à l'interieur 
        this.pseudo = pseudo;
        this.inauguration = false;
        this.nbPoint = 0;
        this.couleur = trouverCouleur(listJoueur);
        this.main = trouverMainJoueur();

    }

    public String trouverPseudo() {//demande le pseudo au joueur 
        String pseudo;
        System.out.println("Veuillez entrer votre pseudo");
        Scanner sc = new Scanner(System.in);
        pseudo = sc.nextLine();
        return (pseudo);
    }
    public Joueur(String v){
        this.couleur=v;
    }
    public Joueur(){
        
    }

}
