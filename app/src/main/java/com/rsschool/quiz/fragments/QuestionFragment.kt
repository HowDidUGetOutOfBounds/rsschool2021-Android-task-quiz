package com.rsschool.quiz.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.rsschool.quiz.MainActivity.Companion.answers
import com.rsschool.quiz.R
import com.rsschool.quiz.Utills
import com.rsschool.quiz.Utills.FRAGMENT_ID
import com.rsschool.quiz.databinding.FragmentQuizBinding

class QuestionFragment : Fragment() {

    private var fragmentSendDataListener: OnFragmentSendDataListener? = null
    private var _binding: FragmentQuizBinding? = null
    private var isLast = false
    private var isFirst = false
    private var fragId: Int = 0
    private var answer: Int = -1

    interface OnFragmentSendDataListener {
        fun onSendData(fragId: Int, answer: Int, direction: Int = 1)
        fun onSubmit(lastId: Int, answer: Int)
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

        fragId = arguments?.getInt(FRAGMENT_ID) ?: 0
        val mConextThemeWrapper: ContextThemeWrapper
        val window = activity?.window

        when (fragId) {
            0 -> {
                mConextThemeWrapper = ContextThemeWrapper(activity, R.style.Theme_Quiz_First)
                window?.statusBarColor =
                    context?.resources?.getColor(R.color.deep_orange_100_dark)!!
                isFirst = true
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
            }
        }
        isLast = (Utills.questionsList.size == fragId+1)

        val localLayoutInflater = inflater.cloneInContext(mConextThemeWrapper)

        _binding = FragmentQuizBinding.inflate(localLayoutInflater, container, false)
        val view = _binding!!.root

        if(answers[fragId] != -1)
        {
            when(answers[fragId]) {
                0 -> {
                    _binding?.optionOne?.isChecked = true
                }
                1->{
                    _binding?.optionTwo?.isChecked = true
                }
                2->{
                    _binding?.optionThree?.isChecked = true
                }
                3->{
                    _binding?.optionFour?.isChecked = true
                }
                4->{
                    _binding?.optionFive?.isChecked = true
                }
            }
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var retVal = 0

        _binding?.question?.text = Utills.questionsList.get(fragId)

        _binding?.apply {
            optionOne.text = Utills.answers[fragId][0]
            optionTwo.text = Utills.answers[fragId][1]
            optionThree.text = Utills.answers[fragId][2]
            optionFour.text = Utills.answers[fragId][3]
            optionFive.text = Utills.answers[fragId][4]

            _binding?.nextButton?.isEnabled = answers[fragId] != -1

            optionOne.setOnClickListener {
                optionClick(1)
            }
            optionTwo.setOnClickListener {
                optionClick(2)
            }
            optionThree.setOnClickListener {
                optionClick(3)
            }
            optionFour.setOnClickListener {
                optionClick(4)
            }
            optionFive.setOnClickListener {
                optionClick(5)
            }
        }



        if(isFirst){
            _binding?.toolbar?.navigationIcon?.alpha = 0
            _binding?.toolbar?.isEnabled = false
            _binding?.previousButton?.isEnabled = false

        }
        else{
            _binding?.toolbar?.setNavigationOnClickListener {
                fragmentSendDataListener?.onSendData(fragId, answer, -1)
            }
        }


        if(isLast)
        {
            _binding?.nextButton?.text = "Submit"
        }

        _binding?.toolbar?.title = "Question ${fragId + 1}"

        _binding?.nextButton?.setOnClickListener {
            if (!isLast) {
                fragmentSendDataListener?.onSendData(fragId,answer)
            } else {
                fragmentSendDataListener?.onSubmit(fragId, answer)
            }
        }

        _binding?.previousButton?.setOnClickListener{
            if (!isFirst) {
                fragmentSendDataListener?.onSendData(fragId,answer, -1)
            }
        }

    }

    fun optionClick(retVal: Int)
    {
        answer = retVal - 1
        _binding?.nextButton?.isEnabled = true
    }


    companion object {
        fun newInstance(fragmentId: Int): QuestionFragment {
            val fragment = QuestionFragment()
            val args = Bundle()
            args.putInt(FRAGMENT_ID, fragmentId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}