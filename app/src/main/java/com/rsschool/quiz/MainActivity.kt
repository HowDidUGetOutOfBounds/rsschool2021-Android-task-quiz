package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    private fun openQuestion(questionNumber: Int, style: Int = 0) {
        val fragment = QuestionFragment.newInstance(questionNumber, style)
        val transition = supportFragmentManager.beginTransaction();
        transition.replace(R.id.container, fragment, "FRAGMENT_$questionNumber")
            .commit()

    }

    override fun onSendData() {
        TODO("Not yet implemented")
    }
}