package worker;

public class Coordinates {
    private Integer x; //Максимальное значение поля: 709, Поле не может быть null
    private Double y; //Значение поля должно быть больше -414, Поле не может быть null

    public Coordinates(Integer x, Double y) {
        setX(x);
        setY(y);
    }

    public Integer getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public void setX(Integer x) {
        if (x == null) throw new IllegalArgumentException("X не может быть null");

        if (x > 709) throw new IllegalArgumentException("X не может быть больше 709");
        this.x = x;
    }

    public void setY(Double y) {
        if (y == null) throw new IllegalArgumentException("Y не может быть null");

        if (y <= -414) throw new IllegalArgumentException("Y должен быть строго больше -414");
        this.y = y;
    }
}