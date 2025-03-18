//I combined the 2 files into 1:
//Since Coding Test only lets me submit 1 link

//Test 1
import java.util.HashMap;
import java.util.Scanner;

public class ATMManagementSystem {

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.addAccount(new Account(12345, "John Doe", 1000.0));
        atm.addAccount(new Account(67890, "Jane Doe", 2000.0));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter account number (0 to exit): ");
            int accountNumber = scanner.nextInt();
            if (accountNumber == 0) {
                System.out.println("Exiting program. Thank you!");
                break;
            }

            Account account = atm.findAccount(accountNumber);
            if (account == null) {
                System.out.println("Account not found. Please try again.");
                continue;
            }

            System.out.println("Welcome, " + account.getAccountHolderName());

            boolean sessionActive = true;
            while (sessionActive) {
                System.out.println("\n1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.printf("Current balance: $%.2f%n", account.checkBalance());
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        if (depositAmount > 0) {
                            account.deposit(depositAmount);
                            System.out.printf("Deposit successful. New balance: $%.2f%n", account.checkBalance());
                        } else {
                            System.out.println("Invalid amount.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawAmount = scanner.nextDouble();
                        if (withdrawAmount > 0) {
                            boolean success = account.withdraw(withdrawAmount);
                            if (success) {
                                System.out.printf("Withdrawal successful. New balance: $%.2f%n", account.checkBalance());
                            } else {
                                System.out.println("Insufficient funds.");
                            }
                        } else {
                            System.out.println("Invalid amount.");
                        }
                        break;
                    case 4:
                        sessionActive = false;
                        System.out.println("Logging out...\n");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
        scanner.close();
    }
}

class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(int accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double checkBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }
}

class ATM {
    private HashMap<Integer, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account findAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }
}


//Test 2
import java.util.Scanner;

public class Palindrome {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] odd = expandAroundCenter(s, i, i);
            int[] even = expandAroundCenter(s, i, i + 1);
            int lenOdd = odd[1] - odd[0] + 1;
            int lenEven = even[1] - even[0] + 1;
            int currentMax = Math.max(lenOdd, lenEven);
            if (currentMax > end - start + 1) {
                if (lenOdd > lenEven) {
                    start = odd[0];
                    end = odd[1];
                } else {
                    start = even[0];
                    end = even[1];
                }
            }
        }
        return s.substring(start, end + 1);
    }

    private static int[] expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return new int[]{left + 1, right - 1};
    }


    public static void main(String[] args) {
        String result = longestPalindrome("babad");
        System.out.println("Longest palindromic substring: " + result);
    }
}

