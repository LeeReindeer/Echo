package Bean;

/**
 *@Author lee
 *@Time  4/27/17.
 */
public class Entry {
    String classtime;
    String content=" ";
    public Entry(String classtime){
        this.classtime=classtime;
    }

    public String getClasstime() {
        return classtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
