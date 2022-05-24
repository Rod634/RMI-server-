package br.edu.ifba.ads.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ads
 */
public class RMIConsumer extends Thread {
   
    static public void main(String args[])
    {
        
        System.out.println(args[0]);
        
        ReceiveMessageInterface rmiServer;
        Registry registry;
        String serverAddress = args[0];
        String serverPort = args[1];
        
        try {
            // get the �registry� 
             registry=LocateRegistry.getRegistry(
                 serverAddress,
                 (new Integer(serverPort)).intValue()
             );

            // look up the remote object
            rmiServer=(ReceiveMessageInterface)(registry.lookup("rmiServer"));
            while (true) {
                rmiServer.downMutex();
                rmiServer.getBuffer();
                rmiServer.removeBuffer();
                rmiServer.removeItem();
                System.out.println("consumer: consuming item ");
                sleep(5);
            }

       }catch (Exception e) {
           e.printStackTrace();
       }
    }
}

