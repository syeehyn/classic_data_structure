/*
 * NAME: Jackie Yuan
 */
/**
 * The CheckingAccount class extends Account.
 * This account does not give any interest.
 * It allows deposit and withdrawals with no fees.
 * The balance cannot go below $0 through a withdrawal.
 *
 * A checking account also allows the users to use checks may be used to make withdrawals.
 * The first three check uses in a month are free,
 * but subsequent uses add a fee of $2 to each check withdrawal.
 * Checks are allowed to take the balance negative (i.e., an overdraft).
 * If the balance goes negative the user incurs an overdraft fee of $35.
 * If the balance is negative we do not allow any additional
 *     withdrawals until enough money is deposited to cover the fee.
 *
 * Refer to the writeup for a more detailed description!
 *  @author Shuibenyang Yuan
 *  @Since 5.16.18
 */
public class CheckingAccount extends Account {

    // Stores the number of checks used every month.
    // Starts at 0, and can be reset using a method below.
    private static final double INITIALBALANCE = 0.01;
    private static final double OVERDRAFTFEE = 35;
    private static final  int FREECHECK = 3;
    private static final  int CHECKFEE = 2;
    private int numberOfChecksUsed;

    // Indicates wether the account currently has an uncovered overdraft fee
    private boolean inOverdraft;

    /**
     * Constructor for our banks checking accounts
     * @param id the id of the bank account.
     * @param initialDeposit the initial deposit of the checking account
     *
     */
    public CheckingAccount(String id, double initialDeposit) throws InsufficientFundsException {
        super(id, initialDeposit);
        if (initialDeposit < INITIALBALANCE) { // not met the initial balance.
            throw new InsufficientFundsException("should be at leas 100 usd initial fund.");
        }
        this.inOverdraft = false;
        this.numberOfChecksUsed = 0;
    }

    /**This method makes the numberOfChecksUsed zero.*/
    public void resetChecksUsed() {
        this.numberOfChecksUsed = 0;
    }

    /**
     * This method returns the numberOfChecksUsed.
     * @return the number of check used.
     */
    public int getChecksUsed() {
        return this.numberOfChecksUsed;
    }

    /**
     * Implements abstract deposit method.
     * Adds money to the checking account and returns the new balance.
     *
     *@param amount the amount the user wishes to put into their account
     *@return the new balance
     */
    public double deposit(double amount) {
        if (this.inOverdraft && amount >= -this.getBalance() + OVERDRAFTFEE) {
            amount -= OVERDRAFTFEE;
            this.inOverdraft = false; //no longer overdraft
        }
        this.balance += amount;


        return this.getBalance();
    }

    /**
     * Implements abstract withdraw method.
     * Rejects withdrawal if the withdrawal would put the account bellow $0 and throws an exception.
     * Rejects withdrawal if the account is in overdraft and throws an exception.
     *
     * @param amount the amount the user wishes to take from their account
     * @return the remaining balance
     *
     */
    public double withdraw(double amount) throws InsufficientFundsException {

        double requiredDeposit = OVERDRAFTFEE - this.getBalance();
        String overdraftRejection = "USER: (" + id + ") CANNOT MAKE A WITHDRAWAL"
                + " FROM THEIR CHECKING ACCOUNT "
                + "UNTIL THEY COVER THEIR OVERDRAFT FEE "
                + "WITH A DEPOSIT OF AT LEAST ($" + requiredDeposit + ")";

        double maxPossibleWithdrawal = this.balance;
        String exceptionMSG =
                "THE MAXIMUM AMOUNT THE USER: (" + id + ") CAN WITHDRAW FROM THEIR CHECKING "
                        + "ACCOUNT IS "
                        +
                "($" + maxPossibleWithdrawal + ")";
        if (this.inOverdraft) { // overdraft condition.
            throw new InsufficientFundsException(overdraftRejection);
        }
        if (amount > maxPossibleWithdrawal) { // no balance condition.
            throw new InsufficientFundsException(exceptionMSG);
        }

        this.balance = this.getBalance() - amount;
        if (this.balance < 0) {
            this.inOverdraft = true;
        }

        return this.getBalance();
    }


    /**
     * Method pulls money from the users account when someone cashes their check and
     * returns the balance after the withdrawal
     *
     * If the account is in overdraft reject the check and inform the check writer(id) of
     * how much they need to deposit to make withdrawals.
     *
     * Otherwise this check can withdraw any amount of money. If it puts the account balance
     * below $0 place the account in overdraft.
     *
     * If the check writer has already used up his free checks,
     * there is an additional $2 fee for this withdrawal.
     * @param amount the amount of money need to be withdraw
     * @return true if successfully withdrawed, false otherwise.
     */
    public double withdrawUsingCheck(double amount) throws InsufficientFundsException {

        double requiredDeposit = OVERDRAFTFEE - this.getBalance();
        String overdraftRejection = "USER: (" + id + ") CANNOT MAKE A WITHDRAWAL "
                + "FROM THEIR CHECKING ACCOUNT "
                + "UNTIL THEY COVER THEIR OVERDRAFT FEE "
                + "WITH A DEPOSIT OF AT LEAST ($" + requiredDeposit + ")";
        if (this.inOverdraft) { // overdraft condition
            throw new InsufficientFundsException(overdraftRejection);
        } else {
            if (this.numberOfChecksUsed >= FREECHECK) {
                this.balance = this.getBalance() - CHECKFEE;
            }
            this.numberOfChecksUsed++;
            this.balance = this.getBalance() - amount;
        }
        if (this.balance < 0) {
            this.inOverdraft = true;
        }
        return this.getBalance();
    }
}
