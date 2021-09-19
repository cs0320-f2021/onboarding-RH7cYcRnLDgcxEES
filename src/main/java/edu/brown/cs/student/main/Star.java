package edu.brown.cs.student.main;
import java.util.*;

public class Star {
    private Double distance;
    private String name;
    private int starID;
    private double x;
    private double y;
    private double z;

    /**
     * Construct star from CSV data
     *
     * @param sID - star ID
     * @param n - Name
     * @param x
     * @param y
     * @param z
     */
    public Star(int sID, String n, double x, double y, double z) {
        this.name = n;
        this.starID = sID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.distance = null;
    }

    /**
     * Calculate distance from this Star to given xyz coordinates
     * @param otherX - x-coordinate
     * @param otherY - y-coordinate
     * @param otherZ - z-coordinate
     * @return dist - Distance between this star and xyz point
     */
    public double calcDist(double otherX, double otherY, double otherZ) {
         this.distance = Math.sqrt(
                 Math.pow(otherX-this.x,2)+
                 Math.pow(otherY-this.y,2)+
                 Math.pow(otherZ-this.z,2));
         return this.distance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Star other = (Star) o;

        return other.getName().equals(this.name) && other.getX()==(this.x)
                && other.getY()==(this.y) && other.getZ()==(this.z);
    }

    public Double getDistance() {
        return distance;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public String getName() {
        return name;
    }
    public int getStarID() {
        return this.starID;
    }
}
