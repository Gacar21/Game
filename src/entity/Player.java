package entity;

import main.Gamepanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    Gamepanel gp;
    KeyHandler keyh;

    public final int screenX;
    public final int screenY;

    public Player(Gamepanel gp, KeyHandler keyh) {
        this.gp = gp;
        this.keyh = keyh;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Arriba1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/Arriba2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/Arriba3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/Arriba4.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/Abajo1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/Abajo2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/Abajo3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/Abajo4.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/Izquierda1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/Izquierda2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/Izquierda3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/Izquierda4.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/Derecha1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/Derecha2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/Derecha3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/Derecha4.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
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

        // si la coloicion es falsa, el jugador se mueve

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
                        spriteNum = 1;
                }


                spriteCounter =0;
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
                 break;
        }
        g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}
