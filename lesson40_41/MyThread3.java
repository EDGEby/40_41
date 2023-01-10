package lesson40_41;

class MyThread3 implements Runnable{
    private int value;

    public MyThread3(int value) {
        this.value = value;
    }

    @Override
    public void run(){
        int limit = this.value/2;

        while(value>=0){
            System.out.println("*** yhe thread "+ Thread.currentThread().getName() + " started with " + value);
            while (value>=0){
                System.out.println("From " + Thread.currentThread().getName()+ "value ="+value);
                value-=1;
                if (value>=limit && Thread.currentThread().isInterrupted()) {
                    System.out.println("*** the thread is interrupted!");
                    return;
                }
                try {
                    Thread.sleep(300);
                }
                catch (InterruptedException ex)
                { }
            }
        }
        System.out.println("*** the thread " + Thread.currentThread().getName()+"has finished");

    }
}
