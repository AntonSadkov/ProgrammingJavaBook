package WorkThreads;

public class URLDemo {
    public static void main(String[] args){
        URLQueue queue = new URLQueue();

        URLProducer p1 = new URLProducer("P1", 100, queue);
        URLProducer p2 = new URLProducer("P2", 100, queue);

        URLConsumer c1 = new URLConsumer("C1", queue);
        URLConsumer c2 = new URLConsumer("C2", queue);
        URLConsumer c3 = new URLConsumer("C3", queue);
        URLConsumer c4 = new URLConsumer("C4", queue);

        System.out.println("Starting...");
        p1.start();
        p2.start();
        c1.start();
        c2.start();
        c3.start();
        c4.start();

        try {
            p1.join();
            p2.join();
        } catch(InterruptedException ie){
            System.err.println("Interrupted waiting for producers to finish");
        }

        c1.setKeepWorking(false);
        c2.setKeepWorking(false);
        c3.setKeepWorking(false);
        c4.setKeepWorking(false);
        try {
            c1.join();
            c2.join();
            c3.join();
            c4.join();
        } catch (InterruptedException io){
            System.err.println("Interrupted waiting for consumers to finish");
        }
        System.out.println("Done");
    }
}