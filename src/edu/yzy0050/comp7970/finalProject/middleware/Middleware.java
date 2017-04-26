package edu.yzy0050.comp7970.finalProject.middleware;

import java.io.*;
import java.util.Random;

import edu.yzy0050.comp7970.finalProject.client.*;

public class Middleware{

    
    private String locationGenerator(NavigationClient client) {
        //Based on some algorithm, new generated dummy clients should be 
        //related to the real client location. But here, we simply randomly
        //generate a fake client.
        
        return Integer.toString(new Random().nextInt(11) + 20) + 
               Integer.toString(new Random().nextInt(11) + 20);
    }
    
    private int xExtractor(String location) {
        return Integer.valueOf(location.substring(0, 2));
        
    }
    
    private int yExtractor(String location) {
        return Integer.valueOf(location.substring(2, 4));
    }

    
    public void tester() {
        String test = this.locationGenerator(null);
        System.out.println(test);
        
        int x = this.xExtractor(test);
        int y = this.yExtractor(test);
        System.out.println(x);
        System.out.println(y);
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        new Middleware().tester();
    }

}
