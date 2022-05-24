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
public class RMIProducer extends Thread {
    
            ReceiveMessageInterface rmiServer;
            Registry registry;
            String serverAddress;
            String serverPort; 
    
        public RMIProducer(String args[]){
            this.serverAddress= args[0];
            this.serverPort= args[1];
        }
    
        int contador =0;
        public void run() {
                   try {
                        // get the �registry� 
                        registry=LocateRegistry.getRegistry(
                            serverAddress,
                            (new Integer(serverPort)).intValue()
                        );

                        // look up the remote object
                        rmiServer=(ReceiveMessageInterface)(registry.lookup("rmiServer"));
                       
                       //producer
                       while (true) {
                            contador ++;
                            rmiServer.addBuffer(contador);
                            rmiServer.addItem();
                            rmiServer.upMutex();
                            System.out.println("produtor: producing item "+contador);
                            sleep(1000);
                        }
                   }
                   catch (Exception e) {
                          e.printStackTrace();
                   }
                   
        }

}
    
