package tiles;

import main.Gamepanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    Gamepanel gp;
    public Tile[] tiles;
    public int mapTileNum[][];


    public TileManager(Gamepanel gp) {
        this.gp = gp;
        tiles = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/World01.txt");
    }

    public void getTileImage(){
        setup(0,"tierra",false);
        setup(1,"agua",true);
        setup(2,"Piedra",true);
        setup(3,"flores",false);
        setup(4,"hongo",true);
        setup(5,"arena",false);
        setup(6,"camino",false);
        setup(7,"muro",true);
        }
    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try {
            tiles[index]= new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tiles[index].image = uTool.scaleImage(tiles[index].image,gp.tileSize, gp.tileSize);
            tiles[index].collision = collision;

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){

        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e){

        }
    }
    public void draw(Graphics2D g2d){

        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if(     worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {

                g2d.drawImage(tiles[tileNum].image, screenX, screenY, null);
            }

            worldCol++;


            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;

            }

        }



    }
}
