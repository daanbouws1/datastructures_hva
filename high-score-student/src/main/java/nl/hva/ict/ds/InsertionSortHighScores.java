package nl.hva.ict.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InsertionSortHighScores implements HighScoreList {
    private List<Player> players = new ArrayList<>();

    @Override
    public void add(Player player) {
        int n = players.size();
        if (n == 0) {
            players.add(player);
        } else {
            for (int i = 0; i < n; i++) {
                if (player.getHighScore() > players.get(i).getHighScore()) {
                    players.add(i, player);
                    break;
                } else {
                    players.add(player);
                    break;
                }
            }
        }
    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        if (numberOfHighScores >= 0) {
            return players.subList(0, Math.min(numberOfHighScores, players.size()));
        }
        return null;
    }

    @Override
    public List<Player> findPlayer(String firstName, String lastName) throws IllegalArgumentException {
        if (firstName != null && lastName != null) {
            return players.stream()
                    .filter(player -> Objects.equals(player.getFirstName(), firstName) && player.getLastName() == lastName)
                    .collect(Collectors.toList());
        } else if (firstName != null && lastName == null) {
            return players.stream()
                    .filter(player -> player.getFirstName() == firstName)
                    .collect(Collectors.toList());
        } else if (firstName == null && lastName != null) {
            return players.stream()
                    .filter(player -> player.getLastName() == lastName)
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public int size() {
        return players.size();
    }
}
