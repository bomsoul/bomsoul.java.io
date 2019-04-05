package Model;

import javafx.beans.property.SimpleStringProperty;

public class Schedule {
    private final SimpleStringProperty courseid;
    private final SimpleStringProperty coursetitle;
    private final SimpleStringProperty credit;

    public Schedule(String courseid, String coursetitle, String credit) {
        this.courseid = new SimpleStringProperty(courseid);
        this.coursetitle = new SimpleStringProperty(coursetitle);
        this.credit = new SimpleStringProperty(credit);
    }

    public String getCourseid() {
        return courseid.get();
    }

    public String getCoursetitle() {
        return coursetitle.get();
    }

    public String getCredit() {
        return credit.get();
    }

}
