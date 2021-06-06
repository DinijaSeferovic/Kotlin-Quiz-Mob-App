package ba.etf.rma21.projekat.view

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.viewmodel.PitanjeKvizViewModel
import ba.etf.rma21.projekat.viewmodel.SendDataViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentPokusaj (var pitanja: List<Pitanje>): Fragment(){
    private var kvizPitanjeViewModel =  PitanjeKvizViewModel()
    var spannable = SpannableString("")
    private lateinit var viewModel: SendDataViewModel


    //Listener za click
    private val mOnNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { item ->

        val pitanjeFragment = FragmentPitanje.newInstance()
        openFragment(pitanjeFragment)
        viewModel.sendDataPitanje(pitanja[item.order], item.order)
        return@OnNavigationItemSelectedListener true

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.framePitanje, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

// dohvatanje pitanja kliknutog kviza
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.pokusaj_fragment, container, false)
        (activity as MainActivity).switchNavigation("pokusaj")
        navigacijaPitanja= view!!.findViewById(R.id.navigacijaPitanja)
        navigacijaPitanja.setNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        viewModel = ViewModelProvider(requireActivity()).get(SendDataViewModel::class.java)
        /*viewModel.dataKviz.observe(viewLifecycleOwner, Observer {
        })*/
        //pitanja= kvizPitanjeViewModel.getPitanja(viewModel.dataKviz.value!!.naziv,viewModel.dataKviz.value!!.nazivPredmeta)
        kvizPitanjeViewModel.getPitanja(viewModel.dataKviz.value!!.id, onSuccess=::onSuccess, onError=::onError)

        return view
    }

//dodavanje pitanja u sideNav, bojenje i setanje prvog pitanja
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)



}


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.nav_pitanja, menu)

    }

    fun onSuccess(p: List<Pitanje>){
        GlobalScope.launch (Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                pitanja= p
                for (item in pitanja.indices) {
                    navigacijaPitanja.menu.add(0, item, item, Integer.toString(item + 1))
                    val s = SpannableString(navigacijaPitanja.menu.getItem(item).title.toString())
                    s.setSpan(BackgroundColorSpan(Color.BLACK), 0, s.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    s.setSpan(ForegroundColorSpan(Color.WHITE), 0, s.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    navigacijaPitanja.menu.getItem(item).setTitle(s)

                }
                val pitanjeFragment = FragmentPitanje.newInstance()
                viewModel.sendDataPitanje(pitanja[0],0)
                viewModel.sendNav(navigacijaPitanja.menu)
                viewModel.sendBrojPitanja(pitanja.size)
                viewModel.sendBrojTacnih(0)
                viewModel.sendTacnost(0.0)
                openFragment(pitanjeFragment)
            }
        }
    }

    fun onError() {
        val toast = Toast.makeText(context, "Greska", Toast.LENGTH_SHORT)
        toast.show()
    }
    companion object {
        private var pitanja = listOf<Pitanje>()
        fun newInstance(): FragmentPokusaj = FragmentPokusaj(pitanja)

        lateinit var navigacijaPitanja: NavigationView
    }
}