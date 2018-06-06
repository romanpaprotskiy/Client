package Database;

import java.io.Serializable;

public class Questions implements Serializable {

    private int id;
    private String ShortContentOfQuestions, ShortContentOfAnswer;
    private Custom custom;

    public Questions(int id, String ShortContentOfQuestions, String ShortContentOfAnswer, Custom custom) {
        this.id = id;
        this.ShortContentOfQuestions = ShortContentOfQuestions;
        this.ShortContentOfAnswer = ShortContentOfAnswer;
        this.custom = custom;
    }

    public Questions(String ShortContentOfQuestions, String ShortContentOfAnswer, Custom custom) {
        this.ShortContentOfQuestions = ShortContentOfQuestions;
        this.ShortContentOfAnswer = ShortContentOfAnswer;
        this.custom = custom;
    }

    public String getShortContentOfQuestions() {
        return ShortContentOfQuestions;
    }

    public String getShortContentOfAnswer() {
        return ShortContentOfAnswer;
    }

    @Override
    public String toString() {
        return ShortContentOfQuestions + " " + ShortContentOfAnswer;
    }

    public int getId() {
        return id;
    }
}