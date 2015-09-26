package application.core;

import application.Main;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for SmallProjectile.java.
 *
 * @author Arthur Breurkes
 */
public class SmallProjectileTest {

    /**
     * Test whether SmallProjectiles are initialized correctly.
     *
     * @throws Exception
     */
    @Test
    public void testSmallProjectileDifficultyOne() throws Exception {
        Main.DIFFICULTY = 1;
        SmallProjectile testProjectile = new SmallProjectile(0, 0);

        assertEquals(2, testProjectile.tSpeed);
    }

    /**
     * Test whether SmallProjectiles are initialized correctly.
     *
     * @throws Exception
     */
    @Test
    public void testSmallProjectileDifficultyTwo() throws Exception {
        Main.DIFFICULTY = 2;
        SmallProjectile testProjectile = new SmallProjectile(0, 0);

        assertEquals(3, testProjectile.tSpeed);
    }

    /**
     * Test whether SmallProjectiles are initialized correctly.
     *
     * @throws Exception
     */
    @Test
    public void testSmallProjectileDifficultyThree() throws Exception {
        Main.DIFFICULTY = 3;
        SmallProjectile testProjectile = new SmallProjectile(0, 0);

        assertEquals(5, testProjectile.tSpeed);
    }

    /**
     * Test whether getImage() returns the correct image.
     *
     * @throws Exception
     */
    @Test
    public void testGetImage() throws Exception {
        SmallProjectile testProjectile = new SmallProjectile(0, 0);

        assertNull(testProjectile.getImage());
    }
}