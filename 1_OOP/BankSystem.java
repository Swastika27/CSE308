import java.io.File;
import java.util.Scanner;

public class BankSystem {
    public static void main(String[] args) throws Exception {
        Bank bank = new Bank();
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("input.txt"));

        final int ACCOUNT = 1;
        final int EMPLOYEE = 2;
        final int NONE = -1;

        int currentlyLoggedIn = -1;
        String currentlyLoggedInName = null;

        // do bank operations
        while(true) {
            String[] command = scanner.nextLine().split(" ");

            // create account
            if (command[0].equalsIgnoreCase("create")) {
                try {
                    bank.createAccount(command[1], command[2], Double.parseDouble(command[3]));
                    System.out.println(command[2] + " account for " + command[1] + " created; initial balance " + command[3] + "$");

                    currentlyLoggedIn = ACCOUNT;
                    currentlyLoggedInName = command[1];
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            // open a customer account or open an employee
            else if (command[0].equalsIgnoreCase("open")) {
                if(bank.isAccount(command[1])) {
                    currentlyLoggedIn = ACCOUNT;
                    currentlyLoggedInName = command[1];
                    System.out.println("Welcome back, " + command[1]);
                }
                else if (bank.isEmployee(command[1])) {
                    currentlyLoggedIn = EMPLOYEE;
                    currentlyLoggedInName = command[1];
                    System.out.println(command[1] + " active");
                }
                else {
                    System.out.println("Invalid command");
                }
            }
            // deposit to an account
            else if (command[0].equalsIgnoreCase("Deposit")) {
                if(currentlyLoggedIn == ACCOUNT) {
                    try {
                        bank.depositToAccount(currentlyLoggedInName, Double.parseDouble(command[1]));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                else {
                    System.out.println("Invalid command");
                }
            }
            // withdraw from account
            else if (command[0].equalsIgnoreCase("Withdraw")) {
                if(currentlyLoggedIn == ACCOUNT) {
                    try {
                        bank.withdrawFromAccount(currentlyLoggedInName, Double.parseDouble(command[1]));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                else {
                    System.out.println("Invalid command");
                }
            }
            // Query
            else if (command[0].equalsIgnoreCase("Query")) {
                if(currentlyLoggedIn == ACCOUNT) {
                    double currentBalance = bank.getAccountDeposit(currentlyLoggedInName);
                    System.out.println("Current balance " + currentBalance + "$");
                }
                else {
                    System.out.println("Invalid command");
                }
            }
            // request for loan
            else if (command[0].equalsIgnoreCase("Request")) {
                if(currentlyLoggedIn == ACCOUNT) {
                    try {
                        bank.requestLoan(currentlyLoggedInName, Double.parseDouble(command[1]));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            // Employee commands
            // lookup
            else if(command[0].equalsIgnoreCase("Lookup")) {
                if(currentlyLoggedIn == EMPLOYEE) {
                    System.out.println(command[1] + "'s current balance is " + bank.getAccountDeposit(command[1]) + "$");
                }
                else {
                    System.out.println("Invalid command");
                }
            }
            // approve loan
            else if(command[0].equalsIgnoreCase("Approve")) {
                if(currentlyLoggedIn == EMPLOYEE) {
                    bank.approveAllLoanRequests(currentlyLoggedInName);
                }
            }
            // change interest rate
            else if(command[0].equalsIgnoreCase("Change")) {
                if(currentlyLoggedIn == EMPLOYEE) {
                    try {
                        if(bank.changeInterestRate(currentlyLoggedInName, command[1], Double.parseDouble(command[2]))) {
                            System.out.println("Interest rate changed");
                        }
                        else {
                            System.out.println("You do not have permission for this operation");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            // see internal fund
            else if (command[0].equalsIgnoreCase("See")) {
                if(currentlyLoggedIn == EMPLOYEE) {
                    double fund = bank.seeInternalFund(currentlyLoggedInName);
                    if (fund == -1) {
                        System.out.println("You do not have permission for this operation");
                    }
                    else {
                        System.out.println("Current fund is " + fund + "$");
                    }
                }
            }
            // log out
            else if(command[0].equalsIgnoreCase("Close")) {
                if(currentlyLoggedIn == EMPLOYEE) {
                    System.out.print("Operations ");
                }
                else if(currentlyLoggedIn == ACCOUNT) {
                    System.out.print("Transactions ");
                }
                System.out.println("closed for " + currentlyLoggedInName);

                currentlyLoggedIn = NONE;
                currentlyLoggedInName = null;
            }
            // increment year
            else if(command[0].equalsIgnoreCase("Inc")) {
                if(currentlyLoggedIn == NONE) {
                    bank.incrementYear();
                    System.out.println("1 year passed");
                }
            }
            // close bank
            else if(command[0].equalsIgnoreCase("END")) {
                break;
            }
        }
    }
}
