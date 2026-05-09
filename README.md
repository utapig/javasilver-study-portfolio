# JavaSilver Study Portfolio

Java Silver合格に向けた学習記録用リポジトリです。
Javaの基礎文法・オブジェクト指向・テスト観点を、複数の小規模アプリ制作を通して学習しています。

## Overview

- 目的: Java Silver合格に必要な知識を実装で定着させる
- 学習形式: 小規模アプリを段階的に制作し、レビューと改修を繰り返す
- 現在の進捗: app01 を完成、app02/app03 を計画中

## AI Usage Policy

- AIは要件整理、レビュー、実装補助、ドキュメント整備を担当
- 学習内容の理解確認、最終判断、最終動作確認は作成者本人が担当
- 各アプリREADMEにAI担当範囲を記録

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
cd app01_quiz
javac -encoding UTF-8 src/main/java/quiz/Question.java src/main/java/quiz/QuizExporter.java src/main/java/quiz/Main.java -d out
java -cp out Main
```

詳細は [app01_quiz/README.md](app01_quiz/README.md) を参照してください。
