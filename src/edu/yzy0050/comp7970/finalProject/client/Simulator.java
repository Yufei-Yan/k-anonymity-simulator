package edu.yzy0050.comp7970.finalProject.client;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import edu.yzy0050.comp7970.finalProject.*;

public class Simulator extends TimerTask{

    private int x;
    private int y;
    private boolean wall = false;
    private int clientNum = 2;
    private String realLocations;
    
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        final long startTime = System.currentTimeMillis();
        
        Simulator simulate = new Simulator();
        String realLocations = simulate.locationGenerator();
        simulate.setRealLocations(realLocations);
        
        new NavigationClient(simulate.getRealLocations(), 3).run();;
        
        Timer timer = new Timer();
        timer.schedule(simulate, 500, 1000);
        final long endTime = System.currentTimeMillis();
        System.out.println("Execution time:" + (endTime - startTime));
    }
    
    public boolean getWall() {
        return wall;
    }

    public String locationGenerator() {
        String location = "";
        for (int i = 0; i < clientNum; ++i) {
            location += "#";
            location += "*";
            location += Integer.toString(new Random().nextInt(9000) + 1000).toString();
            location += Integer.toString(new Random().nextInt(9000) + 1000).toString();
        }
        return location;
    }
    
    public void setRealLocations(String realLocations) {
        this.realLocations = realLocations;
    }
    
    public String getRealLocations() {
        return this.realLocations;
    }
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        //String realLocations = this.locationGenerator();
        final long startTime = System.currentTimeMillis();
        int begin = 0;
        int end = 1;
        
        String oneLocation = "";
        String newRealLocations = "";
        
        for (int i = 0; i < clientNum; ++i) {
            //get individual location
            oneLocation = this.realLocations.substring((begin + i) * 10, (end + i) * 10);
            
            x = Integer.parseInt(oneLocation.substring(2, 6));
            y = Integer.parseInt(oneLocation.substring(6, 10));
            
            this.move(1, wall);
            System.out.println("x: " + x);
            System.out.println("y: " + y);
            
            newRealLocations += "#*" + Integer.toString(this.x) + Integer.toString(this.y);
        }
        
        realLocations = newRealLocations;
        new NavigationClient(newRealLocations, 3).run();;
        final long endTime = System.currentTimeMillis();
        System.out.println("Execution time:" + (endTime - startTime));
    }
    
    /**
     * Bounds are less than 9999, greater than 1000
     * @param direction 1: x-axis, 0: y-axis
     * @param wall if it hits the wall
     */
    public void move(int direction, boolean wall) {
        if (1 == direction) {
           if (x < 9999 && x > 1000 && false == wall) {
               x += 1;
           } else if (x >= 9999) {
               x -= 1;
               this.wall = true;
           } else if (x < 9999 && x > 1000 && true == wall) {
               x -= 1;
           } else if (x <= 1000) {
               x += 1;
               this.wall = false;
           } else if (x > 1000 && x < 1000 && false == wall) {
               x += 1;
           } else if (x > 9999 && x < 1000 && true == wall) {
               x -= 1;
           }
            
        } else {
            if (y < 9999 && y > 1000 && false == wall) {
                y += 1;
            } else if (y >= 9999) {
                y -= 1;
                this.wall = true;
            } else if (y < 9999 && y > 1000 && true == wall) {
                y -= 1;
            } else if (y <= 1000) {
                y += 1;
                this.wall = false;
            } else if (y > 1000 && y < 9999 && false == wall) {
                y += 1;
            } else if (y > 1000 && y < 9999 && true == wall) {
                y -= 1;
            }
        }
        
    }


}
