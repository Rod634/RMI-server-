package br.edu.ifba.ads.rmi;

import java.rmi.*;
import java.util.ArrayList;
public interface ReceiveMessageInterface extends Remote
{
        public ArrayList buffer = new ArrayList();
        public Semaphore mutex = new Semaphore(0);
        public void addItem() throws RemoteException;
        public void removeItem() throws RemoteException;
        public void addBuffer(int contador) throws RemoteException;        
        public int getBuffer() throws RemoteException;        
        public void removeBuffer() throws RemoteException;
        public void upMutex() throws RemoteException;        
        public void downMutex() throws RemoteException;

}
