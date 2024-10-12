package object;

import javax.imageio.ImageIO;

public class Obj_Boots extends SuperObject{
    public Obj_Boots() {

        name = "botas";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/bota.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
        collision = true;
    }
}
