public class FirstBank {

    public static void main(String[] args) {
        // Creating accounts with all required parameters
        Account tim = new Account("Vikasita", "A00001", "1234", "Savings");
        Account beyonce = new Account("Arjun", "B00002", "5678", "Checking");
        tim.showMenu();
        // Show menu for one of the accounts
        beyonce.showMenu();
    }
}
