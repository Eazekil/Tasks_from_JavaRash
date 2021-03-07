package task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private  List<Horse> horses;
    public static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public static void main(String[] args) {
        List<Horse> horses = new ArrayList<>();

        horses.add(new Horse("Lucky",3,0));
        horses.add(new Horse("Chucky",3,0));
        horses.add(new Horse("Bucky",3,0));

        game = new Hippodrome(horses);
        
        game.run();
        game.printWinner();

    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run(){
        for(int i=1; i < 101; i++){
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void move(){
        for(Horse horse : horses){
            horse.move();
        }

    }

    public void print(){
        for(Horse horse : horses){
            horse.print();
        }
        for(int i=0; i < 10; i++){
            System.out.println();
        }
    }

    public Horse getWinner(){
        double max = -1;
        Horse winner = null;
        for(Horse horse : horses){
            if(max < horse.distance){
                max = horse.distance;
                winner = horse;
            }
        }
        return winner;
    }

    public void printWinner(){
        System.out.println("Winner is "+getWinner().name+"!");
    }

}
