package nl.hva.ict.ds;

import nl.hva.ict.ds.util.DoubleHashingMultiValueSymbolTable;
import nl.hva.ict.ds.util.LinearProbingMultiValueSymbolTable;
import nl.hva.ict.ds.util.NameReader;
import nl.hva.ict.ds.util.QuadraticProbingMultiValueSymbolTable;

import java.security.SecureRandom;
import java.util.Random;

public class Main {

    public static void main(String []args) {
        int firstRun = 10501;
        int secondRun = 11701;
        int thirdRun = 13309;
        int fourthRun = 15401;

        for (int i = 0; i < 10; i++) {
//            LinearProbingMultiValueSymbolTable highScores = new LinearProbingMultiValueSymbolTable(fourthRun);
//            QuadraticProbingMultiValueSymbolTable highScores = new QuadraticProbingMultiValueSymbolTable(fourthRun);
            DoubleHashingMultiValueSymbolTable highScores = new DoubleHashingMultiValueSymbolTable(fourthRun);
            String [] firstNames = new NameReader("/firstnames.txt").getNames();
            String [] lastNames = new NameReader("/lastnames.txt").getNames();

            Random randomizer = new SecureRandom();
            for (int x = 0; x < 10000; x++) {
                String firstName = firstNames[randomizer.nextInt(firstNames.length)];
                String lastName = lastNames[randomizer.nextInt(lastNames.length)];
                highScores.put(firstName + lastName, new Player(firstName, lastName, randomizer.nextInt(1000)));
            }
            System.out.println(highScores.getCollisions());
        }
    }
}
