package edu.estu;

import java.util.Random;
public class Ball implements Runnable {//Makes a Thread
     int row;
     int[] results;
    public Ball(int[] results, int row) {
        this.results = results;
        this.row= row;
    }
    @Override
    public void run() {//The problem solving stage
        float position= (float) (row + 1) /2;
        Random rand =new Random();

        for(int i=0; i<row-1; i++) {
            boolean locator = rand.nextBoolean();
            if (locator){
                position-= 0.5F;
            }
            else{
                position+= 0.5F;
            }
        }
        synchronized (results){
            results[(int)position-1]++;
        }
        }
    }

