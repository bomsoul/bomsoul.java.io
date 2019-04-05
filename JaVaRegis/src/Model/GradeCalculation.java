package Model;


public class GradeCalculation {
    private String courseid;
    private String coursename;
    private int credit;
    private double grade;

    public GradeCalculation(String courseid, String coursename, int credit, String grade) {
        this.courseid = courseid;
        this.coursename = coursename;
        this.credit = credit;
        if(grade.equals("A"))
            this.grade = 4;
        if(grade.equals("B+"))
            this.grade = 3.5;
        if(grade.equals("B"))
            this.grade = 3;
        if(grade.equals("C+"))
            this.grade = 2.5;
        if(grade.equals("C"))
            this.grade = 2;
        if(grade.equals("D+"))
            this.grade = 1.5;
        if(grade.equals("D"))
            this.grade = 1;
        if(grade.equals("F"))
            this.grade = 0;
    }

    public String getCourseid() {
        return courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public int getCredit() {
        return credit;
    }

    public double getGrade() {
        return grade;
    }
}
