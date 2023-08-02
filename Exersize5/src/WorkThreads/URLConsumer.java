package WorkThreads;

import java.util.Random;

public class URLConsumer extends Thread{

    String consumerID;
    URLQueue queue;
    boolean keepWorking;
    Random delay;

    public URLConsumer(String id, URLQueue queue){
        if(queue == null){
            throw new IllegalArgumentException("Shared queue cannot be null");
        }
        consumerID = id;
        this.queue = queue;
        keepWorking = true;
        delay = new Random();
    }

    public void run(){
        while(keepWorking || !queue.isEmpty()) {
            String url = queue.getURL();
            if (url != null) {
                System.out.println(consumerID + " consumed " + url);
            } else {
                System.out.println(consumerID + " skipped empty queue");
            }
            try{
                Thread.sleep(delay.nextInt(1000));
            } catch (InterruptedException ie){
                System.err.println("Consumer " + consumerID + " interrupted. Quitting.");
                break;
            }
        }
    }

    public void setKeepWorking(boolean keepWorking) {
        this.keepWorking = keepWorking;
    }
}