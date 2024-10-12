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
   public int hasKey = 0;
   int standCounter = 0;

    public Player(Gamepanel gp, KeyHandler keyh) {
        this.gp = gp;
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
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/arriba1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/arriba2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/arriba3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/arriba4.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/player/arriba5.png"));
            up6 = ImageIO.read(getClass().getResourceAsStream("/player/arriba6.png"));
            up7 = ImageIO.read(getClass().getResourceAsStream("/player/arriba7.png"));
            up8 = ImageIO.read(getClass().getResourceAsStream("/player/arriba8.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/abajo1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/abajo2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/abajo3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/abajo4.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/player/abajo5.png"));
            down6 = ImageIO.read(getClass().getResourceAsStream("/player/abajo6.png"));
            down7 = ImageIO.read(getClass().getResourceAsStream("/player/abajo7.png"));
            down8 = ImageIO.read(getClass().getResourceAsStream("/player/abajo8.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/izquierda1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/izquierda2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/izquierda3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/izquierda4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/player/izquierda5.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/player/izquierda6.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/player/izquierda7.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("/player/izquierda8.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/derecha1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/derecha2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/derecha3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/derecha4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/player/derecha5.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/player/derecha6.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/player/derecha7.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("/player/derecha8.png"));

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

            //verificar colision con objetos
            int objIndex = gp.cChecker.chekObject(this,true);
            pickUpObject(objIndex);
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

                String objectName = gp.obj[i].name;

                switch (objectName){
                    case "llave":
                        gp.playSE(2);
                        hasKey++;
                        gp.obj[i]= null;
                        gp.ui.showMessage("Has obtenido una llave!");

                        break;
                    case "puerta":
                        gp.playSE(3);
                        if(hasKey > 0){
                            gp.obj[i] = null;
                            hasKey--;
                            gp.ui.showMessage("Puerta abierta!");
                        }else{
                            gp.ui.showMessage("Necesitas una llave!");
                        }

                        break;
                    case "botas":
                        gp.playSE(4);
                        speed += 2;
                        gp.obj[i] = null;
                        gp.ui.showMessage("Velocidad +1");
                        break;
                    case "cofre":
                        gp.ui.gameFinished = true;
                        gp.stopMusic();
                        gp.playSE(1);
                        break;
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
        g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
      //visualizar el marco solido del personaje
//        g2d.setColor(Color.red);
//        g2d.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

    }

}
