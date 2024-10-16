package monster;

import entity.Entity;
import main.Gamepanel;

import java.util.Random;

public class Mon_GreenSlime extends Entity {


    public Mon_GreenSlime(Gamepanel gp) {
        super(gp);
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
        up1 = setup("/monster/slime08");
        up2 = setup("/monster/slime09");
        up3 = setup("/monster/slime10");
        up4 = setup("/monster/slime11");
        up5 = setup("/monster/slime08");
        up6 = setup("/monster/slime09");
        up7 = setup("/monster/slime10");
        up8 = setup("/monster/slime11");
        down1 = setup("/monster/slime00");
        down2 = setup("/monster/slime01");
        down3 = setup("/monster/slime02");
        down4 = setup("/monster/slime03");
        down5 = setup("/monster/slime00");
        down6 = setup("/monster/slime01");
        down7 = setup("/monster/slime02");
        down8 = setup("/monster/slime03");
        right1 = setup("/monster/slime04");
        right2 = setup("/monster/slime05");
        right3 = setup("/monster/slime06");
        right4 = setup("/monster/slime07");
        right5 = setup("/monster/slime04");
        right6 = setup("/monster/slime05");
        right7 = setup("/monster/slime06");
        right8 = setup("/monster/slime07");
        left1 =  setup("/monster/slime12");
        left2 =  setup("/monster/slime13");
        left3 =  setup("/monster/slime14");
        left4 =  setup("/monster/slime15");
        left5 =  setup("/monster/slime12");
        left6 =  setup("/monster/slime13");
        left7 =  setup("/monster/slime14");
        left8 =  setup("/monster/slime15");

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
