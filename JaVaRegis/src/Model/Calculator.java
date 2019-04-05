package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Calculator {
    final private SimpleStringProperty courseid;
    final private SimpleStringProperty coursename;
    final private SimpleIntegerProperty credit;
    final private SimpleStringProperty grade;

    public Calculator(String courseid,String coursename,int credit,String grade) {
        this.courseid = new SimpleStringProperty(courseid);
        this.coursename = new SimpleStringProperty(coursename);
        this.credit = new SimpleIntegerProperty(credit);
        this.grade = new SimpleStringProperty(grade);
    }

    public Calculator(String courseid,String coursename,int credit,double grade) {
        SimpleStringProperty grade1 = null;
        this.courseid = new SimpleStringProperty(courseid);
        this.coursename = new SimpleStringProperty(coursename);
        this.credit = new SimpleIntegerProperty(credit);
        if(grade == 4)
            grade1 = new SimpleStringProperty("A");
        if(grade == 3.5)
            grade1 = new SimpleStringProperty("B+");
        if(grade == 3)
            grade1 = new SimpleStringProperty("B");
        if(grade == 2.5)
            grade1 = new SimpleStringProperty("C+");
        if(grade == 2)
            grade1 = new SimpleStringProperty("C");
        if(grade == 1.5)
            grade1 = new SimpleStringProperty("D+");
        if(grade == 1)
            grade1 = new SimpleStringProperty("D");
        if(grade == 0)
            grade1 = new SimpleStringProperty("F");
        this.grade = grade1;
    }

    public String getCourseid() {
        return courseid.get();
    }

    public String getCoursename() {
        return coursename.get();
    }

    public int getCredit() {
        return credit.get();
    }

    public String getGrade() {
        return grade.get();
    }

}
