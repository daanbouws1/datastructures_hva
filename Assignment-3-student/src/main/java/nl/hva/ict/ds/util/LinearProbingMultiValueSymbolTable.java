package nl.hva.ict.ds.util;

import nl.hva.ict.ds.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LinearProbingMultiValueSymbolTable implements MultiValueSymbolTable<String, Player> {

    private int size;
    private List<Player> map;
    private int collision;

    public LinearProbingMultiValueSymbolTable(int arraySize) {
        this.size = arraySize;
        this.map = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            map.add(null);
        }
        this.collision = 0;
    }

    @Override
    public void put(String key, Player value) {
        int hash = Math.abs(key.hashCode() % size);
        if (map.get(hash) == null) {
            map.set(hash, value);
        } else {
            this.collision++;
            for (int i = hash; i < hash+size; i++) {
                if(map.get(i % size) == null) {
                    map.set(i % size, value);
                    break;
                } else {
                    this.collision++;
                }
            }
        }
    }

    @Override
    public List<Player> get(String key) {
        return this.map.stream()
                .filter(player -> player != null && Objects.equals(player.getFirstName(), key))
                .collect(Collectors.toList());
    }

    public int getCollisions() {
        return this.collision;
    }
}
