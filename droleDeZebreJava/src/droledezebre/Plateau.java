/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package droledezebre;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mae
 */
public class Plateau {

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
                        retour = retour + " " + this.plateau[i][j].getTerrain() + " ";
                    }
                } else if (this.plateau[i][j].getPion() != null) {
                    if (this.plateau[i][j].getPion().getIndicateur().equals("Imp")) {

                        retour = retour + "Imp";

                    } else {
                        if (this.plateau[i][j].getPion() instanceof Gazelle) {
                            Gazelle gazelle = (Gazelle) this.plateau[i][j].getPion();
                            if (gazelle.getCache()) {
                                retour = retour + this.plateau[i][j].getPion() + this.getPlateau()[i][j].getTerrain() + "C";
                            } else {
                                retour = retour + this.plateau[i][j].getPion() + this.getPlateau()[i][j].getTerrain() + " ";
                            }
                        } else if (this.plateau[i][j].getPion() instanceof Zebre) {
                            Zebre zebre = (Zebre) this.plateau[i][j].getPion();
                            if (zebre.getCache()) {
                                retour = retour + this.plateau[i][j].getPion() + this.getPlateau()[i][j].getTerrain() + "C";
                            } else {
                                retour = retour + this.plateau[i][j].getPion()+ this.getPlateau()[i][j].getTerrain() + " ";
                            }
                        } else {
                            retour = retour + this.plateau[i][j].getPion() + this.getPlateau()[i][j].getTerrain() + " ";
                        }

                    }
                }

                if (j == 7) {
                    retour = retour + "\n";
                }
            }
        }

        return (retour);
    }

    public void premierePosImpala(Jeu jeu1) {//premier placement de l'impala Jones 
        boolean repCorrect = false;
        while (repCorrect == false) {
            System.out.println(jeu1.getListJoueur().get(1).getPseudo() + " Où voulez vous placer Impala Jones pour ce premier tour? n'oubliez pas que ça doit être une case au bord du plateau! ");

            try {
                System.out.println("Donnez les coordonnées x (lignes)de votre ImpalaJones, ");
                Scanner na = new Scanner(System.in);
                int x = na.nextInt() - 1; // le joueur numerote naturellement les lignes et les colonnes en commencant par 1 
                System.out.println("Donnez les coordonnées y(colonne) de votre ImplaJones ");
                Scanner va=new   Scanner(System.in);
                int y = va.nextInt() - 1;
                if ((x == 0 || y == 0 || x == 6 || y == 7) && (x <= 7 && y <= 8) && (x >= 0 && y >= 0) && !((x == 0 && y == 0) || (x == 6 && y == 0) || (x == 6 && y == 7) || (x == 0 && y == 7))) {
                    repCorrect = true;
                    ImpalaJones impala = new ImpalaJones();

                    this.getPlateau()[x][y].setPion(impala);
                } else {
                    System.out.println("Entrez des coordonnées au bord du plateau");
                }
            } catch (Exception e) {
                System.out.println("Veuillez inscrire des entrées valides ");
            }

        }
    }
    
    public boolean inauguration(int [] coordo,Joueur joueur){
        boolean plein=true;
        for(int i=0;i<7;i++){
            for(int j=0;j<8;j++){
                if(this.plateau[i][j].getTerrain()==this.plateau[coordo[0]][coordo[1]].getTerrain()){
                    if(this.plateau[i][j].getPion()==null){
                        plein=false;
                    }
                }
            }
        }
        if(plein){
            joueur.setInauguration(true);
            System.out.println("Pour avoir rempli le premier secteur, vous remportez le point inauguration");
            return(true);
        }
        else{
            return(false);
        }
    }

    public ArrayList<int[]> verifGazAutourCrocodile(int xCroc, int yCroc) {
        ArrayList<int[]> retour = new ArrayList<>();

        if (this.plateau[xCroc + 1][yCroc].getPion() instanceof Gazelle) {
            if (this.plateau[xCroc + 1][yCroc].getTerrain() != (this.plateau[xCroc][yCroc].getTerrain())) {
                int[] temp = {xCroc + 1, yCroc};
                retour.add(temp);
            }
        }
        if (this.plateau[xCroc - 1][yCroc].getPion() instanceof Gazelle) {
            if (this.plateau[xCroc - 1][yCroc].getTerrain() != (this.plateau[xCroc][yCroc].getTerrain())) {
                int[] temp = {xCroc - 1, yCroc};
                retour.add(temp);
            }
        }
        if (this.plateau[xCroc][yCroc + 1].getPion() instanceof Gazelle) {
            if (this.plateau[xCroc][yCroc + 1].getTerrain() != (this.plateau[xCroc][yCroc].getTerrain())) {
                int[] temp = {xCroc, yCroc + 1};
                retour.add(temp);
            }
        }
        if (this.plateau[xCroc][yCroc - 1].getPion() instanceof Gazelle) {
            if (this.plateau[xCroc][yCroc - 1].getTerrain() != (this.plateau[xCroc][yCroc].getTerrain())) {
                int[] temp = {xCroc, yCroc - 1};
                retour.add(temp);
            }
        }
        return (retour);
    }

    public void demanderQuelleGazchasser(ArrayList<int[]> coordoGaz, int xCroc, int yCroc) {
        if (coordoGaz.isEmpty()) {
            System.out.println("Aucune Gazelle n'est à chasser dans les alentours");
        } else {
            try {
                System.out.println("Quelle Gazelle voulez vous chasser? Choisissez ses coordonnées.");
                for (int i = 0; i < coordoGaz.size(); i++) {
                    System.out.println((i + 1) + ": Gazelle " + (i + 1) + ": ligne: " + (coordoGaz.get(i)[0] + 1) + " colonnes: " + (coordoGaz.get(i)[1] + 1));
                }
                System.out.println((coordoGaz.size() + 1) + ": Ne pas échanger les places ");
                boolean rep = false;
                while (!rep) {
                    Scanner sc = new Scanner(System.in);
                    int choixGaz = sc.nextInt();

                    if (choixGaz > 0 && choixGaz <= coordoGaz.size() + 1) {
                        if (choixGaz == coordoGaz.size() + 1) {
                            System.out.println("Votre crocodile décide d'épargner la gazelle aujourd'hui");
                        } else {
                            Crocodile croc = (Crocodile) this.plateau[xCroc][yCroc].getPion();
                            Gazelle gaz = (Gazelle) this.plateau[coordoGaz.get(choixGaz - 1)[0]][coordoGaz.get(choixGaz - 1)[1]].getPion();
                            this.plateau[coordoGaz.get(choixGaz - 1)[0]][coordoGaz.get(choixGaz - 1)[1]].setPion(croc);
                            this.plateau[xCroc][yCroc].setPion(gaz);
                            System.out.println("Mince, la gazelle s'est enfuie, mais vos places ont été échangées ");
                        }
                        rep = true;
                    } else {
                        System.out.println("Veuillez choisir le numero de la gazelle avec qui vous voulez échanger votre crocodile");
                    }
                }
            } catch (Exception e) {
                System.out.println("Entrez l'indice de la Gazelle que vous voulez échanger");
            }
        }

    }

    public void verifGazZebAutourLion(Jeu j1, int indiceJoueur, int xLi, int yLi) {//quand on pose un lion on vérifie si il y a une gazelle autour et on l'enlève si c'est la cas 
        if (this.plateau[xLi + 1][yLi].getPion() instanceof Gazelle || this.plateau[xLi - 1][yLi].getPion() instanceof Gazelle || this.plateau[xLi][yLi + 1].getPion() instanceof Gazelle || this.plateau[xLi][yLi - 1].getPion() instanceof Gazelle) {
            if (this.plateau[xLi + 1][yLi].getPion() instanceof Gazelle) {
                Gazelle pion = (Gazelle) this.plateau[xLi + 1][yLi].getPion();
                System.out.println("Votre Gazelle à l'emplacement [" + (xLi + 1) + ";" + yLi + "] vous à été retournée ");
                j1.getListJoueur().get(indiceJoueur).getMain().add(pion);
                this.plateau[xLi + 1][yLi].setPion(null);
            } else if (this.plateau[xLi - 1][yLi].getPion() instanceof Gazelle) {
                Gazelle pion = (Gazelle) this.plateau[xLi - 1][yLi].getPion();
                System.out.println("Votre Gazelle à l'emplacement [" + (xLi - 1) + ";" + yLi + "] vous à été retournée ");
                j1.getListJoueur().get(indiceJoueur).getMain().add(pion);
                this.plateau[xLi - 1][yLi].setPion(null);
            } else if (this.plateau[xLi][yLi + 1].getPion() instanceof Gazelle) {
                Gazelle pion = (Gazelle) this.plateau[xLi][yLi + 1].getPion();
                System.out.println("Votre Gazelle à l'emplacement [" + (xLi) + ";" + (yLi + 1) + "] vous à été retournée ");
                j1.getListJoueur().get(indiceJoueur).getMain().add(pion);
                this.plateau[xLi][yLi + 1].setPion(null);
            } else if (this.plateau[xLi][yLi - 1].getPion() instanceof Gazelle) {
                Gazelle pion = (Gazelle) this.plateau[xLi][yLi - 1].getPion();
                System.out.println("Votre Gazelle à l'emplacement [" + (xLi) + ";" + (yLi - 1) + "] vous à été retournée ");
                j1.getListJoueur().get(indiceJoueur).getMain().add(pion);
                this.plateau[xLi][yLi - 1].setPion(null);
            } else {
                System.out.println("Il n'y a pas de Gazelle autour de vous");
            }

        } else if (this.plateau[xLi + 1][yLi].getPion() instanceof Zebre || this.plateau[xLi - 1][yLi].getPion() instanceof Zebre || this.plateau[xLi][yLi + 1].getPion() instanceof Zebre || this.plateau[xLi][yLi - 1].getPion() instanceof Zebre) {
            if (this.plateau[xLi + 1][yLi].getPion() instanceof Zebre) {
                Zebre pion = (Zebre) this.plateau[xLi + 1][yLi].getPion();
                pion.setCache(true);
                this.plateau[xLi + 1][yLi].setPion(pion);
                System.out.println("Votre Zebre à l'emplacement [" + (xLi + 1) + ";" + yLi + "] s'est caché ");
            } else if (this.plateau[xLi - 1][yLi].getPion() instanceof Zebre) {
                Zebre pion = (Zebre) this.plateau[xLi - 1][yLi].getPion();
                pion.setCache(true);
                this.plateau[xLi - 1][yLi].setPion(pion);
                System.out.println("Votre Zebre à l'emplacement [" + (xLi - 1) + ";" + yLi + "] s'est caché ");
            } else if (this.plateau[xLi][yLi + 1].getPion() instanceof Zebre) {
                Zebre pion = (Zebre) this.plateau[xLi][yLi + 1].getPion();
                pion.setCache(true);
                this.plateau[xLi][yLi + 1].setPion(pion);
                System.out.println("Votre Zebre à l'emplacement [" + (xLi) + ";" + (yLi + 1) + "] s'est caché ");
            } else if (this.plateau[xLi][yLi - 1].getPion() instanceof Zebre) {
                Zebre pion = (Zebre) this.plateau[xLi][yLi - 1].getPion();
                pion.setCache(true);
                this.plateau[xLi][yLi - 1].setPion(pion);
                System.out.println("Votre Zebre à l'emplacement [" + (xLi) + ";" + (yLi - 1) + "] s'est caché ");
            } else {
                System.out.println("Il n'y a pas de zebre autour de vous ");
            }
        } else {
            System.out.println("Il n'y a ni zebre ni Gazelle autour de vous, votre lion repart bredouille");
        }
    }

    public void verifAnimalAgressif(int xGaZe, int yGaZe) { // verifie si il y a un animal agressif autour de la gazelle ou du zebre à poser et agit en consequence 
        if (this.plateau[xGaZe + 1][yGaZe].getPion() instanceof Lion || this.plateau[xGaZe - 1][yGaZe].getPion() instanceof Lion || this.plateau[xGaZe][yGaZe + 1].getPion() instanceof Lion || this.plateau[xGaZe - 1][yGaZe].getPion() instanceof Lion) {
            //on regarde si il y a un lion ou un crocodile dans les cases autour du pion 
            if (this.plateau[xGaZe][yGaZe].getPion() instanceof Gazelle) {
                Gazelle pion = (Gazelle) this.plateau[xGaZe][yGaZe].getPion();
                pion.setCache(true);
                this.plateau[xGaZe][yGaZe].setPion(pion);
                System.out.println("Votre Gazelle à l'emplacement [" + xGaZe + ";" + yGaZe + "] s'est cachée ");
            } else {
                Zebre pion = (Zebre) this.plateau[xGaZe][yGaZe].getPion();
                pion.setCache(true);
                this.plateau[xGaZe][yGaZe].setPion(pion);
                System.out.println("Votre Zebre à l'emplacement [" + xGaZe + ";" + yGaZe + "] s'est caché ");
            }
        }

    }

    public void init(int carte) {

        if (carte == 1 || carte == 2) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    if (i == 0 || j == 0 || i == 6 || j == 7) {
                        this.plateau[i][j].setTerrain(0);
                    }
                }
            }
            if (carte == 1) {
                for (int lignes = 1; lignes < 4; lignes++) {
                    this.plateau[lignes][1].setTerrain(1);
                }
                this.plateau[4][1].setTerrain(2);
                for (int lignes = 2; lignes < 5; lignes++) {
                    this.plateau[lignes][2].setTerrain(2);
                }
                this.plateau[2][3].setTerrain(2);
                for (int colonnes = 1; colonnes < 5; colonnes++) {
                    this.plateau[5][colonnes].setTerrain(3);
                }
                this.plateau[4][3].setTerrain(3);
                this.plateau[3][3].setTerrain(3);
                this.plateau[3][4].setTerrain(3);
                for (int colonnes = 4; colonnes < 6; colonnes++) {
                    this.plateau[4][colonnes].setTerrain(4);
                }
                this.plateau[5][5].setTerrain(4);
                for (int colonnes = 2; colonnes < 5; colonnes++) {
                    this.plateau[1][colonnes].setTerrain(5);
                }
                for (int colonnes = 4; colonnes < 6; colonnes++) {
                    this.plateau[2][colonnes].setTerrain(5);
                }
                this.plateau[3][5].setTerrain(5);
                for (int lignes = 3; lignes < 6; lignes++) {
                    this.plateau[lignes][6].setTerrain(5);
                }
                for (int colonnes = 5; colonnes < 7; colonnes++) {
                    this.plateau[1][colonnes].setTerrain(6);
                }
                this.plateau[2][6].setTerrain(6);
                System.out.println("Vous avez choisi la Reserve du President.");
            } else if (carte == 2) {
                for (int lignes = 1; lignes < 4; lignes++) {
                    this.plateau[lignes][1].setTerrain(1);
                }
                for (int lignes = 3; lignes < 5; lignes++) {
                    this.plateau[lignes][2].setTerrain(1);
                }
                for (int lignes = 1; lignes < 3; lignes++) {
                    this.plateau[lignes][2].setTerrain(2);
                }
                for (int lignes = 2; lignes < 5; lignes++) {
                    this.plateau[lignes][3].setTerrain(2);
                }
                for (int colonnes = 4; colonnes < 6; colonnes++) {
                    this.plateau[4][colonnes].setTerrain(2);
                }
                for (int lignes = 4; lignes < 6; lignes++) {
                    this.plateau[lignes][1].setTerrain(3);
                }
                this.plateau[5][2].setTerrain(3);
                for (int colonnes = 3; colonnes < 7; colonnes++) {
                    this.plateau[1][colonnes].setTerrain(4);
                }
                this.plateau[2][4].setTerrain(4);
                for (int colonnes = 4; colonnes < 6; colonnes++) {
                    this.plateau[3][colonnes].setTerrain(4);
                }
                for (int colonnes = 5; colonnes < 7; colonnes++) {
                    this.plateau[2][colonnes].setTerrain(5);
                }
                this.plateau[3][6].setTerrain(5);
                this.plateau[4][6].setTerrain(6);
                for (int colonnes = 3; colonnes < 7; colonnes++) {
                    this.plateau[5][colonnes].setTerrain(6);
                }
                System.out.println("Vous avez choisi la Savane.");
            }
        } else {
            System.out.println("Erreur : cette carte n'existe pas"); // Cette erreur ne devrait jamais s'afficher en vu des contrôles à l'initialisation de "carte"
        }
    }

    public int nbDeplaImpala() {
        int choix = 0;

        while (choix != 1 && choix != 2 && choix != 3) {
            try {
                System.out.println("De combien de cases souhaitez vous déplacer Impala Jones (1, 2 ou 3)");
                Scanner scan = new Scanner(System.in);
                choix = scan.nextInt();
            } catch (Exception all) {
            }
            if (choix != 1 && choix != 2 && choix != 3) {
                System.out.println("Veuillez faire un choix valide");
            }
        }
        return choix;
    }

    // Méthode qui permet de trouver Impala Jones sur le plateau
    public int[] trouverImpala() {

        int[] coord = new int[2];

        for (int lignes = 0; lignes < 7; lignes++) {
            for (int colonnes = 0; colonnes < 8; colonnes++) {
                if (this.plateau[lignes][colonnes].getPion() != null) {
                    if (this.plateau[lignes][colonnes].getPion().indicateur.equals("Imp")) {
                        coord[0] = lignes;
                        coord[1] = colonnes;
                        //System.out.println("dx : " + coord[0] + " /dy : " + coord[1]);
                    }
                }
            }
        }

        return coord;
    }


     // Méthode de base permettant de déplacer Impala de 1 2 ou 3 cases
    public void deplacerImpala(int nbDepla) {

        int dx = 0, dy = 0;

        dx = this.trouverImpala()[0];
        dy = this.trouverImpala()[1];

        ImpalaJones imp = new ImpalaJones();
        if (dx == 0 && dy + nbDepla < 7) {
            this.plateau[dx][dy + nbDepla].setPion(imp);
        } else if (dx == 0 && dy + nbDepla >= 7) {
            this.plateau[-6 + dy + nbDepla][7].setPion(imp);
        } else if (dy == 7 && dx + nbDepla < 6) {
            this.plateau[dx + nbDepla][dy].setPion(imp);
        } else if (dy == 7 && dx + nbDepla >= 6) {
            this.plateau[6][(6 - nbDepla) + (6 - dx)].setPion(imp);
        } else if (dx == 6 && dy - nbDepla > 0) {
            this.plateau[dx][dy - nbDepla].setPion(imp);
        } else if (dx == 6 && dy - nbDepla <= 0) {
            this.plateau[5 + (dy - nbDepla)][0].setPion(imp);
        } else if (dy == 0 && dx - nbDepla > 0) {
            this.plateau[dx - nbDepla][0].setPion(imp);
        } else if (dy == 0 && dx - nbDepla <= 0) {
            this.plateau[0][1 - dx + nbDepla].setPion(imp);
        }

        this.plateau[dx][dy].setPion(null);
    }

    // FONCTION GLOBALE DU DEPLACEMENT DIMPALA
    //
    // Modifier pour rajouter la méthode scanner du nb de déplacement.
    public void deplacementImpala() {

        int[] coordsImp = this.trouverImpala();

        // Partie qui vérifie qu'impala est plaçable dans les 3 prochaines cases
        int compteurVerif = 1;
        ArrayList<Integer> deplaPossibles = new ArrayList<>();
        while (compteurVerif != 4) {
            boolean deplaUser = false;
            if (coordsImp[0] == 0 && coordsImp[1] + compteurVerif < 7) {
                deplaUser = this.verifLigCoVide(coordsImp[0], coordsImp[1] + compteurVerif);
            } else if (coordsImp[0] == 0 && coordsImp[1] + compteurVerif >= 7) {
                deplaUser = this.verifLigCoVide((-6 + coordsImp[1] + compteurVerif), 7);
            } else if (coordsImp[1] == 7 && coordsImp[0] + compteurVerif < 6) {
                deplaUser = this.verifLigCoVide(coordsImp[0] + compteurVerif, coordsImp[1]);
            } else if (coordsImp[1] == 7 && coordsImp[0] + compteurVerif >= 6) {
                deplaUser = this.verifLigCoVide(6, (6 - compteurVerif) + (6 - coordsImp[0]));
            } else if (coordsImp[0] == 6 && coordsImp[1] - compteurVerif > 0) {
                deplaUser = this.verifLigCoVide(coordsImp[0], coordsImp[1] - compteurVerif);
            } else if (coordsImp[0] == 6 && coordsImp[1] - compteurVerif <= 0) {
                deplaUser = this.verifLigCoVide(5 + (coordsImp[1] - compteurVerif), 0);
            } else if (coordsImp[1] == 0 && coordsImp[0] - compteurVerif > 0) {
                deplaUser = this.verifLigCoVide(coordsImp[0] - compteurVerif, 0);
            } else if (coordsImp[1] == 0 && coordsImp[0] - compteurVerif <= 0) {
                deplaUser = this.verifLigCoVide(0, 1 - coordsImp[0] + compteurVerif);
            }

            if (deplaUser == true) {
                deplaPossibles.add((Integer) compteurVerif);
            }
            compteurVerif++;
        }

        //Partie qui demande à l'utilisateur le déplacement qu'il souhaite effectuer(si possible), ou deplace automatiquement Impala.
        if (!deplaPossibles.isEmpty()) {
            System.out.println("Comment voulez-vous déplacer Impala Jones ?");
            for (int i = 0; i < deplaPossibles.size(); i++) {
                System.out.println(i + 1 + " : Deplacer de " + deplaPossibles.get(i) + " case(s)");
            }
            int choix = 0;
            while (choix != 1 && choix != 2 && choix != 3) {
                try {
                    Scanner scan = new Scanner(System.in);
                    choix = deplaPossibles.get(scan.nextInt() - 1);
                } catch (Exception all) {
                    System.out.println("Veuillez faire un choix valide");
                }
            }
            this.deplacerImpala(choix);
        } else if (deplaPossibles.isEmpty()) {
            System.out.println("Impala va être automatiquement déplacé");
            this.deplaImpaAuto(this.prochainePosImpala());
            System.out.println(this);
            System.out.println("Impala a été déplacé automatiquement du au manque de place sur les 3 prochaines positions");
        }
    }
    // Méthode qui renvoie un tableau de coordonnées pour la prochaine position possible d'Impala
    // -- Enlever verif plateau plein car faite dans Jeu ?
    // -- Modifier les "second" while en for ?  
    public int[] prochainePosImpala() {
        int[] coordsImp = this.trouverImpala();

        //On vérifie que le plateau n'est pas plein. S'il ne l'est pas, on peut placer Impala quelque part
        if (this.plateauPlein()) {
            System.out.println("Le plateau est plein et Impala ne sera pas déplacé");
        } else if (!this.plateauPlein()) {
            boolean deplaPossible = false;
            int compteurVerif = 4;
            while (!deplaPossible || compteurVerif < 11) {
                //System.out.println("tourne inf premier while");
                if (coordsImp[0] == 0 && coordsImp[1] + compteurVerif < 7) {
                    if (this.verifLigCoVide(coordsImp[0], coordsImp[1] + compteurVerif)) {
                        coordsImp[1] += compteurVerif;
                        return (coordsImp);
                    }
                    compteurVerif++;

                    if (coordsImp[1] + compteurVerif == 7) {
                        coordsImp[0] = 1;
                        coordsImp[1] = 7;

                        int compteurLigne = 0;
                        while (!deplaPossible) {

                            if (this.verifLigCoVide(coordsImp[0] + compteurLigne, coordsImp[1])) {
                                coordsImp[0] += compteurLigne;
                                return (coordsImp);
                            }
                            deplaPossible = this.verifLigCoVide(coordsImp[0] + compteurLigne, coordsImp[1]);
                            compteurLigne++;
                            compteurVerif++;
                        }
                    }
                } else if (coordsImp[0] == 0 && coordsImp[1] + compteurVerif >= 7) {
                    coordsImp[0] = 1;
                    coordsImp[1] = 7;
                    int compteurLigne = 0;
                    while (!deplaPossible) {

                        if (this.verifLigCoVide(coordsImp[0] + compteurLigne, coordsImp[1])) {
                            coordsImp[0] += compteurLigne;
                            return (coordsImp);
                        }
                        deplaPossible = this.verifLigCoVide(coordsImp[0] + compteurLigne, coordsImp[1]);
                        compteurLigne++;
                        compteurVerif++;
                    }
                } else if (coordsImp[1] == 7 && coordsImp[0] + compteurVerif < 6) {
                    if (this.verifLigCoVide(coordsImp[0] + compteurVerif, coordsImp[1])) {
                        coordsImp[0] += compteurVerif;
                        return (coordsImp);
                    }
                    compteurVerif++;

                    if (coordsImp[0] + compteurVerif == 6) {
                        coordsImp[0] = 6;
                        coordsImp[1] = 7;

                        int compteurColonne = 0;
                        while (!deplaPossible) {

                            if (this.verifLigCoVide(coordsImp[0], coordsImp[1] - compteurColonne)) {
                                coordsImp[1] -= compteurColonne;
                                return (coordsImp);
                            }
                            deplaPossible = this.verifLigCoVide(coordsImp[0], coordsImp[1] - compteurColonne);
                            compteurColonne++;
                            compteurVerif++;
                        }
                    }
                } else if (coordsImp[1] == 7 && coordsImp[0] + compteurVerif >= 6) {
                    coordsImp[0] = 6;
                    coordsImp[1] = 7;
                    int compteurColonne = 0;
                    while (!deplaPossible) {

                        if (this.verifLigCoVide(coordsImp[0], coordsImp[1] - compteurColonne)) {
                            coordsImp[1] -= compteurColonne;
                            return (coordsImp);
                        }
                        deplaPossible = this.verifLigCoVide(coordsImp[0], coordsImp[1] - compteurColonne);
                        compteurColonne++;
                        compteurVerif++;
                    }
                } else if (coordsImp[0] == 6 && coordsImp[1] - compteurVerif > 0) {
                    if (this.verifLigCoVide(coordsImp[0], coordsImp[1] - compteurVerif)) {
                        coordsImp[1] -= compteurVerif;
                        return (coordsImp);
                    }
                    compteurVerif++;
                    if (coordsImp[1] - compteurVerif == 0) {
                        coordsImp[0] = 5;
                        coordsImp[1] = 0;
                        int compteurLigne = 0;
                        while (!deplaPossible) {
                            if (this.verifLigCoVide(coordsImp[0] - compteurLigne, coordsImp[1])) {
                                coordsImp[0] -= compteurLigne;
                                return (coordsImp);
                            }
                            deplaPossible = this.verifLigCoVide(coordsImp[0] - compteurLigne, coordsImp[1]);
                            compteurLigne++;
                            compteurVerif++;
                        }
                    }
                } else if (coordsImp[0] == 6 && coordsImp[1] - compteurVerif <= 0) {
                    coordsImp[0] = 5;
                    coordsImp[1] = 0;
                    int compteurLigne = 0;
                    while (!deplaPossible) {
                        if (this.verifLigCoVide(coordsImp[0] - compteurLigne, coordsImp[1])) {
                            coordsImp[0] -= compteurLigne;
                            return (coordsImp);
                        }
                        deplaPossible = this.verifLigCoVide(coordsImp[0] - compteurLigne, coordsImp[1]);
                        compteurLigne++;
                        compteurVerif++;
                    }
                } else if (coordsImp[1] == 0 && coordsImp[0] - compteurVerif > 0) {
                    if (this.verifLigCoVide(coordsImp[0] - compteurVerif, coordsImp[1])) {
                        coordsImp[0] -= compteurVerif;
                        return (coordsImp);
                    }
                    compteurVerif++;
                    if (coordsImp[0] - compteurVerif == 0) {
                        coordsImp[0] = 0;
                        coordsImp[1] = 1;
                        int compteurColonne = 0;
                        while (!deplaPossible) {
                            if (this.verifLigCoVide(coordsImp[0], coordsImp[1] + compteurColonne)) {
                                coordsImp[0] += compteurColonne;
                                return (coordsImp);
                            }
                            deplaPossible = this.verifLigCoVide(coordsImp[0], coordsImp[1] + compteurColonne);
                            compteurColonne++;
                            compteurVerif++;
                        }
                    }
                } else if (coordsImp[1] == 0 && coordsImp[0] - compteurVerif <= 0) {
                    coordsImp[0] = 0;
                    coordsImp[1] = 1;
                    int compteurColonne = 0;
                    while (!deplaPossible) {
                        if (this.verifLigCoVide(coordsImp[0], coordsImp[1] + compteurColonne)) {
                            coordsImp[0] += compteurColonne;
                            return (coordsImp);
                        }
                        deplaPossible = this.verifLigCoVide(coordsImp[0], coordsImp[1] + compteurColonne);
                        compteurColonne++;
                        compteurVerif++;
                    }
                }
            }
        }

        return coordsImp;
    }

    // Méthode qui permet de déplacer Impala avec sa case d'arrivée en paramètre.
    public void deplaImpaAuto(int[] arrivee) {
        this.plateau[this.trouverImpala()[0]][this.trouverImpala()[1]].setPion(null);
        this.plateau[arrivee[0]][arrivee[1]].setPion(new ImpalaJones());
    }

    //méthode qui détermine si le plateau est plein ou non 
    public boolean plateauPlein() {

        int compteur = 30;
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 7; j++) {
                if (this.plateau[i][j].getPion() != null) {
                    compteur--;
                }
            }
        }
        return (compteur == 0);
    }


    public boolean verifLigCoVide(int dx, int dy) {// Vérifie si la ligne ou la colonne correspondant à la case donnée en paramètre est vide 

        boolean deplaPossible = false;
        int casesVides = 0;

        if (dx == 0 || dx == 6) {
            casesVides = 5;
            for (int i = 1; i < 6; i++) {
                if (this.plateau[i][dy].getPion() != null) {
                    casesVides--;
                }
            }
        } else if (dy == 0 || dy == 7) {
            casesVides = 6;
            for (int i = 1; i < 7; i++) {
                if (this.plateau[dx][i].getPion() != null) {
                    casesVides--;
                }
            }
        }

        if (casesVides > 0) {
            deplaPossible = true;
        }

        return deplaPossible;
    }

    

    public ArrayList<int[]> trouverCoordonneesCasesDispo() {// trouve la liste des coordonnées x et y des cases où le pion peut être placé 
        ArrayList<int[]> retour = new ArrayList<>();
        int[] coordoImp = new int[2];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.plateau[i][j].getPion() != null) {
                    if (this.plateau[i][j].getPion() instanceof ImpalaJones) {
                        coordoImp[0] = i;
                        coordoImp[1] = j;
                    }
                }
            }
        }

        if (coordoImp[0] == 0 || coordoImp[0] == 6) {
            for (int k = 1; k < 6; k++) {
                if (this.plateau[k][coordoImp[1]].getPion() == null) {
                    int[] tab = new int[]{k, coordoImp[1]};
                    retour.add(tab);
                }
            }
        } else if (coordoImp[1] == 0 || coordoImp[1] == 7) {
            for (int l = 1; l < 7; l++) {
                if (this.plateau[coordoImp[0]][l].getPion() == null) {
                    int[] tab = new int[]{coordoImp[0], l};
                    retour.add(tab);
                }
            }
        }
        return (retour);
    }

    public void poserPion(Jeu j1, int nbIndiceJoueur, int[] choixJoueur, Animal choixPion) {// methode qui pose le pion au bon endroit du plateau selon le joueur 
        this.plateau[choixJoueur[0]][choixJoueur[1]].setPion(choixPion);
        if (choixPion instanceof Gazelle || choixPion instanceof Zebre) {
            verifAnimalAgressif(choixJoueur[0], choixJoueur[1]);
        } else if (choixPion instanceof Lion) {
            this.verifGazZebAutourLion(j1, nbIndiceJoueur, choixJoueur[0], choixJoueur[1]);
        } else if (choixPion instanceof Crocodile) {

            this.demanderQuelleGazchasser(this.verifGazAutourCrocodile(choixJoueur[0], choixJoueur[1]), choixJoueur[0], choixJoueur[1]);
        } else {
            System.out.println("il y a eu une erreur votre pion n'est pas valide");
        }

    }

    public void voirPlateau() {

        boolean entreeCorrect = false;
        while (!entreeCorrect) {
            try {
                System.out.println("Voulez vous voir le plateau? o/n");
                Scanner na = new Scanner(System.in);
                String rep = na.nextLine();
                if (rep.equals("o") || rep.equals("O")) {
                    System.out.println(this);
                    System.out.println("Maintenant veuillez choisir votre pion");
                    entreeCorrect = true;
                } else if (rep.equals("n") || rep.equals("N")) {
                    System.out.println("Veuillez donc choisir votre pion directement ");
                    entreeCorrect = true;
                } else {
                    System.out.println("Veuillez repondre par o pour oui et n pour non");
                    entreeCorrect = false;
                }
            } catch (Exception e) {
                System.out.println("Veuillez donner une entrée correct");
                entreeCorrect = false;
            }
        }
    }
    
   

    public Plateau() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                this.plateau[i][j] = new Case();
            }
        }
    }
}
