package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R

class FragmentPitanje : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.pokusaj_fragment, container, false)
        (activity as MainActivity).switchNavigation("pokusaj")
        return view
    }
    companion object {
        fun newInstance(): FragmentPitanje = FragmentPitanje()
    }
}
