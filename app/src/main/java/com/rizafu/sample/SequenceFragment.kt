package com.rizafu.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.rizafu.coachmark.CoachMark

/**
 * Created by RizaFu on 2/27/17.
 */

class SequenceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_multiple_target,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button1 = view.findViewById<Button>(R.id.button1)
        val button2 = view.findViewById<Button>(R.id.button2)
        val button3 = view.findViewById<Button>(R.id.button3)
        activity?.let {fragmentActivity ->
            button1.setOnClickListener {
                CoachMark.Builder(fragmentActivity)
                        .setTarget(button3, button2, button1)
                        .show()
            }
        }
    }

    companion object {

        fun newInstance(): SequenceFragment {

            val args = Bundle()

            val fragment = SequenceFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
