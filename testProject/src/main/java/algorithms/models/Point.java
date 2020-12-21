package algorithms.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point {
    private int y;
    private int x;

    public Point(int y, int x) {
        this.x = x;
        this.y = y;
    }
}
