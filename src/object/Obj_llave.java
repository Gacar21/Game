package object;

import entity.Entity;
import main.Gamepanel;


public class Obj_llave extends Entity {


    public Obj_llave(Gamepanel gp) {
        super(gp);

        name = "llave";
        down1 = setup("/Objects/llave", gp.tileSize,gp.tileSize);

    }

}
