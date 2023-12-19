public class Monstre extends Carte {
    private int niveau;
    private String effet;
    private String incidentFacheux;

    String nom = super.getNom();

    public Monstre(String nom, int niveau, String effet, String incidentFacheux) {
        super(nom);
        this.niveau = niveau;
        this.effet = effet;
        this.incidentFacheux = incidentFacheux;
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
