package object;

import javax.imageio.ImageIO;
import java.io.File;

public class Obj_llave extends SuperObject {
    public Obj_llave() {

        name = "llave";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/llave.png"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
