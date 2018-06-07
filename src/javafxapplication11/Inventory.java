package javafxapplication11;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Interactables> interactables;

    public Inventory(ArrayList<Interactables> interactables) {
        this.interactables = interactables;
        System.out.println(interactables);
    }

    

    public ArrayList<Interactables> getInteractables() {
        return interactables;
    }
}
