let questions = []; // 問題一覧（JSONから読んだ配列）
let result = []; // 結果（正解・不正解の配列）
let currentIndex = 0; // 今何問目か
let score = 0; // 正解数
let quizContainer; // クイズの表示エリア

fetch("questions.json")
  .then((response) => response.json())
  .then((data) => {
    questions = data;
    quizContainer = document.getElementById("quiz-container");
    // showResultTest();  // テスト用の関数呼び出し
    showStartScreen();
  })
  .catch((error) => {
    console.error("Error fetching questions:", error);
    quizContainer = document.getElementById("quiz-container");
    quizContainer.innerHTML = `
    <p class="font-black text-2xl" style="color: #6b9fae;">
      <span class="drop-shadow-lg">問題文の取得に失敗しました。</span>
    </p>  `;
  });

function showStartScreen() {
  quizContainer.innerHTML = `
    <div
      class="px-20 py-10 w-full"
    >
      <img src="../images/title.png" alt="Java クイズ" />
    </div>
    <p id="startTitle" class="cursor-pointer mt-8 font-black text-2xl tracking-widest" style="color: #6b9fae;">
      <span class="drop-shadow-lg">Click to Start</span>
    </p>  `;
  const startTitle = document.getElementById("startTitle");
  if (startTitle) {
    startTitle.addEventListener("click", showQuestion, { once: true });
  }
}

function showQuestion() {
  if (questions.length === 0) {
    quizContainer.innerHTML = `
    <p class="font-black text-2xl" style="color: #6b9fae;">
      <span class="drop-shadow-lg">問題が登録されていません。</span>
    </p>  `;
    return;
  }

  const q = questions[currentIndex];
  quizContainer.innerHTML = `
        <div class="relative inline-block">
          <p class="absolute top-2 my-4 w-full flex items-center justify-center font-bold" style="color: #f2d785;">${currentIndex + 1} / ${questions.length}</p>
          <img src="../images/Question.png" alt="問題文">
          <h3 class="absolute inset-0 flex items-center justify-center text-2xl font-bold" style="color: #f2d785;">${q.text}</h3>
        </div>
        <ul class="grid grid-cols-2 gap-4 mt-6 mx-4 list-none p-0">
          <li class="relative h-16 flex items-center justify-center">
            <img src="../images/Answer.png" alt="" class="absolute inset-0 w-full h-full object-fill choice hover:opacity-70 transition-opacity cursor-pointer">
            <span class="relative z-10 pointer-events-none font-bold" style="color: #6b9fae;"> ${q.choices[0]}</span>
          </li>
          <li class="relative h-16 flex items-center justify-center">
            <img src="../images/Answer.png" alt="" class="absolute inset-0 w-full h-full object-fill choice hover:opacity-70 transition-opacity cursor-pointer">
            <span class="relative z-10 pointer-events-none font-bold" style="color: #6b9fae;"> ${q.choices[1]}</span>
          </li>
          <li class="relative h-16 flex items-center justify-center">
            <img src="../images/Answer.png" alt="" class="absolute inset-0 w-full h-full object-fill choice hover:opacity-70 transition-opacity cursor-pointer">
            <span class="relative z-10 pointer-events-none font-bold" style="color: #6b9fae;"> ${q.choices[2]}</span>
          </li>
          <li class="relative h-16 flex items-center justify-center">
            <img src="../images/Answer.png" alt="" class="absolute inset-0 w-full h-full object-fill choice hover:opacity-70 transition-opacity cursor-pointer">
            <span class="relative z-10 pointer-events-none font-bold" style="color: #6b9fae;"> ${q.choices[3]}</span>
          </li>
        </ul>
        <!-- 選択肢ボタン -->
    `;
  const choiceButtons = document.getElementsByClassName("choice");
  Array.from(choiceButtons).forEach((button, index) => {
    button.addEventListener("click", (e) => {
      e.stopPropagation();
      if (index === q.answerIndex) {
        score++;
        result.push(true);
        launchConfetti();

        quizContainer.innerHTML = `
        <div>
          <p style="color: #6b9fae;">${currentIndex + 1} / ${questions.length}</p>
          <h3 class="text-4xl font-bold mt-4" style="color: #6b9fae;">正解です！</h3>
        </div>
        <h3 class="text-2xl font-bold mt-10 mb-2" style="color: #575656;">解説</h3>
        <p class="text-lg" style="color: #575656;">${q.explanation}</p>
        <p class="cursor-pointer mt-12 font-bold" id="nextBtn" style="color: #6b9fae;">クリックして次へ</p>
      `;
      } else {
        result.push(false);
        quizContainer.innerHTML = `
        <div>
          <p style="color: #6b9fae;">${currentIndex + 1} / ${questions.length}</p>
          <h3 class="text-4xl font-bold mt-4" style="color: #6b9fae;">不正解です...</h3>
        </div>
        <h3 class="text-2xl font-bold mt-10 mb-2" style="color: #575656;">解説</h3>
        <p class="text-lg" style="color: #575656;">${q.explanation}</p>
        <p class="cursor-pointer mt-12 font-bold" id="nextBtn" style="color: #6b9fae;">クリックして次へ</p>
      `;
      }

      document.addEventListener("click", function onNext() {
        document.removeEventListener("click", onNext);
        if (currentIndex < questions.length - 1) {
          currentIndex++;
          showQuestion();
        } else {
          showResult();
        }
      });
    });
  });
}

function launchConfetti() {
  if (typeof confetti !== "function") {
    return;
  }

  confetti({
    particleCount: 120,
    spread: 70,
    origin: { y: 0.65 },
  });
}

function showResult() {
  if (score < questions.length - 2) {
    quizContainer.innerHTML = `
          <img src="../images/result.png" alt="結果発表" class="w-[500px] h-auto mx-auto mb-16"/>
          <img src="../images/beginner.png" alt="" class="w-[240px] md:w-[300px] h-auto mx-auto">
          <h2 class="text-4xl font-bold mt-6" style="color: #6b9fae;">あなたはJavaビギナーです</h2>
          <p class="text-2xl mt-2" style="color: #575656;">（${score} / ${questions.length}問正解）</p>
          <button
            id="start-button"
            class="mt-10 px-6 py-3 bg-blue-300 text-yellow-100 font-bold rounded-lg text-xl hover:bg-blue-400 transition-colors"
          >
            もう一度挑戦する
          </button>
      `;
  } else if (score < questions.length) {
    quizContainer.innerHTML = `
          <img src="../images/result.png" alt="結果発表" class="w-[500px] h-auto mx-auto mb-16"/>
          <img src="../images/professional.png" alt="" class="w-[240px] md:w-[300px] h-auto mx-auto">
          <h2 class="text-4xl font-bold mt-6" style="color: #6b9fae;">あなたはJavaプロフェッショナルです</h2>
          <p class="text-2xl mt-2" style="color: #575656;">（${score} / ${questions.length}問正解）</p>
          <button
            id="start-button"
            class="mt-10 px-6 py-3 bg-blue-300 text-yellow-100 font-bold rounded-lg text-xl hover:bg-blue-400 transition-colors"
          >
            もう一度挑戦する
          </button>
      `;
  } else {
    quizContainer.innerHTML = `
          <img src="../images/result.png" alt="結果発表" class="w-[500px] h-auto mx-auto mb-16"/>
          <img src="../images/master.png" alt="" class="w-[240px] md:w-[300px] h-auto mx-auto">
          <h2 class="text-4xl font-bold mt-6" style="color: #6b9fae;">あなたはJavaマスターです！</h2>
          <p class="text-2xl mt-2" style="color: #575656;">（${score} / ${questions.length}問正解）</p>
          <button
            id="start-button"
            class="mt-10 px-6 py-3 bg-blue-300 text-yellow-100 font-bold rounded-lg text-xl hover:bg-blue-400 transition-colors"
          >
            もう一度挑戦する
          </button>
      `;
  }

  const startButton = document.getElementById("start-button");
  if (startButton) {
    startButton.addEventListener("click", () => {
      // スコアとインデックスをリセットして最初の画面に戻る
      score = 0;
      currentIndex = 0;
      result = [];
      showStartScreen();
    });
  }
}

// function showResultTest() {
//   const testbutton = document.getElementById("testbutton");
//   score = 10; // テスト用にスコアを設定
//   if (!testbutton) {
//     return;
//   }
//   testbutton.addEventListener("click", () => {
//     showResult();
//   });
// }
