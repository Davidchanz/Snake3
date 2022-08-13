package Snake3;

import Engine2D.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Main {
    public static int BorderX = 60;
    public static int BorderY = 40;
    public static int size = 20;
    public static int WIDTH = size * BorderX;
    public static int HEIGHT = size * BorderY;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Random rand = new Random();
        boolean game = true;

        Scene scene = new Scene(WIDTH, HEIGHT);
        scene.setBorder(size, Color.GRAY);
        scene.setBorderVisible(true);

        Snake snake = new Snake(0, 0, size, Color.GREEN);
        for(int i = 0; i < 10; ++i)
            snake.add();

        Apple apple = new Apple((rand.nextInt(BorderX-5)+3)*size, (rand.nextInt(BorderY-5)+3)*size, size/2);
        scene.add(snake);
        scene.add(apple);


        JFrame frame=new JFrame("Snake 3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scene);
        frame.setSize(WIDTH+100,HEIGHT+100);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                        if(snake.Head.Dir != 'd') snake.NextDir = 'u';
                        break;
                    case KeyEvent.VK_UP:
                        if(snake.Head.Dir != 'u') snake.NextDir = 'd';
                        break;
                    case KeyEvent.VK_LEFT:
                        if(snake.Head.Dir != 'r') snake.NextDir = 'l';
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(snake.Head.Dir != 'l') snake.NextDir = 'r';
                        break;
                }
            }
        });

        Toolkit tk = Toolkit.getDefaultToolkit();
        long summ_time = 0;
        long end_time = 0;
        long start_time = 0;

        for(;;){
            if(!game) break;
            end_time = System.currentTimeMillis();
            summ_time += end_time - start_time;
            int delay = 100 - snake.score;
            if(delay <= 0) delay = 20;
            delay = 100;
            if(summ_time >= delay){
                tk.sync();

                if(!snake.move()){
                    System.out.println(snake.score);
                    game = false;
                    continue;
                }
                if(snake.Head.position.x > scene.MaxX-size || snake.Head.position.y > scene.MaxY-size || snake.Head.position.x < scene.MinX+size || snake.Head.position.y < scene.MinY+size){
                    System.out.println(snake.score);
                    game = false;
                    continue;
                }
                if(snake.Head.position.x == apple.position.x && snake.Head.position.y == apple.position.y) {
                    snake.score += apple.score;
                    snake.add();
                    apple.Spawn((rand.nextInt(BorderX-5)+3)*size, (rand.nextInt(BorderY-5)+3)*size);
                }
                summ_time = 0L;
                scene.repaint();
            }
            start_time = end_time;
        }
    }
}