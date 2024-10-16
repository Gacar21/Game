package main;

import java.awt.*;

public class EventHandler {
    Gamepanel gp;
   EventRect eventRect[][];

   int previousEventX, previousEventY;
   boolean canTouchEvent = true;

    public EventHandler(Gamepanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }



    }
    public void checkEvent() {

        //check if the player character is more than 1 tile away from the last events
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }
        if(canTouchEvent == true) {
            //eventos perder vida y curarse:
//            if(hit(30,28,"left")== true){
//                //event happens
//                damagePit(30,28,gp.dialogueState);
//            }
//
//            if(hit(31,26,"any")== true){
//                //event happens
//                damagePit(31,26,gp.dialogueState);
//            }
//
//            if(hit(39,19,"down")== true){
//                heallingPool(39,19,gp.dialogueState);
//            }
        }

        //teleport
//        if(hit(37,14,"down")== true){
//            teleport(gp.dialogueState);
//        }

    }



    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
            if(gp.player.direction.equals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }
    //eventos, daño, vida, teletransportarse
//    public void teleport(int gameState){
//        gp.gameState = gameState;
//        gp.ui.currentDialogue = "Te estas teletransportando";
//        gp.player.worldX = gp.tileSize*41;
//        gp.player.worldY = gp.tileSize*30;
//    }
//    public void damagePit(int col, int row, int gameState){
//        gp.gameState = gameState;
//        gp.ui.currentDialogue = "te heriste!";
//        gp.player.life -= 1;
////        eventRect[col][row].eventDone = true;
//        canTouchEvent = false;
//
//    }
//    public void heallingPool(int col, int row,int gameState){
//        if(gp.keyHandler.jPressed == true){
//            gp.gameState = gameState;
//            gp.ui.currentDialogue = "Bebiste del agua magica alv";
//            gp.player.life = gp.player.maxLife;
//
//        }
//        gp.keyHandler.jPressed = false;
//    }
}
