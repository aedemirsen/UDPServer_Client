/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author aedemirsen
 */
import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Server {

    public static void main(String args[])  {
        
        Scanner sc = new Scanner(System.in);        
        System.out.print("Port : ");
        int port = sc.nextInt();
        
        try {
            DatagramSocket serverSocket = new DatagramSocket(port);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            DatagramPacket receivePacket;
            System.out.println("Server Çalışıyor...");
            while (true) {
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String s = new String(receivePacket.getData());
                System.out.println("Gelen Data: " + s);
                InetAddress IPAddress = receivePacket.getAddress();
                port = receivePacket.getPort();
                sendData = s.getBytes();
                DatagramPacket sendPacket
                        = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
