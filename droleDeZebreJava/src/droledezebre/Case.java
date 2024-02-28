/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package droledezebre;

/**
 *
 * @author mae
 */
public class Case {
   private Pion pion;
   private String terrain;
   
   public Pion getPion(){
       if(this.pion==null){
           return(null);
       }
       return(this.pion);
   }
   public void setPion(Pion newPion){
       this.pion=newPion;
   }
   public String getTerrain(){
       return(this.terrain);
   }
   public void setTerrain(String newTerrain){
       this.terrain=newTerrain;
   }
   public Case(){
       pion=null;
       terrain="0";
   }
}
