package ru.krsu;

public class Horse {
    private double distance;
    private double speed;
    private String name;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double s0peed) {
        this.speed = s0peed;
    }

    public String getName() {
        return name;
    }
    public void move(){
        distance += speed*Math.random();
    }
    public void print(){
        for(int i = 0; i < Math.floor(distance); i++){
            System.out.print(".");
        }
        System.out.println(name);
    }
    public void setName(String name) {
        this.name = name;
    }

    public Horse(String name, double speed, double distance) {
        this.distance = distance;
        this.speed = speed;
        this.name = name;
    }
}
