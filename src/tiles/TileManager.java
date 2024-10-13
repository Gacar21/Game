package tiles;

import main.Gamepanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TileManager {
    Gamepanel gp;
    public Tile[] tiles;
    public int mapTileNum[][];
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus =  new ArrayList<>();


    public TileManager(Gamepanel gp) {
        this.gp = gp;
        //leer tile data file
        InputStream is = getClass().getResourceAsStream("/maps/tileData.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //gettin tile names and collision info from the file
        String line;

        try {
            while ((line = br.readLine()) != null) {
                fileNames.add(line);
                collisionStatus.add(br.readLine());
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        // initialize the tile array based on the filenames size
        tiles = new Tile[fileNames.size()];

        getTileImage();

        //get the maxWorldCol & Row

        is = getClass().getResourceAsStream("/maps/World02.txt");
        br = new BufferedReader(new InputStreamReader(is));

        try {
            String line2 = br.readLine();
            String maxTile[] = line2.split(" ");

            gp.maxWorldCol = maxTile.length;
            gp.maxWorldRow = maxTile.length;
            mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

            br.close();

        }catch (IOException e){
            System.out.println("Exception!");
        }
        loadMap("/maps/World02.txt");

//        getTileImage();
//        loadMap("/maps/World01.txt");
    }

    public void getTileImage(){

        for(int i = 0; i < fileNames.size(); i++){
            String filename;
            boolean collision;

            //get a file name
            filename = fileNames.get(i);
            // get a collision status
            if(collisionStatus.get(i).equals("true")){
                collision = true;
            }
            else {
                collision = false;
            }
            setup(i, filename, collision);
        }
//
        }
    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try {
            tiles[index]= new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName));
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
