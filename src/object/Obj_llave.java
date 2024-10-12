package object;

import main.Gamepanel;

import javax.imageio.ImageIO;


public class Obj_llave extends SuperObject {
    Gamepanel gp;

    public Obj_llave(Gamepanel gp) {
        this.gp = gp;
        name = "llave";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/llave.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
