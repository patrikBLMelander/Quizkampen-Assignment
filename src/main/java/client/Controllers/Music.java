package client.Controllers;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;

public class Music {

    Clip play;

    public void loadGameMusic(String musicPath){
        try {
            File music = new File(musicPath);

            if (music.exists()) {
                AudioInputStream readMusic = AudioSystem.getAudioInputStream(music);
                play = AudioSystem.getClip();
                play.open(readMusic);

                FloatControl gainControl =
                        (FloatControl) play.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-15.0f);

            } else {
                JOptionPane.showMessageDialog(null, "Can't find background music file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMusic(){
        play.start();
        play.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopMusic(){
        play.stop();
    }
}