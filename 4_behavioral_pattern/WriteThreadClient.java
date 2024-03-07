import java.io.IOException;
import java.util.Scanner;

public class WriteThreadClient implements Runnable {
    private Thread thread;
    private SocketWrapper socketWrapper;
    ClientType type;
    String name;

    public WriteThreadClient(SocketWrapper socketWrapper, String name, ClientType type) {
        this.socketWrapper = socketWrapper;
        this.name = name;
        this.type = type;
        this.thread = new Thread(this);
        thread.start();
    }
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.println("Enter command : ");
                String command = scanner.nextLine();
                Request r = new Request(type, name, command);
                socketWrapper.write(r);
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try{
                socketWrapper.write("closing");
                socketWrapper.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
