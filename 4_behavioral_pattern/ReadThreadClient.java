import java.io.IOException;

public class ReadThreadClient implements Runnable {
    private Thread thread;
    private SocketWrapper socketWrapper;

    public ReadThreadClient (SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
        this.thread = new Thread(this);
        thread.start();
    }
    public void run() {
        try {
            while(true) {
                Object o = socketWrapper.read();
                if(o instanceof String) {
                    System.out.println(o);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in client read thread" + e);
        } finally {
            try {
                socketWrapper.write("closing");
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
