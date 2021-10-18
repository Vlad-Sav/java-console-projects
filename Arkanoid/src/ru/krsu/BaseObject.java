package ru.krsu;

public abstract class BaseObject {
    protected double x, y, radius;

    public BaseObject(double x, double y, double radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    void checkBorders(double minx, double maxx, double miny, double maxy) {
        if (x < minx) x = minx;
        if (x > maxx) x = maxx;
        if (y < miny) y = miny;
        if (y > maxy) y = maxy;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    public abstract void draw(Canvas canvas);
    public abstract void move();
    public boolean isIntersec(BaseObject o){
        return Math.sqrt(Math.hypot(o.getX()-getX(), o.getY()-getY())) <= Math.max(getRadius(), o.getRadius());
    }
}
