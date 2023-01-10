package lesson40_41;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyWriter implements Runnable {
    FileWriter fw;
    Object locker;

    public MyWriter(String filePath, Object locker) {
        this.locker = locker;
        try{
            fw = new FileWriter(filePath, true);
        }
        catch (IOException ex){
            Logger.getLogger(MyWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        synchronized (locker){
            while(!Testsynchro.line.equals("exit")){
                try{
                    locker.wait();
                    if(!Testsynchro.line.equals("exit")){
                        fw.write(Testsynchro.line + System.getProperty("line.separator"));
                    }
                    System.out.println("*** Written line: " + Testsynchro.line);
                    locker.notify();
                }
                catch (IOException ex){
                    Logger.getLogger(MyWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (InterruptedException ex){
                    Logger.getLogger(MyWriter.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        try{
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(MyWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}