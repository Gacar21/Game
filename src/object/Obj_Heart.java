package object;

import entity.Entity;
import main.Gamepanel;


public class Obj_Heart extends Entity {


    public Obj_Heart(Gamepanel gp) {
        super(gp);

        name = "Heart";
        image = setup("/Objects/heartFull");
        image2 = setup("/Objects/heartMed");
        image3 = setup("/Objects/heartVoid");

    }

}
