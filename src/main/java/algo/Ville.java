package algo;

public class Ville {

    private int x;
    private int y;
    private int num;

    public Ville(int x, int y, int num) {
        this.x = x;
        this.y = y;
        this.num = num;
    }


    public double distance(Ville v) {
        return Math.sqrt( (x - v.getX()) * (x - v.getX()) + (y - v.getY()) * (y - v.getY()));
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNum() {
        return num;
    }
}
