import java.util.*;

public class MunchkinGame {
    public static void main(String[] args) {
        // Demander le nombre de joueurs
        int nbJoueurs = demanderNombreJoueurs();

        // Création d'un nouveau tour avec le nombre de joueurs
        Tour tour = new Tour(nbJoueurs);

        // Commencer le tour
        tour.tour();
    }

    private static int demanderNombreJoueurs() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Entrez le nombre de joueurs : ");
            int nbJoueurs = scanner.nextInt();
            if (nbJoueurs < 2 || nbJoueurs > 6) {
                System.out.println("Le nombre de joueurs doit être compris entre 2 et 6.");
                return demanderNombreJoueurs();
            }
            return nbJoueurs;
        } catch (InputMismatchException e) {
            System.out.println("Veuillez entrer un nombre valide.");
            return demanderNombreJoueurs();
        }
    }
}