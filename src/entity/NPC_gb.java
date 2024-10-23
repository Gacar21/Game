package entity;
import main.Gamepanel;
import java.util.Random;


public class NPC_gb extends Entity {
    public NPC_gb(Gamepanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }
    public void getImage(){
        up1 = setup("/npc/09arriba", gp.tileSize,gp.tileSize);
        up2 = setup("/npc/10arriba", gp.tileSize,gp.tileSize);
        up3 = setup("/npc/11arriba", gp.tileSize,gp.tileSize);
        up4 = setup("/npc/12arriba", gp.tileSize,gp.tileSize);
        up5 = setup("/npc/13arriba", gp.tileSize,gp.tileSize);
        up6 = setup("/npc/14arriba", gp.tileSize,gp.tileSize);
        up7 = setup("/npc/15arriba", gp.tileSize,gp.tileSize);
        up8 = setup("/npc/16arriba", gp.tileSize,gp.tileSize);
        down1 = setup("/npc/00abajo", gp.tileSize,gp.tileSize);
        down2 = setup("/npc/01abajo", gp.tileSize,gp.tileSize);
        down3 = setup("/npc/02abajo", gp.tileSize,gp.tileSize);
        down4 = setup("/npc/03abajo", gp.tileSize,gp.tileSize);
        down5 = setup("/npc/04abajo", gp.tileSize,gp.tileSize);
        down6 = setup("/npc/05abajo", gp.tileSize,gp.tileSize);
        down7 = setup("/npc/06abajo", gp.tileSize,gp.tileSize);
        down8 = setup("/npc/07abajo", gp.tileSize,gp.tileSize);
        left1 = setup("/npc/25izquierda", gp.tileSize,gp.tileSize);
        left2 = setup("/npc/26izquierda", gp.tileSize,gp.tileSize);
        left3 = setup("/npc/27izquierda", gp.tileSize,gp.tileSize);
        left4 = setup("/npc/28izquierda", gp.tileSize,gp.tileSize);
        left5 = setup("/npc/29izquierda", gp.tileSize,gp.tileSize);
        left6 = setup("/npc/30izquierda", gp.tileSize,gp.tileSize);
        left7 = setup("/npc/31izquierda", gp.tileSize,gp.tileSize);
        left8 = setup("/npc/32izquierda", gp.tileSize,gp.tileSize);
        right1 = setup("/npc/17derecha", gp.tileSize,gp.tileSize);
        right2 = setup("/npc/18derecha", gp.tileSize,gp.tileSize);
        right3 = setup("/npc/19derecha", gp.tileSize,gp.tileSize);
        right4 = setup("/npc/20derecha", gp.tileSize,gp.tileSize);
        right5 = setup("/npc/21derecha", gp.tileSize,gp.tileSize);
        right6 = setup("/npc/22derecha", gp.tileSize,gp.tileSize);
        right7 = setup("/npc/23derecha", gp.tileSize,gp.tileSize);
        right8 = setup("/npc/24derecha", gp.tileSize,gp.tileSize);

    }

    public void setDialogue(){
        dialogue[0] = "Hola, me llamo Gabriel";
        dialogue[1] = "solo como los fines de mes \ncuando me consignan \nquinteros en accion";
        dialogue[2] = "Ahora no tengo internet \nPatrocinenme";
        dialogue[3] = "Dormir en la calle \ndejo de ser un meme \nahora es una realidad, \nHelpme";

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
    public void speak() {

        // do this character specific stuff
        super.speak();
    }
 }
