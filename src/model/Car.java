package model;

import graphics.graphics.Picture;

/**
 * Created by Mikhail on 08.09.16.
 */
public class Car
{
    CarType type;
    int speed;
    int distantion;
    int timeSpawn;  //ms
    int position;
    Picture pic = new Picture();

    public Car(){}

    public void setPic(String path) {
        this.pic.load(path);
    }

    public void setPic(Picture pic) {
        this.pic = pic;
    }

    public Picture getPic() {
        return pic;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDistantion() {
        return distantion;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setDistantion(int distantion) {
        this.distantion = distantion;
    }

    public int getTimeSpawn() {
        return timeSpawn;
    }

    public void setTimeSpawn(int timeSpawn) {
        this.timeSpawn = timeSpawn;
    }
}
