package Snake3;

import Engine2D.ShapesObject;

import java.awt.Color;

public class Snake extends ShapesObject {
    public SnakeElem Head;
    public SnakeElem Tail;
    public char NextDir;
    public int Size;
    public int score;
    public Color color;
    public Snake(int x, int y, int Size, Color color){
        super("Snake", 2);
        this.color = color;
        NextDir = 'r';
        score = 0;
        this.add(new SnakeElem('r', x, y, Size/2, color));
        Tail = Head = (SnakeElem) body.get(0);
        this.Size = Size;
    }
    public void add() {
        switch (Tail.Dir) {
            case 'd' -> Tail = (SnakeElem) this.add(new SnakeElem(Tail.Dir, Tail.position.x, Tail.position.y - Size, Size/2, color));
            case 'u' -> Tail = (SnakeElem) this.add(new SnakeElem(Tail.Dir, Tail.position.x, Tail.position.y + Size, Size/2, color));
            case 'r' -> Tail = (SnakeElem) this.add(new SnakeElem(Tail.Dir, Tail.position.x - Size, Tail.position.y, Size/2, color));
            case 'l' -> Tail = (SnakeElem) this.add(new SnakeElem(Tail.Dir, Tail.position.x + Size, Tail.position.y, Size/2, color));
        }
    }
    public boolean move(){
        Head.Dir = NextDir;
        char Dir = Head.Dir;
        for(var i: this.body) {
            SnakeElem item = (SnakeElem) i;
            if(Head.position.x == item.position.x && Head.position.y == item.position.y && item != Head) {
                return false;
            }
            item.prev_Dir = item.Dir;
            switch (item.Dir) {
                case 'u' -> item.move(0, -Size);
                case 'd' -> item.move(0, Size);
                case 'l' -> item.move(-Size, 0);
                case 'r' -> item.move(Size, 0);
            }
            item.Dir = Dir;
            Dir = item.prev_Dir;
        }
        return true;
    }
}