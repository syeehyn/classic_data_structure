/*
 * NAME: Jackie Yuan
 */
/**
 * Abstract Account class defines a bank accounts id, and the amount in the account(balance)
 * A default constructor, get methods, and a toString method must be implemented here.
 *
 * Abstract methods defining withdraw and deposit operations are left for subclasses to implement
 *  @author Shuibenyang Yuan
 *  @Since 5.16.18
 */
public abstract class Account {
    protected String id; //Unique account identifier
    protected double balance; // stores the current balance


    /**
     * Even though an abstract class cannot be directly instantiated, this constructor
     *      will be used from a sub-class's constructor.
     * @param id the id account should be.
     * @param balance the balance of the account.
     */
    public Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    /**
     * Returns the account id
     * @return the id of the account.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the current balance.
     * @return the balance of the account.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Returns a string that contains the id and balance. For example, if the user id is
     * "marina"  and balance is "123.45", the string returned
     * is as follows (identical punctuation and spaces):
     * ID: marina, Balance: 123.45
     * @return the String of the information of the account.
     *
     */
    public String toString() {
        String toReturn = "ID: " + this.id + ", Balance: " + this.balance;
        return toReturn;
    }


    /**
     * This method will be implemented in the subclasses to allow deposit into this account
     *
     * @param amount the amount the user wishes to put into their account
     * @return the new balance
     */
    public abstract double deposit(double amount);

    /**
     * This method will be implemented in the subclasses to allow withdrawal from this account
     *
     * @param amount the amount the user wishes to take from their account
     * @return the remaining balance
     */
    public abstract double withdraw(double amount) throws InsufficientFundsException;
}
