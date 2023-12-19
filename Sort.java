public class Sort extends Carte {
    private int bonus;
    private String effet;

    public Sort(String nom, int bonus, String effet) {
        super(nom);
        this.bonus = bonus;
        this.effet = effet;
    }

    public int getBonus() {
        return bonus;
    }

    public String getEffet() {
        return effet;
    }

    public void afficherSort() {
        System.out.println("-----------------------------------------------");
        System.out.println("Sort tiré : " + getNom() );
        if (getBonus() != 0) {
            System.out.println("Bonus : " + getBonus());
        }
        if (!getEffet().isEmpty()) {
            System.out.println("Type : " + getEffet());
        }
        System.out.println("-----------------------------------------------");

    }
}
