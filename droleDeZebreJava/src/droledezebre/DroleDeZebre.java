/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package droledezebre;

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
        Plateau plateau=new Plateau();
        ImpalaJones impala=new ImpalaJones();
        Zebre zebre=new Zebre();
        zebre.couleur="v";
        plateau.getPlateau()[0][1].setPion(impala);
        plateau.getPlateau()[5][4].setPion(zebre);
        System.out.println(plateau);
        
        Jeu jeu1=new Jeu();
        jeu1.init(plateau,jeu1);
        
         plateau.deplacerImpala(plateau.nbDeplaImpala(), impala);
        System.out.println(plateau);
        plateau.deplacerImpala(plateau.nbDeplaImpala(), impala);
        System.out.println(plateau);
    }
    
}
