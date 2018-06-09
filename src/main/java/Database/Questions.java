package Database;

import javafx.beans.property.*;

import java.io.Serializable;

public class Questions implements Serializable {

    private IntegerProperty id;
    private StringProperty ShortContentOfQuestions, ShortContentOfAnswer;
    private SimpleObjectProperty<Custom> custom;

    public Questions() {
        this.id = new SimpleIntegerProperty();
        this.ShortContentOfQuestions = new SimpleStringProperty();
        this.ShortContentOfAnswer = new SimpleStringProperty();
        this.custom = new SimpleObjectProperty<Custom>();
    }

    public Questions(int id, String ShortContentOfQuestions, String ShortContentOfAnswer,Custom custom) {
        this.id = new SimpleIntegerProperty(id);
        this.ShortContentOfQuestions = new SimpleStringProperty(ShortContentOfQuestions);
        this.ShortContentOfAnswer = new SimpleStringProperty(ShortContentOfAnswer);
        this.custom = new SimpleObjectProperty<Custom>(custom);
    }

    public Questions(String ShortContentOfQuestions, String ShortContentOfAnswer, Custom custom) {
        this.ShortContentOfQuestions = new SimpleStringProperty(ShortContentOfQuestions);
        this.ShortContentOfAnswer = new SimpleStringProperty(ShortContentOfAnswer);
        this.custom = new SimpleObjectProperty<Custom>(custom);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getShortContentOfQuestions() {
        return ShortContentOfQuestions.get();
    }

    public StringProperty shortContentOfQuestionsProperty() {
        return ShortContentOfQuestions;
    }

    public void setShortContentOfQuestions(String shortContentOfQuestions) {
        this.ShortContentOfQuestions.set(shortContentOfQuestions);
    }

    public String getShortContentOfAnswer() {
        return ShortContentOfAnswer.get();
    }

    public StringProperty shortContentOfAnswerProperty() {
        return ShortContentOfAnswer;
    }

    public void setShortContentOfAnswer(String shortContentOfAnswer) {
        this.ShortContentOfAnswer.set(shortContentOfAnswer);
    }

    public Custom getCustom() {
        return custom.get();
    }

    public SimpleObjectProperty<Custom> customProperty() {
        return custom;
    }

    public void setCustom(Custom custom) {
        this.custom.set(custom);
    }
}