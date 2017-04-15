package Bean.Menu;

/**
 * Created by lee on 4/8/17.
 */
public class ViewButton extends Button {
    private String url;

    public ViewButton() {
        super.setType("view");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
