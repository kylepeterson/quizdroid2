package quizdroid.kylep9.washington.edu.quizdroid;

import java.io.Serializable;

/**
 * Created by kylepeterson on 2/3/15.
 */
public class Question implements Serializable {
    private String question;
    private String[] answers;
    private int correctIndex;

    public Question(String question, String[] answers, int correctIndex) {
        this.question = question;
        this.answers = answers;
        this.correctIndex = correctIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }
}
