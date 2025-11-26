package com.example.lab_week_10_sandy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_10_sandy.viewmodels.TotalViewModel

class FirstFragment : Fragment() {
    companion object {
        fun newInstance(param1: String, param2: String) =
            FirstFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareViewModel()
    }

    private fun updateText(total: Int) {
        view?.findViewById<TextView>(R.id.tv_total)?.text =
            getString(R.string.tv_total, total)
    }

    private fun prepareViewModel() {
        val viewModel =
            ViewModelProvider(requireActivity()).get(TotalViewModel::class.java)
        viewModel.total.observe(viewLifecycleOwner, {
            updateText(it)
        })
    }
}