package dungeonchase;

/**
 * Die Wall Klasse repräsentiert eine Wand im Spiel.
 * Diese Klasse erbt von der Character Klasse.
 */
public class Wall extends Character {

    public Wall() {
        super("Wall");
    }
    @Override
    public String getImage() {
        return "wall.png";
    }

    /**
     * Aktualisiert die Position der Wand.
     * Da die Wand stationär ist, bewegt sie sich nicht.
     * 
     * @param grid das Spielfeld
     * @param x die aktuelle x-Position der Wand
     * @param y die aktuelle y-Position der Wand
     * @param playerMovement die Bewegungsrichtung des Spielers
     * @return Direction.NONE, da die Wand sich nicht bewegt
     */
    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        return Direction.NONE; // Wände können sich nicht bewegen
    }

    /**
     * Regelt die Kollision der Wand mit anderen Charakteren.
     * Die Wand bleibt immer am Leben und lässt keinen anderen Charakter auf ihren Platz.
     * 
     * @param other der andere Charakter, der mit der Wand kollidiert
     * @return false, da die Wand nie durch eine Kollision zerstört wird
     */
    @Override
    public boolean collisionFrom(Character other) {
        return false; // Die Wand bleibt immer am Leben und lässt keinen anderen Charakter auf ihren Platz
    }
}
