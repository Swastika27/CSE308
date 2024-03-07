import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private HashMap<String, SocketWrapper> clientMap;
    private HashMap<String, Stock> stockMap;
    private HashMap<String, User> userMap;

    private void readStocksFromFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        while(scanner.hasNextLine()) {
            String[] stockData = scanner.nextLine().split(" ");
            Stock stock = new Stock(stockData[0], Integer.parseInt(stockData[1]), Float.parseFloat(stockData[1]));
            stockMap.put(stockData[0], stock);
        }
    }
    public Server(String fileName) throws FileNotFoundException {
        clientMap = new HashMap<>();
        stockMap = new HashMap<>();
        userMap = new HashMap<>();
        readStocksFromFile(fileName);
        System.out.println("data read complete");
        try {
            serverSocket = new ServerSocket(5000);
            System.out.println("created serverSocket");
            while(true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        String clientName = (String) socketWrapper.read(); // read Client name
        clientMap.put(clientName, socketWrapper);
        if(!clientName.equalsIgnoreCase("admin")) {
            userMap.putIfAbsent(clientName, new User(clientName));
            User user = userMap.get(clientName);
            System.out.println("user " + user);
            ArrayList<String> pendingNotifications = user.getNotifications();
            socketWrapper.write("You have " + pendingNotifications.size() + " new notifications");
            for(String n : pendingNotifications) {
                socketWrapper.write(n);
            }
            user.clearNotifications();
            for(Stock s : stockMap.values()) {
                socketWrapper.write("Name " + s.getName() + ", price " + s.getPrice());
            }
        }
        new ReadThreadServer(clientMap, stockMap, userMap, socketWrapper);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Server s = new Server("init_stocks.txt");
    }
}
