import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements ActionListener, KeyListener {

    private Ball ball = new Ball();
    private Paddle paddle = new Paddle();
    private Paddle paddleL = new Paddle();
    private int width, height;

    public GamePlay(int width, int height) {
        this.width = width;
        this.height = height;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Timer timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, width, height);
        g.fillOval(ball.x, ball.y, ball.width, ball.height);
        g.fillRect(width - 50, paddle.y, paddle.width, paddle.height);
        g.fillRect(50, paddleL.y, paddleL.width, paddleL.height);
    }

    public void actionPerformed(ActionEvent e) {
        if (ball.dirX == 1) {
            ball.x += ball.spdX;
        } else ball.x -= ball.spdX;
        if (ball.dirY == 1) {
            ball.y -= ball.spdY;
        } else ball.y += ball.spdY;
        if (paddle.dir == 1) {
            paddle.y += paddle.spd;
        } else if (paddle.dir == -1) {
            paddle.y -= paddle.spd;
        }
        if (ball.y + ball.height >= height || ball.y <= 0) {
            ball.dirY = -ball.dirY;
        }
        if (paddle.y + paddle.height >= height) {
            paddle.y = height - paddle.height;
        } else if (paddle.y <= 0) {
            paddle.y = 0;
        }
        if(paddleL.y+paddleL.height/2 < ball.y+ball.height/2){
            paddleL.dir = 1;
        } else if(paddleL.y+paddleL.height/2 > ball.y+ball.height/2){
            paddleL.dir = -1;
        } else paddleL.dir = 0;
        if(paddleL.dir == 1){
            paddleL.y += paddleL.spd;
        } else if(paddleL.dir == -1){
            paddleL.y -= paddleL.spd;
        }
        if (paddleL.y + paddleL.height >= height) {
            paddleL.y = height - paddleL.height;
        } else if (paddleL.y <= 0) {
            paddleL.y = 0;
        }
        if(ball.x < 0 || ball.x > width){
            ball.x = width/2;
        }
        if (new Rectangle(ball.x, ball.y, ball.width, ball.height).intersects(new Rectangle(width - 50, paddle.y, paddle.width, paddle.height))) {
            ball.dirX = -ball.dirX;
        } else if (new Rectangle(ball.x, ball.y, ball.width, ball.height).intersects(new Rectangle(50, paddleL.y, paddleL.width, paddleL.height))) {
            ball.dirX = -ball.dirX;
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.dir = -1;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.dir = 1;
        }
    }

    public void keyReleased(KeyEvent e) {
        paddle.dir = 0;
    }
}