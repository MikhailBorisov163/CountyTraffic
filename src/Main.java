import graphics.graphics.Canvas;
import graphics.graphics.Picture;
import model.Car;
import model.Road;
import model.RoadType;

import java.util.*;

/**
 * Created by Mikhail on 08.09.16.
 */
public class Main
{
    static int position = 95;
    public static void main(String[] args) {

        List<Road> roads = new ArrayList<>();
//        Car car1 = new Car();
//        car1.setDistantion(1);
//        car1.setSpeed(10);
//        car1.setTimeSpawn(1000);
//        car1.setPosition(0);


//        Car car2 = new Car();
//        car2.setDistantion(1);
//        car2.setSpeed(13);
//        car2.setTimeSpawn(1500);
//        car2.setPosition(0);


        Road r1 = new Road(RoadType.HIGHWAY, 3);
        Road r2 = new Road(RoadType.HIGHWAY,4);

        roads.add(r1);
        roads.add(r2);

        drawBackground();

        drawRoad(r1, position, true);
        position = position+ (r1.getLength()-1)*50;

        drawCentr(position);
        position = position +150;

        drawRoad(r2, position, false);


//        Picture carPic1 = new Picture();
//        carPic1.load("/Users/Mikhail/IdeaProjects/Tonnel/Pictures/cars/car_type_test.png");
////        carPic1.draw();
////        carPic1.translate(0, 50);
//
//        Picture carPic2 = new Picture();
//        carPic2.load("/Users/Mikhail/IdeaProjects/Tonnel/Pictures/cars/car_type_test.png");
////        carPic2.draw();
////        carPic2.translate(0, 100);
//
//        car1.setPic(carPic1);
//        car2.setPic(carPic2);

//        Random r = new Random();
          runTraffic(r1);
//        moveCar(car1);
//        moveCar(car2);



    }

    public static void drawBackground()
    {
        Picture back = new Picture();
        back.load("/Users/Mikhail/IdeaProjects/Tonnel/Pictures/road/road_background.png");
        back.grow(700, 500);
        back.draw();
    }

    public static void drawRoad(Road road, int position, boolean isTop)
    {

        if (isTop == true)
        {
            Picture top = new Picture();
            top.load("/Users/Mikhail/IdeaProjects/Tonnel/Pictures/road/road_top.png");
            top.draw();
            drawLines(position,road);
        }
        else
        {
            drawLines(position,road);
            Picture bottom = new Picture();
            bottom.load("/Users/Mikhail/IdeaProjects/Tonnel/Pictures/road/road_botton.png");
            bottom.draw();
            bottom.translate(0,position + 50*(road.getLength()-1));
        }




    }

    public static void drawCentr(int position)
    {
        Picture centr = new Picture();
        centr.load("/Users/Mikhail/IdeaProjects/Tonnel/Pictures/road/road_middle.png");
        centr.draw();
        centr.grow(500,0);
        centr.translate(0, position);
    }

    public static void drawLines(int position, Road road)
    {
        for (int i = 0; i <road.getLength()-1 ; i++)
        {
            Picture mark = new Picture();
            mark.load("/Users/Mikhail/IdeaProjects/Tonnel/Pictures/road/road_mark.png");
            mark.draw();
            mark.grow(740, -1);
            mark.translate(0,position);
            position  = position + 50;

        }
    }

    public static void moveCar(Car car)
    {
        final Timer time = new Timer();
        time.schedule(new TimerTask() {
            int length = 0 ;
            @Override
            public void run() {
                if(length>=800){
                    time.cancel();
                    return;
                }

                car.setPosition(car.getPosition() + car.getSpeed());
                car.getPic().translate(car.getSpeed(), 0);
                length = car.getPosition();

            }
        }, car.getTimeSpawn(), 1000);
    }

    public static void runTraffic(Road r) {

        final int t = 5000;

        List<Car> cars = new ArrayList<>();
        Map<Integer, List<Car>> carsOnCurrentLines = new HashMap<>();

        boolean x = false;
        Random random = new Random();

        final Timer time = new Timer();
        time.schedule(new TimerTask() {
            int timer = 0 ;
            @Override
            public void run() {
                if(x == true){
                    time.cancel();
                    return;
                }

                int prevCarPos=800;

                if (!cars.isEmpty())
                {
                    int mark = cars.size()+1;
                    for (int i = 0; i <cars.size() ; i++)
                    {
                        if (mark<i)
                        {
                            cars.set(mark,cars.get(i));

                            if (i-1 == cars.size())
                            {
                                cars.remove(i);
                            }
                            mark ++;
                        }
                        if (cars.get(i).getPosition() >= 800)
                        {
                            mark = i;
                            cars.remove(cars.get(i));
                        }
                    }
                    prevCarPos = cars.get(cars.size()-1).getPosition();
                }

                Car car1 = new Car();
                car1.setDistantion(20);
                car1.setSpeed(10);
                car1.setTimeSpawn(random.nextInt(1000));
                car1.setPosition(0);

                Picture carPic1 = new Picture();
                carPic1.load("/Users/Mikhail/IdeaProjects/Tonnel/Pictures/cars/car_type_test.png");
                carPic1.translate(0, 50);
                car1.setPic(carPic1);


                if (timer >= car1.getTimeSpawn() && cars.size() <=5 && prevCarPos>(car1.getDistantion()+100))
                {
                    cars.add(car1);
                    car1.getPic().draw();
                    moveCar(car1);
                }
                timer = timer + t;

            }
        }, 1000, t);

    }
}
