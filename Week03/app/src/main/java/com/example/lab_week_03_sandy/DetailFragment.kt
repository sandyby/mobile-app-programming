package com.example.lab_week_03_sandy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    companion object {
        private const val COFFEE_ID = "COFFEE_ID"
        fun newInstance(coffeeId: Int) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt(COFFEE_ID, coffeeId)
            }
        }
    }

    private var param1: String? = null
    private var param2: String? = null

    private val coffeeTitle: TextView?
        get() = view?.findViewById(R.id.coffee_title)
    private val coffeeDesc: TextView?
        get() = view?.findViewById(R.id.coffee_desc)

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
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coffeeId = arguments?.getInt(COFFEE_ID, 0) ?: 0
        setCoffeeData(coffeeId)
        val backBtn = view.findViewById<Button>(R.id.back_btn)
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    fun setCoffeeData(id: Int) {
        when (id) {
            R.id.affogato -> {
                coffeeTitle?.text = getString(R.string.affogato_title)
                coffeeDesc?.text = getString(R.string.affogato_desc)
            }

            R.id.americano -> {
                coffeeTitle?.text = getString(R.string.americano_title)
                coffeeDesc?.text = getString(R.string.americano_desc)
            }

            R.id.latte -> {
                coffeeTitle?.text = getString(R.string.latte_title)
                coffeeDesc?.text = getString(R.string.latte_desc)
            }
            R.id.kenangan_mantan -> {
                coffeeTitle?.text = getString(R.string.kenangan_mantan_title)
                coffeeDesc?.text = getString(R.string.kenangan_mantan_desc)
            }

            R.id.vanilla_latte -> {
                coffeeTitle?.text = getString(R.string.vanilla_latte_title)
                coffeeDesc?.text = getString(R.string.vanilla_latte_desc)
            }

            R.id.butterscotch_kenangan_frappe -> {
                coffeeTitle?.text = getString(R.string.butterscotch_kenangan_frappe_title)
                coffeeDesc?.text = getString(R.string.butterscotch_kenangan_frappe_desc)
            }

            R.id.flat_white -> {
                coffeeTitle?.text = getString(R.string.flat_white_title)
                coffeeDesc?.text = getString(R.string.flat_white_desc)
            }

            R.id.mocha -> {
                coffeeTitle?.text = getString(R.string.mocha_title)
                coffeeDesc?.text = getString(R.string.mocha_desc)
            }

            R.id.cold_brew -> {
                coffeeTitle?.text = getString(R.string.cold_brew_title)
                coffeeDesc?.text = getString(R.string.cold_brew_desc)
            }

            R.id.caramel_latte -> {
                coffeeTitle?.text = getString(R.string.caramel_latte_title)
                coffeeDesc?.text = getString(R.string.caramel_latte_desc)
            }

            R.id.spanish_latte -> {
                coffeeTitle?.text = getString(R.string.spanish_latte_title)
                coffeeDesc?.text = getString(R.string.spanish_latte_desc)
            }

            R.id.butterscotch_sea_salt_latte -> {
                coffeeTitle?.text = getString(R.string.butterscotch_sea_salt_latte_title)
                coffeeDesc?.text = getString(R.string.butterscotch_sea_salt_latte_desc)
            }

            R.id.matcha_espresso -> {
                coffeeTitle?.text = getString(R.string.matcha_espresso_title)
                coffeeDesc?.text = getString(R.string.matcha_espresso_desc)
            }

            R.id.pandan_aren_malt_latte -> {
                coffeeTitle?.text = getString(R.string.pandan_aren_malt_latte_title)
                coffeeDesc?.text = getString(R.string.pandan_aren_malt_latte_desc)
            }

            R.id.butterscotch_sea_salt_crumble -> {
                coffeeTitle?.text = getString(R.string.butterscotch_sea_salt_crumble_title)
                coffeeDesc?.text = getString(R.string.butterscotch_sea_salt_crumble_desc)
            }

            R.id.oatside_kenangan_mantan -> {
                coffeeTitle?.text = getString(R.string.oatside_matcha_latte_title)
                coffeeDesc?.text = getString(R.string.oatside_kenangan_mantan_desc)
            }

            R.id.oatside_latte -> {
                coffeeTitle?.text = getString(R.string.oatside_latte_title)
                coffeeDesc?.text = getString(R.string.oatside_latte_desc)
            }

            R.id.oatside_matcha_latte -> {
                coffeeTitle?.text = getString(R.string.oatside_matcha_latte_title)
                coffeeDesc?.text = getString(R.string.oatside_matcha_latte_desc)
            }
        }
    }
}