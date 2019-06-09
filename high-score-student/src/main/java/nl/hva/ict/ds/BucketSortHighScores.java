package nl.hva.ict.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BucketSortHighScores implements HighScoreList {
    private List<Player> players = new ArrayList<>();

    @Override
    public void add(Player player) {
        players.add(player);
    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        players = bucketsort((int) Math.sqrt(players.size()));
        if (numberOfHighScores >= 0) {
            return players.subList(0, Math.min(numberOfHighScores, players.size()));
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

    private List<Player> bucketsort(int numberOfBuckets) {
        ArrayList[] buckets = new ArrayList[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets[i] = new ArrayList<Player>();
        }

        int i = 0;
        for (Player player: players) {
            buckets[i].add(player);
            i++;
            if (i == numberOfBuckets) {
                i = 0;
            }
        }

        int x = 0;
        for(ArrayList bucket : buckets) {
            buckets[x] = selectionSort(bucket);
            x++;
        }

        ArrayList<Player> sortedHighScores = new ArrayList<>();
        for(ArrayList bucket : buckets){
            sortedHighScores.addAll(bucket);
        }

        return selectionSort(sortedHighScores);
    }

    private ArrayList<Player> selectionSort(ArrayList<Player> arr)
    {
        int n = arr.size();

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr.get(j).getHighScore() > arr.get(min_idx).getHighScore())
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            Collections.swap(arr, min_idx, i);
        }

        return arr;
    }
}
