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
public class Jeton {
    String Couleur;
    public Jeton (String CouleurJeton){
        Couleur=CouleurJeton;
    }
    public String lireCouleur(){
        return Couleur;
    }
    
}
