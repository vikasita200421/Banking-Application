import java.util.*;

public class Account {
    // Class Variables
    private int balance;
    private List<String> transactionHistory;
    private String customerName;
    private String customerID;
    private String pin;
    private final double SAVINGS_INTEREST_RATE = 0.025; // 2.5%
    private final double CHECKING_INTEREST_RATE = 0.015; // 1.5%
    private String accountType;

    // Constructor
    public Account(String cname, String cid, String pin, String accType) {
        this.customerName = cname;
        this.customerID = cid;
        this.pin = pin;
        this.accountType = accType;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    // Deposit money
    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Withdraw money with overdraft protection
    public void withdraw(int amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                transactionHistory.add("Withdrawn: $" + amount);
                System.out.println("Successfully withdrawn $" + amount);
            } else {
                System.out.println("Insufficient balance! Withdrawal failed.");
            }
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }

    // Display transaction history
    public void getTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    // Calculate interest based on account type
    public void calculateInterest(int years) {
        double interestRate = (accountType.equalsIgnoreCase("savings")) ? SAVINGS_INTEREST_RATE : CHECKING_INTEREST_RATE;
        double newBalance = balance * Math.pow(1 + interestRate, years);
        System.out.println("The interest rate for your " + accountType + " account is " + (interestRate * 100) + "%");
        System.out.println("After " + years + " years, your balance will be: $" + newBalance);
    }

    // Fund transfer
    public void transferFunds(Account recipient, int amount) {
        if (amount > 0 && balance >= amount) {
            this.withdraw(amount);
            recipient.deposit(amount);
            System.out.println("Successfully transferred $" + amount + " to " + recipient.customerName);
        } else {
            System.out.println("Transfer failed! Check balance and amount.");
        }
    }

    // User authentication
    public boolean authenticate(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    // Show Menu
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome, " + customerName + "!");
        System.out.println("Your Account Type: " + accountType);
        System.out.println();

        System.out.print("Enter PIN to access your account: ");
        String enteredPin = scanner.next();
        if (!authenticate(enteredPin)) {
            System.out.println("Invalid PIN. Access Denied!");
            return;
        }

        char option;
        do {
            System.out.println("\n==== Bank Menu ====");
            System.out.println("A. Check Balance");
            System.out.println("B. Deposit");
            System.out.println("C. Withdraw");
            System.out.println("D. View Transactions");
            System.out.println("E. Calculate Interest");
            System.out.println("F. Exit");
            System.out.println("Enter an option: ");
            option = Character.toUpperCase(scanner.next().charAt(0));

            switch (option) {
                case 'A':
                    System.out.println("Current Balance: $" + balance);
                    break;
                case 'B':
                    System.out.print("Enter amount to deposit: ");
                    int depositAmount = scanner.nextInt();
                    deposit(depositAmount);
                    break;
                case 'C':
                    System.out.print("Enter amount to withdraw: ");
                    int withdrawAmount = scanner.nextInt();
                    withdraw(withdrawAmount);
                    break;
                case 'D':
                    getTransactionHistory();
                    break;
                case 'E':
                    System.out.print("Enter years to calculate interest: ");
                    int years = scanner.nextInt();
                    calculateInterest(years);
                    break;
                case 'F':
                    System.out.println("Thank you for banking with us!");
                    break;
                default:
                    System.out.println("Invalid option! Please enter A-F.");
                    break;
            }
        } while (option != 'F');

        scanner.close();
    }

    // Main method for testing
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your Customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Set a 4-digit PIN: ");
        String pin = scanner.next();
        System.out.print("Enter Account Type (Savings/Checking): ");
        String accountType = scanner.next();

        Account userAccount = new Account(name, id, pin, accountType);
        userAccount.showMenu();
    }
}
