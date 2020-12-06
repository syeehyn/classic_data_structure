/*
 * NAME: Jackie Yuan
 */
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * the class contians the test for the checking account object.
 * @author Shuibenyang Yuan
 * @Since 5.16.18
 */
public class CheckingAccountTester {

    private static CheckingAccount ca;
    private static CheckingAccount ca2;

    /**
     * setting up.
     * @throws InsufficientFundsException
     */
    @Before
    public void setUp() throws InsufficientFundsException{
        ca = new CheckingAccount("UCSD", 2000);
        ca2 = new CheckingAccount("UCLA", 5000);
    }

    /**
     * test for reset check.
     * @throws InsufficientFundsException
     */
    @Test
    public void resetChecksUsedTest() throws InsufficientFundsException {
        ca.withdrawUsingCheck(100);
        ca.withdrawUsingCheck(100);
        assertEquals(2, ca.getChecksUsed());
        ca.resetChecksUsed();
        assertEquals(0, ca.getChecksUsed());
    }

    /**
     * test for getcheckused.
     * @throws InsufficientFundsException
     */
    @Test
    public void getChecksUsedTest() throws InsufficientFundsException{
        ca.withdrawUsingCheck(20);
        ca.withdrawUsingCheck(20);
        assertEquals(2, ca.getChecksUsed());
        ca.withdrawUsingCheck(20);
        ca.withdrawUsingCheck(20);
        assertEquals(4, ca.getChecksUsed());
        ca.resetChecksUsed();
        assertEquals(0, ca.getChecksUsed());
    }

    /**
     * test for deposit.
     */
    @Test
    public void depositTest() {
        assertEquals(3000, ca.deposit(1000), 0.01);
        assertEquals(8000, ca.deposit(5000), 0.01);
        assertEquals(13000, ca.deposit(5000), 0.01);
    }

    /**
     * test for withdraw.
     * @throws InsufficientFundsException
     */
    @Test
    public void withdrawTest() throws InsufficientFundsException {
        assertEquals(1000, ca.withdraw(1000), 0.01);
        assertEquals(0, ca.withdraw(1000), 0.01);
        try {
            ca.withdraw(150); //withdraw more than what is in balance
        } catch (InsufficientFundsException e) {
            assertEquals("THE MAXIMUM AMOUNT THE USER: (UCSD) CAN WITHDRAW FROM "
                    + "THEIR CHECKING ACCOUNT IS ($0.0)", e.getMessage());
        }
        ca.withdrawUsingCheck(150);
        try {
            ca.withdraw(150); //negative balance
        } catch (InsufficientFundsException e) {
            assertEquals("USER: (UCSD) CANNOT MAKE A WITHDRAWAL FROM THEIR "
                    + "CHECKING ACCOUNT UNTIL THEY COVER THEIR OVERDRAFT FEE WITH A DEPOSIT OF AT "
                    + "LEAST ($185.0)", e.getMessage());
        }


    }

    /**
     * test for withdraw using check.
     * @throws InsufficientFundsException
     */
    @Test
    public void withdrawUsingCheckTest() throws InsufficientFundsException {
        assertEquals(1500, ca.withdrawUsingCheck(500), 0.01);
        assertEquals(1000, ca.withdrawUsingCheck(500), 0.01);
        assertEquals(500, ca.withdrawUsingCheck(500), 0.01);
        assertEquals(448, ca.withdrawUsingCheck(50), 0.01);
        assertEquals(-54, ca.withdrawUsingCheck(500), 0.01);

        try {
            ca.withdrawUsingCheck(100); //negative balance
        } catch (InsufficientFundsException e) {
            assertEquals("USER: (UCSD) CANNOT MAKE A WITHDRAWAL FROM THEIR "
                    + "CHECKING ACCOUNT UNTIL THEY COVER THEIR OVERDRAFT FEE WITH A DEPOSIT OF AT "
                    + "LEAST ($89.0)", e.getMessage());
        }
        ca.deposit(100);
        assertEquals(11, ca.getBalance(), 0.01);



    }
}