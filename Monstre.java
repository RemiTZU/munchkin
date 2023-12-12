public class Monstre extends Carte {
    private int niveau;
    private String effet;
    private String incidentFacheux;

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
}
