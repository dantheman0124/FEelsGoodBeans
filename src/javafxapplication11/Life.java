package javafxapplication11;

public class Life {

    int health;
    private static final int FULL_HEALTH = 100;

    public void setHealth(int health) {
        
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void loseHealth(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        
    }

    public void setFullHealth() {
        health = FULL_HEALTH;
    }

}
