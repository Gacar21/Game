package object;

import javax.imageio.ImageIO;

public class Obj_cofre extends SuperObject{
    public Obj_cofre() {

        name = "cofre";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/cofre.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
