package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_puerta extends Entity {

    public Obj_puerta(Gamepanel gp) {
        super(gp);
        name = "puerta";
        down1 = setup("/Objects/puerta");
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width =48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
