package entity;
import main.Gamepanel;
import java.util.Random;


public class NPC_gb extends Entity {
    public NPC_gb(Gamepanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
    }
    public void getImage(){
        up1 = setup("/npc/09arriba");
        up2 = setup("/npc/10arriba");
        up3 = setup("/npc/11arriba");
        up4 = setup("/npc/12arriba");
        up5 = setup("/npc/13arriba");
        up6 = setup("/npc/14arriba");
        up7 = setup("/npc/15arriba");
        up8 = setup("/npc/16arriba");
        down1 = setup("/npc/00abajo");
        down2 = setup("/npc/01abajo");
        down3 = setup("/npc/02abajo");
        down4 = setup("/npc/03abajo");
        down5 = setup("/npc/04abajo");
        down6 = setup("/npc/05abajo");
        down7 = setup("/npc/06abajo");
        down8 = setup("/npc/07abajo");
        left1 = setup("/npc/25izquierda");
        left2 = setup("/npc/26izquierda");
        left3 = setup("/npc/27izquierda");
        left4 = setup("/npc/28izquierda");
        left5 = setup("/npc/29izquierda");
        left6 = setup("/npc/30izquierda");
        left7 = setup("/npc/31izquierda");
        left8 = setup("/npc/32izquierda");
        right1 = setup("/npc/17derecha");
        right2 = setup("/npc/18derecha");
        right3 = setup("/npc/19derecha");
        right4 = setup("/npc/20derecha");
        right5 = setup("/npc/21derecha");
        right6 = setup("/npc/22derecha");
        right7 = setup("/npc/23derecha");
        right8 = setup("/npc/24derecha");

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
