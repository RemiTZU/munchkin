public class MunchkinGame {
    public static void main(String[] args) {

        // Création d'un paquet
        Paquet paquet = new Paquet();

        paquet.creerCartes();

        // Mélange du paquet
        paquet.melanger();

        Joueur joueur = new Joueur();
        joueur.piocherCarte(paquet.tirerCarteAleatoire("PORTE"));
        joueur.piocherCarte(paquet.tirerCarteAleatoire("PORTE"));
        joueur.piocherCarte(paquet.tirerCarteAleatoire("PORTE"));
        joueur.piocherCarte(paquet.tirerCarteAleatoire("PORTE"));
        joueur.piocherCarte(paquet.tirerCarteAleatoire("TRESORS"));
        joueur.piocherCarte(paquet.tirerCarteAleatoire("TRESORS"));
        joueur.piocherCarte(paquet.tirerCarteAleatoire("TRESORS"));
        joueur.piocherCarte(paquet.tirerCarteAleatoire("TRESORS"));

        joueur.afficherJoueur();

        joueur.jouerCarte(joueur.getMain().get(0));
        joueur.jouerCarte(joueur.getMain().get(0));
        joueur.jouerCarte(joueur.getMain().get(0));
        joueur.jouerCarte(joueur.getMain().get(0));
        joueur.jouerCarte(joueur.getMain().get(0));
        joueur.jouerCarte(joueur.getMain().get(0));
        joueur.jouerCarte(joueur.getMain().get(0));
        joueur.jouerCarte(joueur.getMain().get(0));

        joueur.afficherJoueur();
    }

}
