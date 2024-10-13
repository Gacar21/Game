package main;

import object.Obj_llave;

import java.awt.*;

import java.text.DecimalFormat;

public class UI {
    Gamepanel gp;
    Graphics2D g2d;
    Font arial_40, arial_80B;
//    BufferedImage keyImagen;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(Gamepanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 40);
//       Obj_llave key = new Obj_llave(gp);
//       //keyImagen = key.image;


    }
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2d) {
        this.g2d = g2d;
        g2d.setFont(arial_40);
        g2d.setColor(Color.WHITE);

        if(gp.gameState == gp.playState){
            //do playstate stuff later

        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }

    }
    public void drawPauseScreen() {
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSA";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2d.drawString(text,x,y);

    }
    public int getXforCenteredText(String text) {
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
