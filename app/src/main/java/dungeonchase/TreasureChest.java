package dungeonchase;

/**
 * Die TreasureChest-Klasse repräsentiert eine Schatztruhe im Spiel.
 * Diese Klasse erbt von der Character-Klasse.
 */
public class TreasureChest extends Character {

    public TreasureChest() {
        super("TreasureChest");
    }
    @Override
    public String getImage() {
        return "treasure-chest.png";
    }

    /**
     * Aktualisiert die Position der Schatztruhe.
     * Da die Schatztruhe stationär ist, bewegt sie sich nicht.
     * 
     * @param grid das Spielfeld
     * @param x die aktuelle x-Position der Schatztruhe
     * @param y die aktuelle y-Position der Schatztruhe
     * @param playerMovement die Bewegungsrichtung des Spielers
     * @return Direction.NONE, da die Schatztruhe sich nicht bewegt
     */
    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        return Direction.NONE; // Schatz kann sich nicht bewegen
    }

    /**
     * Regelt Kollision der Schatztruhe mit anderen Charakteren.
     * Wenn ein Spieler mit der Schatztruhe kollidiert, wird die Schatztruhe "getötet".
     * 
     * @param other der andere Charakter, der mit der Schatztruhe kollidiert
     * @return true, wenn es zu einer Kollision mit einem Spieler kommt, ansonsten false
     */
    @Override
    public boolean collisionFrom(Character other) {
        if (other instanceof Player) { // instanceof prüft, ob ein Objekt eine Instanz einer bestimmten Klasse ist
            setAlive(false); // Die Schatztruhe "stirbt"
            return true;
        }
        return false;
    }
}
