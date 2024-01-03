public class Monstre extends Carte {
    private int niveau;
    private String effet;
    private String incidentFacheux;
    private int  niveauVaincu;
    private int carteVaincue;

    String nom = super.getNom();

    public Monstre(String nom, int niveau, String effet, String incidentFacheux, int niveauVaincu, int carteVaincue) {
        super(nom);
        this.niveau = niveau;
        this.effet = effet;
        this.incidentFacheux = incidentFacheux;
        this.niveauVaincu = niveauVaincu;
        this.carteVaincue = carteVaincue;
    }

    public int getCarteVaincue() {
        return carteVaincue;
    }

    public int getNiveauVaincu() {
        return niveauVaincu;
    }

    public int getNiveau() {
        return niveau;
    }

    public String getEffet() {
        return effet;
    }

    public String getIncidentFacheux() {
        return incidentFacheux;
    }

    public void gainNiveau(int niveau){
        this.niveau += niveau;
    }

    public void afficherMonstre() {
        System.out.println("-----------------------------------------------");
        System.out.println("Monstre tiré : " + getNom());
        System.out.println("Niveau: " + getNiveau());
        if (!getEffet().isEmpty()) {
            System.out.println("Effet : " + getEffet());
        }
        System.out.println("Incident Fâcheux : " + getIncidentFacheux());
        System.out.println("-----------------------------------------------");

    }
}