/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author aedemirsen
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Client {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        System.out.print("IP : ");
        String ip = sc.nextLine();
        System.out.print("Port : ");
        int port = sc.nextInt();

        try {
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(ip);
            System.out.print("Mesajınız: ");
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String line = bf.readLine();
            sendData = line.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            clientSocket.send(sendPacket);
            System.out.println("Mesaj Gönderildi...");
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            clientSocket.close();

        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
