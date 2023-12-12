import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Paquet {
    private List<Carte> cartes;

    public Paquet() {
        this.cartes = new ArrayList<>();
    }

    public void ajouterCarte(Carte carte) {
        cartes.add(carte);
    }

    public void creerCartes() {
        // Création des Monstres

        Monstre tpRenduMaison = new Monstre("Tp rendu maison", 1, "", "Il vous laisse partir, il a pitié");
        Monstre tpRenduFinDuCours = new Monstre("Tp rendu fin du cours", 10, "", "Vous perdez 2 niveaux");
        Monstre virusInformatique = new Monstre("Virus informatique", 8, "+5 contre la branche info",
                "Vous perdez un équipement info, sinon -1 niveau");
        Monstre profMalhonete = new Monstre("Un prof un peu malhonnête", 14, "-2 à votre jet de fuite", "-2 niveaux");

        // Ajout des monstres au paquet
        ajouterCarte(tpRenduMaison);
        ajouterCarte(tpRenduFinDuCours);
        ajouterCarte(virusInformatique);
        ajouterCarte(profMalhonete);

        // Ajout des nouveaux équipements
        Equipement jogginsGris = new Equipement("Joggins gris", 1, "pantalon", "");
        Equipement chaussureDeSport = new Equipement("Chaussure de sport", 2, "chaussure", "Plus 1 pour fuir");
        Equipement packDeBiere = new Equipement("Pack de bière", 5, "gros, 1 mains", "");
        Equipement ordiSurpuissant = new Equipement("Ordi surpuissant", 5, "INFO, 1 main", "");

        // Ajout des équipements au paquet
        ajouterCarte(jogginsGris);
        ajouterCarte(chaussureDeSport);
        ajouterCarte(packDeBiere);
        ajouterCarte(ordiSurpuissant);

    }

    public void melanger() {
        Collections.shuffle(cartes);
    }

    public Carte tirerCarte() {
        if (cartes.isEmpty()) {
            return null; // Pas de carte à tirer
        }
        return cartes.remove(0);
    }

    // Méthodes d'affichage pour chaque type de carte

    public void afficherEquipement(Equipement equipement) {
        System.out.println("-----------------------------------------------");
        System.out.println("Equipement tiré : " + equipement.getNom());
        System.out.println("Bonus : " + equipement.getBonus());
        if (!equipement.getType().isEmpty()) {
            System.out.println("Type : " + equipement.getType());
        }
        if (!equipement.getEffet().isEmpty()) {
            System.out.println("Effet : " + equipement.getEffet());
        }
        System.out.println("-----------------------------------------------");
    }

    public void afficherSort(Sort sort) {
        System.out.println("Sort tiré : " + sort.getNom() + " (Puissance : " + sort.getPuissance() + ")");
    }

    public void afficherBonus(Bonus bonus) {
        System.out.println("Bonus tiré : " + bonus.getNom() + " (Description : " + bonus.getDescription() + ")");
    }

    public void afficherCarteMalediction(CarteMalediction malediction) {
        System.out.println("Carte Malediction tirée : " + malediction.getNom());
    }

    public void afficherMonstre(Monstre monstre) {
        System.out.println("-----------------------------------------------");
        System.out.println("Monstre tiré : " + monstre.getNom());
        System.out.println("Niveau: " + monstre.getNiveau());
        if (!monstre.getEffet().isEmpty()) {
            System.out.println("Effet : " + monstre.getEffet());
        }
        System.out.println("Incident Fâcheux : " + monstre.getIncidentFacheux());
        System.out.println("-----------------------------------------------");

    }

    public Carte tirerCarteAleatoire() {
        if (cartes.isEmpty()) {
            System.out.println("Le paquet est vide. Aucune carte à tirer.");
            return null;
        }

        // Sélection aléatoire d'une carte
        Random random = new Random();
        int indexCarteTiree = random.nextInt(cartes.size());
        Carte carteTiree = cartes.remove(indexCarteTiree);

        // Appel de la méthode d'affichage en fonction du type de carte
        if (carteTiree instanceof Equipement) {
            afficherEquipement((Equipement) carteTiree);
        } else if (carteTiree instanceof Sort) {
            afficherSort((Sort) carteTiree);
        } else if (carteTiree instanceof Bonus) {
            afficherBonus((Bonus) carteTiree);
        } else if (carteTiree instanceof CarteMalediction) {
            afficherCarteMalediction((CarteMalediction) carteTiree);
        } else if (carteTiree instanceof Monstre) {
            afficherMonstre((Monstre) carteTiree);
        } else {
            System.out.println("Carte tirée : " + carteTiree.getNom());
        }

        return carteTiree;
    }
}



