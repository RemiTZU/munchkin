import java.util.*;

public class Tour {
    private List<Joueur> joueurs;
    private Paquet paquet;

    public  Paquet getPaquet(){
        return paquet;
    }

    public Tour(int nbJoueurs) {
        paquet = new Paquet();
        paquet.creerCartes();
        paquet.melanger();
        joueurs = new ArrayList<>();
        System.out.println("Création de "+nbJoueurs+" joueurs.");
        demanderNomsJoueurs(nbJoueurs);
        for (Joueur joueur : joueurs) {
            for (int i = 0; i < 4; i++) {
                joueur.piocherCarte(paquet.tirerCarteAleatoire("PORTE"),false);
                joueur.piocherCarte(paquet.tirerCarteAleatoire("TRESORS"),false);
            }
            joueur.setJoueurs(joueurs);
            System.out.println("-----------------------------------------------");

            System.out.println("\nLe joueur " + joueur.getNom() + " a pioché 4 cartes porte et 4 cartes trésors.\nIl peut les jouer ou les garder dans sa main.");
            joueur.afficherJoueur();
            System.out.println("-----------------------------------------------");

            // Le joueur peut choisir de jouer une carte de sa main.
            int choix=0;
            do {
                choix = joueur.jouerCarte();
                System.out.println("-----------------------------------------------");
            } while (choix != 0);
        }
    }

    private void demanderNomsJoueurs(int nbJoueurs) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < nbJoueurs; i++) {
            System.out.print("Entrez le nom du joueur " + (i + 1) + " : ");
            String nom = scanner.nextLine();
            joueurs.add(new Joueur(nom,paquet));
        }
    }

    public void tour() {
        System.out.println("Début du tour.");
        // tour pour chaque joueur
        for (Joueur joueur : joueurs) {
            System.out.println("-----------------------------------------------");

            System.out.println("\nTour du joueur : " + joueur.getNom());
            joueur.afficherJoueur();
            System.out.println("-----------------------------------------------");

            // Le joueur peut choisir de jouer une carte de sa main.
            int choix=0;
            do {
                choix = joueur.jouerCarte();
                System.out.println("-----------------------------------------------");
            } while (choix != 0);

            // Pioche d'une carte porte
            System.out.println("Le  joueur "+ joueur.getNom() +" pioche d'une carte porte.");
            Carte cartePorte = paquet.tirerCarteAleatoire("PORTE"); 
            System.out.println("-----------------------------------------------");

            // Vérification du type de la carte porte
            if (cartePorte instanceof Monstre) {
                // Si c'est un monstre, le joueur doit l'affronter
                Monstre monstre = (Monstre) cartePorte;
                joueur.affronterMonstre(monstre,joueur.calculerBonusTotal(),"");
            } else{

                if (cartePorte instanceof Sort) {
                    // Si c'est un sort, le joueur doit le jouer
                    Sort sort = (Sort) cartePorte;
                    if (sort.getType() == 0) {
                        // Si c'est un sort qui s'applique au joueur, il est joué directement
                        joueur.jouerSortContreJoueur(sort, joueur);
                    }else {
                        joueur.piocherCarte(sort, true);
                    }
                } else {
                    // Sinon, la carte va dans la main du joueur
                    joueur.piocherCarte(cartePorte, true);
                }
                System.out.println("Le joueur n'a pas affronté de monstre pendant ce tour.");

                // Le joueur peut choisir de jouer un monstre de sa main et le combattre
                // ou de piocher une autre carte porte qui va directement dans sa main.
                demanderChoixJoueur(joueur);
                System.out.println("-----------------------------------------------");
            }

            // Le joueur peut choisir de jouer une carte de sa main.
            choix = 0;
            do {
                choix = joueur.jouerCarte();
                System.out.println("-----------------------------------------------");
            } while (choix != 0);
            
            
            // Le joueur peut choisir de vendre un équipement ou un sort
            int vente=0;
            while (vente!=2){
                try{
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Voulez-vous vendre un équipement ou un sort ?");
                    System.out.println("1. Oui\n2. Non");
                    int reponse = scanner.nextInt();
                    if (reponse == 1) {
                        joueur.vendreEquipementOuSort();
                    }
                    else if (reponse == 2) {
                        vente=2;
                    }
                    else{
                        System.out.println("Veuillez entrer un nombre valide.");
                    }
                }
                    catch (InputMismatchException e){
                    System.out.println("Veuillez entrer un nombre valide.");
                }
                System.out.println("-----------------------------------------------");
            }
            
            // A la fin de son tour, le joueur doit avoir maximum 5 cartes dans sa main.
            distribuerCartesEnTrop(joueur);

            // Affichage du joueur après son tour
            System.out.println("-----------------------------------------------");
            joueur.afficherJoueur();
            System.out.println("-----------------------------------------------");
        }

        System.out.println("\nFin du tour.");
    }

    private void distribuerCartesEnTrop(Joueur player) {
        // Trouver le joueur le plus bas niveau
        List<Joueur> joueursPlusBasNiveau = trouverJoueurPlusBasNiveau(player);

        player.gererNombreCartesMain(joueursPlusBasNiveau);
    }

    private List<Joueur> trouverJoueurPlusBasNiveau(Joueur joueurcourant) {
        List<Joueur> joueursSansJoueurCourant = new ArrayList<>(this.joueurs);
        joueursSansJoueurCourant.remove(joueurcourant);
        List<Joueur> joueurPlusBasNiveau = new ArrayList<>();
        int niveauPlusBas = 10;
        for (Joueur joueur : joueursSansJoueurCourant) {
            if (joueur.getNiveau() < niveauPlusBas) {
                niveauPlusBas = joueur.getNiveau();
                joueurPlusBasNiveau.clear();
                joueurPlusBasNiveau.add(joueur);
            } else if (joueur.getNiveau() == niveauPlusBas) {
                joueurPlusBasNiveau.add(joueur);
            }
        }
        return joueurPlusBasNiveau;        
    }

    private int demanderChoixJoueur(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Jouer un monstre de la main");
        System.out.println("2. Piocher une autre carte porte");
        int combat = 0;
        try {
            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            if (choix == 1) {
                // Jouer un monstre de sa main et le combattre
                combat=joueur.jouerMonstreEtCombattre();
                if (combat==0){
                    return demanderChoixJoueur(joueur);
                }
            } else if (choix == 2) {
                // Piocher une autre carte porte qui va dans la main du joueur
                joueur.piocherCarte(paquet.tirerCarteAleatoire("PORTE"), true);
            }
            return choix;
        } catch (InputMismatchException e) {
            return demanderChoixJoueur(joueur);
        }
    }
}