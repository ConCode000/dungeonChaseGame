package dungeonchase;

import java.util.Random;

/**
 * Das Direction-Enum stellt Richtungen in einem 2D-Raster dar.
 * Jede Richtung hat zugeordnete dx (Delta x) und dy (Delta y) Werte.
 */
public enum Direction {
    NONE(0, 0),
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
    public int getDX() {
        return dx;
    }
    public int getDY() {
        return dy;
    }

    /**
     * Gibt die Richtung zurück, die den gegebenen dx- und dy-Werten entspricht.
     * 
     * @param dx die Änderung der x-Koordinate
     * @param dy die Änderung der y-Koordinate
     * @return die Richtung, die den gegebenen dx- und dy-Werten entspricht
     * @throws IllegalArgumentException wenn die dx- und dy-Werte nicht gültig sind
     */
    public static Direction fromD(int dx, int dy) {
        if ((dx != 0 && dx != -1 && dx != 1) || (dy != 0 && dy != -1 && dy != 1)) {
            throw new IllegalArgumentException("Ungültige dx- und dy-Werte");
        }

        for (Direction direction : values()) {
            if (direction.dx == dx && direction.dy == dy) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Ungültige dx- und dy-Werte");
    }

    /** 
     * @return die entgegengesetzte Richtung
     */
    public Direction opposite() {
        return fromD(-dx, -dy); // Dies funktioniert auch, wenn dx oder dy 0 ist.
    }

    /**
     * @return eine zufällige Richtung aus der Menge {NORTH, EAST, SOUTH, WEST}
     */
    public static Direction random4() {
        Direction[] directions = {NORTH, EAST, SOUTH, WEST};
        Random random = new Random();
        return directions[random.nextInt(directions.length)];
    }
}