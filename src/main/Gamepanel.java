package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tiles.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class Gamepanel extends JPanel implements Runnable{

    // configuracion de pantalla
    final int originalTileSize = 16; //16 x 16 title
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48 x 48 title
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    // 48 x 16 = 768  ; 48 x 12 = 576 title scale

    //configuracion de mapa

    public  int maxWorldCol;
    public  int maxWorldRow;



    // FPS
    int FPS =60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler(this);
    Sonido music = new Sonido();
    Sonido se = new Sonido();
    public CollisionCheckout cChecker = new CollisionCheckout(this);
    public AssetSetter aSettter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //entity and object
    public Player player = new Player(this,keyHandler);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];

    //GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;



    public Gamepanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }
    public void setupGame(){
        aSettter.setObject();
        aSettter.setNPC();
        playMusic(0);
        gameState = playState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
//    public void run() {
//
//     double drawIntervalo = 1000000000 / FPS; // 0.16666666 segundos
//     double nextDrawTime = System.nanoTime() + drawIntervalo;
//
//    while(gameThread != null) {
//
//
//        // update: actualizacion de posicion del personaje
//        update();
//
//
//
//        // draw : dibujar en pantalla a la personaje y su actualizacion
//        repaint();
//
//
//
//        try {
//            double romainingTime = nextDrawTime - System.nanoTime();
//            romainingTime = romainingTime/1000000;
//
//            if(romainingTime < 0){
//                romainingTime = 0;
//            }
//
//            Thread.sleep((long)romainingTime);
//
//            nextDrawTime += drawIntervalo;
//
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//
//    }

    //delta mode
    public void run(){

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currenTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){

            currenTime = System.nanoTime();
            delta += (currenTime - lastTime) / drawInterval;
            timer += currenTime - lastTime;
            lastTime = currenTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }
    public void update(){
        if(gameState == playState){
            //jugador
            player.update();
            //npc
            for (int i = 0; i < npc.length ; i++) {
                if(npc[i] != null){
                    npc[i].update();
                    }
            }

        }
        if(gameState == pauseState){
            // nothhing
        }

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //debug
        long drawStart = 0;
        if(keyHandler.checkDrawTime == true){
            drawStart = System.nanoTime();
        }


        //tile
        tileM.draw(g2d);
        //object
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2d,this);
            }
        }
        //NPC
        for(int i = 0; i < npc.length; i++){
            if(npc[i] != null){
                npc[i].draw(g2d);
            }
        }


        //player
        player.draw(g2d);

        //ui
        ui.draw(g2d);
        //debug
        if(keyHandler.checkDrawTime == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2d.setColor(Color.WHITE);
            g2d.drawString("Draw Time: " + passed, 10,400 );
            System.out.println("Draw Time: " + passed);
        }



        g2d.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();

    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();

    }
}
