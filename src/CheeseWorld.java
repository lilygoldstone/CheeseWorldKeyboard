//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;

/***
 * Step 0 for keyboard control - Import
 */
import java.awt.event.*;

/***
 * Step 1 for keyboard control - implements KeyListener
 */
public class CheeseWorld implements Runnable, KeyListener {

    //Variable Definition Section

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 650;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    //Declare the variables needed for images
    public Image cheesePic;
    public Tube[] MorePipes;

    public Tube[] Bottom;


    //    public Image MousePic;
    public Image BirdPic;

    public Image backround;

    public Image Bird;

    public Image GreenPic;

    public Image OtherTubePic;

    public Image OverPic;
public Image BeginingPic;
    //Declare the character objects
//    public Mouse Mouseie;
    public Tube theTube;
    public FlappyBird user;

    public Tube bottomTube;

public boolean GameOver;
public boolean GameStart;
public boolean GameIsPlaying;
public int score;






    // Main method definition
    public static void main(String[] args) {
        CheeseWorld myApp = new CheeseWorld();   //creates a new instance of the game
        new Thread(myApp).start();               //creates a threads & starts up the code in the run( ) method
    }

    // Constructor Method - setup portion of the program
    // Initialize your variables and construct your program objects here.
    public CheeseWorld() {

        setUpGraphics();

        /***
         * Step 2 for keyboard control - addKeyListener(this) to the canvas
         */
        canvas.addKeyListener(this);

        //load images
        cheesePic = Toolkit.getDefaultToolkit().getImage("cheese.gif");
        GreenPic = Toolkit.getDefaultToolkit().getImage("Tubes.png");
        Bird = Toolkit.getDefaultToolkit().getImage("jerry.gif");
        BirdPic = Toolkit.getDefaultToolkit().getImage("Bird.png");
        backround = Toolkit.getDefaultToolkit().getImage("FlappyBackround.png");
        OtherTubePic = Toolkit.getDefaultToolkit().getImage("upsideDown.png");
        OverPic = Toolkit.getDefaultToolkit().getImage("GameOver.jpeg");
        BeginingPic = Toolkit.getDefaultToolkit().getImage("Starting.png");


        MorePipes=new Tube[10];
        for(int x=0;x<10; x++) {
            MorePipes[x] = new Tube(x*200, (int) (350 + (Math.random() * 50)));
        }
            Bottom=new Tube[10];
            for(int x=0;x<10; x++){
                Bottom[x]=new Tube (x*200,(int)(-300+(Math.random()*5)));
        }


        //create (construct) the objects needed for the game
//        Mouseie = new Mouse(200, 300, 4, 4, Bird);
        theTube = new Tube(200, 350, 3, -4, GreenPic);
        user = new FlappyBird(250, 250, 0, 0, BirdPic);
        bottomTube= new Tube (200, -300, 3, -4, OtherTubePic);

    } // CheeseWorld()


//*******************************************************************************
//User Method Section

    // main thread
    // this is the code that plays the game after you set things up
    public void moveThings() {
//        Mouseie.move();
            theTube.move();
            bottomTube.move();
            user.move();
        for(int x=0;x<10; x++){
            //System.out.println("here is my forest");
            //   System.out.println("tree:"+ x +"age:"+ MorePipes[x].xpos+ "height:" +MorePipes[x].height);
            MorePipes[x].move();
            Bottom[x].move();
        }
        }

//public void startGame(){
//        for(int x=0;x<pipes.length;x++);
//    log[x].xpos=10;
//        log[x].dx=-2;

//}

    public void checkIntersections() {


    }

    public void collision() {
        if (user.rec.intersects(theTube.rec) && user.isIntersecting == false) {
            user.isIntersecting = true;
            user.isAlive = false;
            System.out.println("crash!");
            GameOver=true;
        }
        if (user.rec.intersects(bottomTube.rec) && user.isIntersecting == false) {
            user.isIntersecting = true;
            user.isAlive = false;
            System.out.println("crash!");
            GameOver=true;
        }
        for(int x=0;x<10; x++){
            if (user.rec.intersects(Bottom[x].rec) && user.isIntersecting == false) {
                user.isIntersecting = true;
                user.isAlive = false;
                System.out.println("crash!");
                GameOver=true;
            }
            if (user.rec.intersects(MorePipes[x].rec) && user.isIntersecting == false) {
                user.isIntersecting = true;
                user.isAlive = false;
                System.out.println("crash!");
                GameOver=true;
            }
        }
        score=score+1;

    }


    public void run() {
        while (true) {
            moveThings();           //move all the game objects
            checkIntersections();   // check character crashes
            render();
            collision();// paint the graphics
            pause(20);         // sleep for 20 ms
        }
    }

    //paints things on the screen using bufferStrategy
    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw characters to the screen
        g.drawImage(backround, 0, 0, 1000, 700, null);
//        g.drawImage(theTube.pic, theTube.xpos, theTube.ypos, theTube.width, theTube.height, null);
////        g.drawImage(Mouseie.pic, Mouseie.xpos, Mouseie.ypos, Mouseie.width, Mouseie.height, null);
//        g.drawImage(theTube.pic, theTube.xpos, theTube.ypos, theTube.width, theTube.height, null);
//        g.drawRect(theTube.xpos,theTube.ypos, theTube.width, theTube.height);
//        g.drawRect(bottomTube.xpos,bottomTube.ypos, bottomTube.width, bottomTube.height);
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.drawString("SCORE: "+ score, 800, 50);






        for(int x=0;x<10; x++){
            g.drawImage(GreenPic, MorePipes[x].xpos, MorePipes[x].ypos, MorePipes[x].width, MorePipes[x].height, null);
        }
            for(int x=0;x<10; x++){
                g.drawImage(OtherTubePic, Bottom[x].xpos, Bottom[x].ypos, Bottom[x].width, Bottom[x].height, null);
            }

        if(user.isAlive==true) {
            g.drawImage(user.pic, user.xpos, user.ypos, user.width, user.height, null);}

//            g.drawImage(bottomTube.pic, bottomTube.xpos, bottomTube.ypos, bottomTube.width, bottomTube.height, null);
if(GameOver==true){
    g.drawImage(OverPic, 0, 0, 1000, 700, null);
}
        if (GameStart==true){
            g.drawImage(OverPic, 0, 0, 1000, 700, null);
        }
            g.dispose();
            bufferStrategy.show();

    }

    /***
     * Step 3 for keyboard control - add required methods
     * You need to have all 3 even if you aren't going to use them all
     */
    public void keyPressed(KeyEvent event) {
        //This method will do something whenever any key is pressed down.
        //Put if( ) statements here
        char key = event.getKeyChar();     //gets the character of the key pressed
        int keyCode = event.getKeyCode();  //gets the keyCode (an integer) of the key pressed
        System.out.println("Key Pressed: " + key + "  Code: " + keyCode);

        if (keyCode == 68) { // d
            user.right = true;
        }
        if (keyCode == 65) { // a
            user.left = true;
        }

        if (keyCode == 83) { // s
            user.down = true;
        }
        if (keyCode == 87) { // w
            user.up = true;
        }
    }//keyPressed()

    public void keyReleased(KeyEvent event) {
        char key = event.getKeyChar();
        int keyCode = event.getKeyCode();
        //This method will do something when a key is released
        if (keyCode == 68) { // d
            user.right = false;
        }
        if (keyCode == 65) { // a
            user.left = false;
        }
        if (keyCode == 83) { // s
            user.down = false;
        }
        if (keyCode == 87) { // w
            user.up = false;
        }

    }//keyReleased()

    public void keyTyped(KeyEvent event) {
        // handles a press of a character key (any key that can be printed but not keys like SHIFT)
        // we won't be using this method, but it still needs to be in your program
    }//keyTyped()


    //Graphics setup method
    public void setUpGraphics() {
        frame = new JFrame("CheeseWorld");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

}
