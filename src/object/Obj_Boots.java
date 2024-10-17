package object;

import entity.Entity;
import main.Gamepanel;

import javax.imageio.ImageIO;

public class Obj_Boots extends Entity {

    public Obj_Boots(Gamepanel gp) {
        super(gp);
        name = "botas";
        down1 = setup("/Objects/bota", gp.tileSize,gp.tileSize);
    }
}
