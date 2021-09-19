package edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.*;

public class StarTest {

    @Test
    public void testCalcDist() {
        Star star0 = new Star(0,"star 0", 0.0,1.0,0.0);
        Star star1 = new Star(1,"star 1", 1.0,1.0,0.0);
        Star star2 = new Star(2,"star 2", 1.0,1.0,1.0);


        ArrayList<Star> starList = new ArrayList<Star>(Arrays.asList(star0, star1, star2));
//        assertEquals(null, star0.getDistance(), 0.01);
//        assertEquals(null, star1.getDistance(), 0.01);
//        assertEquals(null, star2.getDistance(), 0.01);

        //simple test
        star0.calcDist(0.0,0.0,0.0);
        star1.calcDist(0.0,0.0,0.0);
        star2.calcDist(0.0,0.0,0.0);
        assertEquals(1.0, star0.getDistance(), 0.01);
        assertEquals(Math.sqrt(2), star1.getDistance(), 0.01);
        assertEquals(Math.sqrt(3), star2.getDistance(), 0.01);

        //more complex test
        for (Star s:starList) {
            s.calcDist(4.0,7.5,-12.8);
        }
        assertEquals(Math.sqrt(Math.pow(4.0,2)+Math.pow(1.0-7.5,2)+Math.pow(-12.8,2)), star0.getDistance(), 0.01);
        assertEquals(Math.sqrt(Math.pow(4.0-1.0,2)+Math.pow(1.0-7.5,2)+Math.pow(-12.8,2)), star1.getDistance(), 0.01);
        assertEquals(Math.sqrt(Math.pow(4.0-1.0,2)+Math.pow(1.0-7.5,2)+Math.pow(-12.8-1.0,2)), star2.getDistance(), 0.01);

        // same point
        Star star3 = new Star(3,"star 3", 12.7876,-44536732355.9,0.0);
        star3.calcDist(12.7876,-44536732355.9,0.0);
        assertEquals(0.0, star3.getDistance(), 0.01);

    }

}
