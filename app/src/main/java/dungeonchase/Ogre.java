package dungeonchase;

/**
 * Die Ogre-Klasse repräsentiert einen Oger-Charakter im Spiel.
 * Diese Klasse erbt von der Character-Klasse.
 */
public class Ogre extends Character {

    public Ogre(String name) {
        super(name);
    }
    @Override
    public String getImage() {
        return "ogre.png";
    }

    /**
     * Aktualisiert die Position des Ogers basierend auf der Position des Spielers.
     * Der Oger bewegt sich in Richtung des Spielers, entweder horizontal oder vertikal,
     * abhängig von der größeren Differenz in den x- oder y-Koordinaten.
     * 
     * @param grid das Spielfeld
     * @param x die aktuelle x-Position des Ogers
     * @param y die aktuelle y-Position des Ogers
     * @param playerMovement die Bewegungsrichtung des Spielers
     * @return die neue Richtung, in die sich der Oger bewegt
     */
    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        int playerX = grid.getPlayerX();
        int playerY = grid.getPlayerY();

        int deltaX = playerX - x;
        int deltaY = playerY - y;

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            return deltaX > 0 ? Direction.EAST : Direction.WEST;
        } else {
            return deltaY > 0 ? Direction.NORTH : Direction.SOUTH;
        }
    }
}
