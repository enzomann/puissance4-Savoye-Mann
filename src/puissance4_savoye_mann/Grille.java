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
    
    public boolean ajouterJetonDansColonne(Jeton jeton, int colonne){
        int i=0;
        while (i<6 && Cellules[i][colonne].jetonCourant==null){ 
            i++;  // i augmente de 1 si la cellule est vide cela signifie que l'on descend de 1 dans la colonne 
        }
        if (i==0) {
            return false;// car cela signifie que la colonne est pleine
        }
        else {
            Cellules[i][colonne].jetonCourant=jeton;
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
                    System.out.print("j ");
                }
                else {
                    if(Cellules[i][j].trouNoir){
                        System.out.print("tn ");// dans le cas ou il y a un trou noir ou un trou noir et un desintegrateurdans la cellule on affiche tn
                    }
                    else if(Cellules[i][j].desintegrateur && Cellules[i][j].trouNoir==false){
                        System.out.print("d ");// dans le cas ou il y a un desintegrateur sans trou noir on affiche d
                    }
                    else {
                        System.out.print("  ");// dans le cas ou la case est vide on affiche rien (un espace)
                    }
                }
            }
        }
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
        return Cellules[l][c].jetonCourant.Couleur;    
    }
    public boolean etreGagnantePourJoueur(Joueur joueur){
        int cpt;// compte le nombre de  jeton de la couleur du joueur    
        int cpt2;// compte le nombre de  jeton de la couleur du joueur dans une autre boucle plus affiné
        // verifions d'abord par colonne
        for (int j=0; j<7; j++){
            cpt=0;// on initialise le compteur à 0 sur chaque colonne
            for (int i=0; i<6;i++){ 
                if (lireCouleurDuJeton(i,j).equals(joueur.Couleur)){
                    cpt++;
                    if (cpt>=4){// des que le compteur depasse 4 on rentre dans le if
                        cpt2=0;// on initialise le deuxieme compteur
                        for (; i>(i-4);i--){// on va verifier si la case actuelle et les 3 cases d'au dessus sont de la couleur du joueur
                            if (lireCouleurDuJeton(i,j).equals(joueur.Couleur)){
                                cpt2++;
                            }
                            if (cpt2==4){
                                return true;// retourne vraie si le joueur a aligné 4 jetons sur une colonne
                            }
                        }
                    }
                }
            }
        }
        // verifions maintenant les lignes (on fait pareil que pour les colonnes, on intervertit seulement l'ordre des boucles i et j)
        for (int i=0; i<6;i++){
            cpt=0;// on initialise le compteur à 0 sur chaque colonne
            for (int j=0; j<7; j++){ 
                if (lireCouleurDuJeton(i,j).equals(joueur.Couleur)){
                    cpt++;
                    if (cpt>=4){// des que le compteur depasse 4 on rentre dans le if
                        cpt2=0;// on initialise le deuxieme compteur
                        for (; j>(i-4);j--){// on va verifier si la case actuelle et les 3 cases d'au dessus sont de la couleur du joueur
                            if (lireCouleurDuJeton(i,j).equals(joueur.Couleur)){
                                cpt2++;
                            }
                            if (cpt2==4){
                                return true;// retourne vraie si le joueur a aligné 4 jetons sur une colonne
                            }
                        }
                    }
                }
            }
        }
        // verifions maintenant les diagonales
        int cpt3;
        int l;// (l pour ligne) va permettre de prendre la valeur de i, et l'utiliser dans les boucles sans changer i
        int c;// (c pour colonne) va permettre de prendre la valeur de j l'utiliser dans les boucles sans changer j
        for (int j=0; j<7; j++){
            for (int i=0; i<6;i++){ 
                cpt3=0;
                if (lireCouleurDuJeton(i,j).equals(joueur.Couleur)){// pour chaque jeton de la couleur du joueur nous allons tester 3 jetons autour de celui ci dans deux directions differentes
                    cpt3++;
                    l=i;
                    c=j;        
                    
                    while ( l<(i+2) || c<(j+2)){// on teste deux jetons en dessous a droites de celui de depart
                        l++;
                        c++;
                        if (lireCouleurDuJeton(l,c).equals(joueur.Couleur)){
                            cpt3++;
                        }    
                    }
                    l=i;// on se replace au niveau du jeton de depart
                    c=j;
                    while ( l>(i-1) || c>(j-1)){// on teste le jeton au dessus a gauche 
                        l--;
                        c--;
                        if (lireCouleurDuJeton(l,c).equals(joueur.Couleur)){
                            cpt3++;
                        }    
                    }
                    if (cpt3==4){// actuellement on a teste les trois jetons autour de celui de depart sur la diagonales descendant de gauche vers droite
                        return true;// si ces trois jetons autours de celui de depart sont jaune alors notre compteur vaut 4 
                    }
                    // on va maintenant regarder la diagonale montant de gauche vers droite
                    // similaire a l'autre diagonale 
                    cpt3=1;
                    l=i;
                    c=j;
                    while ( l>(i-2) || c<(j+2)){
                        l--;
                        c++;
                        if (lireCouleurDuJeton(l,c).equals(joueur.Couleur)){
                            cpt3++;
                        }    
                    }
                    l=i;
                    c=j;
                    while ( l<(i+1) || c>(j-1)){
                        l++;
                        c--;
                        if (lireCouleurDuJeton(l,c).equals(joueur.Couleur)){
                            cpt3++;
                        }    
                    }
                    if (cpt3==4){
                        return true;
                    }
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
                        Cellules[i][j].jetonCourant.Couleur=Cellules[i-1][j].jetonCourant.Couleur;
                    }
                }
            }
        }
    }
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
    
    public boolean supprimerJeton(int i,int j){
        return Cellules[i][j].supprimerJeton();
    }
    public Jeton recupererJeton(int i,int j){
        return Cellules[i][j].recupererJeton();
    }
}
