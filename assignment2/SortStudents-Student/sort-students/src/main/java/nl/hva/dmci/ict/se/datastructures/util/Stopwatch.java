/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.dmci.ict.se.datastructures.util;

/**
 *
 * @author REMCO
 */
public class Stopwatch {
    private long start;
    private long end;
    
    public void startStopwatch(){
        start = System.currentTimeMillis();
    }

    public void stopStopwatch(){
        end = System.currentTimeMillis();
    }

    public String getTime(){
        return "Runtime: " + (end - start) + " ms";
    }

}
