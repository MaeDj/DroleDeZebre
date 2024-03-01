/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package droledezebre;

/**
 *
 * @author mae
 */
public class Plateau{

    private Case[][] plateau = new Case[7][8];

    public Case[][] getPlateau() {
        return (this.plateau);
    }

    public String toString() {
        String retour = "";
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i == 0 && j == 0) || (i == 0 && j == 7) || (i == 6 && j == 0) || (i == 6 && j == 7)) {
                    retour = retour + "=> ";

                } else if (this.plateau[i][j].getPion() == null) {
                    if (i == 0 || j == 0 || i == 6 || j == 7) {
                        retour = retour + " X ";
                    } else {
                        retour = retour + " " + this.plateau[i][j].getTerrain()+" ";
                    }
                } else {
                    if (this.plateau[i][j].getPion().getIndicateur().equals("Imp")) {
                        retour = retour + "Imp";

                    } else {
                        
                        retour = retour + this.plateau[i][j].getPion().getIndicateur() +this.plateau[i][j].getPion().getCouleur() + this.getPlateau()[i][j].getTerrain()+" ";
                    }
                }

                if (j == 7) {
                    retour = retour + "\n";
                }
            }
        }

        return (retour);
    }

    public void init() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0 || j == 0 || i == 6 || j == 7) {
                    this.plateau[i][j].setTerrain("X");
                }
            }
        }
        //inserer ici le choix du plateau 

    }

    public Plateau() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                this.plateau[i][j] = new Case();
            }
        }
    }
}
