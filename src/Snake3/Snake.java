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
        this.NextDir = 'r';
        this.score = 0;
        this.Size = Size;
        Tail = Head = (SnakeElem) this.add(new SnakeElem('r', x, y, Size/2, Color.RED));
    }
    public void add() {
        switch (Tail.Dir) {
            case 'd' -> Tail = (SnakeElem) this.add(new SnakeElem(Tail.Dir, Tail.position.x, Tail.position.y - Size, Size/2, color));
            case 'u' -> Tail = (SnakeElem) this.add(new SnakeElem(Tail.Dir, Tail.position.x, Tail.position.y + Size, Size/2, color));
            case 'r' -> Tail = (SnakeElem) this.add(new SnakeElem(Tail.Dir, Tail.position.x - Size, Tail.position.y, Size/2, color));
            case 'l' -> Tail = (SnakeElem) this.add(new SnakeElem(Tail.Dir, Tail.position.x + Size, Tail.position.y, Size/2, color));
        }
    }
    public boolean moveSmothly(){
        for(var i: this.body) {
            SnakeElem item = (SnakeElem) i;
            switch (item.Dir) {
                case 'u' -> item.move(0, -1);
                case 'd' -> item.move(0, 1);
                case 'l' -> item.move(-1, 0);
                case 'r' -> item.move(1, 0);
            }
        }
        if(this.body.get(0).position.x % Size == 0 && this.body.get(0).position.y % Size == 0)
            return true;
        else return false;
    }
    public boolean move(){
        Head.Dir = NextDir;
        for(int i = 0; i < this.body.size()-1; i++) {
            SnakeElem item = (SnakeElem) this.body.get(i);
            SnakeElem nextItem = (SnakeElem) this.body.get(i+1);
            if(Head.position.x == item.position.x && Head.position.y == item.position.y && item != Head) {
                return false;
            }
            char Dir = item.prev_Dir;
            item.prev_Dir = item.Dir;
            nextItem.Dir = Dir;
        }
        SnakeElem lastItem = (SnakeElem) this.body.get(this.body.size()-1);
        lastItem.prev_Dir = lastItem.Dir;
        return true;
    }
}