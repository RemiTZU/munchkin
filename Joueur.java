import java.util.*;

public class Joueur {
    private String nom; // Ajout du nom du joueur
    private int niveau;
    private List<Carte> main;
    private List<Equipement> board;
    private Race race;
    private int minimumPourFuir;
    private int pantalon;
    private int chaussures;
    private int casque;
    private int armure;
    private int mains;
    private int gros;
    private List<Joueur> joueurs;
    private Paquet paquet;

    public Joueur(String nom, Paquet paquet) {
        this.nom = nom;
        this.main = new ArrayList<>();
        this.board = new ArrayList<>();
        this.niveau = 1; // Initialisation du niveau à 1
        this.race = new Race("pas de race", "");
        this.minimumPourFuir = 5;
        this.pantalon = 1;
        this.chaussures = 1;
        this.casque = 1;
        this.armure = 1;
        this.mains = 2;
        this.gros = 1;
        this.joueurs = new ArrayList<>();
        this.paquet = paquet;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        for (Joueur joueur : joueurs) {
            if (!joueur.getNom().equals(this.nom))
                this.joueurs.add(joueur);
        }
    }

    // Ajout de la méthode getNom
    public String getNom() {
        return nom;
    }

    // Ajout du niveau du joueur
    public int getNiveau() {
        return niveau;
    }

    public void gainNiveau(int gain) {
        niveau = niveau + gain;
        if (niveau < 1) {
            niveau = 1;
        }
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

    //----------------------------------------------------------//
    //Tout ce qui est combat

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
            System.out.println((i + 1) + ". " + monstresDansMain.get(i).getNom()+ " Niveau : "+monstresDansMain.get(i).getNiveau());
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

            System.out.println("Le joueur a joué un monstre : " + monstre.getNom());

            // Affronter le monstre
            affronterMonstre(monstre,calculerBonusTotal(),"");

            return 1;
        } else {
            System.out.println("Choix invalide. Le joueur ne joue pas de monstre.");
            return 0;
        }
    }

    // Ajout de la méthode affronteMonstre
    public void affronterMonstre(Monstre monstre,int bonusJoueur, String joueurAide) {
        if (monstre == null) {
            System.out.println("Pas de monstre à affronter.");
            return;
        }

        System.out.println("Le joueur affronte un monstre : " + monstre.getNom());

        int niveauMonstre = monstre.getNiveau();
        System.out.println("Niveau du monstre : " + niveauMonstre);
        System.out.println("Bonus du joueur : " + bonusJoueur);

        if  (race.getNom()=="TC"){
            if (niveauMonstre<6){
                System.out.println("Victoire!!!!!");
                gainNiveau(monstre.getNiveauVaincu());
                System.out.println("Le joueur gagne " + monstre.getNiveauVaincu() + " niveaux.");
                System.out.println("Le joueur pioche " + monstre.getCarteVaincue() + " cartes.");
                for (int i = 0; i < monstre.getCarteVaincue(); i++) {
                    piocherCarte(paquet.tirerCarteAleatoire("TRESORS"),true);
                }
                return;
            }
        }

        // Vérifier si le joueur peut gagner le combat sans l'aide des autres joueurs
        if (bonusJoueur >= niveauMonstre) {
            System.out.println("Le joueur gagne le combat si rien ne change.");
            for (Joueur joueur : joueurs) {
                System.out.println("Le joueur "+joueur.getNom()+" veut-il contrer le joueur ?");

                // Ajout de l'option "Ne rien faire"
                System.out.println("0. Ne rien faire");

                System.out.println("1. Oui");
                System.out.println("2. Non");
                Scanner scanner = new Scanner(System.in);
                try {
                    System.out.print("Choix : ");
                    int choix = scanner.nextInt();
                    switch (choix) {
                        case 0:
                            // Le joueur ne fait rien, le combat est gagné
                            System.out.println("Le joueur "+joueur.getNom()+" ne fait rien.");
                            break;
                        case 1:
                            // Le joueur aide
                            System.out.println("Le joueur "+joueur.getNom()+" veut contrer le joueur "+getNom());
                            aiderMonstre(monstre, joueur,joueurAide);
                            return;
                        case 2:
                            // Le joueur n'aide pas
                            System.out.println("Le joueur "+joueur.getNom()+" ne souhaite pas bloquer le joueur "+getNom());
                            break;
                        default:
                            System.out.println("Choix invalide.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrée invalide.");
                }
            }

            // Le joueur gagne le combat
            System.out.println("Victoire!!!!!");
            gainNiveau(monstre.getNiveauVaincu());
            System.out.println("Le joueur gagne " + monstre.getNiveauVaincu() + " niveaux.");
            if (!Objects.equals(joueurAide, "")){
                System.out.println("Le joueur "+joueurAide+" a aidé le joueur, les récompenses sont partagée "+getNom());
                for (Joueur joueur : joueurs){
                    if (Objects.equals(joueur.getNom(), joueurAide)){
                        if (joueur.race.getNom()=="Energie"){
                            System.out.println("Le joueur "+joueur.getNom()+" peut piocher une carte trésor");
                            joueur.gainNiveau(1);
                        }
                        for (int i = 0; i < monstre.getCarteVaincue(); i++) {
                            if (monstre.getCarteVaincue()-i>1){
                                joueur.piocherCarte(paquet.tirerCarteAleatoire("TRESORS"),true);
                                piocherCarte(paquet.tirerCarteAleatoire("TRESORS"),true);
                            }else{
                                joueur.piocherCarte(paquet.tirerCarteAleatoire("TRESORS"),true);
                            }
                        }
                    }
                }
            }else{
                System.out.println("Le joueur pioche " + monstre.getCarteVaincue() + " cartes.");
                for (int i = 0; i < monstre.getCarteVaincue(); i++) {
                    piocherCarte(paquet.tirerCarteAleatoire("TRESORS"),true);
                }
            }
            
        } else {
            // Le joueur a besoin d'aide ou de jouer des cartes pour augmenter ses bonus
            Scanner scanner = new Scanner(System.in);
            int choix=3;
            while (choix!=0 && choix!=2 && choix!=1){
                System.out.println("Le joueur a besoin d'aide ou de jouer des sorts pour augmenter ses bonus.");
                System.out.println("Voulez vous demander de l'aide ou jouer des sorts ?");

                // Ajout de l'option "Ne rien faire"
                System.out.println("0. Ne rien faire");

                System.out.println("1. Demander de l'aide");
                System.out.println("2. Jouer des sorts");
                try {
                    System.out.print("Choix : ");
                    choix = scanner.nextInt();
                    switch (choix) {
                        case 0:
                            // Le joueur ne fait rien, le combat est perdu
                            perdreCombat(monstre,joueurAide);
                            break;
                        case 1:
                            // Le joueur demande de l'aide
                            demanderAide(monstre, bonusJoueur);
                            break;
                        case 2:
                            // Le joueur joue des sorts
                            jouerSortsPendantCombat(monstre, bonusJoueur,joueurAide);
                            break;
                        default:
                            System.out.println("Choix invalide.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrée invalide.");
                }
            }
        }
    }

    private void aiderMonstre(Monstre monstre, Joueur joueur, String joueurAide) {
        System.out.println("Le joueur "+joueur.getNom()+" veut aider le monstre "+monstre.getNom());
        System.out.println("Le joueur "+joueur.getNom()+" peut jouer des sorts pour augmenter ses bonus.");
        List<Sort> sortsDansMainJouable = new ArrayList<>();
        for (Carte carte : joueur.getMain()) {
            if (carte instanceof Sort) {
                if (((Sort) carte).getType() == 2) {
                    Sort sort = (Sort) carte;
                    sortsDansMainJouable.add(sort);
                }
            }
        }

        if (sortsDansMainJouable.isEmpty()) {
            System.out.println("Le joueur ne peut pas jouer de sorts.");
            System.out.println("Le joueur ne peut pas aider le monstre.");
            return;
        }

        System.out.println("Choisissez le sort à jouer\n0. Ne rien jouer");
        for (int i = 0; i < sortsDansMainJouable.size(); i++) {
            System.out.println((i + 1) + ". " + sortsDansMainJouable.get(i).getNom()+ " Type : "+sortsDansMainJouable.get(i).getType()+ " Effet : "+sortsDansMainJouable.get(i).getEffet());
        }

        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            if (choix == 0) {
                System.out.println("Le joueur ne joue rien.");
                return;
            }
            if (choix > 0 && choix <= sortsDansMainJouable.size()) {
                Sort sort = sortsDansMainJouable.get(choix - 1);
                jouerSortContreMonstre(sort, monstre);
                affronterMonstre(monstre, calculerBonusTotal(), joueurAide);

            } else {
                System.out.println("Choix invalide. Veuillez choisir une carte valide.");
                aiderMonstre(monstre, joueur,joueurAide);
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrée invalide. Veuillez choisir une carte valide.");
            aiderMonstre(monstre, joueur,joueurAide);
        }
    }

    private void demanderAide(Monstre monstre, int bonusJoueur){
        Scanner scanner = new Scanner(System.in);
        for (Joueur joueur : joueurs) {
            System.out.println("Le joueur "+joueur.getNom()+" veut-il aider ?");
            System.out.println("0. Non");
            System.out.println("1. Oui");
            try {
                System.out.print("Choix : ");
                int choix = scanner.nextInt();
                switch (choix) {
                    case 0:
                        break;
                    case 1:
                        // Le joueur demande de l'aide
                        System.out.println("Le joueur "+joueur.getNom()+" aide le joueur "+getNom());
                        affronterMonstre(monstre,bonusJoueur+calculerBonusTotal(),joueur.getNom());
                        return;
                    default:
                        System.out.println("Choix invalide.");
                        demanderAide(monstre, bonusJoueur);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide.");
                demanderAide(monstre, bonusJoueur);
                return;
            }
        }
        
        System.out.println("Le joueur n'a pas reçu d'aide.");
        affronterMonstre(monstre, bonusJoueur,"");
    }

    private void jouerSortsPendantCombat(Monstre monstre, int bonusJoueur, String joueurAide) {
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
        System.out.println("Choisissez le sort à jouer:");
        int choixSorts = scanner.nextInt();

        if (choixSorts!=0) {
            Sort sort = sortsDansMain.get(choixSorts - 1);
            jouerSortContreMonstre(sort, monstre);

            affronterMonstre(monstre, bonusJoueur,joueurAide);
        } else {
            // Le joueur ne joue pas de sorts, le combat est perdu
            System.out.println("Le joueur ne joue pas de sorts.");
            affronterMonstre(monstre, bonusJoueur,joueurAide);
        }
    }

    private void perdreCombat(Monstre monstre, String joueurAide) {
        System.out.println("Le combat est perdu :/");
        if (!Objects.equals(joueurAide, "")){
            System.out.println("Le joueur "+joueurAide+" a aidé le joueur pendant le combat, les deux joueurs peuvent essayer de fuir");
            for (Joueur joueur : joueurs){
                if (Objects.equals(joueur.getNom(), joueurAide)){
                    joueur.fuir(monstre);
                }
            }
            fuir(monstre);
        }else{
            System.out.println("Le joueur peut essayer de fuir");
            fuir(monstre);
        }
    }

    // Ajout de la méthode fuir et des incidents facheux si le joueur ne réussi pas à fuir
    public void fuir(Monstre monstre){
        System.out.println("Lancer de dé pour fuir");
        int de = (int) (Math.random() * 6 + 1);
        System.out.println("Le joueur "+getNom() + " a fait un "+de);
        if (race.getNom()=="Mécanique"){
            minimumPourFuir=minimumPourFuir-2;
        }
        if (race.getNom() == "IMSI"){
            minimumPourFuir=minimumPourFuir-1;
        }
        if (de >= minimumPourFuir){
            System.out.println("Le joueur "+getNom() + " a réussi à fuir");
            if (race.getNom()=="EDIM"){
                System.out.println("Le joueur "+getNom() + " peut piocher une carte trésor");
                piocherCarte(paquet.tirerCarteAleatoire("TRESORS"),true);
            }
        }else{
            System.out.println("Le joueur "+getNom() + " n'a pas réussi à fuir");
            System.out.println("L'effet du monstre s'applique");
            if (Objects.equals(monstre.getIncidentFacheux(), "-2 niveaux")){
                System.out.println("Le joueur perd 2 niveaux");
                gainNiveau(-2);
            }else if (Objects.equals(monstre.getIncidentFacheux(), "-1 niveau")){
                System.out.println("Le joueur perd 1 niveau");
                gainNiveau(-1);
            }else if (Objects.equals(monstre.getIncidentFacheux(), "Perte de tous les équipements")){
                System.out.println("Le joueur perd tous ses équipements");
                board.clear();
            }else if (Objects.equals(monstre.getIncidentFacheux(), "Perte d'un équipement aléatoire")){
                System.out.println("Le joueur perd un équipement aléatoire");
                int index = (int) (Math.random() * board.size());
                board.remove(index);
                System.out.println("L'équipement "+board.get(index).getNom()+" a été retiré");
            }else if (Objects.equals(monstre.getIncidentFacheux(), "La mort")){
                System.out.println("Le joueur meurt");
                niveau = 1;
                board.clear();
                main.clear();
                for (int i = 0; i < 4; i++) {
                    piocherCarte(paquet.tirerCarteAleatoire("PORTE"),false);
                    piocherCarte(paquet.tirerCarteAleatoire("TRESORS"),false);
                }
            }else{
                System.out.println("Perte de un niveau");
                gainNiveau(-1);
            }

        }

    }

    public void jouerSortContreMonstre(Sort sort,Monstre monstre) {
        if (sort == null) {
            System.out.println("Pas de sort à jouer.");
            return;
        }
        System.out.println("Le joueur joue un sort : " + sort.getNom());
    
        switch (sort.getEffet()) {
    
            case "bonus au monstre":
                monstre.gainNiveau(sort.getBonus());
                break;
            case "double le monstre":
                monstre.gainNiveau(monstre.getNiveau());
                break;


            default:
                System.out.println("Effet du sort non défini.");
        }
    
        // Retirer le sort de la main du joueur
        main.remove(sort);
    }
    
    public void jouerSortContreJoueur(Sort sort,Joueur joueurCible) {
        if (sort == null) {
            System.out.println("Pas de sort à jouer.");
            return;
        }
        
        switch (sort.getEffet()) {
    
            case "Gain de 1 niveau":
                joueurCible.gainNiveau(1);
                System.out.println("Le joueur "+joueurCible.getNom()+" gagne 1 niveau");
                System.out.println("Le joueur "+joueurCible.getNom()+" est maintenant niveau "+joueurCible.getNiveau());
                break;
            case "Perte de 2 niveaux":
                joueurCible.gainNiveau(-2);
                System.out.println("Le joueur "+joueurCible.getNom()+" perd 2 niveaux");
                System.out.println("Le joueur "+joueurCible.getNom()+" est maintenant niveau "+joueurCible.getNiveau());
                break;
            case "Perte de 1 niveaux" :
                joueurCible.gainNiveau(-1);
                System.out.println("Le joueur "+joueurCible.getNom()+" perd 1 niveau");
                System.out.println("Le joueur "+joueurCible.getNom()+" est maintenant niveau "+joueurCible.getNiveau());
                break;
            case "Perte d'un équipement aléatoire":
                if (joueurCible.getBoard().isEmpty()){
                    System.out.println("Le joueur n'a pas d'équipement à perdre");
                    break;
                }else{
                    int index = (int) (Math.random() * joueurCible.getBoard().size());
                    joueurCible.getBoard().remove(index);
                    System.out.println("L'équipement "+joueurCible.getBoard().get(index).getNom()+" a été retiré");
                    break;
                }    
            default:
                System.out.println("Effet du sort non défini.");
        }
    
        // Retirer le sort de la main du joueur
        main.remove(sort);
    }

    //----------------------------------------------------------//
    //Tout ce qui est de jouer une carte pendant son tour (equipement ou sort)

    // Ajout de la méthode jouerCarte
    public int jouerCarte() {
        Scanner scanner = new Scanner(System.in);
        List<Sort> peutJouerSort = new ArrayList<>();
        List<Equipement> peutJouerEquipement = new ArrayList<>();
        List<Race> peutJouerRace = new ArrayList<>();
        int tailleSort, tailleEquipement, tailleRace = 0;
    
        for (Carte value : main) {
            if (value instanceof Equipement) {
                Equipement equipement = (Equipement) value;
                peutJouerEquipement.add(equipement);
            }
            if (value instanceof Sort) {
                if (((Sort) value).getType() == 0 || ((Sort) value).getType() == 1) {
                    Sort sort = (Sort) value;
                    peutJouerSort.add(sort);
                }
            }
            if (value instanceof Race) {
                Race race = (Race) value;
                peutJouerRace.add(race);
            }
        }
    
        if (peutJouerSort.isEmpty() && peutJouerEquipement.isEmpty() && peutJouerRace.isEmpty()) {
            System.out.println("Le joueur ne peut pas jouer de carte.");
            System.out.println("-----------------------------------------------");

            return 0;
        } else {
            while (true) {
                System.out.println("Choisissez une carte de votre main à jouer :");
                System.out.println("0. Ne rien jouer");
    
                for (tailleSort = 0; tailleSort < peutJouerSort.size(); tailleSort++) {
                    System.out.println((tailleSort + 1) + ". " + peutJouerSort.get(tailleSort).getNom() + ", Effet : " + peutJouerSort.get(tailleSort).getEffet());
                }
    
                for (tailleEquipement = 0; tailleEquipement < peutJouerEquipement.size(); tailleEquipement++) {
                    System.out.print((tailleEquipement + 1 + tailleSort) + ". " + peutJouerEquipement.get(tailleEquipement).getNom() + ", Type : " + peutJouerEquipement.get(tailleEquipement).getType() + ", Bonus : " + peutJouerEquipement.get(tailleEquipement).getBonus());
                    if (!peutJouerEquipement.get(tailleEquipement).getEffet().isEmpty()) {
                        System.out.println(", Effet : " + peutJouerEquipement.get(tailleEquipement).getEffet());
                    }else{
                        System.out.println("");
                    }
                }
    
                for (tailleRace = 0; tailleRace < peutJouerRace.size(); tailleRace++) {
                    System.out.println((tailleRace + 1 + tailleSort + tailleEquipement) + ". " + peutJouerRace.get(tailleRace).getNom() + ", Bonus : " + peutJouerRace.get(tailleRace).getBonus());
                }
    
                // Choix de la carte à jouer
                try {
                    System.out.print("Choix : ");
                    int choix = scanner.nextInt();
                    if (choix == 0) {
                        System.out.println("Le joueur ne joue rien.");
                        return 0;
                    }
                    if (choix > 0 && choix <= tailleSort) {
                        Sort sort = peutJouerSort.get(choix - 1);
                        jouerSortChoisirJoueur(sort);
                        return 1;
                    }
                    if (choix > tailleSort && choix <= tailleSort + tailleEquipement) {
                        Equipement equipement = peutJouerEquipement.get(choix - 1 - tailleSort);
                        if (!verifierContrainteEquipement(equipement)) {
                            continue;
                        }
                        board.add(equipement);
                        main.remove(equipement);
                        System.out.println("Le joueur a joué un équipement : " + equipement.getNom());
                        break;
                    }
                    if (choix > tailleSort + tailleEquipement && choix <= tailleSort + tailleEquipement + tailleRace) {
                        if (race.getNom()!="pas de race"){
                            System.out.println("Vous avez deja une race : "+ race.getNom());
                            System.out.println("Voulez vous la remplacer ?");
                            System.out.println("Attention cela risque de vous enlever des équipements");
                            System.out.println("1. Oui");
                            System.out.println("2. Non");
                            Scanner scanner2 = new Scanner(System.in);
                            int choix2;
                            try {
                                choix2 = scanner2.nextInt();
                                if (choix2==1){
                                    race = peutJouerRace.get(choix - 1 - tailleSort - tailleEquipement);
                                    main.remove(race);
                                    break;
                                }
                                if (choix2==2){
                                    return jouerCarte();
                                }
                            }
                            catch (InputMismatchException e) {
                                return jouerCarte();
                            }
                            break;
                        }else{
                            race = peutJouerRace.get(choix - 1 - tailleSort - tailleEquipement);
                            main.remove(race);
                            verifierContrainteChangementClasse();
                            break;
                        }
                        
                    }
                } catch (InputMismatchException e) {
                    return jouerCarte();
                }
            }
            return 1;
        }
    }
    
    public void verifierContrainteChangementClasse(){
        for (Equipement equipement : board) {
            if  (!verifierContrainteEquipement(equipement)){
                board.remove(equipement);
            }
        }
    }

    //vérifier si le joueur peut joeur un equipement par rapport à sa race et aux emplacements disponibles
    public boolean verifierContrainteEquipement(Equipement equipement){
        Boolean peutJouer = true;
        boolean  classe = false;
        if (Objects.equals(equipement.getType(), "Pantalon")){
            if (pantalon == 0){
                peutJouer = false;
                System.out.println("Vous avez déjà un pantalon");
            }
        }
        if (Objects.equals(equipement.getType(), "Chaussures")){
            if (chaussures == 0){
                peutJouer = false;
                System.out.println("Vous avez déjà des chaussures");
            }
        }
        if (Objects.equals(equipement.getType(), "Casque")){
            if (casque == 0){
                peutJouer = false;
                System.out.println("Vous avez déjà un casque");
            }
        }
        if (Objects.equals(equipement.getType(), "Armure")){
            if (armure == 0){
                peutJouer = false;
                System.out.println("Vous avez déjà une armure");
            }
        }
        if (Objects.equals(equipement.getType(), "1 main")){
            if (mains == 0){
                peutJouer = false;
                System.out.println("Vous n'avez pas  assez de main");
            }
        }
        if (Objects.equals(equipement.getType(), "2 mains")){
            if (mains <= 1){
                peutJouer = false;
                System.out.println("Vous n'avez pas  assez de main");
            }
        }
        if (Objects.equals(equipement.getType(), "Gros")){
            if (gros == 0){
                peutJouer = false;
                System.out.println("Vous ne pouvez avoir qu'un seul objet gros");
            }
        }
        if ((!Objects.equals(equipement.getEffet(), ""))){
            if (equipement.getEffet()!=race.getNom()){
                peutJouer = false;
                System.out.println("Vous n'êtes pas de la bonne classe pour cet objet");
                classe = true;
            }
        }
        if (!peutJouer  && !classe){
            System.out.println("Le joueur ne peut pas jouer cet équipement.");
            System.out.println("Voulez vous défausser un équipement pour jouer celui-ci ?");
            System.out.println("1. Oui");
            System.out.println("2. Non");
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("Choix : ");
                int choix = scanner.nextInt();
                if (choix == 1) {
                    System.out.println("Choisissez une carte à défausser :");
                    // Affichage des cartes dans le board
                    for (int i = 0; i < board.size(); i++) {
                        System.out.println((i + 1) + ". " + board.get(i).getNom());
                    }
                    // Choix de la carte à défausser
                    try {
                        System.out.print("Choix : ");
                        int choix2 = scanner.nextInt();
                        if (choix2 > 0 && choix2 <= board.size()) {
                            Equipement equipement2 = board.get(choix2 - 1);
                            board.remove(equipement2);
                            System.out.println("Le joueur a défaussé l'équipement : " + equipement2.getNom());
                            if (Objects.equals(equipement2.getType(), "Pantalon")){
                                pantalon = 1;
                            }
                            if (Objects.equals(equipement2.getType(), "Chaussures")){
                                chaussures = 1;
                            }
                            if (Objects.equals(equipement2.getType(), "Casque")){
                                casque = 1;
                            }
                            if (Objects.equals(equipement2.getType(), "Armure")){
                                armure = 1;
                            }
                            if (Objects.equals(equipement2.getType(), "1 main")){
                                mains = mains + 1;
                            }
                            if (Objects.equals(equipement2.getType(), "2 mains")){
                                mains = mains + 2;
                            }
                            if (Objects.equals(equipement2.getType(), "Gros")){
                                gros = 1;
                            }

                            verifierContrainteEquipement(equipement);
                        } else {
                            System.out.println("Choix invalide. Le joueur ne défausse pas de carte.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrée invalide. Le joueur ne défausse pas de carte.");
                    }
                } else {
                    System.out.println("Choix invalide. Le joueur ne défausse pas de carte.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide. Le joueur ne défausse pas de carte.");
            }
        }else{
            if (Objects.equals(equipement.getType(), "Pantalon")){
                pantalon = 0;
            }
            if (Objects.equals(equipement.getType(), "Chaussures")){
                chaussures = 0;
            }
            if (Objects.equals(equipement.getType(), "Casque")){
                casque = 0;
            }
            if (Objects.equals(equipement.getType(), "Armure")){
                armure = 0;
            }
            if (Objects.equals(equipement.getType(), "1 main")){
                mains = mains - 1;
            }
            if (Objects.equals(equipement.getType(), "2 mains")){
                mains = mains - 2;
            }
            if (Objects.equals(equipement.getType(), "Gros")){
                gros = 0;
            }
        }
        return peutJouer;
    }

    //Choix du joueur à qui jouer le sort
    public void jouerSortChoisirJoueur(Sort sort){
        System.out.println("Le joueur a joué le sort : " + sort.getNom());

        if  (sort.getType()==0 ){
            jouerSortContreJoueur(sort,this);
        }
        if  (sort.getType()==1 ){
            System.out.println("Choisissez un joueur à qui jouer le sort :");
            for (int i = 0; i < joueurs.size(); i++) {
                System.out.println((i + 1) + ". " + joueurs.get(i).getNom());
            }
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("Choix : ");
                int choix = scanner.nextInt();
                if (choix > 0 && choix <= joueurs.size()) {
                    Joueur joueur = joueurs.get(choix - 1);
                    jouerSortContreJoueur(sort,joueur);
                } else {
                    System.out.println("Choix invalide. ");
                    jouerSortChoisirJoueur(sort);
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide.");
                jouerSortChoisirJoueur(sort);
            }
        }
        

    }

    //----------------------------------------------------------//
    //Tout ce qui est pioche de carte et gestion de la main

    // Ajout de la méthode gererNombreCartesMain
    public void gererNombreCartesMain(List<Joueur> joueursCible) {
        int size =5;
        if (Objects.equals(race.getNom(), "Alternant")){
            size = 6;
        }
        Joueur joueurCible = joueursCible.get(0);
        while (main.size() > size) {
            System.out.println("La main du joueur dépasse "+size+" cartes.");
            if (joueursCible.isEmpty()){
                System.out.println("Le joueur le plus bas niveau n'a pas été trouvé.");
            }else if (joueursCible.size()==1){
                System.out.println("Le joueur doit donner une carte au joueur "+joueursCible.get(0).getNom());
            }else if(joueursCible.size()>1){
                System.out.println("Choisissez un joueur à qui donner une carte :");
                for (int i = 0; i < joueursCible.size(); i++) {
                    System.out.println((i + 1) + ". " + joueursCible.get(i).getNom());
                }
                Scanner scanner = new Scanner(System.in);
                try {
                    System.out.print("Choix : ");
                    int choix = scanner.nextInt();
                    if (choix > 0 && choix <= joueursCible.size()) {
                        joueurCible = joueursCible.get(choix - 1);
                    } else {
                        System.out.println("Choix invalide. Le joueur ne défausse pas de carte.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrée invalide. Le joueur ne défausse pas de carte.");
                }
            }

            System.out.println("Choisissez une carte à défausser :");
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
                    joueurCible.piocherCarte(carte,true);
                    System.out.println("Le joueur a donner la carte : " + carte.getNom());
                } else {
                    System.out.println("Choix invalide. Le joueur ne défausse pas de carte.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide. Le joueur ne défausse pas de carte.");
            }

        }
    }

    public void piocherCarte(Carte carte,boolean affichage) {
        main.add(carte);
        
        
        if (affichage){
            System.out.println("Le joueur a pioché la carte : " + carte.getNom());
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
        
    }

    //----------------------------------------------------------//
    //Tout ce qui est affichage

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


    //----------------------------------------------------------//
    //Tout ce qui est vente d'équipement et de sort

    public List<Equipement> getEquipementsMainVendable() {
        List<Equipement> equipements = new ArrayList<>();
        for (Carte carte : main) {
            if (carte instanceof Equipement) {
                if (((Equipement) carte).getPrix() > 0) equipements.add((Equipement) carte);
            }
        }
        return equipements;
    }

    public List<Sort> getSortsMainVendable() {
        List<Sort> sorts = new ArrayList<>();
        for (Carte carte : main) {
            if (carte instanceof Sort) {
                if (((Sort) carte).getPrix() > 0) sorts.add((Sort) carte);
            }
        }
        return sorts;
    }

    public List<Equipement> getEquipementsBoardVendable() {
        List<Equipement> equipements = new ArrayList<>();
        for (Equipement equipement : board) {
            if (equipement.getPrix() > 0) equipements.add(equipement);
        }
        return equipements;
    }

    public void vendreEquipementOuSort(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez un équipement ou un sort à vendre, pour pouvoir vendre le prix total doit dépasser 100euros :");
        
        List<Equipement> EquipementAVendreDeLaMain = new ArrayList<>();
        List<Equipement> EquipementAVendreDuBoard = new ArrayList<>();
        List<Sort> SortAVendre = new ArrayList<>();
        List<Equipement> EquipementVenduDeLaMain = new ArrayList<>();
        List<Equipement> EquipementVenduDuBoard = new ArrayList<>();
        List<Sort> SortVendu = new ArrayList<>();
        int somme = 0;

        // Ajout des équipements de la main à la liste des équipements à vendre
        for (Equipement equipement : getEquipementsMainVendable()) {
            EquipementAVendreDeLaMain.add(equipement);
        }
        for (Equipement equipement : getEquipementsBoardVendable()) {
            EquipementAVendreDuBoard.add(equipement);
        }
        for (Sort sort : getSortsMainVendable()) {
            SortAVendre.add(sort);
        }

        // Choix de la carte à vendre
        while (somme < 100 ) {
            try {
                int  i=0;
                System.out.println("0. Ne rien vendre");
                for (Equipement equipement : EquipementAVendreDeLaMain) {
                    System.out.println((i + 1) + ". " + equipement.getNom() + " Prix : " + equipement.getPrix());
                    i++;
                }
                for (Equipement equipement : EquipementAVendreDuBoard) {
                    System.out.println((i + 1) + ". " + equipement.getNom() + " Prix : " + equipement.getPrix());
                    i++;
                }
                int j=i;
                for (Sort sort : SortAVendre) {
                    System.out.println((i + 1) + ". " + sort.getNom() + " Prix : " + sort.getPrix());
                    i++;
                }
                System.out.print("Choix : ");
                int choix = scanner.nextInt();
                if (choix == 0) {
                    System.out.println("Le joueur ne vend rien.");
                    return;
                }
                if (choix > 0 && choix <= EquipementAVendreDeLaMain.size()) {
                    Equipement equipement = EquipementAVendreDeLaMain.get(choix - 1);
                    somme = somme + equipement.getPrix();
                    System.out.println("Le joueur a vendu un équipement : " + equipement.getNom());
                    EquipementAVendreDeLaMain.remove(equipement);
                    EquipementVenduDeLaMain.add(equipement);

                    if (somme >= 100) {
                        System.out.println("Le joueur a vendu pour plus de 100euros.");
                        niveau = niveau + 1;
                        System.out.println("Le joueur a gagné un niveau.");
                    }
                } else if (choix > EquipementAVendreDeLaMain.size() && choix <= EquipementAVendreDuBoard.size() + EquipementAVendreDeLaMain.size()) {
                    Equipement equipement = EquipementAVendreDuBoard.get(choix - EquipementAVendreDeLaMain.size() - 1);
                    somme = somme + equipement.getPrix();
                    System.out.println("Le joueur a vendu un équipement : " + equipement.getNom());
                    EquipementAVendreDuBoard.remove(equipement);
                    EquipementVenduDuBoard.add(equipement);

                    if (somme >= 100) {
                        System.out.println("Le joueur a vendu pour plus de 100euros.");
                        niveau = niveau + 1;
                        System.out.println("Le joueur a gagné un niveau.");
                    }
                } else if (choix > EquipementAVendreDuBoard.size() + EquipementAVendreDeLaMain.size() && choix <= EquipementAVendreDuBoard.size() + EquipementAVendreDeLaMain.size() + SortAVendre.size()) {
                    Sort sort = SortAVendre.get(choix - EquipementAVendreDuBoard.size() - EquipementAVendreDeLaMain.size() - 1);
                    somme = somme + sort.getPrix();
                    System.out.println("Le joueur a vendu un sort : " + sort.getNom());
                    SortAVendre.remove(sort);
                    SortVendu.add(sort);

                    if (somme >= 100) {
                        System.out.println("Le joueur a vendu pour plus de 100euros.");
                        niveau = niveau + 1;
                        System.out.println("Le joueur a gagné un niveau.");
                    }
                } else {
                    System.out.println("Choix invalide. Veuillez choisir une carte valide.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide. Veuillez choisir une carte valide.");
            }
        }

        if (somme >=100){
            for (Equipement equipement : EquipementVenduDuBoard){
                board.remove(equipement);
            }
            for (Sort sort : SortVendu){
                main.remove(sort);
            }
            for (Equipement equipement : EquipementVenduDeLaMain){
                main.remove(equipement);
            }
        }
    }
}