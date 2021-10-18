package ru.krsu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hippodrome {
    private static Hippodrome game;
    private List<Horse> horses;
    public Hippodrome(List<Horse> horses){
        this.horses = horses;
    }
    public List<Horse> getHorses() {
        return horses;
    }
    void run() throws InterruptedException {
        for(int i = 0; i < 100; i++){
            move();
            print();
            Thread.sleep(500);
        }
    }
    void move(){
        for(Horse horse: horses){
            horse.move();

        }
    }
    void print() throws InterruptedException {
        for(Horse horse: horses){
            horse.print();
        }
        Thread.sleep(500);
        System.out.println("\n\n\n\n\n\n\n\n\n");
    }
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Lucky", 3, 0));
        horses.add(new Horse("Slevin", 3, 0));
        horses.add(new Horse("Homer", 3, 0));
        game = new Hippodrome(horses);
        game.run();
    }
}
