package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.R

class FragmentPoruka : Fragment() {
    private lateinit var textView: TextView
    private var poruka="Uspješno ste upisani u grupu "


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.poruka_fragment, container, false)

        var bundle: Bundle? = getArguments()
        var grupa= bundle?.getString("posGru")
        var predmet= bundle?.getString("posPred")

        textView = view.findViewById(R.id.tvPoruka)
        poruka+=grupa+" predmeta "+predmet
        textView.setText(poruka)
        return view
    }

    companion object {
        fun newInstance(): FragmentPoruka = FragmentPoruka()
    }

}