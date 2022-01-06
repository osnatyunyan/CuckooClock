import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class CuckooClock extends JPanel {
    private JPanel West,East,South,North;
    private Image image;
    private int counter;
    private JFrame frame;

    public CuckooClock() throws IOException {
        counter=1;
        frame=new JFrame("CuckooClock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        image= ImageIO.read(new File("Bird.jpg"));
        East = new JPanel();
        East.setPreferredSize( new Dimension(40,310));
        West = new JPanel();
        West.setPreferredSize(new Dimension(40,310) );
        South = new JPanel();
        South.setPreferredSize( new Dimension(350,50) );
        North = new JPanel();
        North.setPreferredSize( new Dimension(350,30) );
        this.setPreferredSize(new Dimension(300,400));
        frame.add(this, BorderLayout.CENTER);
        frame.add(West,BorderLayout.WEST);
        frame.add(East,BorderLayout.EAST);
        frame.add(North,BorderLayout.NORTH);
        frame.add(South,BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
    public void paintClock(Graphics graphics){
        graphics.setColor(Color.green);
        graphics.fillRect(50,100,200,150);
    }
    public void paintRoof(Graphics graphics){
        graphics.setColor(Color.red);
        int x[]={0,150,300};
        int y[]={100,0,100};
        graphics.fillPolygon(x,y,3);
    }

    public void paintOvalLeft(Graphics graphics){
        graphics.setColor(Color.black);
        graphics.fillOval(40,360,30,30);
        graphics.drawLine(150,250,55,375);
    }

    public void paintOvalRight(Graphics graphics){
        graphics.setColor(Color.black);
        graphics.fillOval(235,360,30,30);
        graphics.drawLine(150,250,245,375);
    }


    public void paintBird(Graphics graphics){
        graphics.drawImage(image,125,115,50,50,this);
    }

    public void paintComponent(Graphics graphics){
        paintClock(graphics);
        paintRoof(graphics);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(counter%2==0){
            paintOvalLeft(graphics);
            }
        if (counter%2!=0){
            paintOvalRight(graphics);
            }
        if(counter%8==0){
            paintBird(graphics);
                Clip clip = null;
                try {
                    clip = AudioSystem.getClip();
                    clip.open( AudioSystem.getAudioInputStream( new File("CuckooCall.wav")));
                }
                catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
                clip.start();
            }
        if (counter%9==0){
            paintClock(graphics);
            }
        counter++;
        frame.repaint();
        }
}
