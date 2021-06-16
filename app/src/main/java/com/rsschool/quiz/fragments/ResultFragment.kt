package com.rsschool.quiz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.R
import com.rsschool.quiz.Utills
import com.rsschool.quiz.databinding.FragmentQuizBinding
import com.rsschool.quiz.databinding.ResultBinding

class ResultFragment: Fragment() {

    private var _binding: ResultBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ResultBinding.inflate(inflater, container, false)
        val view = _binding!!.root
        
        val window = activity?.window
        window?.statusBarColor =
            context?.resources?.getColor(R.color.deep_orange_100_dark)!!

        return view
    }

    companion object {
        fun newInstance(amountOfQuestions: Int, correct: Int): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putInt(Utills.AMOUNT_OF_QUESTIONS, amountOfQuestions)
            args.putInt(Utills.CORRECT, correct)
            fragment.arguments = args
            return fragment
        }
    }
}