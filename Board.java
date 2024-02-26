package snake;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener{
   
    private Image apple;
    private Image dot;
    private Image head;
    
    private final int ALL_DOTS = 2750;
    private final int DOT_SIZE = 10;
    private final int RANDOM_POSITION = 50;
    
    private int apple_x;
       private int apple_y;
 
    private final int x[] = new int[ALL_DOTS];
        private final int y[] = new int[ALL_DOTS];
        
        private boolean leftDirection =false;
        private boolean rightDirection = true;
        private boolean upDirection =false;
        private boolean downDirection =false;

        private boolean inGame = true;
        
    private int scores = 0;
    private int length = 3;
   
    private Timer timer;
    
    Board(){
        
        addKeyListener(new TAdapter());
    setPreferredSize(new Dimension(600,600));
        setFocusable(true);
    
    loadImage();
    initGame();
        
    }
    public void loadImage(){
    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("snake/apple.png"));
    apple = i1.getImage();
    
        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("snake/dot.png"));
            dot = i2.getImage();

            ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("snake/head.png"));
    head = i3.getImage();


    }
    
    public void initGame(){
    length = 3;
    for(int i = 0;i < length; i++){
    y[i] = 150;
    x[i] = 250 - i* DOT_SIZE;
    }
    locateApple();
    
    Timer timer = new Timer(120 , this);
   timer.start();
    }
    
    public void locateApple(){
   int r = (int)( Math.random() * RANDOM_POSITION);
   apple_x = r *DOT_SIZE;
   
       r = (int)( Math.random() * RANDOM_POSITION);
   apple_y = r *DOT_SIZE;

    }
    
    public void paintComponent(Graphics g){
    super.paintComponent(g);
    
    draw(g);
    
    }
    
    public void draw(Graphics g){
        if(inGame){
        g.drawImage(apple, apple_x , apple_y, this);
        
    for(int i = 0;i < length; i++){
    if(i == 0){
         g.drawImage(head, x[i], y[i], this);
    }else{
    
            g.drawImage(dot, x[i], y[i], this);
    }
    }
    
    Toolkit.getDefaultToolkit().sync();
        }else{
        gameOver(g);
        }
    }
    
    public void gameOver(Graphics g){
    String msg = "Game Over!";
    Font font = new Font("SAN_SERIF", Font.BOLD , 20);
    FontMetrics metrices = getFontMetrics(font);
    g.setColor(Color.WHITE);
    g.setFont(font);
    g.drawString(msg,(550 - metrices.stringWidth(msg))/2 ,500/2);
    
    g.setFont(new Font("arial",Font.BOLD,20));
    g.drawString("Space to RESTART", 350,340 );
    }
    
    public void paint(Graphics g){
    g.setColor(Color.blue);
    g.drawRect(18, 10, 550, 30);
    
    g.setFont(new Font("SAN_SERIF",Font.BOLD,18));
    g.drawString("Snake Game",230,32);
    
    g.setColor(Color.WHITE);
    g.drawRect(18,50,550,500);
    g.setColor(Color.black);
    g.fillRect(18, 50, 550, 500);
    g.setColor(Color.black);
    g.setFont(new Font("arial",Font.PLAIN,10));
    g.drawString("Scores: "+scores, 490, 20);
    
    g.setColor(Color.black);
    g.setFont(new Font("arial",Font.PLAIN,10));
    g.drawString("Length: " +length, 490, 35);
    
    }
    
    public void move(){
    for(int i = length; i > 0;i--){
    x[i] = x[i - 1];
    y[i] = y[i - 1];
    }
    
    if(leftDirection){
    x[0] = x[0] - DOT_SIZE;
    }
        if(rightDirection){
    x[0] = x[0] + DOT_SIZE;
    }
        if(upDirection){
    y[0] = y[0] - DOT_SIZE;
    }
        if(downDirection){
    y[0] = y[0] + DOT_SIZE;
    }
    
    x[0] += DOT_SIZE;
    y[0] += DOT_SIZE;

    }
    public void checkApple(){
    if((x[0] == apple_x) && (y[0] == apple_y)){
    length++;
    scores++;
    locateApple();
    }
    }
    public void checkCollision(){
    for(int i = length; i > 0; i--){
    if((i > 4) && (x[0] == x[i]) && (y[0] == y[i])){
    inGame = false;
    }
    }
     
    if(y[0] >=500 ){
    inGame = false;
    }
    
        if(x[0] >=550 ){
    inGame = false;
    }

            if(y[0] < 50){
    inGame = false;
    }

                if(x[0] < 18 ){
    inGame = false;
    }

                
                
    }
    
    public void actionPerformed(ActionEvent ae){
        checkApple();
        
        checkCollision();
        
    move();
    
    
    repaint();
    }
    public class TAdapter extends KeyAdapter{
   @Override
        public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_LEFT &&(!rightDirection)){
        leftDirection = true;
        upDirection = false;
        downDirection = false;
        
        }
         if(key == KeyEvent.VK_RIGHT &&(!leftDirection)){
        rightDirection = true;
        upDirection = false;
        downDirection = false;
        
        }
        if(key == KeyEvent.VK_UP &&(!downDirection)){
        upDirection = true;
        leftDirection = false;
        downDirection = false;
        
        }
        if(key == KeyEvent.VK_DOWN &&(!upDirection)){
        downDirection = true;
        leftDirection = false;
        rightDirection = false;
        
        }
       if(key == KeyEvent.VK_SPACE){
        scores =0;
        length = 3;
       repaint();
        }

        }
    }
}
