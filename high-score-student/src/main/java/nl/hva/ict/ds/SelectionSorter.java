package nl.hva.ict.ds;

import java.util.Collections;
import java.util.List;

public class SelectionSorter {

    public SelectionSorter() {
    }

    public List<Player> sortPlayers(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < players.size(); j++) {
                if (players.get(j).getHighScore() > players.get(minIndex).getHighScore()) {
                    minIndex = j;
                }
            }
            Collections.swap(players, minIndex, i);
        }

        return players;
    }

}
