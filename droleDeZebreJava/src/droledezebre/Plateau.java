/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package droledezebre;

/**
 *
 * @author mae
 */
public class Plateau implements EntiteJeu{
    private Case[][] plateau=new Case[7][6];
    
    public Case[][] getPlateau(){
        return(this.plateau);
    }
    
    
    public void init(){
        
    }
}
