package nl.hva.ict.ds;

import java.util.List;

public class InsertionSorter {

    public InsertionSorter() {
    }

    public List<Player> sortPlayers(List<Player> players) {
        Player unsortedPlayer = players.get(players.size() - 1);
        players.remove(players.size() - 1);
        for (int i = players.size() - 1; i >= 0; i--) {
            if (unsortedPlayer.getHighScore() > players.get(i).getHighScore()) {
                players.add(i, unsortedPlayer);
                break;
            }
        }

        return players;
    }
}
