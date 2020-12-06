/*
 * NAME: Jackie Yuan
 */
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * The class contains the test for bank object.
 *  @author Shuibenyang Yuan
 *  @Since 5.16.18
 */
public class BankTester {
    // initial environment.
    private static Bank bank;

    /**
     * Setting up.
     */
    @Before
    public void setUp() {
        bank = new Bank();
        bank.createCheckingAccount("UCSD", 3000);
        bank.createSavingsAccount("UCLA", 4000);
    }

    /**
     * Test for set interest.
     */
    @Test
    public void setSavingsInterestTest() {
        bank.setSavingsInterest(2);
        bank.addInterest(); //add 2% interest to the saving account
        //test the result by using depositing 100
        assertEquals(4080 + 100, bank.deposit("UCLA", 100),
                0.01);
    }

    /**
     * Test  for get number of accounts
     */
    @Test
    public void getNumberOfAccountsTest() {
        assertEquals(2, bank.getNumberOfAccounts());
        bank.createSavingsAccount("UCSC", 2000);
        assertEquals(3, bank.getNumberOfAccounts());
        bank.createCheckingAccount("UCI", 1000);
        assertEquals(4, bank.getNumberOfAccounts());

    }

    /**
     * Test for create checking account
     */
    @Test
    public void createCheckingAccountTest() {
        assertTrue(bank.createCheckingAccount("UCI", 100));
        assertFalse(bank.createCheckingAccount("UCSD", 100));
        assertFalse(bank.createCheckingAccount("UCB", 0));
    }

    /**
     * Test for create saving account.
     */
    @Test
    public void createSavingsAccountTest() {
        assertTrue(bank.createSavingsAccount("UCI", 150));
        assertFalse(bank.createSavingsAccount("UCSD", 150));
        assertFalse(bank.createSavingsAccount("UCB", 99));
    }

    /**
     * Test for deposit.
     */
    @Test
    public void depositTest() {
        assertEquals(3200, bank.deposit("UCSD", 200), 0.01);
        assertEquals(4200, bank.deposit("UCLA", 200), 0.01);
        assertEquals(null, bank.deposit("UCB", 10000));
    }

    /**
     * Test for withdraw.
     * @throws InsufficientFundsException
     */
    @Test
    public void withdrawTest() throws InsufficientFundsException {
        assertEquals(2000, bank.withdraw("UCSD", 1000), 0.01);
        assertEquals(2998, bank.withdraw("UCLA", 1000), 0.01);
        assertEquals(null, bank.withdraw("UCB", 4000));

    }

    /**
     * Test for online transfer.
     */
    @Test
    public void onlineTransferTest() {
        assertTrue(bank.onlineTransfer("UCSD","UCLA", 1000));
        assertTrue(bank.onlineTransfer("UCLA","UCSD", 1000));
        assertFalse(bank.onlineTransfer("UCSD","UCLA", 4000));
        assertFalse(bank.onlineTransfer("UCLA","UCSD", 5000));
        assertFalse(bank.onlineTransfer("UCI","UCB", 5000));
    }

    /**
     * Test for check transfer.
     */
    @Test
    public void checkTransferTest() {
        assertTrue(bank.checkTransfer("UCSD","UCLA", 1000));
        bank.printAccount("UCSD");
        bank.printAccount("UCLA");
        assertFalse(bank.checkTransfer("UCLA","UCSD", 1000));
        assertTrue(bank.checkTransfer("UCSD","UCLA", 5000));
        assertFalse(bank.checkTransfer("UCLA","UCSD", 5000));
        assertFalse(bank.checkTransfer("UCB","UCLA", 1000));
        assertFalse(bank.checkTransfer("UCLA","UCB", 1000));

    }

    /**
     * Test for add interest.
     */
    @Test
    public void addInterestTest() {
        bank.setSavingsInterest(2);
        bank.addInterest(); //add 2% interest to the saving account
        //test the result by using depositing 100
        assertEquals(4080 + 100, bank.deposit("UCLA", 100),
                0.01);
        bank.addInterest();
        assertEquals(4263.6 + 100, bank.deposit("UCLA", 100),
                0.01);
    }
}
