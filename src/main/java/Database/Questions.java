package Database;

import java.io.Serializable;

public class Questions implements Serializable {

    private int id;
    private String ShortContentOfQuestions,ShortContentOfAnswer;

    public Questions(int id,String ShortContentOfQuestions, String ShortContentOfAnswer){
        this.id = id;
        this.ShortContentOfQuestions = ShortContentOfQuestions;
        this.ShortContentOfAnswer = ShortContentOfAnswer;
    }

    public Questions (String ShortContentOfQuestions, String ShortContentOfAnswer){
        this.ShortContentOfQuestions = ShortContentOfQuestions;
        this.ShortContentOfAnswer = ShortContentOfAnswer;
    }

    public String getShortContentOfQuestions(){
        return ShortContentOfQuestions;
    }

    public String getShortContentOfAnswer(){
        return ShortContentOfAnswer;
    }

    @Override
    public String toString(){
        return ShortContentOfQuestions + " " + ShortContentOfAnswer;
    }

    public int getId() {
        return id;
    }
}