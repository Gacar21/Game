package main;

import entity.Player;
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

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int wordWidth = tileSize * maxWorldCol;
    public final int wordHeight = tileSize * maxWorldRow;


    // FPS
    int FPS =60;

    TileManager tileM = new TileManager(this);


    KeyHandler keyHandler = new KeyHandler();

    Thread gameThread;
    public CollisionCheckout cChecker = new CollisionCheckout(this);
    public Player player = new Player(this,keyHandler);


    public Gamepanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

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
        player.update();

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        tileM.draw(g2d);

        player.draw(g2d);

        g2d.dispose();
    }
}
