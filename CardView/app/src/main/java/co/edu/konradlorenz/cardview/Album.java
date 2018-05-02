package co.edu.konradlorenz.cardview;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Album {
    private String name;
    private int numOfCaps;
    private int thumbnail;

    public Album() {
    }

    public Album(String name, int numOfCaps, int thumbnail) {
        this.name = name;
        this.numOfCaps = numOfCaps;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfCaps() {
        return numOfCaps;
    }

    public void setNumOfCaps(int numOfCaps) {
        this.numOfCaps = numOfCaps;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
