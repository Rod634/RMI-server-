package br.edu.ifba.ads.rmi;

import java.rmi.*;

public interface ReceiveMessageInterface extends Remote
{
        public void addItem() throws RemoteException;
        public void removeItem() throws RemoteException;
        public void addBuffer(int contador) throws RemoteException;        
        public int getBuffer() throws RemoteException;        
        public void removeBuffer() throws RemoteException;
        public void upMutex() throws RemoteException;        
        public void downMutex() throws RemoteException;

}
