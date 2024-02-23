package hashtable;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Point))
            return false;
        Point p = (Point) o;
        return p.x == x && p.y == y;
    }

//    @Override
//    public int hashCode(){
//        return (int)(x+y);
//    }
}
