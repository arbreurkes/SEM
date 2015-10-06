package application.core.aliens;

import application.Main;
import application.core.*;
import application.core.projectiles.SmallProjectile;
import application.core.upgrades.*;
import org.w3c.dom.Element;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Class for Alien.
 * @author Thomas Oomens
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier"
})

public class Alien extends Sprite {
    protected boolean tDead = false;
    protected int tShootChance;
    protected int tDirection;
    protected double tSpeed;
    protected boolean tCanShoot = false;
    protected boolean tBonusAlien = false;
    protected ArrayList<Upgrade> tUpgrades = new ArrayList<>();

    /**
     * Constructor for Alien.
     */
    public Alien() {
        applyDifficulty();
    }

    /**
     * Read XML to create Aliens.
     * @param eElement read XML.
     */
    public void readXml(Element eElement) {
        setX(Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent()));
        setY(Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent()));
    }

    /**
     * Update method for the Alien.
     */
    public void update() {
        //ToDo: Dead aliens can't move and shoot anymore, its projectiles should keep moving
        if (!tDead) {
            setX(getX() +  tDirection * (int) tSpeed);
            shoot();
            addShootChance();
        }
        this.updateProjectiles();
        this.updateUpgrades();
    }

    /**
     * Updates the position of all the upgrades that this alien has dropped.
     */
    protected void updateUpgrades() {
        for (Upgrade u: tUpgrades) {
            u.update();
        }
    }

    /**
     * Checks whether an Alien is a bonus Alien.
     * @return a boolean value.
     */
    public boolean isBonusAlien() {
        return tBonusAlien;
    }

    /**
     * Shoot method for the Alien.
     */
    public void shoot() {
        if (tCanShoot) {
            if ((Math.random() * 100) > getRandomChance()) {
                this.addProjectile(new SmallProjectile(getX() + getWidth() / 2, getY() + getHeight()));
                tShootChance = 0;
            }
        }
    }

    /**
     * Setter method to determine whether an Alien can shoot or not.
     * @param canShoot boolean value.
     */
    public void setCanShoot(boolean canShoot) {
        tCanShoot = canShoot;
    }

    /**
     * Method to return a String value describing the Alien.
     * @return a String value.
     */
    public String toString() {
        return "Alien on coords: " + getX() + ", " + getY();
    }

    /**
     * Check whether the Alien is dead.
     * @return a boolean value.
     */
    public boolean isDead() {
        return tDead;
    }

    /**
     * Method to add shoot chance to the Alien.
     */
    public void addShootChance() {
        tShootChance += Math.random() * 0;
    }

    /**
     * Check whether the Alien is at the end of the screen.
     * @return a boolean value.
     */
    public boolean endOfScreen() {
        return (getX() >= (Main.WIDTH - getWidth() - 10)
                && tDirection == 1)
                || (getX() <= 10 && tDirection == -1);
    }

    /**
     * Method to make the Alien switch direction.
     */
    public void switchDirection() {
        setY(getY() + 15);
        tDirection *= -1;
    }

    /**
     * Determines whether the alien is the lowest alien of its column.
     * @param aliens the Aliens to set for the lower Level.
     */
    public void setLowerLevel(ArrayList<Alien> aliens) {
        int bA = 5;
        int hA = 10;
        Rectangle myBox = new Rectangle(getX() + bA,
                Main.HEIGHT,
                getWidth() - (2 * bA),
                getHeight() - (2 * hA));
        tCanShoot = true;
        for (Alien a: aliens) {
            if (!a.isDead()) {
                Rectangle alienBox = new Rectangle(
                        a.getX() + bA,
                        Main.HEIGHT,
                        a.getWidth() - (2 * bA),
                        a.getHeight() - (2 * hA)
                );
                if (alienBox.getBounds().intersects(myBox)) {
                    if (getY() < a.getY()) {
                        tCanShoot = false;
                        return;
                    }
                }
            }
        }
    }

    /**
     * Apply difficulty to Aliens.
     */
    public void applyDifficulty() {
        switch (Main.DIFFICULTY) {
            case 1:
                tSpeed = 1;
                setRandomChance(99.8);
                break;
            case 2:
                tSpeed = 2;
                setRandomChance(99.9);
                break;
            case 3:
                tSpeed = 3;
                setRandomChance(99.5);
                break;
            default: break;
        }
    }

    /**
     * Method to make Alien drop an upgrade.
     */
    protected void drop() {
        int c = (int) (Math.random() * 100);
        if (c > 96) {
            tUpgrades.add(new WeaponUpgrade(getX() + getWidth() / 2, getY() + getHeight()));
        }
        else if (c > 92) {
            tUpgrades.add(new SpeedUpgrade(getX() + getWidth() / 2, getY() + getHeight()));
        }
        else if (c > 88) {
            tUpgrades.add(new HealthUpgrade(getX() + getWidth() / 2, getY() + getHeight()));
        }
        else if (c > 84) {
            tUpgrades.add(new PlayerUpgrade(getX() + getWidth() / 2, getY() + getHeight()));
        }
    }

    /**
     * Getter method for the upgrades the Alien holds.
     * @return an ArrayList of upgrades.
     */
    public ArrayList<Upgrade> getUpgrades() {
        return tUpgrades;
    }

    /**
     * Method to apply impact of a projectily on the Alien.
     * @return the Score to be added to the total Score.
     */
    @Override
    public int hit() {
        int result = super.hit();
        if (getHealth() <= 0) {
            tDead = true;
            this.drop();
        }
        return result;
    }

    /**
     * Get the shootchance belonging to the Alien.
     * @return the shootChance as an integer.
     */
    public int getShootChance() {
        return tShootChance;
    }

    /**
     * Get the direction of the Alien.
     * @return the direction as an integer.
     */
    public int getDirection() {
        return tDirection;
    }

    /**
     * Get the speed of the Alien.
     * @return the speed as a double.
     */
    public double getSpeed() {
        return tSpeed;
    }

    /**
     * Check whether the Alien can shoot.
     * @return the boolean value.
     */
    public boolean canShoot() {
        return tCanShoot;
    }

    /**
     * Set whether the Alien is dead.
     * @param tDead a boolean value.
     */
    public void setDead(boolean tDead) {
        this.tDead = tDead;
    }

    /**
     * Set the shootChance of the Alien.
     * @param tShootChance an integer value.
     */
    public void setShootChance(int tShootChance) {
        this.tShootChance = tShootChance;
    }

    /**
     * Set the direction of the Alien.
     * @param tDirection an integer value.
     */
    public void setDirection(int tDirection) {
        this.tDirection = tDirection;
    }

    /**
     * Set the speed of the Alien.
     * @param tSpeed a double value.
     */
    public void setSpeed(double tSpeed) {
        this.tSpeed = tSpeed;
    }

    /**
     * Set whether the Alien is a bonus Alien.
     * @param tBonusAlien a boolean value.
     */
    public void setBonusAlien(boolean tBonusAlien) {
        this.tBonusAlien = tBonusAlien;
    }

    /**
     * Set the upgrades of an Alien.
     * @param tUpgrades an ArrayList with upgrades.
     */
    public void setUpgrades(ArrayList<Upgrade> tUpgrades) {
        this.tUpgrades = tUpgrades;
    }
}