public class Bonus extends Carte {
    private String description;

    public Bonus(String nom, String description) {
        super(nom);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
