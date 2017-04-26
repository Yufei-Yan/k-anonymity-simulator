package edu.yzy0050.comp7970.finalProject.client;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import edu.yzy0050.comp7970.finalProject.*;

public class Simulator{

    private int x;
    private int y;
    private boolean wall = false;
    
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        Simulator[] test = new Simulator[3];
        for (int i = 0; i < 3; ++i) {
            test[i] = new Simulator();
        }
        NavigationClient client1;
        NavigationClient client2;
        NavigationClient client3;
        test[0].coordinates();
        
        client1 = new NavigationClient(test[0].getX(), test[0].getY(), 0);
        client1.run();
        
        test[1].coordinates();
        
        client2 = new NavigationClient(test[1].getX(), test[1].getY(), 1);
        client2.run();
        
        test[2].coordinates();
        
        client3 = new NavigationClient(test[1].getX(), test[1].getY(), 0);
        client3.run();
        
        Thread.sleep(2000);
        while(true) {
            test[0].move(1, test[0].getWall());
            test[1].move(1, test[1].getWall());
            test[2].move(1, test[2].getWall());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            client2 = new NavigationClient(test[1].getX(), test[1].getY(), 0);
            client2.run();
            client1 = new NavigationClient(test[0].getX(), test[0].getY(), 1);
            client1.run();
            client3 = new NavigationClient(test[2].getX(), test[2].getY(), 0);
            client3.run();
        }
        
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public boolean getWall() {
        return wall;
    }
    
    public void coordinates() {
        this.x = new Random().nextInt(11) + 20;
        this.y = new Random().nextInt(11) + 20;
    }
    
    
    public void move(int direction, boolean wall) {
        if (1 == direction) {
           if (x < 30 && x > 20 && false == wall) {
               x += 1;
           } else if (x >=30) {
               x -= 1;
               this.wall = true;
           } else if (x < 30 && x > 20 && true == wall) {
               x -= 1;
           } else if (x <= 20) {
               x += 1;
               this.wall = false;
           } else if (x > 20 && x < 30 && false == wall) {
               x += 1;
           } else if (x > 20 && x < 30 && true == wall) {
               x -= 1;
           }
            
        } else {
            if (y < 30 && y > 20 && false == wall) {
                y += 1;
            } else if (y >=30) {
                y -= 1;
                this.wall = true;
            } else if (y < 30 && y > 20 && true == wall) {
                y -= 1;
            } else if (y <= 20) {
                y += 1;
                this.wall = false;
            } else if (y > 20 && y < 30 && false == wall) {
                y += 1;
            } else if (y > 20 && y < 30 && true == wall) {
                y -= 1;
            }
        }
        
    }


}
