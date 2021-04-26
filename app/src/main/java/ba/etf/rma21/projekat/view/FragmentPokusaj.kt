package ba.etf.rma21.projekat.view

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.*
import androidx.core.text.toSpannable
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.viewmodel.PitanjeKvizViewModel
import ba.etf.rma21.projekat.viewmodel.SendDataViewModel
import com.google.android.material.navigation.NavigationView

class FragmentPokusaj : Fragment(){
    private var kvizPitanjeViewModel =  PitanjeKvizViewModel()
    private lateinit var sideNavigation: NavigationView
    private var pitanja = listOf<Pitanje>()
    var spannable = SpannableString("")
    private lateinit var viewModel: SendDataViewModel

    //Listener za click
    private val mOnNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { item ->
        var redniBr: Int =0
        for (p: Pitanje in pitanja) {
            if (item.itemId.equals(redniBr)) {
                val pitanjeFragment = FragmentPitanje.newInstance()
                openFragment(pitanjeFragment)
                redniBr++;
                viewModel.sendDataPitanje(pitanja[redniBr])
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.pokusaj_fragment, container, false)
        (activity as MainActivity).switchNavigation("pokusaj")
        sideNavigation= view!!.findViewById(R.id.navigacijaPitanja)
        sideNavigation.setNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        viewModel = ViewModelProvider(requireActivity()).get(SendDataViewModel::class.java)
        /*viewModel.dataKviz.observe(viewLifecycleOwner, Observer {
        })*/
        pitanja= kvizPitanjeViewModel.getPitanja(viewModel.dataKviz.value!!.naziv,viewModel.dataKviz.value!!.nazivPredmeta)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for (item in pitanja.indices) {
            sideNavigation.menu.add(0, item, item, Integer.toString(item+1))
            val s = SpannableString(sideNavigation.menu.getItem(item).title.toString())
            s.setSpan(BackgroundColorSpan(Color.BLACK),0, s.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            s.setSpan(ForegroundColorSpan(Color.WHITE),0, s.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            sideNavigation.menu.getItem(item).setTitle(s)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.nav_pitanja, menu)

    }
    companion object {
        fun newInstance(): FragmentPokusaj = FragmentPokusaj()
    }
}