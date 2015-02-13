package quizdroid.kylep9.washington.edu.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kylepeterson on 2/13/15.
 */
public class QuestionList implements Serializable {
    private List<Question> questions;
    private int currentQuestion;

    public QuestionList(Question[] qs) {
        questions = new ArrayList<Question>();
        for(int i = 0; i < qs.length; i++) {
            questions.add(qs[i]);
        }
        currentQuestion = 0;
    }

    public List<Question> getQuestionList() {
        List<Question> result = new ArrayList<Question>();
        for (int i = 0; i < questions.size(); i++) {
            result.add(questions.get(i));
        }
        return result;
    }

    public Question getQuestion(int i) {
        return questions.get(i);
    }

    public int getCurrentIndex() {
        return currentQuestion;
    }

    public void increaseIndex() {
        currentQuestion++;
    }
}
