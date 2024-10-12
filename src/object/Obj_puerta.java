package object;

import javax.imageio.ImageIO;

public class Obj_puerta extends SuperObject {
    public Obj_puerta() {

        name = "puerta";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/puerta.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
        collision = true;
    }
}
