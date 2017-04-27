package edu.yzy0050.comp7970.finalProject.server;

import java.io.*;
import java.net.*;

public class NavigationServer {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        DatagramSocket serverSocket = new DatagramSocket(10000);

        byte[] receivedData = new byte[10240];
        byte[] sendData = new byte[10240];

        while (true) {
            DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            serverSocket.receive(receivedPacket);
            
            byte[] data = new byte[receivedPacket.getLength()];
            System.arraycopy(receivedPacket.getData(), receivedPacket.getOffset(), data, 0, receivedPacket.getLength());
            
            String dataString = new String(data, "UTF-8");
            
            String sentence = new String(dataString);
            System.out.println("Received: " + sentence);
            System.out.println("length: " + sentence.length());
            
            String allServices = "";
            int i = 0;
            while (i < sentence.length()) {
                
                while (sentence.charAt(i) == '#' || sentence.charAt(i) == '*') {
                    allServices += sentence.charAt(i);
                    ++i;
                }
                String x = sentence.substring(i, i + 4);
                i += 4;
                String y = sentence.substring(i, i + 4);
                i += 4;
                
//                System.out.println(i);
//                System.out.println(x);
//                System.out.println(y);
//                System.out.println(allServices);
                
                int service = Integer.parseInt(x) + Integer.parseInt(y);
                String thisService = Integer.toString(service);
                allServices += "(";
                allServices += x;
                allServices += ", ";
                allServices += y;
                allServices += ")@";
                allServices += thisService;
                
            }
            
            InetAddress ip = receivedPacket.getAddress();
            int port = receivedPacket.getPort();
            String returnedSentence = allServices;
            sendData = returnedSentence.getBytes();
            DatagramPacket sentPacket = new DatagramPacket(sendData, sendData.length, ip, port);
            
            serverSocket.send(sentPacket);
            
        }
    }

}
