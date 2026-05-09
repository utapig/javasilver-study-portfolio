
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

        private static Path resolveOutputPath() {
                Path cwd = Path.of("").toAbsolutePath().normalize();

                Path directWeb = cwd.resolve("web").resolve("index.html");
                if (Files.exists(directWeb)) {
                        return cwd.resolve("web").resolve("questions.json");
                }

                Path nestedWeb = cwd.resolve("app01_quiz").resolve("web").resolve("index.html");
                if (Files.exists(nestedWeb)) {
                        return cwd.resolve("app01_quiz").resolve("web").resolve("questions.json");
                }

                return cwd.resolve("web").resolve("questions.json");
        }

    public static void main(String[] args) {
        List<Question> list = new ArrayList<>();

        // Q1. int型の最大値
        list.add(new Question(
                "int型の変数に格納できる最大値はどれか？",
                List.of("2,147,483,647", "9,223,372,036,854,775,807", "32,767", "2,147,483,648"),
                0,
                "intの最大値はInteger.MAX_VALUEで、2147483647です。"
        ));

        // Q2. long型
        list.add(new Question(
                "次のうちlong型の値として正しいのはどれか？",
                List.of("1000L", "1000l", "1000", "すべて正しい"),
                3,
                "1000LでもlでもOK。整数は通常intなので1000はint型です。"
        ));

        // Q3. 配列
        list.add(new Question(
                "配列の初期化で正しいのはどれか？",
                List.of("int[] arr = new int[5];", "int arr[] = new int[5];", "int[] arr = {1,2,3};", "すべて正しい"),
                3,
                "いずれの書き方でも配列を初期化できます。"
        ));

        // Q4. List
        list.add(new Question(
                "ListにはArrayListとどれが使われることが多いか？",
                List.of("HashMap", "LinkedList", "TreeSet", "Hashtable"),
                1,
                "ArrayListとLinkedListがListの主な実装クラスです。"
        ));

        // Q5. 文字列比較
        list.add(new Question(
                "String型の値を比較するときに使うメソッドはどれか？",
                List.of("==", "equals()", "compareTo()", "match()"),
                1,
                "String の内容を比較するには equals() を使います。==はオブジェクト参照の比較です。"
        ));

        // Q6. for文
        list.add(new Question(
                "拡張for文（for-each）の正しい書き方はどれか？",
                List.of("for(int i=0; i<arr.length; i++)", "for(int x : arr)", "for(i in arr)", "foreach(int x in arr)"),
                1,
                "Java の拡張for文は for(型 変数 : 配列) です。"
        ));

        // Q7. if文
        list.add(new Question(
                "次のコードの出力は？ int x = 5; if(x > 3 && x < 10) { System.out.println(\"OK\"); }",
                List.of("OK", "何も出力されない", "エラー", "5"),
                0,
                "5 > 3 かつ 5 < 10 なので条件は真。OKが出力されます。"
        ));

        // Q8. switch
        list.add(new Question(
                "switch文では、各caseの最後に何を書く必要があるか？",
                List.of("continue", "break", "return", "end"),
                1,
                "breakを書かないと次のcaseへ処理が流れ（フォールスルー）てしまいます。"
        ));

        // Q9. 可変長引数
        list.add(new Question(
                "メソッドの可変長引数はどう宣言するか？",
                List.of("int ...args", "int[] args", "int args[]", "int...args"),
                0,
                "可変長引数は型 ... 変数名 で宣言します。スペースはあってもなくても大丈夫です。"
        ));

        // Q10. null
        list.add(new Question(
                "次のコードはコンパイルされるか？ String str = null; System.out.println(str.length());",
                List.of("コンパイルされ、正常に動作する", "コンパイルされるが、実行時にNullPointerException", "コンパイルエラー", "警告が出る"),
                1,
                "nullのメソッド呼び出しはコンパイルは通りますが、実行時にNullPointerExceptionが発生します。"
        ));

        String json = QuizExporter.exportQuestions(list);

                Path outputPath = resolveOutputPath();
        try {
            Files.createDirectories(outputPath.getParent());
            Files.writeString(outputPath, json);
            System.out.println("questions.json を出力しました: " + outputPath);
        } catch (IOException e) {
            System.out.println("ファイルの出力に失敗しました。");
            System.out.println(e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
