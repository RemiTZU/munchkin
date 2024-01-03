import java.util.*;

public class Paquet {
    private List<Carte> cartesTresors;
    private List<Carte> cartesPorte;

    public Paquet() {
        this.cartesTresors = new ArrayList<>();
        this.cartesPorte = new ArrayList<>();
    }

    // Ajouter les méthodes pour ajouter les cartes à leur liste respective
    public void ajouterCarteTresor(Carte carte) {
        cartesTresors.add(carte);
    }

    public void ajouterCartePorte(Carte carte) {
        cartesPorte.add(carte);
    }

    public void creerCartes() {
        // Création des Monstres

        Monstre tpRenduMaison = new Monstre("Tp rendu maison", 1, "", "Il vous laisse partir, il a pitié", 1, 1);
        Monstre tpRenduFinDuCours = new Monstre("Tp rendu fin du cours", 10, "", "-1 niveau", 1, 3);
        Monstre virusInformatique = new Monstre("Virus informatique", 8, "+5 contre la branche info", "Perte d'un équipement aléatoire", 1, 2);
        Monstre profMalhonete = new Monstre("Un prof un peu malhonnête", 14, "-2 à votre jet de fuite", "-2 niveaux", 1, 3);
        Monstre girardus = new Monstre("Girardus", 25, "t'insulte de segpa et t'enlève 1 niveau si tu le bats", "", 2, 3);
        Monstre tutu = new Monstre("TUTU", 40, "fuite impossible", "La mort", 3, 4);
        Monstre mp2 = new Monstre("MP^2", 15, "sa lenteur te permet de prendre deux cadeaux", "-2 niveaux", 1, 2);
        Monstre marcoco = new Monstre("Marcoco", 10, "son charme te fait oublier de prendre un cadeau", "", 1, 1);
        Monstre meyerMaitre = new Monstre("Meyer Maitre", 20, "+10 contre les TC", "-1 niveau", 2, 2);
        Monstre vuillamier = new Monstre("Vuillamier", 20, "-5 contre les meca", "-1 niveau", 2, 2);
        Monstre pma = new Monstre("PMA", 10, "-2 contre TC", "-1 niveau", 1, 1);

        // Ajout des monstres au paquet de cartes Porte
        for (Monstre monstre : Arrays.asList(tpRenduMaison, tpRenduFinDuCours, virusInformatique, profMalhonete, girardus, tutu, mp2, marcoco, meyerMaitre, vuillamier, pma)) {
            ajouterCartePorte(monstre);
        }

        // Ajout des nouveaux équipements
        Equipement jogginsGris = new Equipement("Joggins gris", 1, "pantalon", "",  20);
        Equipement chaussureDeSport = new Equipement("Chaussure de sport", 2, "chaussure", "", 60);//effet : +1 pour fuir
        Equipement packDeBiere = new Equipement("Pack de bière", 5, "gros", "", 30);
        Equipement ordiSurpuissant = new Equipement("Ordi surpuissant", 5, "1 main", "", 80);
        Equipement pinsBlousard = new Equipement("Pins blousard", 2,  "armure","",  20);
        Equipement chaussettesMcdo = new Equipement("Chaussettes Mcdo", 3, "chaussure", "", 50);
        Equipement casquette = new Equipement("Casquette", 2, "Casque", "", 40); //effet : -2 contre monstres type prof
        Equipement cafeNoisette = new Equipement("Café noisette", 1, "1 main", "", 60);
        Equipement cafeAllonge = new Equipement("Café allongé", 2, "1 main", "", 80);
        Equipement cafeCreme = new Equipement("Café crème", 3, "1 main", "", 100);
        Equipement cheveuxGras = new Equipement("Cheveux gras", 5, "Casque", "Informatique", 20); //faire INFO
        Equipement batonDeLaSagesse = new Equipement("Baton de la sagesse", 7, "1 main", "Boursier", 60); //Faire FEMININ
        Equipement outfitTrendy = new Equipement("Outfit trendy", 5, "armure", "Mécanique", 40); //faire feminin
        Equipement batonMauvaiseFoi = new Equipement("Baton de mauvaise foi", 5, "1 main", "IMSI", 40); //faire MASCULIN
        Equipement crayonsDeCouleur = new Equipement("Crayons de couleur", 3, "1 main", "EDIM", 40); //faire EDIM
        Equipement carteUTBM = new Equipement("Carte UTBM", 3, "collier", "", 80);
        Equipement calculetteNspire = new Equipement("Calculette Nspire", 6, "1 main", "", 100);// effet : détruit le monstre PMA

        // Ajout des nouveaux équipements au paquet de cartes Tresors
        for (Equipement equipement : Arrays.asList( chaussureDeSport, packDeBiere, ordiSurpuissant, chaussettesMcdo, casquette, cafeNoisette, cafeAllonge, cheveuxGras, batonDeLaSagesse, outfitTrendy, batonMauvaiseFoi, crayonsDeCouleur)) {
            ajouterCartePorte(equipement);
        }

        //Ajoute de certains équipements au paquet de cartes Porte
        for (Equipement equipement  : Arrays.asList(jogginsGris, calculetteNspire, carteUTBM, cafeCreme, pinsBlousard)) {
            ajouterCarteTresor(equipement);
        }

        // Ajout des nouvelles races
        Race informatique = new Race("Informatique", "Puanteur : niveau doublé en affrontant des monstres");
        Race mecanique = new Race("Mécanique", "Main pleine de graisse : +2 pour fuir");
        Race imsi = new Race("IMSI", "Baratineur, +1 pour fuir");
        Race edim = new Race("EDIM", "Fuite à succès : quand réussi à fuir, prend 1 trésor");
        Race energie = new Race("Energie", "Gagne 1 niveau quand aide un adversaire");
        Race alternant = new Race("Alternant", "Multi casquette : peut avoir 6 cartes en main");
        Race tc = new Race("TC", "Chance du débutant : les monstres de niveau 5 ou moins sont vaincus automatiquement");
        Race boursier = new Race("Boursier", "Vend pour le double du prix (eh merce le crous)");

        // Ajout des races au paquet de cartes Porte
        for (Race race : Arrays.asList(informatique, mecanique, imsi, edim, energie, alternant, tc, boursier)) {
            ajouterCartePorte(race);
        }

        // Ajout des nouveaux sorts
        //type   : 0 = au joueur hors  combat, 1 = à un autre joueur hors combat , 2 = en combat
        Sort pleurerDansLesJupesDuProf = new Sort("Pleurer dans les jupes du prof", 0, "Gain de 1 niveau", 50,0);
        Sort tricherPourPasser = new Sort("Tricher pour passer", 0, "Gain de 1 niveau", 50,0);
        Sort recopierPourMieuxPasser = new Sort("Recopier pour mieux passer", 0, "Gain de 1 niveau", 50,0);
        Sort feuilleDeTriche = new Sort("Feuille de triche", -5, "bonus au monstre",20,2);
        Sort enerverLeProf = new Sort("Enerver le prof", 10, "bonus au monstre", 40,2);
        Sort seFaireChopperATricher = new Sort("Se faire chopper à tricher", 0, "Perte de 2 niveaux", 0,1);
        Sort absenceInjustifiee = new Sort("Absence injustifiée", 5, "bonus au monstre", 0,2);
        Sort retourEnTC1 = new Sort("Retour en TC1", 10, "bonus au monstre", 0,2);
        Sort controlSurprise = new Sort("Control surprise", 0, "double le monstre", 40, 2);

        // Ajout des nouveaux sorts au paquet de cartes Tresors
        for (Sort sort : Arrays.asList(pleurerDansLesJupesDuProf,  tricherPourPasser, recopierPourMieuxPasser, feuilleDeTriche, enerverLeProf, seFaireChopperATricher, absenceInjustifiee, retourEnTC1, controlSurprise)) {
            ajouterCarteTresor(sort);
        }

    }

    public void melanger() {
        Collections.shuffle(cartesTresors);
        Collections.shuffle(cartesPorte);
    }

    public Carte tirerCarteTresors() {
        if (cartesTresors.isEmpty()) {
            return null; // Pas de carte à tirer
        }
        return cartesTresors.remove(0);
    }

    public Carte tirerCartePortes() {
        if (cartesPorte.isEmpty()) {
            return null; // Pas de carte à tirer
        }
        return cartesPorte.remove(0);
    }

    public Carte tirerCarteAleatoire(String paquetType) {
        List<Carte> paquet;

        // Sélectionner le paquet en fonction du type spécifié
        if (Objects.equals(paquetType, "TRESORS")) {
            paquet = cartesTresors;
        } else if (Objects.equals(paquetType, "PORTE")) {
            paquet = cartesPorte;
        } else {
            System.out.println("Type de paquet non reconnu.");
            return null;
        }

        if (paquet.isEmpty()) {
            System.out.println("Le paquet est vide. Aucune carte à tirer.");
            return null;
        }

        // Sélection aléatoire d'une carte
        Random random = new Random();
        int indexCarteTiree = random.nextInt(paquet.size());
        Carte carteTiree = paquet.remove(indexCarteTiree);

        return carteTiree;
    }
}


