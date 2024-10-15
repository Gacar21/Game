package main;

import java.awt.*;

public class EventHandler {
    Gamepanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(Gamepanel gp) {
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;


    }
    public void checkEvent() {
//eventos perder vida y curarse:
//        if(hit(30,28,"left")== true){
//            //event happens
//            damagePit(gp.dialogueState);
//        }
//        if(hit(39,19,"down")== true){
//            heallingPool(gp.dialogueState);
//        }

        //teleport
//        if(hit(37,14,"down")== true){
//            teleport(gp.dialogueState);
//        }

    }



    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)) {
            if(gp.player.direction.equals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }
    //eventos, daño, vida, teletransportarse
//    public void teleport(int gameState){
//        gp.gameState = gameState;
//        gp.ui.currentDialogue = "Te estas teletransportando";
//        gp.player.worldX = gp.tileSize*41;
//        gp.player.worldY = gp.tileSize*30;
//    }
//    public void damagePit(int gameState){
//        gp.gameState = gameState;
//        gp.ui.currentDialogue = "te heriste!";
//        gp.player.life -= 1;
//    }
//    public void heallingPool(int gameState){
//        if(gp.keyHandler.jPressed == true){
//            gp.gameState = gameState;
//            gp.ui.currentDialogue = "Bebiste del agua magica alv";
//            gp.player.life = gp.player.maxLife;
//
//        }
//        gp.keyHandler.jPressed = false;
//    }
}
