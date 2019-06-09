package nl.hva.ict.ds;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * If you have any tests that should be overwritten or added please put them to this class.
 */
public class ExtendedPlayerFinderTest extends HighScorePlayerFinderTest {

    @Test
    public final void returnNonIfLinearProbingTableEmpty() {
        highscores = new HighScorePlayerFinder(7);

        List<Player> albusses = highscores.findPlayer("Albus", null);

        assertEquals(0, albusses.size());
    }

    @Test
    public final void returnNonIfQuadraticProbingTableEmpty() {
        highscores = new HighScorePlayerFinder(7);

        List<Player> albusses = highscores.findPlayer(null, "Dumbledore");

        assertEquals(0, albusses.size());
    }

    @Test
    public final void returnNonIfDoubleHashingTableEmpty() {
        highscores = new HighScorePlayerFinder(7);

        List<Player> albusses = highscores.findPlayer("Albus", "Dumbledore");

        assertEquals(0, albusses.size());
    }

    @Test
    public void playerShouldPersistInAllHashTables() {
        List<Player> albusses = highscores.findPlayer("Albus", null);
        List<Player> dumbledores = highscores.findPlayer(null, "Dumbledore");
        List<Player> albusDumbledores = highscores.findPlayer("Albus", "Dumbledore");

        assertEquals(1, albusses.size());
        assertEquals(1, dumbledores.size());
        assertEquals(1, albusDumbledores.size());
        assertTrue(albusses.get(0) == dumbledores.get(0) && dumbledores.get(0) == albusDumbledores.get(0));
    }
}
