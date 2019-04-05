package Model;

public class User {
    private static String name ;
    private static String id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        User.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        User.id = id;
    }
}
