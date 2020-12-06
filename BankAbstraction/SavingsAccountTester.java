/*
 * NAME: Jackie Yuan
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * The class contains the test for savingsaccount object.
 */
public class SavingsAccountTester {

    private static SavingsAccount sa;
    private static SavingsAccount sa2;

    /**
     * Setting up.
     * @throws InsufficientFundsException
     */
    @Before
    public void setUp() throws InsufficientFundsException {
        sa = new SavingsAccount("UCSD", 3000);
        sa2 = new SavingsAccount("UCLA", 5000);
    }

    /**
     * test for deposit method.
     */
    @Test
    public void depositTest() {
        assertEquals(3100, sa.deposit(100), 0.01);
        assertEquals(5100, sa.deposit(2000), 0.01);
        assertEquals(6100, sa.deposit(1000), 0.01);
        assertEquals(5500, sa2.deposit(250), 0.01);

    }

    /**
     * test for withdraw method.
     * @throws InsufficientFundsException
     */
    @Test
    public void withdrawTest() throws InsufficientFundsException {
        assertEquals(2898, sa.withdraw(100), 0.01);
        assertEquals(2890, sa.withdraw(6), 0.01);
        assertEquals(4998, sa2.withdraw(250), 0.01);
        try {
            sa.withdraw(5000); //causing balance smaller than 100
        } catch (InsufficientFundsException e) {
            assertEquals("THE MAXIMUM AMOUNT THE USER: (UCSD) CAN WITHDRAW FROM THEIR "
                    + "SAVINGS ACCOUNT IS ($2788.0)", e.getMessage());
        }
    }

    /**
     * Test for addinterest method.
     */
    @Test
    public void addInterestTest() {
        assertEquals(3090, sa.addInterest(3), 0.01);
        assertEquals(5355, sa2.addInterest(2), 0.01);

    }
}