import java.util.*;

public class Joueur {
    private String nom; // Ajout du nom du joueur
    private int niveau;
    private List<Carte> main;
    private List<Equipement> board;
    private Race race;

    public Joueur(String nom) {
        this.nom = nom;
        this.main = new ArrayList<>();
        this.board = new ArrayList<>();
        this.niveau = 1; // Initialisation du niveau à 1
        this.race = null;

    }

    // Ajout de la méthode getNom
    public String getNom() {
        return nom;
    }

    // Ajout de la méthode affronteMonstre
    public void affronterMonstre(Monstre monstre,int bonusJoueur) {
        if (monstre == null) {
            System.out.println("Pas de monstre à affronter.");
            return;
        }

        System.out.println("Le joueur affronte un monstre : " + monstre.getNom());

        int niveauMonstre = monstre.getNiveau();

        // Vérifier si le joueur peut gagner le combat sans l'aide des autres joueurs
        if (bonusJoueur >= niveauMonstre) {
            System.out.println("Le joueur gagne le combat si rien ne change.\nVos adveraires veulent ils vous empêcher de gagner ?");


            // Ajouter ici la logique pour récompenser le joueur (gagner des niveaux, trésors, etc.)
        } else {
            // Le joueur a besoin d'aide ou de jouer des cartes pour augmenter ses bonus
            demanderAideOuBoostSorts(monstre, bonusJoueur);
        }
    }

    private void demanderAideOuBoostSorts(Monstre monstre, int bonusJoueur) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Le joueur a besoin d'aide ou de jouer des sorts pour augmenter ses bonus.");

        // Le joueur peut demander de l'aide à un autre joueur
        // (vous pouvez ajouter ici la logique pour demander de l'aide à d'autres joueurs)

        // Le joueur peut jouer des sorts pour augmenter ses bonus
        jouerSortsPendantCombat(monstre, bonusJoueur);
    }

    private void jouerSortsPendantCombat(Monstre monstre, int bonusJoueur) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Le joueur peut jouer des sorts pour augmenter ses bonus.");

        // Affichage des sorts dans la main
        List<Sort> sortsDansMain = new ArrayList<>();
        for (Carte carte : main) {
            if (carte instanceof Sort) {
                sortsDansMain.add((Sort) carte);
            }
        }

        for (int i = 0; i < sortsDansMain.size(); i++) {
            System.out.println((i + 1) + ". " + sortsDansMain.get(i).getNom());
        }

        // Choix des sorts à jouer
        System.out.println("Choisissez les sorts à jouer (séparés par des virgules, 0 pour ne rien jouer) :");
        String choixSorts = scanner.nextLine();

        if (!choixSorts.equals("0")) {
            // Séparer les choix de sorts
            String[] choixSortsArray = choixSorts.split(",");
            int bonusSorts = 0;

            // Calculer le bonus total des sorts joués
            for (String choix : choixSortsArray) {
                int indexSort = Integer.parseInt(choix) - 1;
                if (indexSort >= 0 && indexSort < sortsDansMain.size()) {
                    Sort sort = sortsDansMain.get(indexSort);
                    bonusSorts += sort.getBonus();
                    main.remove(sort);  // Retirer le sort de la main après l'avoir joué
                }
            }

            // Nouveau total des bonus du joueur
            int nouveauTotalBonus = bonusJoueur + bonusSorts;

            // Vérifier à nouveau si le joueur peut gagner le combat
            if (nouveauTotalBonus >= monstre.getNiveau()) {
                System.out.println("Le joueur gagne le combat après avoir joué des sorts !");
                // Ajouter ici la logique pour récompenser le joueur
            } else {
                // Le joueur a épuisé toutes les options, le combat est perdu
                perdreCombat(monstre);
            }
        } else {
            // Le joueur a choisi de ne rien jouer, le combat est perdu
            perdreCombat(monstre);
        }
    }

    private void perdreCombat(Monstre monstre) {
        // Ajouter ici la logique pour gérer la perte du combat
        System.out.println("Le joueur perd le combat contre le monstre.");
        // Vous pouvez appliquer des effets néfastes ou autres conséquences.
    }

    // Ajout de la méthode jouerMonstreEtCombattre
    public int jouerMonstreEtCombattre() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez un monstre de votre main à jouer :");

        // Ajout de l'option "Ne rien jouer"
        System.out.println("0. Ne rien jouer");

        // Affichage des monstres dans la main
        List<Monstre> monstresDansMain = new ArrayList<>();
        for (Carte carte : main) {
            if (carte instanceof Monstre) {
                monstresDansMain.add((Monstre) carte);
            }
        }

        for (int i = 0; i < monstresDansMain.size(); i++) {
            System.out.println((i + 1) + ". " + monstresDansMain.get(i).getNom());
        }

        // Choix du monstre à jouer
        int choix;
        try {
            System.out.print("Choix : ");
            choix = scanner.nextInt();
            if (choix == 0) {
                System.out.println("Le joueur ne joue rien.");
                return 0;
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrée invalide. Le joueur ne joue pas de monstre.");
            return 0;
        }

        if (choix > 0 && choix <= monstresDansMain.size()) {
            Monstre monstre = monstresDansMain.get(choix - 1);
            main.remove(monstre);

            // Ajouter le monstre à la liste des monstres à affronter
            affronterMonstre(monstre,calculerBonusTotal());

            System.out.println("Le joueur a joué un monstre : " + monstre.getNom());
            return 1;
        } else {
            System.out.println("Choix invalide. Le joueur ne joue pas de monstre.");
            return 0;
        }
    }

    public void jouerSort(Sort sort) {
        if (sort == null) {
            System.out.println("Pas de sort à jouer.");
            return;
        }

        System.out.println("Le joueur joue un sort : " + sort.getNom());

        // Ici, vous pouvez ajouter la logique de jeu du sort
        // ...

        // Retirer le sort de la main du joueur
        main.remove(sort);
    }

    // Ajout de la méthode jouerSort
    public int jouerCarte() {
        Scanner scanner = new Scanner(System.in);
        List<Carte> peutJouer = new ArrayList<>();
        for (Carte value : main) {
            if (value instanceof Equipement || value instanceof Sort || value instanceof Race) {
                peutJouer.add(value);
            }
        }
        if (peutJouer.isEmpty()) {
            System.out.println("Le joueur ne peut pas jouer de carte.");
            return 0;
        }else{
            System.out.println("Choisissez une carte de votre main à jouer :");
            System.out.println("0. Ne rien jouer");
            for (int i = 0; i < peutJouer.size(); i++) {
                System.out.println((i + 1) + ". " + peutJouer.get(i).getNom());
            }
        }
        // Choix de la carte à jouer
        try {
            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            if (choix == 0) {
                System.out.println("Le joueur ne joue rien.");
                return 0;
            }
            if (choix > 0 && choix <= peutJouer.size()) {
                Carte carte = peutJouer.get(choix - 1);
                if (carte instanceof Sort) {
                    jouerSort((Sort) carte);
                } else if (carte instanceof Equipement) {
                    board.add((Equipement) carte);
                    main.remove(carte);
                    System.out.println("Le joueur a joué un équipement : " + carte.getNom());
                } else if (carte instanceof Race) {
                    race = (Race) carte;
                    main.remove(carte);
                } else if (carte instanceof Monstre){
                    System.out.println("Le joueur ne peut pas jouer un monstre.");
                }
                return 1;
            } else {
                System.out.println("Choix invalide. Veuillez choisir une carte valide.");
                return jouerCarte();
            }
        } catch (InputMismatchException e) {
            return jouerCarte();
        }
    }

    // Ajout de la méthode gererNombreCartesMain
    public void gererNombreCartesMain(Joueur joueurCible) {
        int size =5;
        if (Objects.equals(race.getNom(), "Alternant")){
            size = 6;
        }
        while (main.size() > size) {
            System.out.println("La main du joueur dépasse "+size+" cartes. Choisissez une carte à donner :");

            // Affichage des cartes dans la main
            for (int i = 0; i < main.size(); i++) {
                System.out.println((i + 1) + ". " + main.get(i).getNom());
            }

            // Choix de la carte à défausser
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("Choix : ");
                int choix = scanner.nextInt();
                if (choix > 0 && choix <= main.size()) {
                    Carte carte = main.get(choix - 1);
                    main.remove(carte);
                    joueurCible.piocherCarte(carte);
                    System.out.println("Le joueur a donner la carte : " + carte.getNom());
                } else {
                    System.out.println("Choix invalide. Le joueur ne défausse pas de carte.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide. Le joueur ne défausse pas de carte.");
            }

        }
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
        if (Objects.equals(race.getNom(), "Informatique")){
            bonusTotal=bonusTotal+(getNiveau()*2);
        }else{
            bonusTotal=bonusTotal+getNiveau();
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
        System.out.println("Le joueur "+ getNom() +" a pioché une carte : " + carte.getNom());
        // Vérifier si la carte piochée est une race et si le joueur a déjà une race
        if (carte instanceof Race) {
            if (race == null) {
                race = (Race) carte;
                main.remove(carte);
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
