package dungeonchase;

/**
 * Die Fire Klasse repräsentiert ein Feuer im Spiel, das sich replizieren kann.
 * Diese Klasse erbt von der Character-Klasse und implementiert das Replicable-Interface.
 */
public class Fire extends Character implements Replicable {

    private final double replicationProbability; // Die Wahrscheinlichkeit, dass sich das Feuer in einer Iteration des Spiels repliziert; muss zwischen 0 und 1 liegen

    /**
     * @param replicationProbability die Wahrscheinlichkeit der Replikation, muss zwischen 0 und 1 liegen
     * @throws IllegalArgumentException wenn die Replikationswahrscheinlichkeit nicht zwischen 0 und 1 liegt
     */
    public Fire(double replicationProbability) {
        super("Fire");
        if (replicationProbability < 0 || replicationProbability > 1) {
            throw new IllegalArgumentException("Replication probability must be between 0 and 1.");
        }
        this.replicationProbability = replicationProbability;
    }

    /**
     * Gibt das Bild des Feuers zurück.
     * 
     * @return der Dateiname des Bildes des Feuers
     */
    @Override
    public String getImage() {
        return "fire.png";
    }

    /**
     * Aktualisiert die Position des Feuers.
     * Da das Feuer stationär ist, bewegt es sich nicht.
     * @param grid das Spielfeld
     * @param x die aktuelle x-Position des Feuers
     * @param y die aktuelle y-Position des Feuers
     * @param playerMovement die Bewegungsrichtung des Spielers
     * @return Direction.NONE, da das Feuer sich nicht bewegt
     */
    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        return Direction.NONE; // Fire bewegt sich nicht
    }

    /**
     * Regelt Kollision des Feuers mit anderen Charakteren.
     * Wenn ein Spieler mit dem Feuer kollidiert, wird der Spieler "getötet".
     * 
     * @param other der andere Charakter, der mit dem Feuer kollidiert
     * @return true, wenn es zu einer Kollision mit einem Spieler kommt, ansonsten false
     */
    @Override
    public boolean collisionFrom(Character other) {
        if (other instanceof Player) {
            other.setAlive(false); // Setzt alive von other auf false, wenn es der Held ist
            return true;
        }
        return false; // Andere Charaktere können nicht auf das Feuer ziehen
    }

    /**
     * Bestimmt die Richtung der Replikation basierend auf der Replikationswahrscheinlichkeit.
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
     * @return ein neues Fire Objekt mit der gleichen Replikationswahrscheinlichkeit
     */
    @Override
    public Character generateReplication() {
        return new Fire(replicationProbability);
    }
}
