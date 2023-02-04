package eg.gov.iti.yummy.fragments.favourite;

public class FavList {
    String title;
    String desc;
    private int imgId;
    private int imgId2;
    public FavList() {
    }

    public FavList(String title, String desc, int imgId,int imgId2) {
        this.title = title;
        this.desc = desc;
        this.imgId = imgId;
        this.imgId2 = imgId2;
    }
    public FavList(int imgId2) {
        this.imgId2 = imgId2;
    }

    public int getImgId2() {
        return imgId2;
    }

    public void setImgId2(int imgId2) {
        this.imgId2 = imgId2;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getImgId() {
        return imgId;
    }
}
