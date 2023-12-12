public class Sort extends Carte {
    private int puissance;

    public Sort(String nom, int puissance) {
        super(nom);
        this.puissance = puissance;
    }

    public int getPuissance() {
        return puissance;
    }
}
