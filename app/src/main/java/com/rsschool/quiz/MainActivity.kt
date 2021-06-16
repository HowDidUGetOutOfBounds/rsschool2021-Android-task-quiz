package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.fragments.QuestionFragment
import com.rsschool.quiz.fragments.ResultFragment

class MainActivity : AppCompatActivity(), QuestionFragment.OnFragmentSendDataListener {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        openQuestion(0);


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
        } else if (answers[fragId] != -1)
        {
            updateAnswer(fragId, answers[fragId])
        }

        openQuestion(fragId + direction)
    }

    override fun onSubmit(lastId: Int) {
        val correct = calculateResult(lastId+1)
        openResultFragment(lastId+1, correct)
    }

    fun calculateResult(amountOfQuestions: Int): Int{
        var correct = 0

        var iterableVar: Int
        for(mId in 0..amountOfQuestions-1)
        {
            iterableVar = answers[mId]
            if(iterableVar == Utills.answersList.get(mId))
            {
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

    fun updateAnswer(mId:Int, answ:Int) {
        answers[mId] = answ
    }

    companion object{
        var answers = mutableListOf<Int>(-1, -1, -1, -1, -1)
    }

}