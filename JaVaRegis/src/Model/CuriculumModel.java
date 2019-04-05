package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CuriculumModel {
    private final SimpleStringProperty courseid;
    private final SimpleStringProperty coursetitle;
    private final SimpleStringProperty credit;
    private final SimpleStringProperty prerequirsite;
    private final SimpleIntegerProperty year;
    private final SimpleStringProperty term;
    private Pane level;
    private ImageView status;

    public CuriculumModel(String courseid, String coursetitle, String credit, String prerequirsite,String term,int year,int level,int status) {
        this.courseid = new SimpleStringProperty(courseid);
        this.coursetitle = new SimpleStringProperty(coursetitle);
        this.credit = new SimpleStringProperty(credit);
        this.prerequirsite = new SimpleStringProperty(prerequirsite);
        this.term = new SimpleStringProperty(term);
        this.year = new SimpleIntegerProperty(year);
        this.level = new Pane();
        this.status = new ImageView();
        if(level == 1){
            this.level.setStyle("-fx-background-color: green;");
        }
        else if(level == 2){
            this.level.setStyle("-fx-background-color: blue;");
        }
        else if(level == 3){
            this.level.setStyle("-fx-background-color: red;");
        }

        if(status == 1){
            this.status.setVisible(false);
        }
        else if(status == 0){
            Image im = new Image("/image/key.jpg");
            this.status.setImage(im);
        }
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

    public String getPrerequirsite() {
        return prerequirsite.get();
    }

    public int getYear() {
        return year.get();
    }

    public String getTerm() {
        return term.get();
    }

    public Pane getLevel() {
        return level;
    }

    public ImageView getStatus() {
        return status;
    }
}
