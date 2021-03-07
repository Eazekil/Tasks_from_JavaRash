package task2113;

public class Horse {
    public String name;
    public double speed;
    public double distance;

    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void move(){
        distance += speed*Math.random();
    }

    public void print(){
        //int distancePrint =(int) distance;
        for(int i=0; i<Math.floor(distance); i++){
            System.out.print(".");
        }
        System.out.println(name);

    }
}
