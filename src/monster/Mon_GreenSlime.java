package monster;

import entity.Entity;
import main.Gamepanel;

import java.util.Random;

public class Mon_GreenSlime extends Entity {

        Gamepanel gp;
    public Mon_GreenSlime(Gamepanel gp) {
        super(gp);

        this.gp = gp;

        type = 2;
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 9;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        getImage();
    }

    public void getImage(){
        up1 = setup("/monster/slime08", gp.tileSize,gp.tileSize);
        up2 = setup("/monster/slime09", gp.tileSize,gp.tileSize);
        up3 = setup("/monster/slime10", gp.tileSize,gp.tileSize);
        up4 = setup("/monster/slime11", gp.tileSize,gp.tileSize);
        up5 = setup("/monster/slime08", gp.tileSize,gp.tileSize);
        up6 = setup("/monster/slime09", gp.tileSize,gp.tileSize);
        up7 = setup("/monster/slime10", gp.tileSize,gp.tileSize);
        up8 = setup("/monster/slime11", gp.tileSize,gp.tileSize);
        down1 = setup("/monster/slime00", gp.tileSize,gp.tileSize);
        down2 = setup("/monster/slime01", gp.tileSize,gp.tileSize);
        down3 = setup("/monster/slime02", gp.tileSize,gp.tileSize);
        down4 = setup("/monster/slime03", gp.tileSize,gp.tileSize);
        down5 = setup("/monster/slime00", gp.tileSize,gp.tileSize);
        down6 = setup("/monster/slime01", gp.tileSize,gp.tileSize);
        down7 = setup("/monster/slime02", gp.tileSize,gp.tileSize);
        down8 = setup("/monster/slime03", gp.tileSize,gp.tileSize);
        right1 = setup("/monster/slime04", gp.tileSize,gp.tileSize);
        right2 = setup("/monster/slime05", gp.tileSize,gp.tileSize);
        right3 = setup("/monster/slime06", gp.tileSize,gp.tileSize);
        right4 = setup("/monster/slime07", gp.tileSize,gp.tileSize);
        right5 = setup("/monster/slime04", gp.tileSize,gp.tileSize);
        right6 = setup("/monster/slime05", gp.tileSize,gp.tileSize);
        right7 = setup("/monster/slime06", gp.tileSize,gp.tileSize);
        right8 = setup("/monster/slime07", gp.tileSize,gp.tileSize);
        left1 =  setup("/monster/slime12", gp.tileSize,gp.tileSize);
        left2 =  setup("/monster/slime13", gp.tileSize,gp.tileSize);
        left3 =  setup("/monster/slime14", gp.tileSize,gp.tileSize);
        left4 =  setup("/monster/slime15", gp.tileSize,gp.tileSize);
        left5 =  setup("/monster/slime12", gp.tileSize,gp.tileSize);
        left6 =  setup("/monster/slime13", gp.tileSize,gp.tileSize);
        left7 =  setup("/monster/slime14", gp.tileSize,gp.tileSize);
        left8 =  setup("/monster/slime15", gp.tileSize,gp.tileSize);

    }
    public void setAction(){

        actionLockCounter ++;

        if(actionLockCounter  == 120){

            Random random = new Random();
            int i = random.nextInt(100)+1; // pick up a number from 1 to 100
            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }

            actionLockCounter = 0;
        }

    }
}
