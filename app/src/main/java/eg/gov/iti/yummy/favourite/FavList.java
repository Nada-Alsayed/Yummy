package eg.gov.iti.yummy.favourite;

public class FavList {
    String Name;

    String origin;

    String thumbnail;

    public FavList(){}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public FavList(String name, String origin, String thumbnail) {
        Name = name;
        this.origin = origin;
        this.thumbnail = thumbnail;
    }
}
