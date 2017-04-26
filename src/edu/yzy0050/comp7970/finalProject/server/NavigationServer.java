package edu.yzy0050.comp7970.finalProject.server;

import java.io.*;
import java.net.*;

public class NavigationServer {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        DatagramSocket serverSocket = new DatagramSocket(10000);

        byte[] receivedData = new byte[1024];
        byte[] sendData = new byte[1024];

        while (true) {
            DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            serverSocket.receive(receivedPacket);
            
            String sentence = new String(receivedPacket.getData());
            System.out.println("Received: " + sentence);
            
            int result = Integer.valueOf(sentence.substring(0, 2)) + 
                         Integer.valueOf(sentence.substring(2, 4));
            String service = "Service at: (" + 
                             sentence.substring(0, 2) + ", " + 
                             sentence.substring(2, 4) + ") is " + 
                             result;
            
            InetAddress ip = receivedPacket.getAddress();
            int port = receivedPacket.getPort();
            String returnedSentence = service;
            sendData = returnedSentence.getBytes();
            DatagramPacket sentPacket = new DatagramPacket(sendData, sendData.length, ip, port);
            
            serverSocket.send(sentPacket);
            
        }
    }

}
