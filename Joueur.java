import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private List<Carte> main;
    private List<Equipement> board;
    private int niveau;

    private Race race;

    public Joueur() {
        this.main = new ArrayList<>();
        this.board = new ArrayList<>();
        this.niveau = 1;
        this.race = null;
    }

    // Ajout du niveau du joueur
    public int getNiveau() {
        return niveau;
    }

    // Méthode pour calculer la somme des bonus des équipements dans le board
    public int calculerBonusTotal() {
        int bonusTotal = 0;
        for (Equipement equipement : board) {
            bonusTotal += equipement.getBonus();
        }
        return bonusTotal;
    }

    public List<Carte> getMain() {
        return main;
    }

    public List<Equipement> getBoard() {
        return board;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void piocherCarte(Carte carte) {
        main.add(carte);
        System.out.println("Le joueur a pioché une carte : " + carte.getNom());
        // Vérifier si la carte piochée est une race et si le joueur a déjà une race
        if (carte instanceof Race) {
            if (race == null) {
                race = (Race) carte;
                System.out.println("Le joueur a obtenu une nouvelle race : " + race.getNom());
            } else {
                System.out.println("Le joueur ne peut pas avoir plus d'une race, la carte est mise dans sa main.");
            }
        }

        // Appel de la méthode d'affichage en fonction du type de carte
        if (carte instanceof Equipement) {
            ((Equipement) carte).afficherEquipement();
        } else if (carte instanceof Sort) {
            ((Sort) carte).afficherSort();
        } else if (carte instanceof Monstre) {
            ((Monstre) carte).afficherMonstre();
        } else if (carte instanceof Race) {
            ((Race) carte).afficherRace();
        } else {
            System.out.println("Carte tirée : " + carte.getNom());
        }
    }

    public void jouerCarte(Carte carte) {
        if (main.contains(carte)) {
            main.remove(carte);
            if (carte instanceof Equipement) {
                board.add((Equipement) carte);
                System.out.println("Le joueur a joué un équipement : " + carte.getNom());
            } else {
                System.out.println("Le joueur a joué une carte : " + carte.getNom());
            }

            // Vérifier si la carte jouée est une race et mettre à jour la race du joueur
            if (carte instanceof Race) {
                race = (Race) carte;
            }
        } else {
            System.out.println("Le joueur ne peut pas jouer cette carte, car elle n'est pas dans sa main.");
        }
    }

    public void afficherMain() {
        System.out.println("Main du joueur :");
        for (Carte carte : main) {
            System.out.println("- " + carte.getNom());
        }
        System.out.println("Fin de la main.");
    }

    public void afficherBoard() {
        System.out.println("Board du joueur :");
        for (Equipement equipement : board) {
            equipement.afficherEquipement();
        }
        System.out.println("Fin du board.");
    }

    public void afficherJoueur() {
        System.out.println("Niveau du joueur : " + niveau);
        System.out.println("Bonus total du joueur : " + calculerBonusTotal());
        System.out.println("Race du joueur : " + (race != null ? race.getNom() : "Aucune race"));
        afficherMain();
        afficherBoard();
    }
}
