package application.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

//ToDo: Write testMove(), testCheckCollision() (dependencies unreachable)
/**
 * Test class for Projectile.java.
 *
 * @author Arthur Breurkes
 */
public class ProjectileTest {
    private Projectile testProjectile;

    /**
     * Initialize variables for testing process.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        testProjectile = new Projectile();
    }

    /**
     * Test whether setDirection() works correctly.
     *
     * @throws Exception
     */
    @Test
    public void testSetDirection() throws Exception {
        testProjectile.setDirection(2);

        assertEquals(2, testProjectile.tDirection);
    }

//    @Test
//    public void testMove() throws Exception {
//
//    }

    /**
     * Test whether toString() returns the correct String;
     *
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception {
        testProjectile.settX(0);
        testProjectile.settY(0);

        assertEquals("Projectile on coords: 0, 0", testProjectile.toString());
    }

//    @Test
//    public void testCheckCollision() throws Exception {
//
//    }

    /**
     * Test whether isRemoved() returns the correct boolean value.
     *
     * @throws Exception
     */
    @Test
    public void testIsRemoved() throws Exception {
        testProjectile.tRemoved = false;
        assertFalse(testProjectile.isRemoved());

        testProjectile.tRemoved = true;
        assertTrue(testProjectile.isRemoved());
    }
}