public class MunchkinGame {
    public static void main(String[] args) {

        // Création d'un paquet
        Paquet paquet = new Paquet();

        paquet.creerCartes();

        // Mélange du paquet
        paquet.melanger();

        paquet.tirerCarteAleatoire("TRESORS");
        paquet.tirerCarteAleatoire("PORTE");
        paquet.tirerCarteAleatoire("PORTE");
        paquet.tirerCarteAleatoire("TRESORS");
        paquet.tirerCarteAleatoire("PORTE");
        paquet.tirerCarteAleatoire("TRESORS");
        paquet.tirerCarteAleatoire("PORTE");

    }
}
