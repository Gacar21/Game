package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sonido {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sonido(){
        soundURL[0] = getClass().getResource("/musica/fondo.wav");
        soundURL[1] = getClass().getResource("/musica/abrir.wav");
        soundURL[2] = getClass().getResource("/musica/agarrar.wav");
        soundURL[3] = getClass().getResource("/musica/puerta.wav");
        soundURL[4] = getClass().getResource("/musica/powerup.wav");
        soundURL[5] = getClass().getResource("/musica/Attack.wav");
        soundURL[6] = getClass().getResource("/musica/recibirdaño.wav");
        soundURL[7] = getClass().getResource("/musica/espada.wav");


    }
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){

        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
