package com.example.lab_week_03_sandy

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

//    private lateinit var coffeeListener: CoffeeListener;

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is CoffeeListener){
//            coffeeListener = context
//        } else {
//            throw RuntimeException("Must implement CoffeeListener")
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coffeeList = listOf<View>(
            view.findViewById(R.id.affogato),
            view.findViewById(R.id.americano),
            view.findViewById(R.id.latte),
            view.findViewById(R.id.kenangan_mantan),
            view.findViewById(R.id.vanilla_latte),
            view.findViewById(R.id.butterscotch_kenangan_frappe),
            view.findViewById(R.id.flat_white),
            view.findViewById(R.id.mocha),
            view.findViewById(R.id.cold_brew),
            view.findViewById(R.id.caramel_latte),
            view.findViewById(R.id.spanish_latte),
            view.findViewById(R.id.butterscotch_sea_salt_latte),
            view.findViewById(R.id.matcha_espresso),
            view.findViewById(R.id.pandan_aren_malt_latte),
            view.findViewById(R.id.butterscotch_sea_salt_crumble),
            view.findViewById(R.id.oatside_kenangan_mantan),
            view.findViewById(R.id.oatside_latte),
            view.findViewById(R.id.oatside_matcha_latte),
        )
        coffeeList.forEach{
            coffee ->
            val fragmentBundle = Bundle()
            fragmentBundle.putInt(COFFEE_ID, coffee.id)
            coffee.setOnClickListener {
                coffee.findNavController().navigate(
                    R.id.coffee_id_action, fragmentBundle
                )
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        const val COFFEE_ID = "COFFEE_ID"
    }
}