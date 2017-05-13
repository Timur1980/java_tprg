package ru.xtim.prts.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by timur.khisamutdinov on 13.05.2017.
 */
public class PointTests {

    @Test
    public void testDistance(){
        Point p1=new Point(3,4);
        Point p2=new Point(4,5);
        Assert.assertEquals(p2.distance(p1),1.4142135623730951);
    }

    @Test
    public void testDistance1(){
        Point p1=new Point(4,5);
        Point p2=new Point(4,6);
        Assert.assertEquals(p2.distance(p1),1.0);
    }

    @Test
    public void testDistance2(){
        Point p1=new Point(-2.5,4);
        Point p2=new Point(9.5,0.7);
        Assert.assertEquals(p2.distance(p1),12.445481107614924);
    }

}
