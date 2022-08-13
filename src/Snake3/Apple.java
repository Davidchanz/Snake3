package Snake3;

import Engine2D.AbstractShape;
import Engine2D.Circle;
import Engine2D.Scene;
import Engine2D.ShapesObject;
import UnityMath.Vector2;

import java.awt.*;
import java.util.Random;

class Apple extends ShapesObject {
    public int score;
    public boolean Eated;
    public int Size;

    Apple(int x, int y, int size){
        super("Apple", 2);
        var dir = new Vector2(x, y);
        Scene.fromSceneCoord(dir);
        this.add(new Circle(size, new Vector2(dir), Color.RED));
        this.position = new Vector2(dir);
        Size = size;
        Eated = false;
        score = 1;
    }

    public void Spawn(int x, int y){
        Random rand = new Random();
        var dir = new Vector2(x,y);
        Scene.fromSceneCoord(dir);
        var tmp = new Vector2(this.position);
        this.move(dir.sub(tmp));
    }
}