/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package droledezebre;

/**
 *
 * @author mae
 */
public class Zebre extends Animaux {
   private boolean cache;
   public final void trouverIndicateur(){
       indicateur="Z";
   }
   public Zebre(){
       super();
       cache=false;
       trouverIndicateur();
   }
}
