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
}
