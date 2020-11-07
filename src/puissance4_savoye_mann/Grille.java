/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4_savoye_mann;

/**
 *
 * @author emann
 */
public class Grille {
    Cellule [][] Cellules=new Cellule[6][7];
    
    public Grille(){
        for (int i=0; i<6; i++){
            for (int j = 0; j < 7; j++) {
                Cellules[i][j] = new Cellule();
            }
        }
    }
    public boolean ajouterJetonDansColonne(Jeton jeton, int colonne, Joueur joueurcourant){// j'ai rajouté une entree de type joueur pour pouvoir incrementer son nombre de desintegrateur.
        int i=0;
        while (i<6 && Cellules[i][colonne].jetonCourant==null){ 
            i++; // i augmente de 1 si la cellule est vide cela signifie que l'on descend de 1 dans la colonne 
        }
        if (i==0) {
            return false;// car cela signifie que la colonne est pleine
        }
        else {
            i--;// on revient sur cellule du dessus car lorsque l'on sort de la boucle cela veut dire que la cellule du i actuel est soit occupee soit i a depacer la grille.
            Cellules[i][colonne].jetonCourant=jeton;
            if (presenceTrouNoir(i,colonne)){ // pour le 1.1
                Cellules[i][colonne].activerTrouNoir(); //pour le 1.1
            }
            if (presenceDesintegrateur(i,colonne)){// pour le 1.3
                Cellules[i][colonne].recupererDesintegrateur();
                joueurcourant.nombreDesintegrateurs++;// si il y a presence d'un desintegrateur on le supprime avec recupererdesintegrateur puis on incremente son nombre de desintegrateur qu'il a en sa possession.
            }
            return true;
        }
    }
    

    public boolean etreRemplie(){
        for (int j=0; j<7; j++){
            for (int i=0; i<6;i++){ 
                if (Cellules[i][j].jetonCourant==null){
                    return false;// des qu'une cellule est vide on sort des boucles et cela retourne faux
                }
            }
        }
        return true;// si on sort des boucles une fois qu'elles sont terminés cela signifie qu'il n'y avait aucune cases vides
    }
    public void viderGrille(){
        for (int j=0; j<7; j++){
            for (int i=0; i<6;i++){ 
                if (Cellules[i][j].jetonCourant!=null){
                    Cellules[i][j].jetonCourant=null;// des qu'une case est occupee on la vide
                }
            }
        }
    }
    public void afficherGrilleSurConsole(){
        for (int i=0; i<6;i++){
            System.out.println();
            for (int j=0; j<7; j++){ 
                if (Cellules[i][j].jetonCourant!=null){
                    if(Cellules[i][j].jetonCourant.Couleur=="rouge"){
                        System.out.print("R ");
                    }
                    else{
                        System.out.print("J ");
                    }
                }
                else {
                    if(Cellules[i][j].trouNoir){
                        System.out.print("T ");// dans le cas ou il y a un trou noir ou un trou noir et un desintegrateurdans la cellule on affiche T
                    }
                    else if(Cellules[i][j].desintegrateur && Cellules[i][j].trouNoir==false){
                        System.out.print("D ");// dans le cas ou il y a un desintegrateur sans trou noir on affiche D
                    }
                    else {
                        System.out.print("  ");// dans le cas ou la case est vide on affiche rien (un espace)
                    }
                }
            }
        }
        System.out.println();// on passe a la ligne a la fin de l'affichege de la grille
    }
    public boolean celluleOccupee(int l, int c){
        if (Cellules[l][c].jetonCourant==null){
            return false;
        }
        else {
            return true;
        }
    }
    public String lireCouleurDuJeton(int l, int c){
        if (celluleOccupee(l,c)){// on verifie que la cellule est bien occupee par un jeton
            return Cellules[l][c].jetonCourant.Couleur; 
        }
        else {
            return "pas de couleur";// si la cellule n'est pas occupee par un jeton il n'y a donc pas de couleur(cela me servira pour la fonction etregagnant)
        }
    }
    public boolean etreGagnantePourJoueur(Joueur joueur){
        int cpt;// compte le nombre de  jeton de la couleur du joueur    
        int cpt2;// compte le nombre de  jeton de la couleur du joueur dans une autre boucle plus affiné
        // verifions d'abord par colonne
        for (int j=0; j<4; j++){
            for (int i=0; i<6;i++){ // on se place dans les cellules formant un rectangle a gauche 
                if(joueur.Couleur.equals(lireCouleurDuJeton(i,j)) && joueur.Couleur.equals(lireCouleurDuJeton(i,j+1)) && joueur.Couleur.equals(lireCouleurDuJeton(i,j+2)) && joueur.Couleur.equals(lireCouleurDuJeton(i,j+3))){// 
                    return true;
                }
            }
        }
        // verifions maintenant les lignes (on fait pareil que pour les colonnes, on intervertit seulement l'ordre des boucles i et j)
        for (int j=0; j<7; j++){
            for (int i=0; i<3;i++){ // on se place dans les cellules formant un petit rectangle en haut a gauche 
                if(joueur.Couleur.equals(lireCouleurDuJeton(i,j)) && joueur.Couleur.equals(lireCouleurDuJeton(i+1,j)) && joueur.Couleur.equals(lireCouleurDuJeton(i+2,j)) && joueur.Couleur.equals(lireCouleurDuJeton(i+3,j))){// on verifie les diagonales descendantes
                    return true;
                }
            }
        }
        // verifions maintenant les diagonales
        
        
        for (int j=0; j<4; j++){
            for (int i=0; i<3;i++){ // on se place dans les cellules formant un petit rectangle en haut a gauche 
                if(joueur.Couleur.equals(lireCouleurDuJeton(i,j)) && joueur.Couleur.equals(lireCouleurDuJeton(i+1,j+1)) && joueur.Couleur.equals(lireCouleurDuJeton(i+2,j+2)) && joueur.Couleur.equals(lireCouleurDuJeton(i+3,j+3))){// on verifie les diagonales descendantes
                    return true;
                }
            }
        }
        for (int j=0; j<4; j++){
            for (int i=3; i<6;i++){ // on se place dans un rectangle en bas a gauche de la grille
                if(joueur.Couleur.equals(lireCouleurDuJeton(i,j)) && joueur.Couleur.equals(lireCouleurDuJeton(i-1,j+1)) && joueur.Couleur.equals(lireCouleurDuJeton(i-2,j+2)) && joueur.Couleur.equals(lireCouleurDuJeton(i-3,j+3))){// on verifie les diagonales montantes
                    return true;
                }
            }
        }
        // si on sort dans la boucle alors qu'elle est terminee cela veut dire qu'il n'y pas 4 jetons aligne
        return false;
    }
    public void tasserGrille(int j){
        for (int i=5;i>0;i--){// on part du bas de la grille et on remonte jusqu'a qu'une case soit vide et que celle du dessus ne le soit pas
            if (Cellules[i][j].jetonCourant==null && Cellules[i-1][j].jetonCourant!=null){//si une case est vide et que celle du dessus ne l'est pas pas on rentre dans la boucle for
                for (; i>0;i--){// cette boucle permet de baisser chaque jeton d'une case
                    if (Cellules[i-1][j].jetonCourant!=null){// on verifie bien que la case du dessus est une couleur sinon ce la ne sert a rien de faire l'instruction suivante
                        Cellules[i][j].jetonCourant=Cellules[i-1][j].jetonCourant;// on affecte le jeton courant a la case d'en dessous
                        Cellules[i-1][j].supprimerJeton();// maintenant que ce jeton est present dans la cellule d'en dessous on peut le supprimer
                    }
                }
            }
        }
    }
    
    // BD : méthode OK. Cependant, il sufit juste de regardder que la cellule la plus en haut est remplie.
    // si oui, du fait de la gravité, tous les jetons en dessous sont présents 
    public boolean colonneRemplie(int j){// j'ai rajouté une entrée car il faut bien selectionne la colonne a tester
        for (int i=0; i<6;i++){
            if(Cellules[i][j].jetonCourant==null){
                return false;// si il y a une case vide on sort de la methode et cela return faux
            }
        }
        return(true);// une fois arrivee a cette ligne cela veut dire qu'aucune case n'est vide.
    }
    public boolean placerTrouNoir(int i,int j){
        return Cellules[i][j].placerTrouNoir();
    }
    public boolean presenceTrouNoir(int i,int j){
        return Cellules[i][j].presenceTrouNoir();
    }
    
    public boolean placerDesintegrateur(int i,int j){
        return Cellules[i][j].placerDesintegrateur();
    }
    public boolean presenceDesintegrateur(int i,int j){
        return Cellules[i][j].presenceDesintegrateur();
    }
    
    public boolean supprimerJeton(int i,int j){
        return Cellules[i][j].supprimerJeton();
    }
    public Jeton recupererJeton(int i,int j){
        return Cellules[i][j].recupererJeton();
    }
}
