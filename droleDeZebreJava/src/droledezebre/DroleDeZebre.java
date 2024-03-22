/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package droledezebre;

import java.util.ArrayList;

/**
 *
 * @author mae
 */
public class DroleDeZebre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      
        
       Jeu jeu1=new Jeu();
        jeu1.jeu();
        
        // test de compter point 
        
        /*Jeu jeu2=new Jeu();
        jeu2.getListJoueur().add(new Joueur("A",true,"v"));
        jeu2.getListJoueur().add(new Joueur("B",false,"r"));
        
        Plateau plateau1=new Plateau();
        jeu2.setPlateauDejeu(plateau1);
        jeu2.getPlateauDejeu().init(1);
        
        for(int i=1;i<6;i++){
            Zebre zebre =new Zebre();
            zebre.setCouleur("v");
            plateau1.getPlateau()[i][2].setPion(zebre);
            
            Lion lion =new Lion();
            lion.setCouleur("r");
            plateau1.getPlateau()[i][1].setPion(lion);
            
            if(i==1||i==2||i==3){
            Gazelle gaz=new Gazelle();
            gaz.setCouleur("v");
            plateau1.getPlateau()[i][3].setPion(gaz);
            }
            else{
                Gazelle gazCache=new Gazelle();
            gazCache.setCouleur("v");
            gazCache.setCache(true);
            plateau1.getPlateau()[i][3].setPion(gazCache);
            }
            
            Elephant eleph =new Elephant();
            eleph.setCouleur("r");
            plateau1.getPlateau()[i][4].setPion(eleph);
            
           Crocodile croc =new Crocodile();
            croc.setCouleur("v");
            plateau1.getPlateau()[i][5].setPion(croc);
            
            if(i==1||i==2||i==3){
            Zebre zeb=new Zebre();
            zeb.setCouleur("r");
            plateau1.getPlateau()[i][6].setPion(zeb);
            }
            else{
                Zebre zebCache=new Zebre();
            zebCache.setCouleur("r");
            zebCache.setCache(true);
            plateau1.getPlateau()[i][6].setPion(zebCache);
            }
            
            
            
        }
        System.out.println(plateau1);
        ArrayList<Joueur> gagnant=new ArrayList<>();
        gagnant=jeu2.compterPoints();
        
        for(int i=0;i<gagnant.size();i++){
            System.out.println(gagnant.get(i).getPseudo() +"pts: "+gagnant.get(i).getNbPoint());
        }
        System.out.println(jeu2.getListJoueur().get(0).getPseudo()+":"+jeu2.getListJoueur().get(0).getNbPoint()+" "+jeu2.getListJoueur().get(1).getPseudo()+":"+jeu2.getListJoueur().get(1).getNbPoint());*/
    }
    
}
