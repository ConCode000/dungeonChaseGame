package dungeonchase;

public class Grid {

    public static final int GRID_WIDTH = 16;
    public static final int GRID_HEIGHT = 16;

    private Character[][] grid;
    private int playerX;
    private int playerY;
    private Player player;
    private int currentIteration;
    private TreasureChest treasureChest;

    public Grid() {
        currentIteration = 0;
        initGrid();
    }

    @SuppressWarnings("unchecked")
    private void initGrid() {
        grid = new Character[GRID_WIDTH][GRID_HEIGHT];
        grid = Helper.fillGrid(
            15,  // Anzahl der zu erzeugenden Gegner
            () -> new Ogre(Helper.generateName("Ogre")),
            () -> new Wall(),  // Konstruktor ohne Parameter verwenden
            () -> new Bomb(),
            () -> new Bat(Helper.generateName("Bat")), // Fügen Sie Bats hinzu
            () -> new Blob(Helper.generateName("Blob"), 0.09),    // ### Blob
            () -> new Fire(0.09)                                  // ### Fire
        );

        playerX = 1;
        playerY = GRID_HEIGHT / 2;
        player = new Player("DefaultHero"); // DefaultHero habe ich hinzugefügt 
        grid[playerX][playerY] = player;

        treasureChest = new TreasureChest();
        grid[GRID_WIDTH - 2][GRID_HEIGHT / 2] = treasureChest;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public TreasureChest getTreasureChest() {
        return treasureChest;
    }

    public Character get(int x, int y) {
        if (x < 0 || x >= GRID_WIDTH)
            throw new IllegalArgumentException("x outside of range");

        if (y < 0 || y >= GRID_HEIGHT)
            throw new IllegalArgumentException("y outside of range");

        return this.grid[x][y];
    }

    /**
     * Aktualisiert die Position des Spielers basierend auf der angegebenen Bewegungsrichtung.
     *
     * @param playerMovement die Bewegungsrichtung des Spielers
     * @throws IllegalArgumentException wenn playerMovement null ist
     */
    public void updatePlayer(Direction playerMovement) {
        if (playerMovement == null) {
            throw new IllegalArgumentException("playerMovement darf nicht null sein!");
        }

        // Ermitteln der neuen Bewegungsrichtung des Spielers
        Direction movementDirection = player.update(this, playerX, playerY, playerMovement);

        // Zielposition ermitteln
        int targetX = playerX + movementDirection.getDX();
        int targetY = playerY + movementDirection.getDY();

        // Bedingungen für die Bewegung prüfen
        if (targetX >= 0 && targetX < GRID_WIDTH && targetY >= 0 && targetY < GRID_HEIGHT) {
            Character targetCell = grid[targetX][targetY];
            if (targetCell == null || (targetCell != null && targetCell.collisionFrom(player))) {
                // Bewegung durchführen
                grid[playerX][playerY] = null; // Aktuelle Position leeren
                playerX = targetX;
                playerY = targetY;
                grid[playerX][playerY] = player; // Neue Position setzen
            }
        }
    }

    /**
     * Aktualisiert die Positionen aller anderen Charaktere auf dem Spielfeld und regelt Replikationen.
     *
     * @param playerMovement die Bewegungsrichtung des Spielers
     */
    public void updateOthers(Direction playerMovement) {
        currentIteration++; // Eine neue Iteration beginnt
    
        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                Character character = grid[x][y]; // holt den Character an der aktuellen Position 
    
                // Überspringen der Felder, die null sind (also kein Ch. an dieser Position), 
                // vom Helden belegt sind (also die Position gehört dem Spieler)
                // oder bereits verarbeitet wurden (also der Ch. wurde in dieser Iteration bereits aktualisiert)
                if (character == null || character == player || character.getLastUpdate() == currentIteration) {
                    continue;
                }
    
                // Setzen Sie lastUpdate auf currentIteration
                character.setLastUpdate(currentIteration);
    
                // Replikation des Charakters 
                if (handleReplication(character, x, y)) {
                    continue; // Mit dem nächsten Feld im Gitter fortfahren, wenn Replikation erfolgt ist
                }
    
                // Ermitteln der neuen Bewegungsrichtung des Charakters
                Direction movementDirection = character.update(this, x, y, playerMovement);
    
                // Zielposition ermitteln
                int targetX = x + movementDirection.getDX();
                int targetY = y + movementDirection.getDY();
    
                // Bedingungen für die Bewegung prüfen
                if (targetX >= 0 && targetX < GRID_WIDTH && targetY >= 0 && targetY < GRID_HEIGHT) {
                    Character targetCell = grid[targetX][targetY];
                    if (targetCell == null || (targetCell != null && targetCell.collisionFrom(character))) {
                        // Bewegung durchführen
                        grid[x][y] = null; // Aktuelle Position des Ch. leeren
                        if (character.isAlive()) { // wenn Ch. noch nach der Bewegung lebt, dann auf neue Pos setzen
                            grid[targetX][targetY] = character; // Neue Position setzen
                        }
                    }
                }
    
                // Wenn der Ch. nach der Bewegung nicht mehr lebt, dann das Feld leeren 
                if (!character.isAlive()) {
                    grid[x][y] = null;
                }
            }
        }
    }

    /**
  * Behandelt die Replikation eines Charakters, wenn dieser das Replicable Interface implementiert.
  *
  * @param character der zu prüfende Charakter
  * @param x die aktuelle X-Position des Charakters
  * @param y die aktuelle Y-Position des Charakters
  * @return true, wenn eine Replikation stattgefunden hat, andernfalls false
  */
    
    private boolean handleReplication(Character character, int x, int y) {
        if (character instanceof Replicable) { // instanceof prüft, ob ein Objekt eine Instanz einer Klasse ist 
            Replicable replicableCharacter = (Replicable) character;
            Direction replicationDirection = replicableCharacter.replicationDirection();
    
            // Wenn replicationDirection eine Richtung != NONE liefert
            if (replicationDirection != Direction.NONE) {
                int targetX = x + replicationDirection.getDX();
                int targetY = y + replicationDirection.getDY();
    
                // Prüfen, ob die Zielposition innerhalb des Gitters liegt und ob das Feld leer ist
                if (targetX >= 0 && targetX < GRID_WIDTH && targetY >= 0 && targetY < GRID_HEIGHT && grid[targetX][targetY] == null) {
                    Character newCharacter = replicableCharacter.generateReplication();
                    grid[targetX][targetY] = newCharacter;
                    return true; // Replikation erfolgt
                }
            }
        }
        return false; // Keine Replikation erfolgt
    }
}