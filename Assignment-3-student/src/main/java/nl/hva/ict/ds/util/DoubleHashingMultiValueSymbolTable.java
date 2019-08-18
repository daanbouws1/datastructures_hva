package nl.hva.ict.ds.util;

import nl.hva.ict.ds.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DoubleHashingMultiValueSymbolTable implements MultiValueSymbolTable<String, Player> {

    private int size;
    private Player[] map;
    private int collision;
    private int prime = getPrime();

    public DoubleHashingMultiValueSymbolTable(int arraySize) {
        this.size = arraySize;
        this.map = new Player[arraySize];
        this.collision = 0;
    }

    @Override
    public void put(String key, Player value) {
        int hash = Math.abs(key.hashCode() % size);
        if (map[hash] == null) {
            map[hash] = value;
        } else {
            this.collision++;
            for (int i = 0; i < size; i++) {
                int nextIndex = prime - (hash % prime);
                hash = (hash + nextIndex) % size;
                if (map[hash] == null) {
                    map[hash] = value;
                    break;
                } else {
                    this.collision++;
                }
            }
        }
    }

    public int getPrime()
    {
        for (int i = size - 1; i >= 1; i--)
        {
            int fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    fact++;
            if (fact == 0)
                return i;
        }
        /* Return a prime number */
        return 3;
    }

    @Override
    public List<Player> get(String key) {
        Stream<Player> stream = Arrays.stream(this.map);
        return stream
                .filter(player -> player != null && Objects.equals(player.getFirstName() + player.getLastName(), key))
                .collect(Collectors.toList());
    }

    public int getCollisions() {
        return collision;
    }
}
