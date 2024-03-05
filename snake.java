package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.Graphics;

public class snake extends JPanel implements ActionListener, KeyListener {


    static final int screenWidth =600;
    static final int screenHeight=600;
    static final int Size =30;
    static final int delay=250;
    final int [] x=new int[screenHeight*screenWidth];
    final int [] y=new int[screenHeight*screenWidth];

    int Score=0;
    int BodyPart = 2;
    int Food_Eat;
    int FoodX;
    int FoodY;

    int currentDirectionX =1 ;
    int currentDirectionY = 0;
    Boolean running =false;
    Random random;
    Timer timer;

    ImageIcon snakeImage;  // New variable to store the snake image


public snake()
{
random =new Random();
this.setPreferredSize(new Dimension(screenWidth,screenHeight));
this.setBackground(Color.darkGray);
this.setFocusable(true);
this.addKeyListener(this);
StartGame();


}

public  void StartGame()
{
    createFood();
    running=true;
    timer=new Timer(delay,this);
    timer.start();

}
public void paintComponent(Graphics g)
{
    super.paintComponent(g);
    draw(g);
}
public void draw(Graphics g) {
    if (running) {
//        for (int i = 0; i < screenHeight; i++) {
//            g.drawLine(i * Size, 0, i * Size, screenHeight);
//            g.drawLine(0, i * Size, screenHeight, i * Size);
//        }


        g.setColor(Color.yellow);
        g.fillOval(FoodX, FoodY, Size, Size);
        for (int i = 0; i < BodyPart; i++) {
            g.setColor(Color.red);
            g.fillRect(x[i], y[i], Size, Size);
        }
    }
        else{
            GameOver(g);
        }
    }

public void move()
{
for (int i=BodyPart;i>0;i--)
{
    x[i]= x[i-1];
    y[i]= y[i-1];
}
x[0]=x[0]+currentDirectionX * Size;
y[0]=y[0]+currentDirectionY* Size;
}
public void createFood()
{
FoodX = random.nextInt(((int)screenWidth/Size))*Size;
FoodY = random.nextInt(((int)screenHeight/Size))*Size ;
}
public void TakeFood()
{
          if(x[0]==FoodX && y[0]==FoodY)
          {
              createFood();
              Score++;
              BodyPart++;
          }
}
public void Body_collision() {
    for (int i = BodyPart; i > 0; i--) {
        if (x[0] == x[i] && y[0] == y[i]) {
            running = false;
        }
        if(x[0]<0)
        {
            running=false;
        }
        if(x[0]>=screenWidth)
        {
            running=false;
        }
        if(y[0]<0)
        {
            running=false;
        }
        if(y[0]>=screenHeight)
        {
            running=false;
        }
    }
    if(!running)
    {
        timer.stop();
    }
}
public void GameOver(Graphics g)
{
    g.setColor(Color.blue);
    g.setFont(new Font("Serif",Font.BOLD,75));
    FontMetrics met =getFontMetrics(g.getFont());
    g.drawString("Score : "+Score,screenHeight/5,screenHeight/3);
    g.drawString("Game Over",screenHeight/5,screenHeight/2);
}
    @Override
    public void actionPerformed(ActionEvent e) {
if(running)
{
    move();
    TakeFood();
    Body_collision();
}
repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
  int key =e.getKeyCode();
  switch (key)
  {
      case KeyEvent.VK_LEFT :
          if(currentDirectionX != 1)
          {
              currentDirectionX = -1;
              currentDirectionY = 0;
          }
          break;
      case KeyEvent.VK_RIGHT:
          if(currentDirectionX!=-1)
          {
              currentDirectionX=1;
              currentDirectionY=0;
          }
          break;
      case KeyEvent.VK_UP :
          if(currentDirectionY!=1)
          {
              currentDirectionY=-1;
              currentDirectionX=0;
          }
          break;
      case KeyEvent.VK_DOWN :
          if(currentDirectionY!=-1)
          {
              currentDirectionY=1;
              currentDirectionX=0;
          }
          break;
  }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
