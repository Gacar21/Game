package object;

import main.Gamepanel;

import javax.imageio.ImageIO;

public class Obj_cofre extends SuperObject{
    Gamepanel gp;

    public Obj_cofre(Gamepanel gp) {

        this.gp = gp;
        name = "cofre";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/cofre.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
