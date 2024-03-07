package Problem1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Passengers crew = new Crewmate();
        Imposters imposter = new Imposters(crew);

        Passengers current = null;

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String[] command = scanner.nextLine().split(" ");

            if(command[0].equals("login")) {
                if(command[1].contains("crew")) {
                    current = crew;
                    current.login();
                }
                else if(command[1].contains("imp")) {
                    current = imposter;
                    current.login();
                }
                else {
                    System.out.println("unknown command");
                }
            }
            else if(command[0].equals("repair")) {
                if(current != null) {
                    current.repair();
                }
                else {
                    System.out.println("You have to login first");
                }
            }
            else if(command[0].equals("work")) {
                if(current != null) {
                    current.work();
                }
                else {
                    System.out.println("You have to login first");
                }
            }
            else if(command[0].equals("logout")) {
                if(current != null) {
                    current.logout();
                    current = null;
                }
                else {
                    System.out.println("You have to login first");
                }
            }
            else {
                System.out.println("unknown command");
            }
        }
    }
}
