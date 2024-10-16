package main;

import entity.NPC_gb;


public class AssetSetter {
    Gamepanel gp;
    public AssetSetter(Gamepanel gp) {
        this.gp = gp;
    }


    public void setObject(){

    }
    public void setNPC(){
        gp.npc[0] = new NPC_gb(gp);
        gp.npc[0].worldX = gp.tileSize*39;
        gp.npc[0].worldY = gp.tileSize*30;
    }
}
