import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void showShakes() {
        System.out.println("Press 1 for Chocolate Shake(Tk. 230)");
        System.out.println("Press 2 for Coffee Shake(Tk. 250)");
        System.out.println("Press 3 for Strawberry Shake(Tk. 200)");
        System.out.println("Press 4 for Vanilla Shake(Tk. 190)");
        System.out.println("Press 5 for Zero Shake(Tk. 240)");
    }

    static void showCustomizations() {
        System.out.println("How would you like to customize your shake?");
        System.out.println("Press 1 for lactose-free shake (Adds Tk. 60 to the base price)");
        System.out.println("Press 2 for candy on top (Adds Tk. 50 to the base price)");
        System.out.println("Press 3 for cookie on top (Adds Tk. 40 to the base price)");
        System.out.println("Press 4 for none ");
    }

    static boolean setCustomization(String c, ShakeBuilder s) {
        if(c.equalsIgnoreCase("1")) {
            s.setLactoseFree();
        }
        else if(c.equalsIgnoreCase("2")) {
            s.setCandy();
        }
        else if(c.equalsIgnoreCase("3")) {
            s.setCookies();
        }
        else if (c.equalsIgnoreCase("4")) {

        }
        else {
            System.out.println("unknown customization");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Shake>> orders = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Shake> currentOrder = new ArrayList<>();
        int state = 0;

        System.out.println("Press 'o' to open an order, press 'e' to end and order, press 'q' to quit");

        while(true) {
            String[] command = scanner.nextLine().split(" ");
            if((command[0].equalsIgnoreCase("o") && state == 0) || (state == 1 && command[0].equalsIgnoreCase("y"))) {
                //start an order
                System.out.println("What would you like to order?");
                showShakes();
                int shakeSelect = Integer.parseInt(scanner.nextLine());
                if(shakeSelect == 1) {
                    ChocolateShakeBuilder builder = new ChocolateShakeBuilder();

                    showCustomizations();
                    String[] customizations = scanner.nextLine().split(" ");
                    for(int i = 0; i < customizations.length; i++) {
                        setCustomization(customizations[i], builder);
                    }
                    state = 1;
                    currentOrder.add(builder.getShake());
                }
                else if(shakeSelect == 2) {
                    CoffeeShakeBuilder builder = new CoffeeShakeBuilder();

                    showCustomizations();
                    String[] customizations = scanner.nextLine().split(" ");
                    for(int i = 0; i < customizations.length; i++) {
                        setCustomization(customizations[i], builder);
                    }
                    state = 1;
                    currentOrder.add(builder.getShake());
                }
                else if(shakeSelect == 3) {
                    StrawberryShakeBuilder builder = new StrawberryShakeBuilder();

                    showCustomizations();
                    String[] customizations = scanner.nextLine().split(" ");
                    for(int i = 0; i < customizations.length; i++) {
                        setCustomization(customizations[i], builder);
                    }
                    state = 1;
                    currentOrder.add(builder.getShake());
                }
                else if(shakeSelect == 4) {
                    VanillaShakeBuilder builder = new VanillaShakeBuilder();

                    showCustomizations();
                    String[] customizations = scanner.nextLine().split(" ");
                    for(int i = 0; i < customizations.length; i++) {
                        setCustomization(customizations[i], builder);
                    }
                    state = 1;
                    currentOrder.add(builder.getShake());
                }
                else if(shakeSelect == 5) {
                    ZeroShakeBuilder builder = new ZeroShakeBuilder();

                    showCustomizations();
                    String[] customizations = scanner.nextLine().split(" ");
                    for(int i = 0; i < customizations.length; i++) {
                        setCustomization(customizations[i], builder);
                    }
                    state = 1;
                    currentOrder.add(builder.getShake());
                }
                else {
                    System.out.println("invalid input");
                }
                System.out.println("Would you like to order something else?(y/n)");
            }
            else if(state == 1 && command[0].equalsIgnoreCase("n")) {
                System.out.println("Press 'e' to end order");
            }
            else if(state == 1 && command[0].equalsIgnoreCase("e")) {
                orders.add(currentOrder);
                int totalPrice = 0;
                for(int i = 0; i < currentOrder.size(); i++) {
                    currentOrder.get(i).print();
                    totalPrice += currentOrder.get(i).getTotalPrice();
                }
                System.out.println("Total price: " + totalPrice + "Tk");
                state = 0;
            }
            else if(state == 0 && command[0].equalsIgnoreCase("e")) {
                System.out.println("Order at least one shake before closing the order");
                System.out.println("Press 'o'(okay) to continue");
            }
            else if(command[0].equalsIgnoreCase("q")) {
                break;
            }
        }
    }
}
