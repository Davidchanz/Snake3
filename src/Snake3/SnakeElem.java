package Snake3;

import Engine2D.AbstractShape;
import Engine2D.Circle;
import Engine2D.Rectangle;
import Engine2D.Triangle;
import UnityMath.Vector2;

import java.awt.Color;

public class SnakeElem extends Triangle {
    public char Dir;
    public char prev_Dir;

    public SnakeElem(char Dir, float x, float y, int size, Color color){
        super(size, new Vector2(x, y) ,color);
        this.Dir = Dir;
        this.prev_Dir = Dir;
        switch (Dir){
            case 'u' -> this.angZ = -180;
            case 'd' -> this.angZ = 0;
            case 'l' -> this.angZ = 90;
            case 'r' -> this.angZ = -90;
        }
    }
    public void move(float x, float y){
        switch (Dir){
            case 'u' -> this.angZ = -180;
            case 'd' -> this.angZ = 0;
            case 'l' -> this.angZ = 90;
            case 'r' -> this.angZ = -90;
        }
        this.position.add(new Vector2(x, y));
    }
}
