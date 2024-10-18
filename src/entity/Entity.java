package entity;

import main.Gamepanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    Gamepanel gp;
    public BufferedImage up1, up2, up3, up4,up5,up6,up7,up8, down1, down2, down3, down4, down5,down6,down7,down8, left1, left2, left3, left4,left5,left6,left7,left8,
            right1, right2, right3, right4,right5,right6,right7,right8;
    public BufferedImage attackup1, attackup2, attackleft1, attackleft2,attackright1,attackrigth2,attackdown1,attackdown2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    String dialogue[] = new String[20];

    //state
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collision = false;
    public boolean invincible = false;
    boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;


   //counter
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;

    //character status
    public int type; // 0 = player, 1 = npc, 2 = monster
    public String name;
    public int speed;
    public int maxLife;
    public int life;



    public Entity(Gamepanel gp){
        this.gp = gp;
    }
    public void setAction(){}
    public void damageReaction(){}
    public void speak(){
        if(dialogue[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogue[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }

    }

    public void update(){
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.chekObject(this,false);
        gp.cChecker.checkEntity(this,gp.npc);
        gp.cChecker.checkEntity(this,gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer == true){
            if(gp.player.invincible == false){
                //we can give damage
                gp.playSE(6);
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

        //si la colision es falsa, el jugador se mueve
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

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "up":
                    if(spriteNum == 1){ image = up1;}
                    if(spriteNum == 2){ image = up2;}
                    if(spriteNum == 3){image = up3;}
                    if(spriteNum == 4){image = up4;}
                    if(spriteNum == 5){image = up5;}
                    if(spriteNum == 6){ image = up6;}
                    if(spriteNum == 7){image = up7;}
                    if(spriteNum == 8){ image = up8;}
                    break;
                case "down":
                    if(spriteNum == 1){image = down1;}
                    if(spriteNum == 2){image = down2;}
                    if(spriteNum == 3){image = down3;}
                    if(spriteNum == 4){image = down4;}
                    if(spriteNum == 5){image = down1;}
                    if(spriteNum == 6){image = down2;}
                    if(spriteNum == 7){image = down3;}
                    if(spriteNum == 8){image = down4;}
                    break;
                case "left":
                    if(spriteNum == 1){image = left1;}
                    if(spriteNum == 2){image = left2;}
                    if(spriteNum == 3){image = left3;}
                    if(spriteNum == 4){ image = left4;}
                    if(spriteNum == 5){image = left1;}
                    if(spriteNum == 6){image = left2;}
                    if(spriteNum == 7){image = left3;}
                    if(spriteNum == 8){ image = left4;}
                    break;
                case "right":
                    if(spriteNum == 1){image = right1;}
                    if(spriteNum == 2){image = right2;}
                    if(spriteNum == 3){image = right3;}
                    if(spriteNum == 4){ image = right4;}
                    if(spriteNum == 5){ image = right1;}
                    if(spriteNum == 6){image = right2;}
                    if(spriteNum == 7){ image = right3;}
                    if(spriteNum == 8){image = right4;}
                    break;
            }
            //monster hp bar
            if(type == 2 && hpBarOn == true){
                double oneScale = (double)gp.tileSize/maxLife;
                double hpBarValue = oneScale*life;


                g2d.setColor((new Color(35,35,35)));
                g2d.fillRect(screenX-1,screenY-16,gp.tileSize+2,12);

                g2d.setColor(new Color(0x1A6A02));
                g2d.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);


                hpBarCounter++;

                if(hpBarCounter > 600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }


            if(invincible == true){
                hpBarOn = true;
                hpBarCounter = 0;
                changeApha(g2d,0.4F);
            }

            if(dying == true){
                dyingAnimation(g2d);
            }

            g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            changeApha(g2d,1F);

        }
    }
    public void dyingAnimation(Graphics2D g2d){
        dyingCounter++;
        int i = 5;

        if(dyingCounter <= i){changeApha(g2d,0F);}
        if(dyingCounter > i && dyingCounter <= i*2){ changeApha(g2d,1F);}
        if(dyingCounter > i*2 && dyingCounter <= i*3){ changeApha(g2d,0F);}
        if(dyingCounter > i*3 && dyingCounter <= i*4){ changeApha(g2d,1F);}
        if(dyingCounter > i*4 && dyingCounter <= i*5){ changeApha(g2d,0F);}
        if(dyingCounter > i*5 && dyingCounter <= i*6){changeApha(g2d,1F);}
        if(dyingCounter > i*6 && dyingCounter <= i*7){ changeApha(g2d,0F);}
        if(dyingCounter > i*7 && dyingCounter <= i*8){ changeApha(g2d,1F);}
        if(dyingCounter > i*8){

            dying = false;
            alive = false;
        }
    }
    public void changeApha(Graphics2D g2d, float alphaValue){
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));

    }
    public BufferedImage setup(String imagePath, int width, int height){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream( imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);

        }catch (Exception e){
            e.printStackTrace();
        }
        return image;
    }
}
