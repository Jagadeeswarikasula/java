import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATMInterface {
    
    private static class BankAccount {
        private int accountNumber;
        private int pin;
        private double balance;

        public BankAccount(int accountNumber, int pin, double balance) {
            this.accountNumber = accountNumber;
            this.pin = pin;
            this.balance = balance;
        }

        public int getAccountNumber() {
            return accountNumber;
        }

        public int getPin() {
            return pin;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                return true;
            } else {
                System.out.println("Insufficient funds or invalid amount!");
                return false;
            }
        }
    }

    
    private static class Bank {
        private List<BankAccount> accounts;

        public Bank() {
            accounts = new ArrayList<>();
           
            accounts.add(new BankAccount(12345, 1234, 1000.0));
            accounts.add(new BankAccount(67890, 5678, 500.0));
        }

        public BankAccount authenticateUser(int accountNumber, int pin) {
            for (BankAccount account : accounts) {
                if (account.getAccountNumber() == accountNumber && account.getPin() == pin) {
                    return account;
                }
            }
            return null; 
        }

        public void displayMenu() {
            System.out.println("Welcome to the ATM!");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
        }

        public void processTransaction(BankAccount account, int choice, Scanner scanner) {
            switch (choice) {
                case 1:
                    System.out.println("Your balance is: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: $");
                    double amount = scanner.nextDouble();
                    if (account.withdraw(amount)) {
                        System.out.println("Withdrawn $" + amount + ". Remaining balance: $" + account.getBalance());
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        ATMInterface.Bank bank = new ATMInterface.Bank();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM!");

        boolean authenticated = false;
        BankAccount currentAccount = null;

        
        while (!authenticated) {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            System.out.print("Enter PIN: ");
            int pin = scanner.nextInt();

            currentAccount = bank.authenticateUser(accountNumber, pin);
            if (currentAccount != null) {
                authenticated = true;
            } else {
                System.out.println("Invalid account number or PIN. Please try again.");
            }
        }

        
        boolean exit = false;
        while (!exit) {
            bank.displayMenu();
            int choice = scanner.nextInt();
            bank.processTransaction(currentAccount, choice, scanner);

            if (choice == 3) {
                exit = true;
            }
        }

        scanner.close();
    }
}
