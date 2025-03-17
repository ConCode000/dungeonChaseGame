package dungeonchase;

public interface Replicable {

    /**
     * Liefert eine Richtung != NONE zurück, wenn eine Vervielfältigung stattfinden soll.
     * NONE heißt, es soll nur eine herkömmliche Bewegung stattfinden, keine Vervielfältigung.
     * 
     * @return eine Bewegungsrichtung oder NONE.
     */
    Direction replicationDirection();

    /**
     * Liefert das Replikat des Charakters.
     * 
     * @return eine neue Instanz des Charakters, der die Vervielfältigung darstellt.
     */
    Character generateReplication();
}
