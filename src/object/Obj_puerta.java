package object;

import main.Gamepanel;

import javax.imageio.ImageIO;

public class Obj_puerta extends SuperObject {
    Gamepanel gp;
    public Obj_puerta(Gamepanel gp) {
        this.gp = gp;
        name = "puerta";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/puerta.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        collision = true;
    }
}
