abstract public class Shake {
    static int lactoseFreeCost = 60;
    static int candyCost = 50;
    static int cookieCost = 40;

    protected boolean lactoseFree;
    protected boolean candy;
    protected boolean cookies;
    Shake(boolean lactoseFree, boolean candy, boolean cookie) {
        this.lactoseFree = lactoseFree;
        this.candy = candy;
        this.cookies = cookie;
    }
    abstract void makeBasic();
    void makeCustomization(boolean candy, boolean cookie) {
        if(candy) {
            System.out.println("Adding candy");
        }
        if(cookie) {
            System.out.println("Adding cookie");
        }
    }
    int calculateCustomizationCost() {
        int cost = 0;
        if(lactoseFree) cost += lactoseFreeCost;
        if(candy) cost += candyCost;
        if(cookies) cost += cookieCost;

        return cost;
    }
    abstract int getTotalPrice();
    void printCustomizations() {
        if(lactoseFree) {
            System.out.print("(lactose-free) ");
        }
        if(candy) {
            System.out.print("+ candy topping");
        }
        if(cookies) {
            System.out.print("+ cookie topping");
        }
    }
    void printCustomizationCost() {
        if(lactoseFree) {
            System.out.print("+ " + lactoseFreeCost);
        }
        if(candy) {
            System.out.print("+ " + candyCost);
        }
        if(cookies) {
            System.out.print("+ " + cookieCost);
        }
    }
    abstract void print();
}
class ChocolateShake extends Shake {
    static int basePrice = 230;

    ChocolateShake(boolean lactoseFree, boolean candy, boolean cookie) {
        super(lactoseFree, candy, cookie);
    }
    @Override
    public void makeBasic() {
        System.out.print("Adding ");
        if(lactoseFree) {
            System.out.println("almond milk");
        }
        else {
            System.out.println("milk");
        }
        System.out.println("Adding chocolate syrup");
        System.out.println("Adding chocolate ice cream");
    }

    public int getTotalPrice() {
        return basePrice + calculateCustomizationCost();
    }
    void print() {
        System.out.print("1 chocolate shake ");
        printCustomizations();
        System.out.print(": " + basePrice);
        printCustomizationCost();
        System.out.println(" = " + getTotalPrice() + "Tk");
    }

}
class CoffeeShake extends Shake {
    static int basePrice = 250;

    public CoffeeShake(boolean lactoseFree, boolean candy, boolean cookie) {
        super(lactoseFree, candy, cookie);
    }
    @Override
    public void makeBasic() {
        if(lactoseFree) {
            System.out.println("Adding almond milk");
        }
        else {
            System.out.println("Adding milk");
        }
        System.out.println("Adding sugar");
        System.out.println("Adding coffee");
        System.out.println("Adding jello");
    }

    @Override
    int getTotalPrice() {
        return basePrice + calculateCustomizationCost();
    }
    void print() {
        System.out.print("1 Coffee shake ");
        printCustomizations();
        System.out.print(": " + basePrice);
        printCustomizationCost();
        System.out.println(" = " + getTotalPrice() + "Tk");
    }

}
class StrawberryShake extends Shake {
    static int basePrice = 200;

    public StrawberryShake(boolean lactoseFree, boolean candy, boolean cookie) {
        super(lactoseFree, candy, cookie);

    }
    @Override
    void makeBasic() {
        if(lactoseFree) {
            System.out.println("Adding almond milk");
        }
        else {
            System.out.println("Adding milk");
        }
        System.out.println("Adding sugar");
        System.out.println("Adding strawberry syrup and strawberry ice cream");
    }

    @Override
    int getTotalPrice() {
        return basePrice + calculateCustomizationCost();
    }
    void print() {
        System.out.print("1 strawberry shake ");
        printCustomizations();
        System.out.print(": " + basePrice);
        printCustomizationCost();
        System.out.println(" = " + getTotalPrice() + "Tk");
    }
}
class VanillaShake extends Shake {
    static int basePrice = 190;

    public VanillaShake(boolean lactoseFree, boolean candy, boolean cookie) {
        super(lactoseFree, candy, cookie);
    }

    @Override
    void makeBasic() {
        if(lactoseFree) {
            System.out.println("Adding almond milk");
        }
        else {
            System.out.println("Adding milk");
        }
        System.out.println("Adding sugar");
        System.out.println("Adding vanilla flavoring and jello");
    }

    @Override
    int getTotalPrice() {
        return basePrice + calculateCustomizationCost();
    }

    void print() {
        System.out.print("1 vanilla shake ");
        printCustomizations();
        System.out.print(": " + basePrice);
        printCustomizationCost();
        System.out.println(" = " + getTotalPrice() + "Tk");
    }
}
class ZeroShake extends Shake {
    static int basePrice = 240;

    public ZeroShake(boolean lactoseFree, boolean candy, boolean cookie) {
        super(lactoseFree, candy, cookie);
    }
    @Override
    void makeBasic() {
        if(lactoseFree) {
            System.out.println("Adding almond milk");
        }
        else {
            System.out.println("Adding milk");
        }
        System.out.println("Adding sweetener");
        System.out.println("Adding vanilla flavoring and sugar-free jello");
    }

    @Override
    int getTotalPrice() {
        return basePrice + calculateCustomizationCost();
    }
    void print() {
        System.out.print("1 zero shake ");
        printCustomizations();
        System.out.print(": " + basePrice);
        printCustomizationCost();
        System.out.println(" = " + getTotalPrice() + "Tk");
    }
}