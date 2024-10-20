package main;

import entity.Entity;
import object.Obj_Heart;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    Gamepanel gp;
    Graphics2D g2d;
    Font Purisa;
    BufferedImage heartFull, heartMed,heartVoid;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;

    public UI(Gamepanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            Purisa = Font.createFont(Font.TRUETYPE_FONT, is);

        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //create hud object
        Entity heart = new Obj_Heart(gp);
        heartFull = heart.image;
        heartMed = heart.image2;
        heartVoid = heart.image3;

    }
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2d) {
        this.g2d = g2d;

        g2d.setFont(Purisa);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);

        //title state

        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        //play state

        if(gp.gameState == gp.playState){
            drawPlayerLife();

        }
        // pause state
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();

        }
        // Dialogue state
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
        //character state
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
        }

    }
    public void drawPlayerLife(){


        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        //draw max life
        while(i < gp.player.maxLife/2){
            g2d.drawImage(heartVoid, x, y, null);
            i++;
            x += gp.tileSize;
        }
        //reset
         x = gp.tileSize/2;
         y = gp.tileSize/2;
         i = 0;

         //draw current life
        while (i < gp.player.life){
            g2d.drawImage(heartMed, x, y, null);
            i++;
            if(i < gp.player.life){
                g2d.drawImage(heartFull, x, y, null);
            }
            i++;
            x += gp.tileSize;

        }

    }
    public void drawTitleScreen() {

        g2d.setColor(new Color(0, 0, 0));
        g2d.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        //title name
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,80F));
        String text = "PROJECT GAME";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*3;

        //shadow
        g2d.setColor(Color.gray);
        g2d.drawString(text, x+5, y+5);

        //main color
        g2d.setColor(Color.WHITE);
        g2d.drawString(text,x,y);

        //image personaje
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2d.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        //menu
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,48F));
        text = "NUEVA PARTIDA";
        x = getXforCenteredText(text);
        y += gp.tileSize*3.5;
        g2d.drawString(text, x, y);
        if(commandNum == 0){
            g2d.drawString(">", x- gp.tileSize, y);
        }

        text = "CONTINUAR";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2d.drawString(text, x, y);
        if(commandNum == 1){
            g2d.drawString(">", x- gp.tileSize, y);
        }

        text = "SALIR";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2d.drawString(text, x, y);
        if(commandNum == 2){
            g2d.drawString(">", x- gp.tileSize, y);
        }
    }

    public void drawPauseScreen() {
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSA";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2d.drawString(text,x,y);

    }
    public void drawDialogueScreen(){

        //windows
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 28F));

        x += gp.tileSize;
        y += gp.tileSize;

        for(String line: currentDialogue.split("\n")){
            g2d.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawCharacterScreen(){
        // create a frame
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int framewidth = gp.tileSize*5;
        final int frameheight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, framewidth, frameheight);
        //text
        g2d.setColor(Color.white);
        g2d.setFont(g2d.getFont().deriveFont(20F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineaHeight = 28;

        // Names
        g2d.drawString("Nivel", textX,textY);
        textY += lineaHeight;
        g2d.drawString("Vida", textX,textY);
        textY += lineaHeight;
        g2d.drawString("Fortaleza", textX,textY);
        textY += lineaHeight;
        g2d.drawString("Destreza", textX,textY);
        textY += lineaHeight;
        g2d.drawString("Ataque", textX,textY);
        textY += lineaHeight;
        g2d.drawString("Defensa", textX,textY);
        textY += lineaHeight;
        g2d.drawString("Experiencia", textX,textY);
        textY += lineaHeight;
        g2d.drawString("Sig Nivel", textX,textY);
        textY += lineaHeight;
        g2d.drawString("Dinero", textX,textY);
        textY += lineaHeight+20;
        g2d.drawString("Arma", textX,textY);
        textY += lineaHeight+15;
        g2d.drawString("Escudo", textX,textY);
        textY += lineaHeight;

        //value

        int tailX = (frameX + framewidth) - 30;
        //reset textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2d.drawString(value, textX, textY);
        textY += lineaHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2d.drawString(value, textX, textY);
        textY += lineaHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2d.drawString(value, textX, textY);
        textY += lineaHeight;


        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2d.drawString(value, textX, textY);
        textY += lineaHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2d.drawString(value, textX, textY);
        textY += lineaHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2d.drawString(value, textX, textY);
        textY += lineaHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2d.drawString(value, textX, textY);
        textY += lineaHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2d.drawString(value, textX, textY);
        textY += lineaHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2d.drawString(value, textX, textY);
        textY += lineaHeight;

        g2d.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY-14, null);
        textY += gp.tileSize;
        g2d.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY-14, null);




    }
    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0,0,0,210);
        g2d.setColor(c);
        g2d.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        g2d.setColor(c);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

    }
    public int getXforCenteredText(String text) {
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public int getXforAlignToRightText(String text, int tailX) {
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = tailX - length;
        return x;
    }
}
