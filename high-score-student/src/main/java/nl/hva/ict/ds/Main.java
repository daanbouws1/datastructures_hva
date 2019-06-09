package nl.hva.ict.ds;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        ArrayList<Long> times = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            HighScoreList highScores = new InsertionSortHighScores();
//            HighScoreList highScores = new BucketSortHighScores();
//            HighScoreList highScores = new PriorityQueueHighScores();

            final int MAX_HIGH_SCORE = 100000;
            Random randomizer = new SecureRandom();

            Boolean keepLooping = true;
            long start = System.nanoTime();
            long timeElapsed  = 0;

            while(keepLooping) {
                Player dumbledore = new Player("Albus", "Dumbledore", randomizer.nextInt(MAX_HIGH_SCORE));
                highScores.add(dumbledore);
                timeElapsed = (System.nanoTime() - start);

                if (highScores.size() == 102400) {
                    if (highScores instanceof BucketSortHighScores) {
                        highScores.getHighScores(10);
                        timeElapsed = (System.nanoTime() - start);
                    }
                    keepLooping = false;
                } else if ( timeElapsed / Math.pow(10, 9) >= 15) {
                    System.out.println("reached limit on time");
                    keepLooping = false;
                }
            }
            System.out.println(timeElapsed);
            times.add(timeElapsed);
        }

        long meanTime = 0;
        for (Long time: times) {
            meanTime += time;
        }

        System.out.println("______");
        System.out.println(meanTime / times.size());
    }
}
