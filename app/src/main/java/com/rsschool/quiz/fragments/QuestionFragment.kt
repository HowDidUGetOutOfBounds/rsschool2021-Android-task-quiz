package com.rsschool.quiz.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.rsschool.quiz.MainActivity.Companion.quizResults
import com.rsschool.quiz.R
import com.rsschool.quiz.Utills
import com.rsschool.quiz.Utills.FRAGMENT_ID
import com.rsschool.quiz.Utills.FRAGMENT_STYLE
import com.rsschool.quiz.databinding.FragmentQuizBinding

class QuestionFragment : Fragment() {

    private var fragmentSendDataListener: OnFragmentSendDataListener? = null
    private var _binding: FragmentQuizBinding? = null
    private var isLast = false
    private var fragId: Int = 0

    interface OnFragmentSendDataListener {
        fun onSendData()
        fun onSubmit()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentSendDataListener = context as OnFragmentSendDataListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val styleId = arguments?.getInt(FRAGMENT_STYLE) ?: 0
        fragId = arguments?.getInt(FRAGMENT_ID) ?: 0
        val mConextThemeWrapper: ContextThemeWrapper
        val window = activity?.window

        when (styleId) {
            0 -> {
                mConextThemeWrapper = ContextThemeWrapper(activity, R.style.Theme_Quiz_Fifth)
                _binding?.previousButton?.isEnabled = false
                window?.statusBarColor =
                    context?.resources?.getColor(R.color.deep_orange_100_dark)!!
            }
            1 -> {
                mConextThemeWrapper = ContextThemeWrapper(activity, R.style.Theme_Quiz_Second)
                window?.statusBarColor = context?.resources?.getColor(R.color.yellow_100_dark)!!
            }
            2 -> {
                mConextThemeWrapper = ContextThemeWrapper(activity, R.style.Theme_Quiz_Third)
                window?.statusBarColor =
                    context?.resources?.getColor(R.color.light_green_100_dark)!!

            }
            3 -> {
                mConextThemeWrapper = ContextThemeWrapper(activity, R.style.Theme_Quiz_Fouth)
                window?.statusBarColor = context?.resources?.getColor(R.color.deep_purple_100)!!
            }
            else -> {
                mConextThemeWrapper = ContextThemeWrapper(activity, R.style.Theme_Quiz_Fifth)
                window?.statusBarColor = context?.resources?.getColor(R.color.cyan_100)!!
                _binding?.nextButton?.text = getString(R.string.submit)
                isLast = true
            }
        }
        val localLayoutInflater = inflater.cloneInContext(mConextThemeWrapper)

        _binding = FragmentQuizBinding.inflate(localLayoutInflater, container, false)
        val view = _binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        _binding?.question?.text = Utills.questionsList.get(fragId)

        _binding?.apply {
            optionOne.text = Utills.answers[fragId][0]
            optionTwo.text = Utills.answers[fragId][1]
            optionThree.text = Utills.answers[fragId][2]
            optionFour.text = Utills.answers[fragId][3]
            optionFive.text = Utills.answers[fragId][4]
        }



        _binding?.nextButton?.setOnClickListener {
            if (!isLast) {
                fragmentSendDataListener?.onSendData()
            } else {
                fragmentSendDataListener?.onSubmit()
            }
        }
    }

    companion object {
        fun newInstance(fragmentId: Int, fragmentStyle: Int = 0): QuestionFragment {
            val fragment = QuestionFragment()
            val args = Bundle()
            args.putInt(FRAGMENT_ID, fragmentId)
            fragment.arguments = args
            return fragment
        }
    }
}