/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package droledezebre;

/**
 *
 * @author mae
 */
public final class Gazelle extends Animal {
    private boolean cache;
    
    public void setCache(boolean a ){
        this.cache=a;
    }
    public boolean getCache(){
        return(this.cache);
    }
     
      public Gazelle(){
       super();
       cache=false;
       indicateur="G";
       this.pts=2;
   }
}
