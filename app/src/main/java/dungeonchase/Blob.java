package dungeonchase;

/**
 * Die Blob-Klasse repräsentiert ein Blob-Charakter im Spiel, der sich replizieren kann.
 * Diese Klasse erbt von der Character-Klasse und implementiert das Replicable-Interface.
 */
public class Blob extends Character implements Replicable {

    private double replicationProbability; // Die Wahrscheinlichkeit, dass in einer Iteration des Spiels eine Vervielfältigung stattfindet; muss ≥ 0 und ≤ 1 sein

    /**
     * Konstruktor für die Blob-Klasse.
     * 
     * @param name der Name des Blobs
     * @param replicationProbability die Wahrscheinlichkeit der Vervielfältigung, muss zwischen 0 und 1 liegen
     * @throws IllegalArgumentException wenn replicationProbability nicht zwischen 0 und 1 liegt
     */
    public Blob(String name, double replicationProbability) {
        super(name);
        if(replicationProbability < 0 || replicationProbability > 1) {
            throw new IllegalArgumentException("Die replicationProbability muss zwischen 1 und 0 sein!");
        }
        this.replicationProbability = replicationProbability;
    }

    /**
     * @return der Dateiname des Bildes des Blobs
     */
    @Override
    public String getImage() {
        return "blob.png";
    }

    /**
     * Aktualisiert die Position des Blobs zufällig.
     * 
     * @param grid das Spielfeld
     * @param x die aktuelle x-Position des Blobs
     * @param y die aktuelle y-Position des Blobs
     * @param playerMovement die Bewegungsrichtung des Spielers
     * @return eine zufällige Richtung, in die sich der Blob bewegt
     */
    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        return Direction.random4();
    }

    /**
     * Bestimmt die Richtung der Vervielfältigung basierend auf der Replikationswahrscheinlichkeit.
     * @return eine zufällige Richtung, wenn die Replikation stattfindet; ansonsten keine Bewegung
     */
    @Override
    public Direction replicationDirection() {
        if (Math.random() < replicationProbability) {
            return Direction.random4();
        }
        return Direction.NONE;
    }

    /**
     * Erzeugt eine Replikation des Blobs.
     * @return ein neues Blob Objekt mit dem gleichen Namen und der gleichen Replikationswahrscheinlichkeit
     */
    @Override 
    public Character generateReplication() {
        return new Blob(getName(), replicationProbability);
    }
}
