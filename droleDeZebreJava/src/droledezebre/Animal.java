/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package droledezebre;

/**
 *
 * @author mae
 */
public abstract class Animal extends Pion{
   
    protected int pts;
    
    public String toString(){
       return(this.indicateur+this.couleur);
    }
    public int getPts(){
        return(this.pts);
    }
}
