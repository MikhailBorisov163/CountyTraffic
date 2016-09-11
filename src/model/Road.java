package model;

/**
 * Created by Mikhail on 08.09.16.
 */
public class Road
{
    RoadType type;
    int length;

    public Road(RoadType type, int length)
    {
        this.type = type;
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
