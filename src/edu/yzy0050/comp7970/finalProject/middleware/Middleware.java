package edu.yzy0050.comp7970.finalProject.middleware;

import java.io.*;
import java.util.Random;

import edu.yzy0050.comp7970.finalProject.client.*;

public class Middleware{
    
    private String realLocation;
    private int locationNum;

    public Middleware (String realLocation, int locationNum) {
        this.realLocation = realLocation;
        this.locationNum = locationNum;
    }
    
    /**
     * 
     * @param realLocation the real location from client
     * @param locationNum the number of fake locations to be generated
     * @return assembled location array
     */
    public String locationGenerator() {
        //Based on some algorithm, new generated dummy clients should be 
        //related to the real client location. But here, we simply randomly
        //generate a fake client.
        String locations = this.realLocation;
        String result = "";
        
        int count = locations.length() - locations.replace("#", "").length();
        int begin = 0;
        int end = 1;
        for (int j = 0; j < count; ++j) {
            String oneLocation = locations.substring((begin + j) * 10, (end + j) * 10);
            for (int i = 0; i < locationNum - 1; ++i) {
                oneLocation += "*";
                oneLocation += Integer.toString(new Random().nextInt(9000) + 1000).toString();
                oneLocation += Integer.toString(new Random().nextInt(9000) + 1000).toString();
            }
            
            result += oneLocation;
        }
        return result;
    }
    
    /**
     * 
     * @param allService all services returned by server
     * @return the real service for the client
     */
    public String getRealService(String allService) {
        String realService = "";
        
        for (int i = 0; i < allService.length(); ++i) {
            if (allService.charAt(i) == '#') {
                realService += allService.substring(i + 2, i + 19) + "\n";
                i += 19;
            }
        }
        
        return realService;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        final long startTime = System.currentTimeMillis();
        
        Middleware test = new Middleware("#*10001000#*20002000",3);
        System.out.println(test.locationGenerator());
        new NavigationClient("#*10001000#*20002000", 3).run();
        final long endTime = System.currentTimeMillis();
        System.out.println("Execution time:" + (endTime - startTime));
    }

}
