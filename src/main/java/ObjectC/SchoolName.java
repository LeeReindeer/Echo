package ObjectC;

/**
 * Created by lee on 4/23/17.
 */
public class SchoolName {
    public final static String ZJOU="zjouUsers";
    public final static String PKU="pkuUsers";
    public static String getSchoolId(String school){
        if (school.equals("浙江海洋大学")) return ZJOU;
        if (school.equals("北京大学")) return PKU;
        return null;
    }
}
