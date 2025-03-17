package dungeonchase;

import java.util.Random;

/**
 * Die Bat-Klasse repräsentiert eine Fledermaus im Spiel, die sich basierend auf bestimmten Bedingungen bewegt.
 * Sie erbt von der Character-Klasse.
 */
public class Bat extends Character {

    private boolean willMove; // gibt an ob sich die Fledermaus bewegen wird
    private boolean moveNextTurn; // gibt an, ob sich die Fledermaus im nächsten Zug bewegt
    private static final Random random = new Random(); 

    /**
     * @param name der Name der Fledermaus
     */
    public Bat(String name) { 
        super(name); // Ruft Konstruktor der Character Klasse auf.
        this.willMove = random.nextBoolean(); // zufällig entscheiden, ob die Fledermaus sich im ersten Zug bewegt
        this.moveNextTurn = true; // moveNextTurn auf true setzen, damit die Fledermaus sich im ersten Zug bewegt.
    }

    /**
     * Gibt das Bild der Fledermaus zurück, basierend darauf, ob sie sich bewegt oder nicht.
     * @return der Dateiname des Bildes
     */
    @Override
    public String getImage() { 
        return willMove ? "bat-turn.png" : "bat.png"; // 
    }

    /*
    Konstruktor:
    willMove zufällig gesetzt (bewegen oder nicht).
    moveNextTurn auf true gesetzt (bereit zu bewegen).
    update-Methode:
    Wenn moveNextTurn false ist, bleibt die Fledermaus stehen und moveNextTurn wird auf true gesetzt.
    Wenn moveNextTurn true ist, wird die Bewegungsrichtung berechnet, die Fledermaus bewegt sich und moveNextTurn wird auf false gesetzt.
    */

    /**
     * Aktualisiert die Position der Fledermaus.
     * 
     * @param grid das Spielfeld
     * @param x die aktuelle x-Position der Fledermaus
     * @param y die aktuelle y-Position der Fledermaus
     * @param playerMovement die Bewegungsrichtung des Spielers
     * @return die neue Richtung, in die sich die Fledermaus bewegt
     */
    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) { 
        if (!moveNextTurn) { // Wenn die Fledermaus sich im aktuellen Zug nicht bewegen soll...
            moveNextTurn = true; // ...dann moveNextTurn auf true setzen, damit sie sich im nächsten Zug bewegt.
            return Direction.NONE; // Fledermaus bewegt sich nicht
        }

        // Position des Spielers im grid bestimmen.
        int playerX = grid.getPlayerX();
        int playerY = grid.getPlayerY();

        // Abstand zwischen der Fledermaus und dem Spieler.
        int deltaX = playerX - x; // x und y sind die aktuellen Positionen der Fledermaus.
        int deltaY = playerY - y;

        // Richtung, in die sich die Fledermaus bewegen soll, bestimmen.
        Direction direction = getDirection(deltaX, deltaY);

        moveNextTurn = false;
        willMove = true; 
        return direction; // Gibt berechnete Richtung zurück.
    }

    /**
     * Bestimmt die Bewegungsrichtung der Fledermaus basierend auf der Position des Spielers.
     * 
     * @param deltaX die Differenz in der x-Position zwischen Spieler und Fledermaus
     * @param deltaY die Differenz in der y-Position zwischen Spieler und Fledermaus
     * @return die Bewegungsrichtung der Fledermaus
     */
    private Direction getDirection(int deltaX, int deltaY) { 
        if (deltaX > 0 && deltaY > 0) return Direction.NORTH_EAST; // Spieler rechts oben ist --> bewegt sich die Fledermaus nach Nordosten.
        if (deltaX > 0 && deltaY < 0) return Direction.SOUTH_EAST; //
        if (deltaX < 0 && deltaY > 0) return Direction.NORTH_WEST; // Wenn Spieler links oben ist, bewegt sich die Fledermaus nach Nordwesten.
        if (deltaX < 0 && deltaY < 0) return Direction.SOUTH_WEST; 
        if (deltaX > 0) return Direction.EAST; // Wenn Spieler rechts ist, bewegt sich die Fledermaus nach Osten.
        if (deltaX < 0) return Direction.WEST; // Wenn Spieler links ist, bewegt sich die Fledermaus nach Westen.
        if (deltaY > 0) return Direction.NORTH; 
        if (deltaY < 0) return Direction.SOUTH; 
        return Direction.NONE; // Wenn Spieler an der gleichen Position ist, bewegt sich die Fledermaus nicht.
    }

    /**
     * Regelt die Kollision der Fledermaus mit anderen Charakteren.
     * 
     * @param other der andere Charakter, mit dem die Fledermaus kollidiert
     * @return true, wenn eine Kollision mit einem Spieler vorliegt, ansonsten false
     */
    @Override
    public boolean collisionFrom(Character other) { 
        if (other instanceof Player) { // Wenn der andere Charakter ein Spieler ist...
            setAlive(false); // ...stirbt die Fledermaus.
            return true; // true bestätigt Kollision 
        }
        return false; // Andere Charaktere können die Fledermaus nicht töten.
    }
}