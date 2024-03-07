import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadThreadServer implements Runnable {
    private Thread thread;
    private SocketWrapper socketWrapper;
    private HashMap<String, SocketWrapper> clientMap;
    private HashMap<String, Stock> stockMap;
    private HashMap<String, User> userMap;

    public ReadThreadServer(HashMap<String, SocketWrapper> map, HashMap<String, Stock> stockMap, HashMap<String, User> userMap, SocketWrapper socketWrapper) {
        this.clientMap = map;
        this.socketWrapper = socketWrapper;
        this.stockMap = stockMap;
        this.userMap = userMap;
        this.thread = new Thread(this);
        thread.start();
    }

    private void notifyAllSubscribers(Stock s, String msg) {
        ArrayList<Subscriber> subscribers = s.getSubscribers();
        for(Subscriber subscriber : subscribers) {
            SocketWrapper socketWrapper = clientMap.get(subscriber.getName());
            try {
                socketWrapper.write(msg);
            } catch (Exception e) {
                subscriber.addToNotifications(msg);
                clientMap.put(subscriber.getName(), null);
            }
        }
    }
    private void decodeAndExecute(ClientType t, String name, String cmd) throws IOException {
//        System.out.println("inside decode execute");
        String[] command = cmd.split(" ");
        if(t == ClientType.admin) {
//            System.out.println("sender admin");
            if(command.length != 3) {
                socketWrapper.write("Invalid command");
                return;
            }
            Stock stock = stockMap.get(command[1]);
            if(stock == null) {
                socketWrapper.write("Stock does not exist");
                return;
            }
            switch (command[0]) {
                case "I":
                    stock.increasePrice(Float.parseFloat(command[2]));
                    notifyAllSubscribers(stock, stock.getName() + "'s price increased!");
                    break;
                case "D":
                    stock.decreasePrice(Float.parseFloat(command[2]));
                    notifyAllSubscribers(stock, stock.getName() + "'s price decreased!");
                    break;
                case "C":
                    stock.changeCount(Integer.parseInt(command[2]));
                    notifyAllSubscribers(stock, stock.getName() + "'s count changed!");
                    break;
                case "q":
                    clientMap.remove(name);
                default:
                    socketWrapper.write("Unknown command");
            }
        }
        else { // client is user
//            System.out.println("sender user");
            User u = userMap.get(name);
            if(u == null) {
                System.out.println(name + " user not found");
                return;
            }
            switch (command[0]) {
                case "S":
                    Stock stock = stockMap.get(command[1]);
                    if(stock == null) {
                        socketWrapper.write("Stock does not exist");
                        return;
                    }
                    stock.subscribe(u);
                    u.addToSubscribedStocks(stock);
                    break;
                case "U":
                    stock = stockMap.get(command[1]);
                    if(stock == null) {
                        socketWrapper.write("Stock does not exist");
                        return;
                    }
                    stock.unsubscribe(u);
                    u.removeFromSubscribedStocks(stock);
                    break;
                case "V":
//                    System.out.println("inside v");
                    ArrayList<Stock> subscribedStocks = u.getSubscribedStocks();
                    System.out.println(subscribedStocks.size());
                    for(Stock s : subscribedStocks) {
                        socketWrapper.write("Name: " + s.getName() + ", count: " + s.getCount() + ", price: " + s.getPrice());
                    }
                    break;
                case "q":
                    clientMap.remove(name);
                default:
                    socketWrapper.write("Unknown command");
            }
        }
    }
    public void run() {
        try {
            while(true) {
                Object o = socketWrapper.read();
                if(o instanceof Request) {
                    Request r = (Request) o;
                    decodeAndExecute(r.getType(), r.getClientName(), r.getCommand());
                }
                else if(o instanceof String) {
                    if(o.equals("closing"));
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
