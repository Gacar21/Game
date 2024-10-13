package main;

import object.Obj_Boots;
import object.Obj_cofre;
import object.Obj_llave;
import object.Obj_puerta;

public class AssetSetter {
    Gamepanel gp;
    public AssetSetter(Gamepanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new Obj_llave(gp);
        gp.obj[0].worldX = 38 * gp.tileSize;
        gp.obj[0].worldY = 20 * gp.tileSize;

        gp.obj[1] = new Obj_llave(gp);
        gp.obj[1].worldX = 42 * gp.tileSize;
        gp.obj[1].worldY = 30 * gp.tileSize;

        gp.obj[2] = new Obj_llave(gp);
        gp.obj[2].worldX = 22 * gp.tileSize;
        gp.obj[2].worldY = 22 * gp.tileSize;

        gp.obj[3] = new Obj_puerta(gp);
        gp.obj[3].worldX = 25 * gp.tileSize;
        gp.obj[3].worldY = 19 * gp.tileSize;

        gp.obj[4] = new Obj_puerta(gp);
        gp.obj[4].worldX = 31 * gp.tileSize;
        gp.obj[4].worldY = 27 * gp.tileSize;

        gp.obj[5] = new Obj_puerta(gp);
        gp.obj[5].worldX = 38 * gp.tileSize;
        gp.obj[5].worldY = 33 * gp.tileSize;

        gp.obj[6] = new Obj_cofre(gp);
        gp.obj[6].worldX = 25 * gp.tileSize;
        gp.obj[6].worldY = 13 * gp.tileSize;

        gp.obj[7] = new Obj_Boots(gp);
        gp.obj[7].worldX = 37 * gp.tileSize;
        gp.obj[7].worldY = 42 * gp.tileSize;
    }
}
