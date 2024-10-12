package main;

import object.Obj_llave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    Gamepanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImagen;
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
        Obj_llave key = new Obj_llave();
        keyImagen = key.image;


    }
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics g2d) {
        if(gameFinished == true){

            g2d.setFont(arial_40);
            g2d.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text = "ENCONTRASTE EL TESORO!";
            textLength = (int)g2d.getFontMetrics().getStringBounds(text,g2d).getWidth();
             x = gp.screenWidth/2 - textLength/2;
             y = gp.screenHeight/2 - (gp.tileSize*3);
             g2d.drawString(text,x,y);

            text = "Tu tiempo es: " + dFormat.format(playTime) + "!";
            textLength = (int)g2d.getFontMetrics().getStringBounds(text,g2d).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2d.drawString(text,x,y);

             g2d.setFont(arial_80B);
             g2d.setColor(Color.yellow);

            text = "JUEGO TERMINADO";
            textLength = (int)g2d.getFontMetrics().getStringBounds(text,g2d).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2d.drawString(text,x,y);



            gp.gameThread = null;

        }else{
            g2d.setFont(arial_40);
            g2d.setColor(Color.WHITE);
            g2d.drawImage(keyImagen ,gp.tileSize/2, gp.tileSize/2, gp.tileSize,gp.tileSize,null);
            g2d.drawString("x = " + gp.player.hasKey, 74, 65);

            //time
            playTime += (double)1/60;
            g2d.drawString("Tiempo: " + dFormat.format(playTime), gp.tileSize*11, 65 );

            //Mensaje
            if(messageOn==true) {
                g2d.setFont(g2d.getFont().deriveFont(30F));
                g2d.drawString(message, gp.tileSize/2, gp.tileSize*5);

                messageCounter++;

                if(messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

}
