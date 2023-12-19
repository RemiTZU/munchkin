public class Equipement extends Carte {
    private int bonus;
    private String type;
    private String effet;

    public Equipement(String nom, int bonus, String type, String effet) {
        super(nom);
        this.bonus = bonus;
        this.type = type;
        this.effet = effet;
    }

    public int getBonus() {
        return bonus;
    }

    public String getType() {
        return type;
    }

    public String getEffet() {
        return effet;
    }

    public void afficherEquipement() {
        System.out.println("-----------------------------------------------");
        System.out.println("Equipement tiré : " + getNom());
        System.out.println("Bonus : " + getBonus());
        if (!getType().isEmpty()) {
            System.out.println("Type : " + getType());
        }
        if (!getEffet().isEmpty()) {
            System.out.println("Effet : " + getEffet());
        }
        System.out.println("-----------------------------------------------");
    }
}
