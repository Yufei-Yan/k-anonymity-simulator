package edu.yzy0050.comp7970.finalProject.client;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import edu.yzy0050.comp7970.finalProject.middleware.*;

public class NavigationClient extends TimerTask{

    private int port = 10000;
    private int clientNum = 3;
    private int x;
    private int y;
    
    public NavigationClient() {
        x = 20;
        y = 20;
    }
    
    public NavigationClient(int x, int y, int clients) {
        this.x = x;
        this.y = y;
        this.clientNum = clients;
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
    
    private void clientReceiver(DatagramSocket socket) throws IOException {
        //DatagramSocket socket = new DatagramSocket();
        //InetAddress ip = InetAddress.getByName("localhost");
        
        byte[] serviceRaw = new byte[100];
        
        DatagramPacket packet = new DatagramPacket(serviceRaw, serviceRaw.length);
        socket.receive(packet);
        String serviceInfo = new String(packet.getData());
        System.out.println(serviceInfo);
        
        //socket.close();
    }
    
    public String getLocation() {
        //System.out.println(x);
        //System.out.println(y);
        return Integer.toString(x) + Integer.toString(y);
    }
    

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            System.out.println("Client running...");
            DatagramSocket socket = new DatagramSocket();
            
            this.clientSender(socket, this.getLocation());
            this.clientReceiver(socket);
            
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
