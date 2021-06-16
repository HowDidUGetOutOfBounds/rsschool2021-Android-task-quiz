package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.fragments.QuestionFragment

class MainActivity : AppCompatActivity(), QuestionFragment.OnFragmentSendDataListener {

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
        updateAnswer(fragId, answer)
        openQuestion(fragId + direction)
    }

    override fun onSubmit(lastId: Int) {
        val correct = calculateResult(lastId+1)
        openResultFragmetn(lastId+1, correct)
    }

    fun calculateResult(amountOfQuestions: Int): Int{
        var correct = 0

        var iterableVar: Int
        for(mId in 0..amountOfQuestions-1)
        {
            val sp = getPreferences(MODE_PRIVATE)
            iterableVar = sp.getInt(Utills.FRAGMENT_ID+mId, -1)

            if(iterableVar == Utills.answersList.get(mId))
            {
                correct++
            }
        }

        return correct
    }

    fun openResultFragmetn(amountOfQuestions: Int, correct: Int) {

    }

    fun updateAnswer(mId:Int, answ:Int) {
        val sp = getPreferences(MODE_PRIVATE)
        val editor = sp.edit()

        editor.putInt(Utills.FRAGMENT_ID+mId, answ)
        editor.apply()
        Log.d("TAG", "updateAnswer: " + Utills.FRAGMENT_ID+mId + " " + answ)
    }

}