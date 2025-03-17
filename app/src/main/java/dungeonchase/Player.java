package dungeonchase;
/**
 * Die Player-Klasse repräsentiert den Spielercharakter im Spiel.
 * Diese Klasse erbt von der Character Klasse.
 */
public class Player extends Character {

    public Player(String name) {
        super(name); // super ruft den Konsturker der Basisklasse auf 
    }

    /*Grund für Override: Der Compiler überprüft, ob die Methode tatsächlich 
    eine Methode der übergeordneten Klasse überschreibt. 
    Wenn sie das nicht tut, wird ein Fehler angezeigt. */

    @Override
    public String getImage(){
        return "player.png";
    }

    /**
     * Aktualisiert die Position des Spielers.
     * Der Spieler bewegt sich in die Richtung, die durch playerMovement angegeben ist.
     * 
     * @param grid das Spielfeld
     * @param x die aktuelle x-Position des Spielers
     * @param y die aktuelle y-Position des Spielers
     * @param playerMovement die Bewegungsrichtung des Spielers
     * @return die Richtung, in die sich der Spieler bewegt
     */

    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement){
        return playerMovement;

    }

}


