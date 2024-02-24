/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package droledezebre;

import java.util.ArrayList;

/**
 *
 * @author mae
 */
public class Joueur implements EntiteJeu {
    private String pseudo;
    private int nbPoint;
    private char couleur;
    private ArrayList<Animaux> main;
    private boolean inauguration;
    
    public String getPseudo(){
        return(this.pseudo);
    }
    public void init(){
        
    }
}
