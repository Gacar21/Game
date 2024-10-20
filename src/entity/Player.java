package entity;

import main.Gamepanel;
import main.KeyHandler;
import object.Obj_Shield_Wood;
import object.Obj_Sword_Normal;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity{

    KeyHandler keyh;

    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCancel = false;

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

        attackArea.width = 36;
        attackArea.height = 36;


        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 42;
        worldY = gp.tileSize * 31;
        speed = 4;
        direction = "down";

        //player status
        level = 1;
        maxLife = 6;
        life = maxLife;
        strength = 1; //the more strengt he has, the more damage he gives
        dexterity = 1; // the more dexterity he has, the less damage receives
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new Obj_Sword_Normal(gp); // the total attack value is decided by strengt and weapon
        currentShield = new Obj_Shield_Wood(gp); // the total defense value is decided by desterity and shiedl
        attack = getAttack();
        defense = getDefense();
    }
    public int getAttack(){
        return attack = strength * currentWeapon.attacValue;
    }
    public int getDefense(){
        return defense = dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage(){
        up1 = setup("/player/arriba1", gp.tileSize,gp.tileSize);
        up2 = setup("/player/arriba2", gp.tileSize,gp.tileSize);
        up3 = setup("/player/arriba3", gp.tileSize,gp.tileSize);
        up4 = setup("/player/arriba4", gp.tileSize,gp.tileSize);
        up5 = setup("/player/arriba5", gp.tileSize,gp.tileSize);
        up6 = setup("/player/arriba6", gp.tileSize,gp.tileSize);
        up7 = setup("/player/arriba7", gp.tileSize,gp.tileSize);
        up8 = setup("/player/arriba8", gp.tileSize,gp.tileSize);
        down1 = setup("/player/abajo1", gp.tileSize,gp.tileSize);
        down2 = setup("/player/abajo2", gp.tileSize,gp.tileSize);
        down3 = setup("/player/abajo3", gp.tileSize,gp.tileSize);
        down4 = setup("/player/abajo4", gp.tileSize,gp.tileSize);
        down5 = setup("/player/abajo5", gp.tileSize,gp.tileSize);
        down6 = setup("/player/abajo6", gp.tileSize,gp.tileSize);
        down7 = setup("/player/abajo7", gp.tileSize,gp.tileSize);
        down8 = setup("/player/abajo8", gp.tileSize,gp.tileSize);
        left1 = setup("/player/izquierda1", gp.tileSize,gp.tileSize);
        left2 = setup("/player/izquierda2", gp.tileSize,gp.tileSize);
        left3 = setup("/player/izquierda3", gp.tileSize,gp.tileSize);
        left4 = setup("/player/izquierda4", gp.tileSize,gp.tileSize);
        left5 = setup("/player/izquierda5", gp.tileSize,gp.tileSize);
        left6 = setup("/player/izquierda6", gp.tileSize,gp.tileSize);
        left7 = setup("/player/izquierda7", gp.tileSize,gp.tileSize);
        left8 = setup("/player/izquierda8", gp.tileSize,gp.tileSize);
        right1 = setup("/player/derecha1", gp.tileSize,gp.tileSize);
        right2 = setup("/player/derecha2", gp.tileSize,gp.tileSize);
        right3 = setup("/player/derecha3", gp.tileSize,gp.tileSize);
        right4 = setup("/player/derecha4", gp.tileSize,gp.tileSize);
        right5 = setup("/player/derecha5", gp.tileSize,gp.tileSize);
        right6 = setup("/player/derecha6", gp.tileSize,gp.tileSize);
        right7 = setup("/player/derecha7", gp.tileSize,gp.tileSize);
        right8 = setup("/player/derecha8", gp.tileSize,gp.tileSize);

    }
    public void getPlayerAttackImage(){
        attackup1 = setup("/player/attackup", gp.tileSize,gp.tileSize*2);
        attackup2 = setup("/player/attackup1", gp.tileSize,gp.tileSize*2);
        attackdown1 = setup("/player/attackdown", gp.tileSize,gp.tileSize*2);
        attackdown2 = setup("/player/attackdown1", gp.tileSize,gp.tileSize*2);
        attackleft1 = setup("/player/attackleft", gp.tileSize*2,gp.tileSize);
        attackleft2 = setup("/player/attackleft1", gp.tileSize*2,gp.tileSize);
        attackright1 = setup("/player/attackright", gp.tileSize*2,gp.tileSize);
        attackrigth2 = setup("/player/attackright1", gp.tileSize*2,gp.tileSize);
    }

  public void update(){

        if(attacking == true){
            attacking();
        }

       else if(keyh.upPressed == true || keyh.downPressed == true || keyh.leftPressed == true || keyh.rightPressed == true
        || keyh.jPressed == true ){

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
            contactMonster(monsterIndex);

            //check event
            gp.eHandler.checkEvent();


        // si la colision es falsa, el jugador se mueve

            if(collisionOn == false && keyh.jPressed == false ){

                switch (direction){
                    case "up":worldY -= speed;break;
                    case "down":worldY += speed; break;
                    case "left":worldX -= speed;break;
                    case "right": worldX += speed;break;
                }
            }

            if(keyh.jPressed == true && attackCancel == false ){
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }
            attackCancel = false;

            gp.keyHandler.jPressed = false;

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
        // this needs to be outside of key if statement
      if(invincible == true){
          invincibleCounter++;
          if(invincibleCounter > 60){
              invincible = false;
              invincibleCounter = 0;
          }
      }
    }
    public void attacking(){
        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum =1;
        }
        if(spriteCounter > 5 && spriteCounter <=25){
            spriteNum = 2;

            //save the current worldx, worldy, solidarea

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //adjust player' worldx/y for the attackArea
            switch (direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += gp.tileSize; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += gp.tileSize; break;
            }
            //attack area beceoms solidarea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            //check monster collision with the update worldX, worldY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            //after checking collision, resotret the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }

        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i){
            if(i != 999){

            }
    }

    public void interactNPC(int i){
        if(gp.keyHandler.jPressed == true){
            if(i != 999){
                attackCancel = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
           }

        }
    }

    public void contactMonster(int i){
        if(i != 999){
            if(invincible == false){
                gp.playSE(6);
                life -= 1;
                invincible = true;
            }
        }
    }
    public void damageMonster(int i){
        if(i != 999) {
            if(gp.monster[i].invincible == false){
                gp.playSE(5);
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if(gp.monster[i].life <= 0){
                    gp.monster[i].dying = true;
                }
            }
        }
    }

    public void draw(Graphics2D g2d){


//        g2d.setColor(Color.WHITE);
//        g2d.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        int tempScreenx = screenX;
        int tempScreeny = screenY;


        switch (direction) {
            case "up":
                if(attacking == false){
                    if(spriteNum == 1){ image = up1;}
                    if(spriteNum == 2){ image = up2;}
                    if(spriteNum == 1){ image = up1;}
                    if(spriteNum == 2){ image = up2;}
                    if(spriteNum == 3){  image = up3;}
                    if(spriteNum == 4){ image = up4;}
                    if(spriteNum == 5){ image = up5;}
                    if(spriteNum == 6){ image = up6;}
                    if(spriteNum == 7){ image = up7;}
                    if(spriteNum == 8){image = up8;}
                }
                if(attacking == true){
                    tempScreeny = screenY-gp.tileSize;
                    if(spriteNum == 1){ image = attackup1;}
                    if(spriteNum == 2){ image = attackup2;}
                }
                break;
            case "down":
                if(attacking == false){
                    if(spriteNum == 1){image = down1;}
                    if(spriteNum == 2){image = down2;}
                    if(spriteNum == 3){image = down3;}
                    if(spriteNum == 4){image = down4;}
                    if(spriteNum == 5){image = down1;}
                    if(spriteNum == 6){image = down2;}
                    if(spriteNum == 7){image = down3;}
                    if(spriteNum == 8){image = down4;}
                }
                if(attacking == true){
                    if(spriteNum == 1){ image = attackdown1;}
                    if(spriteNum == 2){ image = attackdown2;}
                }
                break;
            case "left":
                if(attacking == false){
                    if(spriteNum == 1){image = left1;}
                    if(spriteNum == 2){image = left2;}
                    if(spriteNum == 3){image = left3;}
                    if(spriteNum == 4){image = left4;}
                    if(spriteNum == 5){image = left1;}
                    if(spriteNum == 6){image = left2;}
                    if(spriteNum == 7){image = left3;}
                    if(spriteNum == 8){image = left4;}
                }
                if(attacking == true){
                    tempScreenx = screenX-gp.tileSize;
                    if(spriteNum == 1){ image = attackleft1;}
                    if(spriteNum == 2){ image = attackleft2;}
                }
                break;
             case "right":
                 if(attacking == false){
                     if(spriteNum == 1){image = right1;}
                     if(spriteNum == 2){image = right2;}
                     if(spriteNum == 3){image = right3;}
                     if(spriteNum == 4){image = right4;}
                     if(spriteNum == 5){image = right1;}
                     if(spriteNum == 6){image = right2;}
                     if(spriteNum == 7){ image = right3;}
                     if(spriteNum == 8){image = right4;}
                 }
                 if(attacking == true){
                     if(spriteNum == 1){ image = attackright1;}
                     if(spriteNum == 2){ image = attackrigth2;}
                 }
                 break;
        }
        if(invincible == true){
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
        }
            g2d.drawImage(image, tempScreenx, tempScreeny, null);

        //reset alpha
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));

    }

}

//debug (visualizar contador de inmunidad
//        g2d.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2d.setColor(Color.WHITE);
//        g2d.drawString("invincible: " + invincibleCounter, 10,400);



//visualizar el marco solido del personaje
//        g2d.setColor(Color.red);
//        g2d.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
