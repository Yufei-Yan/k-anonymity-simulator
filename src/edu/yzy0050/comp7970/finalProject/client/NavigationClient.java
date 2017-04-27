package edu.yzy0050.comp7970.finalProject.client;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import edu.yzy0050.comp7970.finalProject.middleware.*;

public class NavigationClient extends TimerTask{

    private int port = 10000;
    //private int clientNum = 2;
    private int locationNum = 3;
    private String realLocation;
    
    public NavigationClient(String realLocation, int locationNum) {
        //this.clientNum = clientNum;
        this.locationNum = locationNum;
        this.realLocation = realLocation;
    }
    
    private void clientSender(DatagramSocket socket, String location) throws IOException {
        //DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        
        //System.out.println(location);
        byte[] locationByte = location.getBytes();
        
        DatagramPacket packet = new DatagramPacket(locationByte, locationByte.length, ip, port);
        
        socket.send(packet);
        //socket.close();
    }
    
    private String clientReceiver(DatagramSocket socket) throws IOException {
        //DatagramSocket socket = new DatagramSocket();
        //InetAddress ip = InetAddress.getByName("localhost");
        
        byte[] serviceRaw = new byte[10240];
        
        DatagramPacket packet = new DatagramPacket(serviceRaw, serviceRaw.length);
        socket.receive(packet);
        String serviceInfo = new String(packet.getData());
        System.out.println("service info: " + serviceInfo);
        
        //socket.close();
        
        return serviceInfo;
    }
    
    /**
     * 
     * @return a randomly generated real location 
     */
    private String locationGenerator() {
        return Integer.toString(new Random().nextInt(9000) + 1000) + 
               Integer.toString(new Random().nextInt(9000) + 1000);
    }
    

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("Client running");
        DatagramSocket socket;
        try {
            socket = new DatagramSocket();
            //String realLocation = this.locationGenerator();
            //String realLocation = "#*10001000#*20002000";
            Middleware dummy = new Middleware(realLocation, locationNum);
            
            String fakeLocations = dummy.locationGenerator();
            this.clientSender(socket, fakeLocations);
            String allService = this.clientReceiver(socket);
            
            String realService = dummy.getRealService(allService);
            
            System.out.println("Services are: \n" + realService);
            
            socket.close();
        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }
    
    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        //Timer timer = new Timer();
        //timer.schedule(new NavigationClient(), 500, 500);
    }
    

}
