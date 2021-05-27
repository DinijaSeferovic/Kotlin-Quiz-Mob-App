package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.MainActivity.Companion.saveViewModel
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.viewmodel.SaveStateViewModel

class FragmentPoruka : Fragment() {
    private lateinit var textView: TextView
    private var poruka="Uspješno ste upisani u grupu "
    private var zavrsna="Završili ste kviz "


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.poruka_fragment, container, false)

        var bundle: Bundle? = getArguments()
        var grupa= bundle?.getString("posGru")
        var predmet= bundle?.getString("posPred")
        var kviz= bundle?.getString("kviz")
        var tacnost= bundle?.getString("tacnost")
        textView = view.findViewById(R.id.tvPoruka)
        poruka+= grupa+" predmeta "+ predmet
        zavrsna+= kviz+" sa tačnosti "+ tacnost

        if (saveViewModel.getPorukaFragment().equals("upisi")) {
            textView.setText(poruka)
        }
        else if (saveViewModel.getPorukaFragment().equals("predaj")) {
            textView.setText(zavrsna)
        }

        return view
    }

    companion object {
        fun newInstance(): FragmentPoruka = FragmentPoruka()
    }

}