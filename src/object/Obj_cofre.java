package object;

import entity.Entity;
import main.Gamepanel;


public class Obj_cofre extends Entity {


    public Obj_cofre(Gamepanel gp) {

        super(gp);

        name = "cofre";
        down1 =setup("/Objects/cofre", gp.tileSize,gp.tileSize);
        collision = true;
    }
}
