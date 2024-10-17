package object;

import entity.Entity;
import main.Gamepanel;


public class Obj_Heart extends Entity {


    public Obj_Heart(Gamepanel gp) {
        super(gp);

        name = "Heart";
        image = setup("/Objects/heartFull", gp.tileSize,gp.tileSize);
        image2 = setup("/Objects/heartMed", gp.tileSize,gp.tileSize);
        image3 = setup("/Objects/heartVoid", gp.tileSize,gp.tileSize);

    }

}
