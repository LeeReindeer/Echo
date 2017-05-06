package ObjectC.Menu;

/**
 * Created by lee on 4/8/17.
 */
public class Button {
    private String type;
    private String name;
    private Button[] sub_button;


    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getType() {

        return type;
    }

    public Button[] getSub_button() {

        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }

    public void setType(String type) {

        this.type = type;
    }
}
