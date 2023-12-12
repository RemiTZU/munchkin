public class Race extends Carte {
    private String bonus;

    public Race(String nom, String bonus) {
        super(nom);
        this.bonus = bonus;
    }

    public String getBonus() {
        return bonus;
    }
}
