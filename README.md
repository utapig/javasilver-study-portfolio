# JavaSilver Study Portfolio

Java Silver合格に向けた学習記録用リポジトリです。
Javaの基礎文法・オブジェクト指向・テスト観点を、複数の小規模アプリ制作を通して学習しています。

## Learning Goal

- Java Silver範囲の知識を実装で定着させる
- JavaScriptで画面実装の基礎を身につける
- 要件定義 -> 設計 -> 実装 -> テスト -> 改修の流れを実践する

## Applications

| App | Theme | Main Tech | Status |
|---|---|---|---|
| [app01_quiz](app01_quiz) | Java学習クイズ | Java, JavaScript, Tailwind CSS | Completed |
| [app02_todo](app02_todo) | ToDo管理 | Java / JavaScript (planned) | Planned |
| [app03_inventory](app03_inventory) | 在庫管理 | Java / JavaScript (planned) | Planned |

## Common Development Rules

- JavaコンパイルはUTF-8指定を必須とする
- 1アプリごとにREADMEと成果物を残す
- レビュー結果は各アプリの成果物に記録する
- AI使用時は担当範囲をREADMEに明記する

## app01 Quick Start

```powershell
cd "c:\Users\utapi\Documents\10_study\20260313_JavaStudy\app01_quiz"
javac -encoding UTF-8 src/main/java/quiz/Question.java src/main/java/quiz/QuizExporter.java src/main/java/quiz/Main.java -d out
java -cp out Main
```

詳細は [app01_quiz/README.md](app01_quiz/README.md) を参照してください。
