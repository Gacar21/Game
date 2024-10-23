package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Shield_Wood extends Entity {

    public Obj_Shield_Wood(Gamepanel gp) {
        super(gp);
        name = "ESCUDO DE MADERA";
        down1 = setup("/Objects/escudo", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "["+ name + "]\nHecho de Madera";
    }
}
