/*
 * NAME: Jackie Yuan
 */
import java.util.HashMap;

/**
 * The Bank class acts as the interface between a user and their money.
 * The Bank consists of a HashMap of accounts and a interest rate for savings accounts.
 * Through this class users can create accounts, deposit to accounts,
 * withdraw from accounts, and transfer between accounts.
 *  @author Shuibenyang Yuan
 *  @Since 5.16.18
 */
public class Bank {
    //Structure holding all accounts. Key = accountID, Value = account
    private HashMap<String, Account> accounts;
    //The interest rate given to savings account holders as a percent
    private double savingsInterestRate;

    /**
     * Default constructor creates an empty accounts map and set initial interest rate to 0%
     */
    public Bank() {
        accounts = new HashMap<>();
        savingsInterestRate = 0;
    }

    /**
     * Sets the savings interest rate
     * @param rate the interest rate to be set up.
     */
    public void setSavingsInterest(double rate) {
        this.savingsInterestRate = rate;
    }

    /**
     * Returns the number of accounts this bank has
     * @return  the number of accounts this bank has.
     */
    public int getNumberOfAccounts() {
        return this.accounts.size();
    }

    /**
     * Gets the account associated with accountID
     * @param accountID the account id of the account to get.
     * @return the account to be get.
     */
    private Account getAccount(String accountID) {
        String noAccountMessage = " DOES NOT HAVE AN ACCOUNT!";
        // Print no account found.
        if (!this.accounts.containsKey(accountID)) {
            System.out.println(accountID + noAccountMessage);
            return null;
        } else { // get access the the account.
            return this.accounts.get(accountID);
        }
    }

    /**
     * Creates a checking account with the given accountID and
     * returns true or false depending on success.
     *
     * The accountID must not already be taken and the initialDeposit must be greater than $0
     * @param accountID the account id be created.
     * @param initialDeposit the initial fund.
     * @return true if successfully created, false otherwise.
     */
    public boolean createCheckingAccount(String accountID, double initialDeposit) {
        String minimumMessage = "The minimum initial deposit for a checking account was not met!";
        try {
            if (this.getAccount(accountID) == null) { // try to create a new account.
                Account toCreate = new CheckingAccount(accountID, initialDeposit);
                this.accounts.put(accountID, toCreate);
            } else {
                return false;
            }
        } catch (InsufficientFundsException e) { // When the minimum fund not reach.
            System.out.println(minimumMessage);
            return false;
        }
        return true;
    }

    /**
     * Creates a savings account with the given accountID and
     * returns true or false depending on success.
     *
     * The accountID must not already be taken and the initialDeposit must be greater than $100
     * @param accountID the account id be created.
     * @param initialDeposit the initial fund.
     * @return true if successfully created, false otherwise.
     */
    public boolean createSavingsAccount(String accountID, double initialDeposit) {
        String minimumMessage = "The minimum initial deposit for a checking account was not met!";
        try {
            if (this.getAccount(accountID) == null) { // try to create a new account.
                Account toCreate = new SavingsAccount(accountID, initialDeposit);
                this.accounts.put(accountID, toCreate);
            } else {
                return false;
            }
        } catch (InsufficientFundsException e) { // When the minimum fund not reach.
            System.out.println(minimumMessage);
            return false;
        }
        return true;
    }

    /**
     * Adds money to the account associated with accountID
     * @param accountID the account id of the account should be withdrawed.
     * @param amount the amount the user wishes to put to their account
     * @return the new balance
     */
    public Double deposit(String accountID, double amount)  {
        if (this.getAccount(accountID) == null) { // not id found.
            return null;
        } else {
            return this.getAccount(accountID).deposit(amount);
        }
    }

    /**
     * Removes money from the account associated with accountID
     * @param accountID the account id of the account should be withdrawed.
     * @param amount the amount the user wishes to take from their account
     * @return the new balance
     */
    public Double withdraw(String accountID, double amount) {
        // Example of how to print the message that have been passed to Exception
        if (this.getAccount(accountID) == null) {
            return null;
        }
        try {
            this.getAccount(accountID).withdraw(amount);
        } catch (InsufficientFundsException e) {

            //When there isn't enough money inform the user whats wrong and return null
            System.out.println(e.getMessage());
            return null;
        }

        //Withdrawal succeeded. Inform user of their updated balance
        return this.getAccount(accountID).getBalance();
    }

    /**
     * Moves money from an account to another account via "online" transfer.
     * No check fees are charged.
     *
     * If either account does not exist the transfer will fail.
     * If the fromAccount does not have enough funds or rejects the withdrawal for any other reason,
     * the transfer fails.
     * @param fromAccountID the account id of the account to transfer from.
     * @param toAccountID the account id of the account to get to.
     * @return true if successfully transferred, false otherwise.
     *
     */
    public boolean onlineTransfer(String fromAccountID, String toAccountID, double amount) {
        if (this.getAccount(fromAccountID) == null || this.getAccount(toAccountID) == null) {
            // either account not found.
            return false;
        }
        try { // try to withdraw from from account.
            this.getAccount(fromAccountID).withdraw(amount);
        } catch (InsufficientFundsException e) {

            //When there isn't enough money inform the user whats wrong and return null
            System.out.println(e.getMessage());
            return false;
        }
        this.getAccount(toAccountID).deposit(amount); // deposit to to account.
        return true;
    }

    /**
     * Moves money from an account to another account using a "check."
     *
     * If either account does not exist the transfer will fail.
     * If the from account is not a checking account the transfer will fail.
     * If the fromAccount does not have enough funds or rejects the withdrawal for any other reason,
     * the transfer fails.
     * @param fromAccountID the account id of the account to transfer from.
     * @param toAccountID the account id of the account to get to.
     * @return true if successfully transferred, false otherwise.
     */
    public boolean checkTransfer(String fromAccountID, String toAccountID, double amount) {
        String notCheckingMessage = " IS NOT A CHECKING ACCOUNT!";
        if (this.getAccount(fromAccountID) == null || this.getAccount(toAccountID) == null) {
            // either account not found.
            return false;
        }
        if (!(this.getAccount(fromAccountID) instanceof CheckingAccount)) {
            // check if the from account checking account.
            System.out.println(fromAccountID + notCheckingMessage);
            return false;
        }
        try { // withdraw from the account
            ((CheckingAccount) this.getAccount(fromAccountID)).withdrawUsingCheck(amount);
        } catch (InsufficientFundsException e) {

            //When there isn't enough money inform the user whats wrong and return null
            System.out.println(e.getMessage());
            return false;
        }
        // deposit to the account.
        this.getAccount(toAccountID).deposit(amount);
        return true;
    }

    /**
     * Adds interest to every savings account.
     */
    public void addInterest() {
        for (Account acount : this.accounts.values()) { // add interest to savings.
            if (acount instanceof SavingsAccount) {
                ((SavingsAccount) acount).addInterest(this.savingsInterestRate);
            }
        }
    }

    /**
     * Prints the account information associated with accountID if an account exists.
     * @param accountID the account id to be printed.
     */
    public void printAccount(String accountID) {
        if (this.getAccount(accountID) == null) {
            return;
        }
        System.out.println(this.getAccount(accountID).toString());
    }

}
