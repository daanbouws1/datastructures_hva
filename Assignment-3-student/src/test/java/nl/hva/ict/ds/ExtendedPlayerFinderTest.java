package nl.hva.ict.ds;

import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

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

    @Test
    public void shouldBeAbleToRetrieveAllPlayers() {
        highscores = new HighScorePlayerFinder(200);
        Player[] testPlayers = new Player[100];
        for (int i = 0; i < testPlayers.length; i++) {
            String randomFirstname = getAlphaNumericString(10);
            String randomLastname = getAlphaNumericString(11);
            Player randomPlayer = new Player(randomFirstname, randomLastname, (long) Math.random() * 10000);
            testPlayers[i] = randomPlayer;
            highscores.add(randomPlayer);
        }

        for (Player player: testPlayers) {
            assertEquals(player, highscores.findPlayer(player.getFirstName(), player.getLastName()).get(0));
        }
    }

    @Test
    public void shouldGetAllIdenticallyNamedPlayers() {
        highscores = new HighScorePlayerFinder(3);

        Player player1 = new Player("test","test",10);
        Player player2 = new Player("test", "test", 10);
        highscores.add(player1);
        highscores.add(player2);

        assertEquals(2, highscores.findPlayer("test", "test").size());
    }

    String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

}
