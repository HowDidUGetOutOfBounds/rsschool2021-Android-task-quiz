package com.rsschool.quiz.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.rsschool.quiz.R
import com.rsschool.quiz.Utills
import com.rsschool.quiz.Utills.FRAGMENT_ID
import com.rsschool.quiz.Utills.FRAGMENT_STYLE
import com.rsschool.quiz.databinding.FragmentQuizBinding

class QuestionFragment : Fragment() {

    private var fragmentSendDataListener: OnFragmentSendDataListener? = null
    private var _binding: FragmentQuizBinding? = null

    interface OnFragmentSendDataListener {
        fun onSendData()
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
        val mConextThemeWrapper: ContextThemeWrapper

        when (styleId) {
            0 -> {
                mConextThemeWrapper = ContextThemeWrapper(activity, R.style.Theme_Quiz_First)
            }
            1 -> {
                mConextThemeWrapper = ContextThemeWrapper(activity, R.style.Theme_Quiz_Second)
            }
            2 -> {
                mConextThemeWrapper =  ContextThemeWrapper(activity, R.style.Theme_Quiz_Third)
            }
            3 -> {
                mConextThemeWrapper =  ContextThemeWrapper(activity, R.style.Theme_Quiz_Fouth)
            }
            else -> {
                mConextThemeWrapper =  ContextThemeWrapper(activity, R.style.Theme_Quiz_Fifth)
            }
        }
        val localLayoutInflater = inflater.cloneInContext(mConextThemeWrapper)

        _binding = FragmentQuizBinding.inflate(localLayoutInflater, container, false)
        val view = _binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(fragmentId: Int, fragmentStyle: Int = 0): QuestionFragment {
            val fragment = QuestionFragment()
            val args = Bundle()
            args.putInt(FRAGMENT_ID, fragmentId)
            args.putInt(FRAGMENT_STYLE, fragmentStyle)
            fragment.arguments = args
            return fragment
        }
    }
}