package object;

import main.Gamepanel;

import javax.imageio.ImageIO;

public class Obj_Boots extends SuperObject{
    Gamepanel gp;
    public Obj_Boots(Gamepanel gp) {
        this.gp = gp;
        name = "botas";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/bota.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        collision = true;
    }
}
