package dungeonchase;

/**
 * Die Bomb-Klasse repräsentiert eine Bombe im Spiel.
 * Diese Klasse erbt von der Character-Klasse.
 */
public class Bomb extends Character {

    /**
     * Setzt den Namen des Charakters auf "Bomb".
     */
    public Bomb() {
        super("Bomb");
    }

    /**
     * @return der Dateiname des Bildes der Bombe
     */
    @Override
    public String getImage() {
        return "bomb.png";
    }

    /**
     * Aktualisiert die Position der Bombe.
     * Da die Bombe stationär ist, bewegt sie sich nicht.
     * @param grid das Spielfeld
     * @param x die aktuelle x-Position der Bombe
     * @param y die aktuelle y-Position der Bombe
     * @param playerMovement die Bewegungsrichtung des Spielers
     * @return Direction.NONE, da die Bombe sich nicht bewegt
     */
    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        return Direction.NONE; // Die Bombe bleibt immer an der gleichen Position
    }

    /**
     * Regelt Kollision der Bombe mit anderen Charakteren.
     * Wenn ein Spieler mit der Bombe kollidiert, wird der Spieler "getötet".
     * @param other der andere Charakter, der mit der Bombe kollidiert
     * @return true, wenn es zu einer Kollision mit einem Spieler kommt, ansonsten false
     */
    @Override
    public boolean collisionFrom(Character other) {
        if (other instanceof Player) {
            other.setAlive(false); // Setzt alive von other auf false, wenn es der Held ist
            return true;
        }
        return false; // Andere Charaktere können nicht auf die Bombe ziehen
    }

    /*
    @Override
    public boolean collisionFrom(Character other) {
        other.setAlive(false); // Setzt alive von other auf false
        return true; 
        
    }
     */
}
