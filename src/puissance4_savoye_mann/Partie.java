/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4_savoye_mann;

/**
 *
 * @author enzom
 */
import java.util.Random;
import java.util.Scanner;
public class Partie {
    Joueur [] ListeJoueurs= new Joueur[2];
    Joueur joueurCourant;
    Grille Grillejeu= new Grille();// on creer une grille
    public void attribuerCouleursAuxJoueurs(){
        Random r = new Random();
        int i = r.nextInt(1);// on genere un nombre aleatoire entre 0 et 1
        String [] ListeCouleur={"rouge","jaune"};// on cree un tableau contenant les couleurs
        ListeJoueurs[0].Couleur=ListeCouleur[i];// on attribue au joueur 0 une couleur aleatoire
        if (ListeJoueurs[0].Couleur=="rouge"){
            ListeJoueurs[1].Couleur="jaune";// on attribue la couleur restante
        }
        else {
            ListeJoueurs[0].Couleur="rouge";
        }
    }
    
    public void initialiserPartie(){
        // la grille est deja creer aux niveaux des attributs de la partie
        Grillejeu.viderGrille();// on la vide
        String nom1;// 
        String nom2;
        Scanner sc;
        sc = new Scanner(System.in);
        System.out.println("tapez le nom du joueur 1");
        nom1=sc.nextLine();// on recupere le nom du joueur1
        sc = new Scanner(System.in);
        System.out.println("tapez le nom du joueur 2");
        nom2=sc.nextLine();// on recupere le nom du joueur 2
        ListeJoueurs[0].Nom=nom1;// on affecte les noms
        ListeJoueurs[1].Nom=nom2;
        Random r = new Random();
        for (int t=0;t<2;t++){// on va placer les 2 desintegrateurs et 2 trou noirs qui sont ensemble
            int i = r.nextInt(6);// on genere un nombre aleatoire entre 0 et 6
            int j = r.nextInt(7);// on genere un nombre aleatoire entre 0 et 7
            if(Grillejeu.placerTrouNoir(i,j)){   
                Grillejeu.placerDesintegrateur(i,j);
            }
            else{
                t--;
            }
        }
        for (int t=0;t<3;t++){// on va placer les 3 trou noirs restants
            int i = r.nextInt(6);
            int j = r.nextInt(7);
            if(Grillejeu.placerTrouNoir(i,j)){
                Grillejeu.placerTrouNoir(i,j);
            }
            else{
                t--;
            }
        }
        for (int t=0;t<3;t++){// on va placer les 3 desintegrateurs restants
            int i = r.nextInt(6);
            int j = r.nextInt(7);
            if(Grillejeu.presenceTrouNoir(i,j)==false && Grillejeu.placerDesintegrateur(i,j)){
                Grillejeu.placerDesintegrateur(i,j);
            }
            else{
                t--;
            }
        }
        
    }
}
