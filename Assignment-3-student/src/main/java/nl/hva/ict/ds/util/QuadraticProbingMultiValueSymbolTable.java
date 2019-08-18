package nl.hva.ict.ds.util;

import nl.hva.ict.ds.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuadraticProbingMultiValueSymbolTable implements MultiValueSymbolTable<String, Player> {

    private int size;
    private Player[] map;
    private int collision;

    public QuadraticProbingMultiValueSymbolTable(int arraySize) {
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
            int nextIndex = hash;
            for (int i = 0; i < size; i++) {
                nextIndex = (int) ((nextIndex+Math.pow(i,2)) % size);
                if (map[nextIndex] == null) {
                    map[nextIndex] = value;
                    break;
                } else {
                    this.collision++;
                }
            }
        }
    }

    @Override
    public List<Player> get(String key) {
        Stream<Player> stream = Arrays.stream(this.map);
        return stream
                .filter(player -> player != null && Objects.equals(player.getLastName(), key))
                .collect(Collectors.toList());
    }

    public int getCollisions() {
        return this.collision;
    }
}
