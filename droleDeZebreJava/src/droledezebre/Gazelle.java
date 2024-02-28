/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package droledezebre;

/**
 *
 * @author mae
 */
public class Gazelle extends Animaux {
    private boolean cache;
     public final void trouverIndicateur(){
       indicateur="G";
   }
      public Gazelle(){
       super();
       cache=false;
       trouverIndicateur();
   }
}
