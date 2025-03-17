package dungeonchase;

/**
 * Diese Klasse erbt von der Character-Klasse.
 */
public class BlackKnight extends Character {

    /**
     * @param name der Name des schwarzen Ritters
     */
    public BlackKnight(String name) {
        super(name);
    }

    /**
     * @return der Dateiname des Bildes des schwarzen Ritters
     */
    @Override
    public String getImage() {
        return "black-knight.png";
    }

    /**
     * Aktualisiert die Position des schwarzen Ritters basierend auf der Bewegungsrichtung des Spielers.
     * Der schwarze Ritter bewegt sich in die entgegengesetzte Richtung des Spielers.
     * 
     * @param grid das Spielfeld
     * @param x die aktuelle x-Position des schwarzen Ritters
     * @param y die aktuelle y-Position des schwarzen Ritters
     * @param playerMovement die Bewegungsrichtung des Spielers
     * @return die neue Richtung, in die sich der schwarze Ritter bewegt
     */
    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        return playerMovement.opposite();
    }
}