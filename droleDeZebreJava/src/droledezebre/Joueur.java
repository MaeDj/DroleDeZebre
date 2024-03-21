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
    private ArrayList<Animal> main;
    private boolean inauguration;

    public boolean getInauguration() {
        return (this.inauguration);
    }

    public void setInauguration(boolean a) {
        this.inauguration = a;
    }

    public String getPseudo() {
        return (this.pseudo);
    }

    public String getCouleur() {
        return (this.couleur);
    }

    public void setNbPoint(int newPts) {
        this.nbPoint = newPts;
    }

    public int getNbPoint() {
        return (this.nbPoint);
    }

    public String trouverCouleur(ArrayList listJoueur) {
        if (listJoueur.isEmpty()) {
            return ("v");
        } else {
            return ("r");
        }
    }

    public ArrayList<Animal> getMain() {
        return (this.main);
    }

    public ArrayList<Animal> trouverMainJoueur() {
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

    public ArrayList<Animal> trouverPionsAProposer(int[] tab) {
        ArrayList<Animal> pionAJouer = new ArrayList<>();
        for (int i = 0; i < this.getMain().size(); i++) {
            boolean presence = false;
            switch (this.getMain().get(i).getClass().getName()) {
                case "droledezebre.Gazelle": tab[0]+=1;
                break;
                case "droledezebre.Zebre":tab[1]+=1;
                break;
                case "droledezebre.Lion":tab[2]+=1;
                break;
                case "droledezebre.Elephant":tab[3]+=1;
                break;
                case "droledezebre.Crocodile":tab[4]+=1;
                break;
            }
            for (int k = 0; k < pionAJouer.size(); k++) {
                if (this.getMain().get(i).getIndicateur().equals(pionAJouer.get(k).getIndicateur())) {
                    presence = true;
                }
            }
            if (presence == false) {
                pionAJouer.add(this.getMain().get(i));
                
            }
        }
        return (pionAJouer);
    }

    public Animal proposerPion() {
        int[] nbChaquePion=new int[5];
        for(int k=0;k<5;k++){
            nbChaquePion[k]=0;
        }
        ArrayList<Animal> pionAJouer=this.trouverPionsAProposer(nbChaquePion);
        System.out.println("Quel Pion voulez vous poser? \n");

        System.out.println("Vous pouvez poser:");
        for (int l = 0; l < pionAJouer.size(); l++) {
            
            switch(pionAJouer.get(l).getClass().getName()){
                case "droledezebre.Gazelle": System.out.println(l + 1 + ": " + " Gazelle "+"("+nbChaquePion[0]+")");
                break;
                case "droledezebre.Zebre":System.out.println(l + 1 + ": " + " Zebre "+"("+nbChaquePion[1]+")");
                break;
                case "droledezebre.Lion":System.out.println(l + 1 + ": " + " Lion "+"("+nbChaquePion[2]+")");
                break;
                case "droledezebre.Elephant":System.out.println(l + 1 + ": " + " Elephant "+"("+nbChaquePion[3]+")");
                break;
                case "droledezebre.Crocodile":System.out.println(l + 1 + ": " + " Crocodile "+"("+nbChaquePion[4]+")");
                break;
            }
            
        }
        boolean bonneReponse = false;
        while (bonneReponse == false) {
            try {
                System.out.println("Séléctionnez le numero du pion que vous voulez jouer ");
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

    public void init(String pseudo, ArrayList listJoueur) {
        this.pseudo = pseudo;
        this.inauguration = false;
        this.nbPoint = 0;
        this.couleur = trouverCouleur(listJoueur);
        this.main = trouverMainJoueur();

    }

    public String trouverPseudo() {
        String pseudo;
        System.out.println("Veuillez entrer votre pseudo");
        Scanner sc = new Scanner(System.in);
        pseudo = sc.nextLine();
        return (pseudo);
    }

    public Joueur() { // à enlever  pour test compterPts 
        this.pseudo = null;
        this.nbPoint = 0;
        this.main = null;
        this.inauguration = false;
        this.couleur = null;

    }

    public Joueur(String pseudo1, boolean inauguration1, String couleur1) { //à enlever, pour test compterPts
        this.inauguration = inauguration1;
        this.couleur = couleur1;
        this.nbPoint = 0;
        this.pseudo = pseudo1;

    }
}
