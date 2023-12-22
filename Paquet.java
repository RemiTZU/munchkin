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

        Monstre tpRenduMaison = new Monstre("Tp rendu maison", 1, "", "Il vous laisse partir, il a pitié");
        Monstre tpRenduFinDuCours = new Monstre("Tp rendu fin du cours", 10, "", "Vous perdez 2 niveaux");
        Monstre virusInformatique = new Monstre("Virus informatique", 8, "+5 contre la branche info",
                "Vous perdez un équipement info, sinon -1 niveau");
        Monstre profMalhonete = new Monstre("Un prof un peu malhonnête", 14, "-2 à votre jet de fuite", "-2 niveaux");

        // Ajout des monstres au paquet de cartes Porte
        ajouterCartePorte(tpRenduMaison);
        ajouterCartePorte(tpRenduFinDuCours);
        ajouterCartePorte(virusInformatique);
        ajouterCartePorte(profMalhonete);

        // Ajout des nouveaux équipements
        Equipement jogginsGris = new Equipement("Joggins gris", 1, "pantalon", "");
        Equipement chaussureDeSport = new Equipement("Chaussure de sport", 2, "chaussure", "Plus 1 pour fuir");
        Equipement packDeBiere = new Equipement("Pack de bière", 5, "gros, 1 mains", "");
        Equipement ordiSurpuissant = new Equipement("Ordi surpuissant", 5, "INFO, 1 main", "");

        // Ajout des nouveaux équipements au paquet de cartes Tresors
        ajouterCarteTresor(jogginsGris);
        ajouterCarteTresor(chaussureDeSport);
        ajouterCarteTresor(packDeBiere);
        ajouterCarteTresor(ordiSurpuissant);

        // Ajout des nouvelles races
        Race informatique = new Race("Informatique", "Puanteur : niveau doublé en affrontant des monstres seul");
        Race mecanique = new Race("Mécanique", "Main pleine de graisse : +1 pour fuir");
        Race imsi = new Race("IMSI", "Baratineur");
        Race edim = new Race("EDIM", "Fuite à succès : quand réussi à fuir, prend 1 trésor");
        Race energie = new Race("Energie", "Gagne 1 niveau quand aide un adversaire");
        Race alternant = new Race("Alternant", "Multi casquette : peut avoir 6 cartes en main");
        Race tc = new Race("TC", "Chance du débutant : les monstres de niveau 5 ou moins sont vaincus automatiquement");
        Race doubleDiplome = new Race("Double Diplôme", "2 classes possibles");
        Race boursier = new Race("Boursier", "Vend pour le double du prix (eh merce le crous)");

        // Ajout des races au paquet de cartes Porte
        for (Race race : Arrays.asList(informatique, mecanique, imsi, edim, energie, alternant, tc, doubleDiplome, boursier)) {
            ajouterCartePorte(race);
        }

        // Ajout des nouveaux sorts
        Sort pleurerDansLesJupesDuProf = new Sort("Pleurer dans les jupes du prof", 0, "Gain de 1 niveau");
        Sort larbinEnInfo = new Sort("Larbin en info", 1, "Permet d'avoir un objet d'info");
        Sort larbinEnEDIM = new Sort("Larbin en EDIM", 1, "Permet d'avoir un objet en EDIM");
        Sort larbinEnMeca = new Sort("Larbin en Meca", 1, "Permet d'avoir un objet en Meca");
        Sort larbinEnImsi = new Sort("Larbin en Imsi", 1, "Permet d'avoir un objet en Imsi");
        Sort larbinEnEnergie = new Sort("Larbin en energie", 1, "Permet d'avoir un objet en energie");
        Sort larbin = new Sort("Larbin", 1, "Permet d'avoir un objet en plus");
        Sort tricherPourPasser = new Sort("Tricher pour passer", 0, "Gain de 1 niveau");
        Sort recopierPourMieuxPasser = new Sort("Recopier pour mieux passer", 0, "Gain de 1 niveau");
        Sort feuilleDeTriche = new Sort("Feuille de triche", +5, "au joueur");
        Sort enerverLeProf = new Sort("Enerver le prof", +10, "au monstre");
        Sort seFaireChopperATricher = new Sort("Se faire chopper à tricher", -10, "au joueur");
        Sort absenceInjustifiee = new Sort("Absence injustifiée", -5, "au joueur");
        Sort retourEnTC1 = new Sort("Retour en TC1", +10, "Au monstre");

        // Ajout des nouveaux sorts au paquet de cartes Tresors
        for (Sort sort : Arrays.asList(pleurerDansLesJupesDuProf, larbinEnInfo, larbinEnEDIM, larbinEnMeca, larbinEnImsi, larbinEnEnergie, larbin, tricherPourPasser, recopierPourMieuxPasser, feuilleDeTriche, enerverLeProf, seFaireChopperATricher, absenceInjustifiee, retourEnTC1)) {
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

        System.out.println("Carte tirée : " + paquetType );

        // Sélection aléatoire d'une carte
        Random random = new Random();
        int indexCarteTiree = random.nextInt(paquet.size());
        Carte carteTiree = paquet.remove(indexCarteTiree);

        return carteTiree;
    }
}



