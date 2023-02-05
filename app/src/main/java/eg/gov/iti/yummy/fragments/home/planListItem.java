package eg.gov.iti.yummy.fragments.home;

public class planListItem {

    private int imageID;
    private String title;

    public planListItem(String title, int imageID) {
        this.title = title;
        this.imageID = imageID;
    }

    public int getImageID() {
        return imageID;
    }

    public String getTitle() {
        return title;
    }
}
