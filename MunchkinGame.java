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
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Veuillez entrer un nombre valide.");
            return demanderNombreJoueurs();
        }
    }
}
