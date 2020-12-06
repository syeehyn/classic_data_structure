/*
 * NAME: Jackie Yuan
 */
/**
 * The SavingsAccount class extends Account.
 * The minimum amount a customer can keep in a savings account is $100.
 * If the customer deposits at least $5000 initially the bank gives them an additional $250 bonus
 * This account earns interest at a rate specified by the bank.
 * It allows deposits with no fees.
 * Withdrawals are charged $2 per withdrawal.
 * The balance cannot go below $100 through a withdrawal.
 *
 * Refer to the writeup for a more detailed description!
 *  @author Shuibenyang Yuan
 *  @Since 5.16.18
 */
public class SavingsAccount extends Account {
    private static final double MINIMUM_BALANCE = 100.0;
    private static final double BONUSBOUND = 5000;
    private static final double OPENBONUS = 250;
    private static final double WITHDRAWFEE = 2;
    private static final double PERCENT = 0.01;
    /**
     * Constructor for our banks savings accounts
     * If initial deposit is at least $5000 the customer receives a $250 bonus
     * @param userid the id of the account
     * @param initialDeposit the initial deposit.
     */
    public SavingsAccount(String userid, double initialDeposit) throws InsufficientFundsException {
        super(userid, initialDeposit);
        if (initialDeposit < MINIMUM_BALANCE) {
            throw new InsufficientFundsException("should be at leas 100 usd initial fund.");
        }
        // get bonus offer.
        if (initialDeposit >= BONUSBOUND) {
            this.balance = this.getBalance() + OPENBONUS;
        }
    }


    /**
     * Implements abstract deposit method.
     * Adds money to the savings account.
     *@param amount the amount the user wishes to put into their account
     *@return the new balance
     */
    public double deposit(double amount) {
        this.balance = this.getBalance() + amount;
        return this.getBalance();
    }


    /**
     * Implements abstract withdraw method.
     * Savings accounts at our bank charge a WITHDRAWAL_FEE.
     * Rejects withdrawal if the withdrawal would put the account below the minimum balance
     * and throws an exception.
     * @param amount the amount the user wishes to take from their account
     * @return the remaining balance
     *
     */
    public double withdraw(double amount) throws InsufficientFundsException {
        //Get the new balance
        double potentialBalance = this.getBalance() - amount - WITHDRAWFEE;
        // value

        //Reject if withdrawal violates our constraints
        if (potentialBalance < MINIMUM_BALANCE) {
            double maxPossibleWithdrawal = this.getBalance() - MINIMUM_BALANCE - WITHDRAWFEE;
            String exceptionMSG =
                    "THE MAXIMUM AMOUNT THE USER: (" + id + ") CAN "
                            + "WITHDRAW FROM THEIR SAVINGS ACCOUNT "
                            + "IS ($" + maxPossibleWithdrawal + ")";
            throw new InsufficientFundsException(exceptionMSG);
        } else {
            this.balance = potentialBalance;
        }

        return this.getBalance();
    }


    /**
     * Method that the bank will use to add an interest payment to the savings account.
     *
     * NewBalance = OldBalance + OldBalance*InterestRate
     * ie. If OldBalance was $1000.00 and InterestRate = 2%
     *        NewBalance would be $1020.00
     * @param rate the interest rate.
     * @return the new balance of the account.
     */
    public double addInterest(double rate) {
        this.balance = this.getBalance() + this.getBalance() * (rate * PERCENT);
        return this.getBalance();
    }
}
