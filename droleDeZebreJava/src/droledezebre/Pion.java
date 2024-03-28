/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package droledezebre;

/**
 *
 * @author mae
 */
public abstract class Pion {
   protected String indicateur; //identifiant pour savoir de quel pion il s'agit 
    protected String couleur; // obligé de donner à tout pion une couleur car sinon on ne peut pas afficher correctement les pions 
   
   
   public String getIndicateur(){
       return(this.indicateur);
   }
   
    public String getCouleur(){
        return(this.couleur);
    }
    public void setCouleur(String v){ //uniquement test 
         this.couleur=v;
     }
}
