
import java.util.List;

public class QuizExporterTest {

    public static void testQuestion() {
        Question q = new Question("問題X", List.of("A", "B", "C", "D"), 2, "解説X");
        assertEqual("testQuestionGetters_text", "問題X", q.getText());
        assertEqual("testQuestionGetters_choices", "[A, B, C, D]", q.getChoices().toString());
        assertEqual("testQuestionGetters_answerIndex", "2", String.valueOf(q.getAnswerIndex()));
        assertEqual("testQuestionGetters_explanation", "解説X", q.getExplanation());
    }

    public static void testExportQuestions() {
        List<Question> list = List.of(
                new Question("問題1", List.of("A", "B", "C", "D"), 0, "解説1")
        );
        String result = QuizExporter.exportQuestions(list);
        String expected = "[{\"text\":\"問題1\",\"choices\":[\"A\",\"B\",\"C\",\"D\"],\"answerIndex\":0,\"explanation\":\"解説1\"}]";
        assertEqual("testExportQuestions", expected, result);
    }

    public static void testExportThreeQuestions() {
        List<Question> list = List.of(
                new Question("問題1", List.of("A", "B", "C", "D"), 0, "解説1"),
                new Question("問題2", List.of("A", "B", "C", "D"), 0, "解説2"),
                new Question("問題3", List.of("A", "B", "C", "D"), 0, "解説3")
        );
        String result = QuizExporter.exportQuestions(list);
        String expected = "[{\"text\":\"問題1\",\"choices\":[\"A\",\"B\",\"C\",\"D\"],\"answerIndex\":0,\"explanation\":\"解説1\"},{\"text\":\"問題2\",\"choices\":[\"A\",\"B\",\"C\",\"D\"],\"answerIndex\":0,\"explanation\":\"解説2\"},{\"text\":\"問題3\",\"choices\":[\"A\",\"B\",\"C\",\"D\"],\"answerIndex\":0,\"explanation\":\"解説3\"}]";
        assertEqual("testExportThreeQuestions", expected, result);
    }

    public static void testExportEmpty() {
        List<Question> list = List.of();
        String result = QuizExporter.exportQuestions(list);
        assertEqual("testExportEmpty", "[]", result);
    }

    public static void testEscapeDoubleQuote() {
        List<Question> list = List.of(
                new Question("\"テスト\"", List.of("A", "B", "C", "D"), 0, "解説")
        );
        String result = QuizExporter.exportQuestions(list);
        String expected = "[{\"text\":\"\\\"テスト\\\"\",\"choices\":[\"A\",\"B\",\"C\",\"D\"],\"answerIndex\":0,\"explanation\":\"解説\"}]";
        assertEqual("testEscapeDoubleQuote", expected, result);
    }

    private static void assertEqual(String testName, String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError(
                    "FAIL: " + testName + "\n"
                    + "  expected: " + expected + "\n"
                    + "  actual  : " + actual
            );
        }
        System.out.println("PASS: " + testName);
    }

    public static void main(String[] args) {
        testQuestion();
        testExportQuestions();
        testExportThreeQuestions();
        testExportEmpty();
        testEscapeDoubleQuote();
    }
}
