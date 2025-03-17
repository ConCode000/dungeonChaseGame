package dungeonchase;

/**
 * Die abstrakte Klasse Character repräsentiert einen allgemeinen Charakter im Spiel.
 * Diese Klasse enthält grundlegende Attribute und Methoden, die alle Charaktere teilen.
 */
public abstract class Character {
    private String name;
    private boolean alive;
    private int lastUpdate;

    /**
     * Konstruktor für die Character-Klasse.
     * 
     * @param name der Name des Charakters
     * @throws IllegalArgumentException wenn der Name null oder leer ist
     */
    public Character(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name darf nicht leer oder null sein");
        }
        this.name = name;
        this.alive = true;
        this.lastUpdate = 0;
    }
    public String getName() {
        return name;
    }
    public boolean getAlive() {
        return alive;
    }
    public int getLastUpdate() {
        return lastUpdate;
    }

    /** 
     * @param alive der neue Alive Status des Charakters
     */
    protected void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Setzt den letzten Update Zeitpunkt des Charakters. 
     * @param lastUpdate der neue letzte Update-Zeitpunkt des Charakters
     */
    public void setLastUpdate(int lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**

     * Diese Methode muss von Unterklassen implementiert werden.
     * @return der Dateiname des Bildes des Charakters
     */
    public abstract String getImage();

    /**
     * Aktualisiert die Position des Charakters.
     * Diese Methode muss von Unterklassen implementiert werden.
     * 
     * @param grid das Spielfeld
     * @param x die aktuelle x-Position des Charakters
     * @param y die aktuelle y-Position des Charakters
     * @param playerMovement die Bewegungsrichtung des Spielers
     * @return die neue Richtung, in die sich der Charakter bewegt
     */
    public abstract Direction update(Grid grid, int x, int y, Direction playerMovement);

    /**
     * Regelt Kollision des Charakters mit anderen Charakteren.
     * Standardmäßig wird der AliveStatus dieses Charakters auf false gesetzt.
     * 
     * @param other der andere Charakter, der mit diesem kollidiert
     * @return true, wenn eine Kollision auftritt
     */
    public boolean collisionFrom(Character other) {
        this.alive = false;
        return true;
    }

    /**
     * Überprüft, ob Charakter lebt.
     * @return true, wenn der Charakter lebt, ansonsten false
     */
    public boolean isAlive() {
        return this.alive;
    }
}
