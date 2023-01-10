package lesson40_41;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Testsynchro {
    public static String line = "";

    public static void main(String[] args) {
        Object locker = new Object();

        MyReader reader = new MyReader("lines.txt", locker);
        MyWriter writer = new MyWriter("lines_out.txt", locker);

        Thread t1 = new Thread(reader);
        Thread t2 = new Thread(writer);
        t1.setDaemon(true);
        t2.setDaemon(true);
        t2.start();
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException ex){
            Logger.getLogger(MyReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        t1.start();
        try{
            t1.join();
            t2.join();
        }
        catch(InterruptedException ex){
            ex.printStackTrace();
            Logger.getLogger(MyReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}