package com.rsschool.quiz.fragments

import android.content.Context
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

    private var _binding2: ResultBinding? = null


    private var fragmentResultDataListener: OnFragmentResultDataListener? = null

    interface OnFragmentResultDataListener {
        fun onBack()
        fun onShare()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentResultDataListener = context as OnFragmentResultDataListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding2 = ResultBinding.inflate(inflater, container, false)
        val view = _binding2!!.root
        
        val window = activity?.window
        window?.statusBarColor =
            context?.resources?.getColor(R.color.deep_orange_100_dark)!!

        val q1 = arguments?.getInt(Utills.AMOUNT_OF_QUESTIONS, 0)
        val q2 = arguments?.getInt(Utills.CORRECT, 0)

        _binding2?.textViewResult?.text = resources.getString(R.string.result) + q2 + "/" + q1
        _binding2?.exitButton?.setOnClickListener {
            activity?.finish()
        }
        _binding2?.backButton?.setOnClickListener {

        }

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