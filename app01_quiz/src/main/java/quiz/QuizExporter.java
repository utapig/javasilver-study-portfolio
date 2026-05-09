import java.util.List;

public class QuizExporter {

    public static String exportQuestions(List<Question> list) {
        StringBuilder str = new StringBuilder();
        str.append("[");

        for (int i = 0; i < list.size(); i++) {
            str.append("{");
            str.append("\"text\":\"").append(escapeJson(list.get(i).getText())).append("\"");
            str.append(",\"choices\":[");
            List<String> choice = list.get(i).getChoices();
            for (int j = 0; j < choice.size(); j++) {
                str.append("\"").append(escapeJson(choice.get(j))).append("\"");
                if (j < choice.size() - 1) {
                    str.append(",");
                }
            }
            str.append("],");
            str.append("\"answerIndex\":").append(list.get(i).getAnswerIndex());
            str.append(",");
            str.append("\"explanation\":\"").append(escapeJson(list.get(i).getExplanation())).append("\"");
            str.append("}");

            if (i < list.size() - 1) {
                str.append(",");
            }
        }
        str.append("]");

        return str.toString();
    }

    private static String escapeJson(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n");
    }
}