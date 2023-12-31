public class Equipement extends Carte {
    private int bonus;
    private String type;
    private String effet;
    private int prix=0;

    public Equipement(String nom, int bonus, String type, String effet, int prix) {
        super(nom);
        this.bonus = bonus;
        this.type = type;
        this.effet = effet;
        this.prix = prix;
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

    public int getPrix() {
        return prix;
    }

    public void afficherEquipement() {
        System.out.println("-----------------------------------------------");
        System.out.println("Equipement : " + getNom());
        System.out.println("Bonus : " + getBonus());
        if (!getType().isEmpty()) {
            System.out.println("Type : " + getType());
        }
        if (!getEffet().isEmpty()) {
            System.out.println("Effet : " + getEffet());
        }
        if (getPrix() != 0) {
            System.out.println("Valeur : " + getPrix()  + "euros");
        }
        System.out.println("-----------------------------------------------");
    }
}
