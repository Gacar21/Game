package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Sword_Normal extends Entity {

    public Obj_Sword_Normal(Gamepanel gp) {
        super(gp);
        name = "ESPADA NORMAL";
        down1 = setup("/Objects/espada", gp.tileSize, gp.tileSize);
        attacValue = 1 ;
        description = "["+ name + "]\n Nivel basica";
    }
}
