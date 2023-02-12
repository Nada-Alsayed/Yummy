package eg.gov.iti.yummy.weeklyPlan.view.view;

public class planListItem {

    private String imageID;
    private String title;
    public planListItem(){}
    public planListItem(String title, String imageID) {
        this.title = title;
        this.imageID = imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageID() {
        return imageID;
    }

    public String getTitle() {
        return title;
    }
}
