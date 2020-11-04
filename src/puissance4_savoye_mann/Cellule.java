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
public class Cellule {
    Jeton jetonCourant;
    boolean  trouNoir;
    boolean desintegrateur;
    
    public Cellule(){
        jetonCourant=null;
        trouNoir=false;
        desintegrateur=false;// on construit le constructeur
    }
    public boolean affecterJeton(Jeton jeton){
        if (jetonCourant==null){
            jetonCourant=jeton;
            return true;//si la cellule est vide on lui met maintenant le jetonn dedans
        }
        else {
            return false;// si la cellule est deja occupée on renvoie false
        }
    }
    public Jeton recupererJeton(){
        return jetonCourant;
    }
    public boolean supprimerJeton(){
        if (jetonCourant==null){
            return false;// renvoie faux si la cellule est vide 
        }
        else {
            jetonCourant=null;// on supprimme le jeton courant.
            return true;
        }
    }
    public boolean placerTrouNoir(){
        if (trouNoir){
            return false;// si il y a deja un trou noir on return faux
        }
        else {
            trouNoir=true;// on attribue la valeur vraie trou noir
            return true;
        }
    }
    public boolean placerDesintegrateur(){
        if (desintegrateur){
            return false;// si il y a deja un desintegrateur on return faux
        }
        else {
            desintegrateur=true;// on attribue la valeur vraie a desintegrateur
            return true;
        }
    }
    public boolean presenceTrouNoir(){
        return trouNoir;// on return la valeur de trou noir c'est a dire vraie ou faux selon la presence ou non
    }
    public boolean presenceDesintegrateur(){
        return desintegrateur;// pareil que pour presence trou noir
    }
    public String lireCouleurDuJeton(){
        return jetonCourant.Couleur;// on renvoie la couleur du jeton courant
    }
    public boolean recupererDesintegrateur(){
        if (desintegrateur){
            desintegrateur=false;
            return true;// on supprime le desintegrateur present
        }
        else {
            return false;// on renvoie faux car il n'y a pas de desintegrateur present dans la cellule.
        }
    }
    public boolean activerTrouNoir(){
        if (trouNoir){
            jetonCourant=null;// on supprime le jeton
            trouNoir=false;// on enleve le trouNoir
            return true;
        }
        else {
            return false;// on retourne faux car il n'y a pas de trouNoir present
        }
    }
}
