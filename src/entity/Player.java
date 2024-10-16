package entity;

import main.Gamepanel;
import main.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity{

    KeyHandler keyh;

    public final int screenX;
    public final int screenY;
    int standCounter = 0;

    public Player(Gamepanel gp, KeyHandler keyh) {
        super(gp);

        this.keyh = keyh;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 42;
        worldY = gp.tileSize * 31;
        speed = 4;
        direction = "down";

        //player status

        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage(){
        up1 = setup("/player/arriba1");
        up2 = setup("/player/arriba2");
        up3 = setup("/player/arriba3");
        up4 = setup("/player/arriba4");
        up5 = setup("/player/arriba5");
        up6 = setup("/player/arriba6");
        up7 = setup("/player/arriba7");
        up8 = setup("/player/arriba8");
        down1 = setup("/player/abajo1");
        down2 = setup("/player/abajo2");
        down3 = setup("/player/abajo3");
        down4 = setup("/player/abajo4");
        down5 = setup("/player/abajo5");
        down6 = setup("/player/abajo6");
        down7 = setup("/player/abajo7");
        down8 = setup("/player/abajo8");
        left1 = setup("/player/izquierda1");
        left2 = setup("/player/izquierda2");
        left3 = setup("/player/izquierda3");
        left4 = setup("/player/izquierda4");
        left5 = setup("/player/izquierda5");
        left6 = setup("/player/izquierda6");
        left7 = setup("/player/izquierda7");
        left8 = setup("/player/izquierda8");
        right1 = setup("/player/derecha1");
        right2 = setup("/player/derecha2");
        right3 = setup("/player/derecha3");
        right4 = setup("/player/derecha4");
        right5 = setup("/player/derecha5");
        right6 = setup("/player/derecha6");
        right7 = setup("/player/derecha7");
        right8 = setup("/player/derecha8");

    }

  public void update(){

        if(keyh.upPressed == true || keyh.downPressed == true || keyh.leftPressed == true || keyh.rightPressed == true){

            if(keyh.upPressed){
                direction = "up";
            }else if(keyh.downPressed){
                direction = "down";
            }else if(keyh.leftPressed){
                direction = "left";
            } else if (keyh.rightPressed) {
                direction = "right";


            }
            // verificar colission

            collisionOn = false;
            gp.cChecker.checkTile(this);

            //verificar colision con objetos
            int objIndex = gp.cChecker.chekObject(this,true);
            pickUpObject(objIndex);
            //check NPC collision

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //check mosnter collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);


            //check event
            gp.eHandler.checkEvent();
            gp.keyHandler.jPressed = false;

        // si la colision es falsa, el jugador se mueve

            if(collisionOn == false){

                switch (direction){
                    case "up":worldY -= speed;break;
                    case "down":worldY += speed; break;
                    case "left":worldX -= speed;break;
                    case "right": worldX += speed;break;
                }
            }


            spriteCounter++;

            if(spriteCounter >= 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if(spriteNum == 3){
                    spriteNum = 4;
                } else if(spriteNum == 4){
                    spriteNum = 5;
                }else if(spriteNum == 5){
                    spriteNum = 6;
                }else if(spriteNum == 6){
                    spriteNum = 7;
                }else if(spriteNum == 7){
                    spriteNum = 1;
                }else if(spriteNum == 8){
                    spriteNum = 1;
                }


                spriteCounter =0;
            }
        }
        else{
            standCounter++;
            if(standCounter == 20){
                spriteNum = 1;
                standCounter = 0;
            }

        }
    }

    public void pickUpObject(int i){
            if(i != 999){

            }
    }

    public void interactNPC(int i){
        if(i != 999){
            if(gp.keyHandler.jPressed == true) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }

    }

    public void draw(Graphics2D g2d){


//        g2d.setColor(Color.WHITE);
//        g2d.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                if(spriteNum == 3){
                    image = up3;
                }
                if(spriteNum == 4){
                    image = up4;
                }
                if(spriteNum == 5){
                    image = up5;
                }
                if(spriteNum == 6){
                    image = up6;
                }
                if(spriteNum == 7){
                    image = up7;
                }
                if(spriteNum == 8){
                    image = up8;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                if(spriteNum == 3){
                    image = down3;
                }
                if(spriteNum == 4){
                    image = down4;
                }
                if(spriteNum == 5){
                    image = down1;
                }
                if(spriteNum == 6){
                    image = down2;
                }
                if(spriteNum == 7){
                    image = down3;
                }
                if(spriteNum == 8){
                    image = down4;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                if(spriteNum == 3){
                    image = left3;
                }
                if(spriteNum == 4){
                    image = left4;
                }
                if(spriteNum == 5){
                    image = left1;
                }
                if(spriteNum == 6){
                    image = left2;
                }
                if(spriteNum == 7){
                    image = left3;
                }
                if(spriteNum == 8){
                    image = left4;
                }
                break;
             case "right":
                 if(spriteNum == 1){
                     image = right1;
                 }
                 if(spriteNum == 2){
                     image = right2;
                 }
                 if(spriteNum == 3){
                     image = right3;
                 }
                 if(spriteNum == 4){
                     image = right4;
                 }
                 if(spriteNum == 5){
                     image = right1;
                 }
                 if(spriteNum == 6){
                     image = right2;
                 }
                 if(spriteNum == 7){
                     image = right3;
                 }
                 if(spriteNum == 8){
                     image = right4;
                 }
                 break;
        }
        g2d.drawImage(image, screenX, screenY, null);
      //visualizar el marco solido del personaje
//        g2d.setColor(Color.red);
//        g2d.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

    }

}
