import java.util.ArrayList;
import java.util.List;

public class Question {
    private String text;
    private List<String> choices = new ArrayList<String>();
    private int answerIndex;
    private String explanation;

    Question(String text, List<String> choices, int answerIndex, String explanation) {
        this.text = text;
        this.choices = choices;
        this.answerIndex = answerIndex;
        this.explanation = explanation;
    }

    public String getText() {
        return text;
    }

    public List<String> getChoices () {
        return choices;
    }

    public int getAnswerIndex () {
        return answerIndex;
    }

    public String getExplanation() {
        return explanation;
    }
}