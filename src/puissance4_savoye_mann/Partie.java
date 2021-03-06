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
            ListeJoueurs[1].Couleur="rouge";
        }
    }
    
    public void initialiserPartie(){
        // la grille est deja creer aux niveaux des attributs de la partie
        Grillejeu.viderGrille();// on la vide
        // recuperons les noms des joueurs
        
        String nom1; 
        String nom2;
        
        Scanner sc;
        sc = new Scanner(System.in);
        System.out.println("tapez le nom du joueur 1");
        nom1=sc.nextLine();// on recupere le nom du joueur1
        sc = new Scanner(System.in);
        System.out.println("tapez le nom du joueur 2");
        nom2=sc.nextLine();// on recupere le nom du joueur 2
        Joueur j1 = new Joueur(nom1);
        Joueur j2 = new Joueur(nom2);
        ListeJoueurs[0]=j1;// on affecte J1 etJ2 au tableau
        ListeJoueurs[1]=j2;
        attribuerCouleursAuxJoueurs();
        // placons les trou noirs et desintegrateurs
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
            if(Grillejeu.presenceTrouNoir(i,j)==false && Grillejeu.placerDesintegrateur(i,j)){// si il n'y a pas de trou noirs on peut le placer
                Grillejeu.placerDesintegrateur(i,j);
            }
            else{
                t--;
            }
        }
        // on va distribuer les jetons aux joueurs
        for(int i=0;i<21;i++){
            Jeton jetonrouge=new Jeton("rouge");
            Jeton jetonjaune=new Jeton("jaune");
            if (ListeJoueurs[0].Couleur=="rouge"){
                ListeJoueurs[0].ajouterJeton(jetonrouge);
                ListeJoueurs[1].ajouterJeton(jetonjaune);
            }
            else {
                ListeJoueurs[1].ajouterJeton(jetonrouge);
                ListeJoueurs[0].ajouterJeton(jetonjaune);
            }
        }
    }
    public void debuterPartie(){
        // on initialise la partie
        initialiserPartie();
        // on tire au sort la couleur qui va commencer
        Random r = new Random();
        int i = r.nextInt(1);// on genere un nombre aleatoire entre 0 et 1
        String [] ListeCouleur={"rouge","jaune"};// on cree un tableau contenant les couleurs
        if (ListeJoueurs[0].Couleur.equals(ListeCouleur[i])){
            joueurCourant=ListeJoueurs[0];
            System.out.println(ListeJoueurs[0].Nom+" a vous de commencer"+" vous etes les "+ListeCouleur[i]);
        }
        else {
            joueurCourant=ListeJoueurs[1];
            System.out.println(ListeJoueurs[1].Nom+" a vous de commencer"+" vous etes les "+ListeCouleur[i]);
        }
        Grillejeu.afficherGrilleSurConsole();
        while (Grillejeu.etreGagnantePourJoueur(ListeJoueurs[0])==false && Grillejeu.etreGagnantePourJoueur(ListeJoueurs[1])==false){// tant que aucun des joueurs n'a gagne on reste dans la boucle, meme si la grille est remplie ou bien que les joueurs n'ont plus de jetons la partie continue car il y a la fonctionnalité recuperer un jeton.
            Scanner sc;
            int choix;
            do{
            sc = new Scanner(System.in);
            System.out.println("tapez 1 si vous souhaitez jouer un jeton, tapez 2 si vous souhaitez récuperer un jeton et tapez 3 si vous souhaitez désintegrer un jeton");
            choix=sc.nextInt();
            if (choix==3){
                if (joueurCourant.nombreDesintegrateurs==0){
                    System.out.println("veuillez choisir une autre action vous n'avez plus de desintegrateur");
                    choix=4;// choix prend une autre valeur pour refaire un tour de boucle
                }
            } 
            }while(choix!=1 && choix!=2 && choix!=3);
            
            switch(choix){
                
                case 1:
                    int j;
                    do {
                    sc = new Scanner(System.in);
                    System.out.println("tapez le numero de la  colonne que vous voulez choisir entre 1 et 7");
                    j=sc.nextInt();
                    j--;// le tableau commence a 0 et non 1 
                    }while(Grillejeu.colonneRemplie(j));//on verifit si cette colonne est pleine et tant que la colonne choisit est pleine on demande a l'utilisateur de changer de colonne
                    // on ajoute ensuite le jeton dans la colonne
                    Grillejeu.ajouterJetonDansColonne(joueurCourant.recupererJeton(), j,joueurCourant);
                    break;
                
                case 2 :
                    int c;//pour referencer la colonne
                    int l;//pour referencer la ligne
                
                    sc = new Scanner(System.in);
                    System.out.println("tapez la ligne du jeton que vous voulez récupérer entre 1 et 6");
                    l=sc.nextInt();// on recupere la ligne
                    l--;
                    sc = new Scanner(System.in);
                    System.out.println("tapez la colonne du jeton que vous voulez récupérer la premiere colonne entre 1 et 7");
                    c=sc.nextInt();// on recupere la colonne
                    c--;
                    Grillejeu.supprimerJeton(l,c);// on supprime le jeton
                    joueurCourant.nombreJetons_restants++;// on lui rajoute son jeton
                    Grillejeu.tasserGrille(c);// on oublie pas de tasser la grille
                    break;
                
                case 3 :
                    int cd;//pour referencer la colonne
                    int ld;//pour referencer la ligne
                
                    sc = new Scanner(System.in);
                    System.out.println("tapez la ligne du jeton que vous voulez désintégrer la premiere ligne entre 1 et 6");
                    ld=sc.nextInt();// on recupere la ligne
                    ld--;
                    sc = new Scanner(System.in);
                    System.out.println("tapez la colonne du jeton que vous voulez désintégrer la premiere colonne entre 1 et 7");
                    cd=sc.nextInt();// on recupere la colonne
                    cd--;
                    Grillejeu.supprimerJeton(ld, cd);// on supprime le jeton
                    Grillejeu.tasserGrille(cd);// on tasse la grille
                    if (Grillejeu.etreGagnantePourJoueur(ListeJoueurs[0]) && Grillejeu.etreGagnantePourJoueur(ListeJoueurs[1]) ){
                        System.out.println(joueurCourant.Nom+" vous avez perdu car vous avez provoqué un alignement de 4 jetons pour vous ET votre adversaire");
                    }
                    
                
            }
            // on change le joueur courant
            if (joueurCourant==ListeJoueurs[0]){
                joueurCourant=ListeJoueurs[1];
            }
            else {
                joueurCourant=ListeJoueurs[0];
            }
            Grillejeu.afficherGrilleSurConsole();// on affiche maintenant la grille 
            
        }
        if(Grillejeu.etreGagnantePourJoueur(ListeJoueurs[0])){
            System.out.println(ListeJoueurs[0].Nom+" vous avez gagné !!!");
        }
        if(Grillejeu.etreGagnantePourJoueur(ListeJoueurs[1])){
            System.out.println(ListeJoueurs[1].Nom+" vous avez gagné !!!");
        }
        
        
    }
    
}
