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
   protected String indicateur; 
   
   public String getIndicateur(){
       return(this.indicateur);
   }
   public abstract void trouverIndicateur();
}
