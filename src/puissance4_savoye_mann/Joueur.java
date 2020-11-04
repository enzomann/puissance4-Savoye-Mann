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
public class Joueur {
    String Nom;
    String Couleur;
    Jeton [] ListeJetons;
    int nombreDesintegrateurs;
    int nombreJetons_restants;
    
    public Joueur (String nom){
        Nom=nom;
        ListeJetons=new Jeton [21];
        nombreJetons_restants=0;
        nombreDesintegrateurs=0;
    }
    public void affecterCouleur(String couleur){
        Couleur=couleur;
    }
    public boolean  ajouterJeton(Jeton jeton){
        if (nombreJetons_restants<21){
            ListeJetons[nombreJetons_restants]=jeton;
            nombreJetons_restants++;
            return true;
        }
        else 
            return false;
    }
    public void obtenirDesintegrateur(){
        nombreDesintegrateurs++;
    }
    public boolean utiliserDesintegrateur(){
        if (nombreDesintegrateurs==0){
            return false;
        }
        else {
            nombreDesintegrateurs--;
            return true;
        }
    }
    public Jeton recupererJeton(){
        return ListeJetons[nombreJetons_restants--];
    }
}
