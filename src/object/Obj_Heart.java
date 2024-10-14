package object;

import main.Gamepanel;

import javax.imageio.ImageIO;

public class Obj_Heart extends  SuperObject{
    Gamepanel gp;

    public Obj_Heart(Gamepanel gp) {
        this.gp = gp;
        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/heartFull.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/Objects/heartMed.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/Objects/heartVoid.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
