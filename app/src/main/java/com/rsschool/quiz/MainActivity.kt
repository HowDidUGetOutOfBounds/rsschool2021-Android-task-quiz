package com.rsschool.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.fragments.QuestionFragment
import com.rsschool.quiz.fragments.ResultFragment

class MainActivity : AppCompatActivity(), QuestionFragment.OnFragmentSendDataListener,
    ResultFragment.OnFragmentResultDataListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        openQuestion(0)
    }

    private fun openQuestion(questionNumber: Int) {
        val fragment = QuestionFragment.newInstance(questionNumber)
        val transition = supportFragmentManager.beginTransaction();
        transition.replace(R.id.container, fragment, "FRAGMENT_$questionNumber")
            .commit()
    }

    override fun onSendData(fragId: Int, answer: Int, direction: Int) {
        if (answer != -1) {
            updateAnswer(fragId, answer)
        } else if (answers[fragId] != -1) {
            updateAnswer(fragId, answers[fragId])
        }

        openQuestion(fragId + direction)
    }

    override fun onSubmit(lastId: Int, answer: Int) {
        if (answer != -1) {
            updateAnswer(lastId, answer)
        } else if (answers[lastId] != -1) {
            updateAnswer(lastId, answers[lastId])
        }

        val correct = calculateResult(lastId + 1)
        openResultFragment(lastId + 1, correct)
    }

    fun calculateResult(amountOfQuestions: Int): Int {
        var correct = 0

        var iterableVar: Int
        for (mId in 0..amountOfQuestions - 1) {
            iterableVar = answers[mId]
            if (iterableVar + 1 == Utills.answersList[mId]) {
                correct++
            }
        }

        return correct
    }

    fun openResultFragment(amountOfQuestions: Int, correct: Int) {
        val fragment = ResultFragment.newInstance(amountOfQuestions, correct)
        val transition = supportFragmentManager.beginTransaction();
        transition.replace(R.id.container, fragment, "FRAGMENT_RESULT")
            .commit()
    }

    fun updateAnswer(mId: Int, answ: Int) {
        answers[mId] = answ
    }

    companion object {
        var answers = mutableListOf<Int>(-1, -1, -1, -1, -1)
    }

    override fun onBack() {
        Log.d("b", "b")
        answers = mutableListOf<Int>(-1, -1, -1, -1, -1)
        openQuestion(0)
    }

    override fun onShare(all: Int, my: Int) {
        val response = generateResponce("$my/$all")
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, response)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Send to your friend!")
        startActivity(shareIntent)
    }
    
    fun generateResponce(data: String): String {
        var result = ""
        result += resources.getString(R.string.result) + data + "\n"
        for (i in 0..Utills.questionsList.size - 1) {
            result += Utills.questionsList[i] + " and your answer:" + answers[i] + "\n"
        }

        return result
    }
}