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
        Monstre profMalhonete = new Monstre("Un prof un peu malhonnête", 14, "-1 à votre jet de fuite", "-2 niveaux", 1, 3);
        Monstre girardus = new Monstre("Girardus", 25, "", "", 2, 3);
        Monstre tutu = new Monstre("TUTU", 40, "Fuite impossible", "La mort", 3, 4);
        Monstre mp2 = new Monstre("MP^2", 15, "sa lenteur te permet de prendre un trésors en fuyant", "-2 niveaux", 1, 2);
        Monstre marcoco = new Monstre("Marcoco", 10, "son charme te fait oublier de prendre un cadeau", "", 1, 0);
        Monstre meyerMaitre = new Monstre("Meyer Maitre", 20, "+10 contre les TC", "-1 niveau", 2, 2);
        Monstre vuillamier = new Monstre("Vuillamier", 20, "-5 contre les mecaniques", "-1 niveau", 2, 2);
        Monstre pma = new Monstre("PMA", 10, "-2 contre TC", "-1 niveau", 1, 1);
        Monstre examenImprevu = new Monstre("Examen Imprevu", 10, "Fuite impossible", "La mort", 1, 2);
        Monstre professeurRigoureux = new Monstre("Professeur Rigoureux", 12, "", "-1 niveau", 1, 2);
        Monstre procrastination = new Monstre("Procrastination", 10, "", "-1 niveau", 1, 2);
        Monstre professeurDistrait = new Monstre("Professeur Distrait", 8, "", "-1 niveau", 1, 2);
        Monstre professeurTropCool = new Monstre("Professeur Trop Cool", 5, "", "-1 niveau", 1, 2);
        Monstre professeurTropStrict = new Monstre("Professeur Trop Strict", 17, "", "Perte d'un équipement aléatoire", 1, 3);
        Monstre professeurTropLent = new Monstre("Professeur Trop Lent", 15, "", "-1 niveau", 1, 2);
        Monstre bibliothecaire = new Monstre("Bibliothécaire", 12, "", "-1 niveau", 1, 2);
        Monstre moutonDeSevenan = new Monstre("Mouton de Sevenans", 5, "", "Il vous laisse partir, il a pitié", 1, 2);
        Monstre lendemainDeSoiree = new Monstre("Lendemain de soirée", 10, "", "-1 niveau", 1, 2);
        Monstre afterCuite = new Monstre("After cuite", 15, "", "La mort", 1, 4);
        Monstre panneDeCourant = new Monstre("Panne de courant", 10, "+5 contre la branche info", "-1 niveau", 1, 2);
        Monstre panneDeReveil = new Monstre("Panne de réveil", 8, "", "-1 niveau", 1, 2);
        Monstre oublieDeReveil = new Monstre("Oublié de réveil", 10, "", "-1 niveau", 1, 2);
        Monstre oublieDeCarteDeBus = new Monstre("Oublié de carte de bus", 13, "", "Perte d'un équipement aléatoire", 1, 2);
        Monstre oublieDeCarteDeCrous = new Monstre("Oublié de carte de crous", 12, "", "Perte d'un équipement aléatoire", 1, 2);
        Monstre oublieDeStylo = new Monstre("Oublié de stylo", 1, "", "Il vous laisse partir, il a pitié", 1, 1);
        Monstre oublieDeCrayon = new Monstre("Oublié de crayon", 1, "", "Il vous laisse partir, il a pitié", 1, 1);
        Monstre oublieDeFeuille = new Monstre("Oublié de feuille", 1, "", "Il vous laisse partir, il a pitié", 1, 1);
        Monstre chienDévoreurDeDevoirs = new Monstre("Chien dévoreur de devoirs", 20, "", "Perte d'un équipement aléatoire", 2, 5);
        Monstre chatSurOrdinateur = new Monstre("Chat sur ordinateur", 10, "", "-1 niveau", 1, 2);
        Monstre chatSurClavier = new Monstre("Chat sur clavier", 15, "", "-1 niveau", 1, 2);

        // Ajout des monstres au paquet de cartes Porte
        for (Monstre monstre : Arrays.asList(tpRenduMaison, tpRenduFinDuCours, virusInformatique, profMalhonete, girardus, tutu, mp2, marcoco, meyerMaitre, vuillamier, pma, examenImprevu, professeurRigoureux, procrastination, professeurDistrait, professeurTropCool,
         professeurTropStrict, professeurTropLent, bibliothecaire, moutonDeSevenan, lendemainDeSoiree, afterCuite, panneDeCourant, panneDeReveil, oublieDeReveil, oublieDeCarteDeBus, oublieDeCarteDeCrous, oublieDeStylo, oublieDeCrayon, oublieDeFeuille, chienDévoreurDeDevoirs, 
         chatSurOrdinateur, chatSurClavier)) {
            ajouterCartePorte(monstre);
        }

        // Ajout des nouveaux équipements
        Equipement jogginsGris = new Equipement("Joggins gris", 1, "pantalon", "",  20);
        Equipement chaussureDeSport = new Equipement("Chaussure de sport", 2, "chaussure", "", 60);
        Equipement packDeBiere = new Equipement("Pack de bière", 5, "gros", "", 30);
        Equipement ordiSurpuissant = new Equipement("Ordi surpuissant", 5, "1 main", "", 80);
        Equipement pinsBlousard = new Equipement("Pins blousard", 2,  "armure","",  20);
        Equipement chaussettesMcdo = new Equipement("Chaussettes Mcdo", 3, "chaussure", "", 50);
        Equipement casquette = new Equipement("Casquette", 2, "Casque", "", 40); 
        Equipement cafeNoisette = new Equipement("Café noisette", 1, "1 main", "", 60);
        Equipement cafeAllonge = new Equipement("Café allongé", 2, "1 main", "", 80);
        Equipement cafeCreme = new Equipement("Café crème", 3, "1 main", "", 100);
        Equipement cheveuxGras = new Equipement("Cheveux gras", 5, "Casque", "Informatique", 20); 
        Equipement batonDeLaSagesse = new Equipement("Baton de la sagesse", 7, "1 main", "Boursier", 60);
        Equipement outfitTrendy = new Equipement("Outfit trendy", 5, "armure", "Mécanique", 40); 
        Equipement batonMauvaiseFoi = new Equipement("Baton de mauvaise foi", 5, "1 main", "IMSI", 40); 
        Equipement crayonsDeCouleur = new Equipement("Crayons de couleur", 3, "1 main", "EDIM", 40); 
        Equipement carteUTBM = new Equipement("Carte UTBM", 3, "collier", "", 80);
        Equipement calculetteNspire = new Equipement("Calculette Nspire", 6, "1 main", "", 100);
        Equipement carteBibliotheque = new Equipement("Carte bibliothèque", 3, "collier", "", 50);
        Equipement carteCrous = new Equipement("Carte Crous", 3, "", "", 30);
        Equipement carteBus = new Equipement("Carte bus", 3, "", "", 80);
        Equipement sacDeCours = new Equipement("Sac de cours", 4, "gros", "", 80);
        Equipement sacDeSport = new Equipement("Sac de sport", 4, "", "", 90);
        Equipement sacDeVoyage = new Equipement("Sac de voyage", 4, "gros", "", 80);
        Equipement trousse = new Equipement("Trousse", 3, "", "", 50);
        Equipement trousseDeToilette = new Equipement("Trousse de toilette", 3, "", "", 50);
        Equipement stylo = new Equipement("Stylo", 1, "1 main", "", 20);
        Equipement styloPlume = new Equipement("Stylo plume", 2, "1 main", "", 40);
        Equipement stylo4Couleurs = new Equipement("Stylo 4 couleurs", 4, "1 main", "", 70);
        Equipement abonnementAChatGPT4 = new Equipement("Abonnement à chat GPT4", 6, "", "", 100);
        Equipement abonnementAChatGPT3 = new Equipement("Abonnement à chat GPT3", 3, "", "", 80);
        Equipement roller = new Equipement("Roller", 3, "chaussure", "", 80);
        Equipement skate = new Equipement("Skate", 4, "chaussure", "", 80);
        Equipement trottinette = new Equipement("Trottinette", 4, "1 main", "", 80);
        Equipement velo = new Equipement("Vélo", 5, "gros", "", 80);
        Equipement voiture = new Equipement("Voiture", 6, "gros", "", 80);
        Equipement batterieExterne = new Equipement("Batterie externe", 3, "", "Informatique", 80);
        Equipement batterieExterneDeLaBatterieExerne = new Equipement("Batterie externe de la batterie externe", 5, "", "Informatique", 80);
        Equipement moteur = new Equipement("Moteur", 5, "", "Mécanique", 80);
        Equipement feuillesDeCours = new Equipement("Feuilles de cours", 2, "", "", 40);
        Equipement feuilleBlanche = new Equipement("Feuille blanche", 1, "", "EDIM", 20);
        Equipement feuilleDeBrouillon = new Equipement("Feuille de brouillon", 1, "", "IMSI", 20);
        Equipement regle = new Equipement("Règle", 1, "", "", 20);
        Equipement cableEthernet = new Equipement("Câble ethernet", 2, "", "Informatique", 40);
        Equipement cableLong = new Equipement("Câble long", 3, "", "Energie", 60);
        Equipement cableCourt = new Equipement("Câble court", 1, "", "Energie", 20);
        Equipement bourseMensuelle = new Equipement("Bourse mensuelle", 3, "", "Boursier", 30);
        Equipement bourseAnnuelle = new Equipement("Bourse annuelle", 5, "", "Boursier", 50);
        Equipement bourseDeStage = new Equipement("Bourse de stage", 8, "", "Boursier", 80);
        Equipement salaire = new Equipement("Salaire", 10, "", "Alternant", 100);
        Equipement salaireDeStage = new Equipement("Salaire de stage", 4, "", "Alternant", 40);
        Equipement tempslibre = new Equipement("Temps libre", 3, "", "TC", 30);
        Equipement calculetteCollege = new Equipement("Calculette collège", 2, "1 main", "TC", 40);
        Equipement calculetteLycee = new Equipement("Calculette lycée", 3, "1 main", "TC", 50);
        
        // Ajout des nouveaux équipements au paquet de cartes Tresors
        for (Equipement equipement : Arrays.asList( chaussureDeSport, packDeBiere, ordiSurpuissant, chaussettesMcdo, casquette, cafeNoisette, cafeAllonge, cheveuxGras, batonDeLaSagesse, outfitTrendy, batonMauvaiseFoi, crayonsDeCouleur,
         carteUTBM, calculetteNspire, carteBibliotheque, carteCrous, carteBus, sacDeCours)) {
            ajouterCartePorte(equipement);
        }

        //Ajoute de certains équipements au paquet de cartes Porte
        for (Equipement equipement  : Arrays.asList(jogginsGris, calculetteNspire, carteUTBM, cafeCreme, pinsBlousard,
         sacDeSport, sacDeVoyage, trousse, trousseDeToilette, stylo, styloPlume, stylo4Couleurs, abonnementAChatGPT4, abonnementAChatGPT3, roller, skate, 
         trottinette, velo, voiture, batterieExterne, batterieExterneDeLaBatterieExerne, moteur, feuillesDeCours, feuilleBlanche, feuilleDeBrouillon, regle, cableEthernet, cableLong, cableCourt, bourseMensuelle, bourseAnnuelle, 
         bourseDeStage, salaire, salaireDeStage, tempslibre, calculetteCollege, calculetteLycee)) {
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
        Sort seFaireChopperEnTrainDeTricher = new Sort("Se faire chopper en train de tricher", 0, "Perte de 2 niveaux", 0,1);
        Sort caféine = new Sort("Caféine", 0, "Gain de 1 niveau", 50,0);
        Sort caféineMagique = new Sort("Caféine magique", 0, "Gain de 1 niveau", 50,0);
        Sort révisionDeDerniereMinute = new Sort("Révision de dernière minute", 0, "Pioche 1 carte", 50,0);
        Sort révisionApprofondie = new Sort("Révision approfondie", 0, "Pioche 2 cartes", 50,0);
        Sort révisionIntensive = new Sort("Révision intensive", 0, "Pioche 3 cartes", 50,0);
        Sort erreurDansLaNotation = new Sort("Erreur dans la notation", 0, "Gain de 1 niveau", 50,0);
        Sort fuiteDeSujeSort = new Sort("Fuite de sujet", 0, "Gain de 1 niveau", 50,0);
        Sort racket = new Sort("Racket", 0, "Perte d'un équipement aléatoire", 50,1);
        Sort volDeSujet = new Sort("Vol de sujet", 0, "Gain de 1 niveau", 50,0);
        Sort deuxiemeSujet = new Sort("Deuxième sujet", 0, "double le monstre", 40, 2);
        Sort manqueDeRévision = new Sort("Manque de révision", 0, "Perte de 2 niveaux", 0,1);
        Sort manqueDeCaféine = new Sort("Manque de caféine", 5, "bonus au monstre", 0,2);
        Sort manqueDeTemps = new Sort("Manque de temps", 10, "bonus au monstre", 0,2);
        Sort manqueDeChance = new Sort("Manque de chance", 0, "Perte de 2 niveaux", 0,1);
        Sort coupDeChance = new Sort("Coup de chance", -5, "bonus au monstre",20,2);



        // Ajout des nouveaux sorts au paquet de cartes Tresors
        for (Sort sort : Arrays.asList(pleurerDansLesJupesDuProf,  tricherPourPasser, recopierPourMieuxPasser, feuilleDeTriche, enerverLeProf, seFaireChopperATricher, absenceInjustifiee, retourEnTC1, controlSurprise,deuxiemeSujet,racket,coupDeChance,révisionDeDerniereMinute,révisionApprofondie,révisionIntensive)) {
            ajouterCarteTresor(sort);
        }

        for (Sort sort : Arrays.asList(seFaireChopperEnTrainDeTricher,caféine, caféineMagique,manqueDeCaféine,manqueDeChance,manqueDeRévision,manqueDeTemps,erreurDansLaNotation,fuiteDeSujeSort,volDeSujet)){
            ajouterCartePorte(sort);
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


