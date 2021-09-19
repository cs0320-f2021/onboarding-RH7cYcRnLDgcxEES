package edu.brown.cs.student.main;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StarSorter {
    private HashMap<String,Star> namesToLocations;
    private HashSet<String> repeatedNames;
    private ArrayList<Star> stars;
    private boolean isPopulated;

    /**
     * Constructs StarSorter object. No data yet.
     */
    public StarSorter() {
//        this.stars = new ArrayList<Star>();
//        this.namesToLocations = new HashMap<String,Star>();
//        this.repeatedNames = new HashSet<String>();
        this.isPopulated = false;
    }

    /**
     * Method to process inputted CSV file and populate Star data.
     * @param filePath - filepath of CSV file with Star Data
     */
    public int processCSV(String filePath) {
        this.stars = new ArrayList<Star>();
        this.namesToLocations = new HashMap<String,Star>();
        this.repeatedNames = new HashSet<String>();
        BufferedReader br = null;
        int starCount = 0;
        try
        {
            br = new BufferedReader(new FileReader(filePath));

            String line = "";
            br.readLine();

            while ((line=br.readLine()) != null)
            {
                starCount++;
                String[] starData = line.split(",");

                if(starData.length > 0 )
                {
                    Star s = new Star(
                            Integer.parseInt(starData[0]), starData[1],
                            Double.parseDouble(starData[2]),
                            Double.parseDouble(starData[3]),
                            Double.parseDouble(starData[4]));
                    this.stars.add(s);
                    if (!this.repeatedNames.contains(starData[1])) {
                        if (!this.namesToLocations.containsKey(starData[1])) {
                            this.namesToLocations.put(starData[1].toLowerCase(), s);
                        }
                        else {
                            this.namesToLocations.remove(starData[1]);
                            this.repeatedNames.add(starData[1]);
                        }
                    }
                }
            }
            this.isPopulated = true;
        }
        catch(Exception e)
        {
            System.out.println("ERROR: Issue with reading CSV\n" + e);
        }
        finally
        {
            try
            {
                br.close();
            }
            catch(IOException f)
            {
                System.out.println("ERROR: Issue with closing reader\n" + f);
            }
        }

        return starCount;
    }

    public boolean checkIsPopulated() {
        return this.isPopulated;
    }

    /**
     * Naively finds list of k Stars closest to given Star
     * @param k - number of stars to find
     * @param starName
     * @return
     */
    public ArrayList<Star> naiveNeighborsStarName(int k, String starName) {
        if (this.repeatedNames.contains(starName)) {
            throw new RuntimeException("ERROR: Name occurs multiple times in data file");
        }
        else {
            Star s = namesToLocations.get(starName.toLowerCase());
            return naiveNeighborsXYZ(k, s.getX(), s.getY(), s.getZ(), s);
        }

    }

    /**
     * Naively finds list of k stars closest to given xyz-coordinate
     * @param k - number of stars to find
     * @param x - x-coordinate
     * @param y - y-coordinate
     * @param z - z-coordinate
     * @param withStar - should be null unless point is associated with a certain star
     * @return - ArrayList of k stars in order from closest to farthest
     */
    public ArrayList<Star> naiveNeighborsXYZ(int k, double x, double y, double z, Star withStar) {
        if (k > this.namesToLocations.size()) {
            throw new RuntimeException("Number of neighbors larger than star sample");
        }
//        else if (withStar!= null && k > this.namesToLocations.size()-1)
        for (Star nextStar: stars) {
            nextStar.calcDist(x,y,z);
        }
        List<Star> sortedList = stars.stream().sorted(Comparator.comparingDouble(Star :: getDistance)).collect(Collectors.toList());
//        Collections.sort(stars);
        ArrayList<Star> topK = new ArrayList<Star>();
        if (withStar == null) {
            for (int i = 0; i<k; i++) {
                topK.add(sortedList.get(i));
            }
        }
        else {
            int i = 0;
            do {
                // issue to fix: exclude star example, this gives out of bounds
                    // should return empty list instead
                    // check if k is longer than list-1 in this case
                Star t = sortedList.get(i);
                if (!t.equals(withStar)) {
                    topK.add(t);
                }
                i++;
            }
            while (topK.size()<k);
        }
        return topK;

    }

}
