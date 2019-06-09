package nl.hva.ict.ds;

import java.util.*;
import java.util.stream.Collectors;

public class PriorityQueueHighScores implements HighScoreList {
    Comparator<Player> playerComparator = (player1, player2) -> Long.compare(player2.getHighScore(), player1.getHighScore());

    PriorityQueue<Player> players = new PriorityQueue<>(playerComparator);

    @Override
    public void add(Player player) {
        players.add(player);
    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        PriorityQueue<Player> playersCopy = new PriorityQueue<>(players);
        if (numberOfHighScores >= 0) {
            int amountOfScores = Math.min(numberOfHighScores, players.size());
            ArrayList<Player> highScores = new ArrayList<>();
            for (int i = 0; i < amountOfScores; i++) {
                highScores.add(playersCopy.poll());
            }
            return highScores;
        }
        return null;
    }

    @Override
    public List<Player> findPlayer(String firstName, String lastName) throws IllegalArgumentException {
        if (firstName != null && lastName != null) {
            return players.stream()
                    .filter(player -> Objects.equals(player.getFirstName(), firstName) && Objects.equals(player.getLastName(), lastName))
                    .collect(Collectors.toList());
        } else if (firstName != null) {
            return players.stream()
                    .filter(player -> Objects.equals(player.getFirstName(), firstName))
                    .collect(Collectors.toList());
        } else if (lastName != null) {
            return players.stream()
                    .filter(player -> Objects.equals(player.getLastName(), lastName))
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public int size() {
        return players.size();
    }
}
