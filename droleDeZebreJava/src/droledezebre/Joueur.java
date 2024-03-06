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
public class Joueur  {
    private String pseudo;
    private int nbPoint;
    private String couleur;
    private ArrayList<Animaux> main;
    private boolean inauguration;
    
    public String getPseudo(){
        return(this.pseudo);
    }
    public String trouverCouleur(ArrayList listJoueur){
       if(listJoueur.isEmpty()){
           return("v");  
       }
       else{
           return("r");
       }
    }
    public ArrayList<Animaux> getMain(){
        return(this.main);
    }
    public ArrayList<Animaux> trouverMainJoueur(){
        ArrayList<Animaux> mainJoueur =new ArrayList<>();
        for(int i=0;i<6;i++){        
        Gazelle gazelle=new Gazelle();
        gazelle.couleur=this.couleur;
        mainJoueur.add(gazelle);
        }
        for(int j=0;j<5;j++){
            Zebre zebre=new Zebre();
            zebre.couleur=this.couleur;
            mainJoueur.add(zebre);
        }
         for(int k=0;k<2;k++){
            Crocodile croco=new Crocodile();
            croco.couleur=this.couleur;
            mainJoueur.add(croco);
        }
         Elephant eleph=new Elephant();
         eleph.couleur=this.couleur;
         mainJoueur.add(eleph);
         
         Lion lion =new Lion();
         lion.couleur=this.couleur;
         mainJoueur.add(lion);
         
         return(mainJoueur);
        
    }
    public Animaux proposerPion() {
        System.out.println("Quel Pion voulez vous poser? \n");
        ArrayList<Animaux> pionAJouer = new ArrayList<>();
        for (int i = 0; i < this.getMain().size(); i++) {
            boolean presence = false;
            for (int k = 0; k < pionAJouer.size(); k++) {
                if (this.getMain().get(i).getIndicateur().equals(pionAJouer.get(k).getIndicateur())) {
                    presence = true;
                }
            }
            if (presence == false) {
                pionAJouer.add(this.getMain().get(i));
            }
        }
        System.out.println("Vous pouvez poser:");
        for (int l = 0; l < pionAJouer.size(); l++) {
            System.out.println(l + 1 + ": " + pionAJouer.get(l).getIndicateur());
        }
        boolean bonneReponse = false;
        while (bonneReponse == false) {
            try {
                Scanner sc = new Scanner(System.in);
                int rep = sc.nextInt();
                bonneReponse = true;
                return (pionAJouer.get(rep - 1));
            } catch (Exception e) {
                System.out.println("Veuillez entrer le numero du pion que vous voulez jouer");

            }
        }

        return (null);
    }
    public void init(String pseudo,ArrayList listJoueur){
        this.pseudo=pseudo;
        this.inauguration=false;
        this.nbPoint=0;
        this.couleur=trouverCouleur(listJoueur);
        this.main=trouverMainJoueur();
        
    }
    public String trouverPseudo(){
        String pseudo;
        System.out.println("Veuillez entrer votre pseudo");
        Scanner sc=new Scanner(System.in);
        pseudo=sc.nextLine();
        return(pseudo);
    }
}
