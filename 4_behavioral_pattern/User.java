import java.io.Serializable;
import java.util.ArrayList;

public class User implements Subscriber {
    private String name;
    private ArrayList<Stock> subscribedStocks;
    private ArrayList<String> notifications;
    private boolean hasNewNotification;

    User(String name) {
        this.name = name;
        notifications = new ArrayList<>();
        subscribedStocks = new ArrayList<>();
    }
    @Override
    public void addToNotifications(String event) {
        notifications.add(event);
        hasNewNotification = true;
    }
    @Override
    public String getName() {
        return name;
    }
    public void addToSubscribedStocks(Stock s) {
        subscribedStocks.add(s);
    }
    public void removeFromSubscribedStocks(Stock s) {
        subscribedStocks.remove(s);
    }
    public ArrayList<Stock> getSubscribedStocks() {
        return subscribedStocks;
    }
    public ArrayList<String> getNotifications() {
        return notifications;
    }
    public void clearNotifications() {
        notifications.clear();
    }
    public String toString() {
        return name;
    }
}
