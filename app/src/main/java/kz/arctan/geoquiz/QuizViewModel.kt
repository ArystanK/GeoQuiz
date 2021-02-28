package kz.arctan.geoquiz

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {
    private val questionBank = listOf(
            Question(R.string.question_australia, true),
            Question(R.string.question_oceans, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, false),
            Question(R.string.question_americas, true),
            Question(R.string.question_asia, true))

    private var disabledButtons = mutableListOf<Int>() // List of indexes of question in which buttons disabled (question already have been answered)

    var currentIndex = 0

    var scoreCount = 0

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val isQuizComplete: Boolean
        get() = disabledButtons.size == questionBank.size

    val isCurrentButtonDisabled: Boolean
        get() = currentIndex in disabledButtons

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrev() {
        currentIndex = if (currentIndex != 0)
            (currentIndex - 1) % questionBank.size
        else
            questionBank.size - 1
    }

    fun showResult(context: Context) {
        Toast.makeText(context, "Congratulation! You completed quiz. Percent of correct answers is ${scoreCount.toDouble() / questionBank.size * 100}", Toast.LENGTH_LONG).show()
    }

    fun addCurrentToDisabledButtons() {
        disabledButtons.add(currentIndex)
    }
}