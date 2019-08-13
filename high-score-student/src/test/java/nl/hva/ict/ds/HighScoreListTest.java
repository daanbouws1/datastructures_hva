package nl.hva.ict.ds;

import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains some unit tests. They by no means ensure that all the requirements are implemented
 * correctly.
 */
public class HighScoreListTest {
    private static final int MAX_HIGH_SCORE = 100000;
    private Random randomizer = new SecureRandom();
    private HighScoreList highScores;
    private Player nearlyHeadlessNick;
    private Player dumbledore;

    @Before
    public void setup() {
        // Here you should select your implementation to be tested.
//        highScores = new DummyHighScores();
        highScores = new InsertionSortHighScores();
//        highScores = new SelectionSortHighScores();
//        highScores = new BucketSortHighScores();
//        highScores = new PriorityQueueHighScores();

        nearlyHeadlessNick = new Player("Nicholas", "de Mimsy-Porpington", getHighScore() % 200);
        dumbledore = new Player("Albus", "Dumbledore", nearlyHeadlessNick.getHighScore() * 1000);
    }

    @Test
    public void noPlayerNoHighScore() {
        assertTrue("There are high-score while there should be no high-scores!", highScores.getHighScores(1).isEmpty());
    }

    @Test
    public void whenNoHighScoreIsAskedForNonShouldBeGiven() {
        highScores.add(dumbledore);

        assertEquals(0, highScores.getHighScores(0).size());
    }

    @Test
    public void noMoreHighScoresCanBeGivenThenPresent() {
        highScores.add(nearlyHeadlessNick);
        highScores.add(dumbledore);

        assertEquals(2, highScores.getHighScores(10).size());
    }

    @Test
    public void keepAllHighScores() {
        highScores.add(nearlyHeadlessNick);
        highScores.add(dumbledore);

        assertEquals(2, highScores.getHighScores(2).size());
    }

    @Test
    public void singlePlayerHasHighScore() {
        highScores.add(dumbledore);

        assertEquals(dumbledore, highScores.getHighScores(1).get(0));
    }

    @Test
    public void harryBeatsDumbledore() {
        highScores.add(dumbledore);
        Player harry = new Player("Harry", "Potter", dumbledore.getHighScore() + 1);
        highScores.add(harry);

        for (Player player: highScores.getHighScores(10)) {
            System.out.println(player.getHighScore());
        }
        assertEquals(harry, highScores.getHighScores(2).get(0));
    }

    // Extra unit tests go here

    private long getHighScore() {
        return randomizer.nextInt(MAX_HIGH_SCORE);
    }

    @Test
    public void noNegativeAmountOfHighScores() {
        assertEquals(null, highScores.getHighScores(-5));
    }

    @Test
    public void highScoresAreSorted() {
        for (int i = 0; i < 50; i++) {
            Player random = new Player("random", "random", (long) (Math.random() * 10000));
            highScores.add(random);
        }

        List<Player> sortedHighscores = highScores.getHighScores(50);

        int i = 0;
        for(Player player: sortedHighscores) {
            System.out.println(player.getHighScore());
            if (i > 0) {
                assertTrue(player.getHighScore() <= sortedHighscores.get(i - 1).getHighScore());
            }
            i++;
        }
    }

    @Test
    public void findSinglePlayerByFirstname() {
        highScores.add(dumbledore);

        List<Player> filtered = highScores.findPlayer(dumbledore.getFirstName(), null);

        assertEquals(dumbledore, filtered.get(0));
        assertEquals(1, filtered.size());
    }

    @Test
    public void findMultiplePlayerByFirstname() {
        highScores.add(dumbledore);
        Player test = new Player(dumbledore.getFirstName(), "Test", dumbledore.getHighScore() + 1);
        highScores.add(test);

        List<Player> filtered = highScores.findPlayer(dumbledore.getFirstName(), null);

        for (Player player: filtered) {
            assertEquals(dumbledore.getFirstName(), player.getFirstName());
        }

        assertEquals(2, filtered.size());
    }

    @Test
    public void findSinglePlayerByLastname() {
        highScores.add(dumbledore);

        List<Player> filtered = highScores.findPlayer(null, dumbledore.getLastName());

        assertEquals(dumbledore, filtered.get(0));
    }

    @Test
    public void findMultiplePlayerByLastname() {
        highScores.add(dumbledore);
        Player test = new Player("Test", dumbledore.getLastName(), dumbledore.getHighScore() + 1);
        highScores.add(test);

        List<Player> filtered = highScores.findPlayer(null, dumbledore.getLastName());

        for (Player player: filtered) {
            assertEquals(dumbledore.getLastName(), player.getLastName());
        }

        assertEquals(2, filtered.size());
    }

    @Test
    public void findSinglePlayerByFullname() {
        highScores.add(dumbledore);

        List<Player> filtered = highScores.findPlayer(dumbledore.getFirstName(), dumbledore.getLastName());

        assertEquals(dumbledore, filtered.get(0));
    }

    @Test
    public void findMultiplePlayersByFullname() {
        highScores.add(dumbledore);
        Player test = new Player(dumbledore.getFirstName(), dumbledore.getLastName(), dumbledore.getHighScore() + 1);
        highScores.add(test);

        List<Player> filtered = highScores.findPlayer(dumbledore.getFirstName(), dumbledore.getLastName());

        for (Player player: filtered) {
            assertEquals(dumbledore.getFirstName(), player.getFirstName());
            assertEquals(dumbledore.getLastName(), player.getLastName());
        }

        assertEquals(2, filtered.size());
    }

    @Test
    public void findPlayerReturnsEmptyIfNotPresent() {
        highScores.add(dumbledore);

        List<Player> filtered = highScores.findPlayer("Bestaat", "Niet");

        assertEquals(0, filtered.size());
    }
}
