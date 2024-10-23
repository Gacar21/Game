package main;

import entity.NPC_gb;
import monster.Mon_GreenSlime;
import object.Obj_puerta;


public class AssetSetter {
    Gamepanel gp;
    public AssetSetter(Gamepanel gp) {
        this.gp = gp;
    }


    public void setObject(){
//        gp.obj[0] = new Obj_puerta(gp);
//        gp.obj[0].worldX = gp.tileSize*38;
//        gp.obj[0].worldY = gp.tileSize*33;

        gp.obj[1] = new Obj_puerta(gp);
        gp.obj[1].worldX = gp.tileSize*31;
        gp.obj[1].worldY = gp.tileSize*27;

        gp.obj[1] = new Obj_puerta(gp);
        gp.obj[1].worldX = gp.tileSize*25;
        gp.obj[1].worldY = gp.tileSize*19;


    }
    public void setNPC(){
        gp.npc[0] = new NPC_gb(gp);
        gp.npc[0].worldX = gp.tileSize*33;
        gp.npc[0].worldY = gp.tileSize*30;

    }
    public void setMonster(){
        int i = 0;

        gp.monster[i] = new Mon_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*34;
        gp.monster[i].worldY = gp.tileSize*29;
        i++;
        gp.monster[i] = new Mon_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*32;
        gp.monster[i].worldY = gp.tileSize*29;
        i++;
        gp.monster[i] = new Mon_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*34;
        gp.monster[i].worldY = gp.tileSize*32;
        i++;
        gp.monster[i] = new Mon_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*32;
        gp.monster[i].worldY = gp.tileSize*24;
        i++;
        gp.monster[i] = new Mon_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*30;
        gp.monster[i].worldY = gp.tileSize*24;
        i++;
        gp.monster[i] = new Mon_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*28;
        gp.monster[i].worldY = gp.tileSize*23;
        i++;
    }

}
