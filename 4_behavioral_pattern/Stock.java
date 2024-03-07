import java.io.Serializable;
import java.util.ArrayList;

public class Stock {
    private String name;
    private int count;
    private float price;
    private ArrayList<Subscriber> subscribers;

    public void notifyAllSubscribers(String event) {
        for(Subscriber s : subscribers) {
            s.addToNotifications(event);
        }
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public float getPrice() {
        return price;
    }

    Stock(String name, int count, float price) {
        this.name = name;
        this.count = count;
        this.price = price;
        subscribers = new ArrayList<>();
    }
    void subscribe(Subscriber s) {
        subscribers.add(s);
    }
    void unsubscribe(Subscriber s) {
        subscribers.remove(s);
    }

    void increasePrice(float amount) {
        price = price + amount;
    }
    void decreasePrice(float amount) {
        price = price - amount;
    }
    void changeCount(int count) {
        this.count = count;
    }
    @Override
    public String toString() {
        return name + " " + count + " " + price;
    }

    public ArrayList<Subscriber> getSubscribers() {
        return subscribers;
    }
}
