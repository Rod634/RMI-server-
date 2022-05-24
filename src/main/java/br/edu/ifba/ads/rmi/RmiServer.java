package br.edu.ifba.ads.rmi;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.net.*;
import java.util.ArrayList;
 
public class RmiServer extends java.rmi.server.UnicastRemoteObject
implements ReceiveMessageInterface
{
    int      thisPort;
    String   thisAddress;
    Registry registry;    // rmi registry for lookup the remote objects.
    
    //program
    int itemCount = 0;
    ArrayList buffer;
    Semaphore mutex;
     
    public RmiServer() throws RemoteException
    {
        try{
            // get the address of this host.
            thisAddress= (InetAddress.getLocalHost()).toString();
            //program
            itemCount = 0;
            buffer = new ArrayList();
            mutex = new Semaphore(0);
        }
        catch(Exception e){
            throw new RemoteException("can't get inet address.");
        }
thisPort=9999;  // this port(registry�s port)
        System.out.println("this address="+thisAddress+",port="+thisPort);
        try{
        // create the registry and bind the name and object.
        registry = LocateRegistry.createRegistry( thisPort );
            registry.rebind("rmiServer", this);
                String[] params = {thisAddress.split("/")[1], Integer.toString(thisPort)} ;    
                RMIProducer rp1 = new RMIProducer(params);
                rp1.start();
        }
        catch(RemoteException e){
        throw e;
        }
    }
   
    static public void main(String args[])
    {
        try{
        RmiServer s=new RmiServer();
    }
    catch (Exception e) {
           e.printStackTrace();
           System.exit(1);
    }
     }

   
    
    public void addItem(){
        this.itemCount++;
    }
    
    public void removeItem(){
        this.itemCount--;
    }
    
    public void addBuffer(int contador) {
        this.buffer.add(contador);
    }

   
    public int getBuffer()  {
        return (Integer) this.buffer.get(0);
    }

   
    public void removeBuffer() {
        this.buffer.remove(0);
    }

  
    public void upMutex()  {
        this.mutex.up();
    }

   
    public void downMutex() {
        this.mutex.down();
    }
    
       
}