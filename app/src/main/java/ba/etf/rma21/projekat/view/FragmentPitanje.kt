package ba.etf.rma21.projekat.view

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.viewmodel.SendDataViewModel


class FragmentPitanje : Fragment(){
    private lateinit var textPitanja: TextView
    private lateinit var odgovoriLista: ListView
    private lateinit var viewModel: SendDataViewModel
    private lateinit var pitanje: Pitanje
    private var redniBr: Int=0
    private var mapaOdgovora: HashMap<Pitanje, Int> = hashMapOf<Pitanje, Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.pitanje_fragment, container, false)
        (activity as MainActivity).switchNavigation("pokusaj")

        textPitanja= view!!.findViewById(R.id.tekstPitanja)
        odgovoriLista= view!!.findViewById(R.id.odgovoriLista)

        viewModel = ViewModelProvider(requireActivity()).get(SendDataViewModel::class.java)
        pitanje= viewModel.dataPitanje.value!!
        redniBr= viewModel.redniBrPitanja.value!!
        var brojTacnih: Int= viewModel.brojTacnih.value!!
        textPitanja.text= pitanje.tekstPitanja

        val odgovoriAdapter: ArrayAdapter<*>
        odgovoriAdapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, pitanje.opcije)
        odgovoriLista.adapter = odgovoriAdapter

        if (viewModel.mapa.value != null) mapaOdgovora= viewModel.mapa.value!!

        if (mapaOdgovora.contains(pitanje)) {
            oznaciOdg(pitanje)
        }


        odgovoriLista.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedOdgovor = parent.getItemAtPosition(position)
            var tacnost: Boolean = false
            if (selectedOdgovor.toString().equals(pitanje.opcije[pitanje.tacan])) {
                tacnost=true
                odgovoriLista.get(position).setBackgroundColor(Color.parseColor("#3DDC84"))
                brojTacnih++
            }
            else {
                view.setBackgroundColor(Color.parseColor("#DB4F3D"))
                odgovoriLista.get(pitanje.tacan).setBackgroundColor(Color.parseColor("#3DDC84"))
                tacnost=false
            }
            odgovoriLista.setEnabled(false)
            if (!mapaOdgovora.containsKey(pitanje)) mapaOdgovora.put(pitanje, position)
            viewModel.saveDataOdgovor(mapaOdgovora)

            postaviNav(position, tacnost)

            viewModel.sendTacnost(brojTacnih/viewModel.brojPitanja.value!!.toDouble()*100)
            viewModel.sendBrojTacnih(brojTacnih)

        }
        return view
    }

    private fun postaviNav(position: Int, tacnost:Boolean) {

        val s = SpannableString(viewModel.navigacija.value?.getItem(redniBr)?.title.toString())
        if (position > -1 && tacnost) {
            s.setSpan(ForegroundColorSpan(Color.parseColor("#3DDC84")), 0, s.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        } else if (position > -1 && !tacnost) {
            s.setSpan(ForegroundColorSpan(Color.parseColor("#DB4F3D")), 0, s.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        viewModel.navigacija.value?.getItem(redniBr)?.setTitle(s)

    }

    private fun oznaciOdg(pitanje: Pitanje) {
        if (mapaOdgovora.get(pitanje)?.equals(pitanje.tacan)!!) {
            odgovoriLista.post { odgovoriLista.get(mapaOdgovora.get(pitanje)!!).setBackgroundColor(Color.parseColor("#3DDC84")) }
            postaviNav(redniBr, true)
        }
        else {
            odgovoriLista.post{ odgovoriLista.get(mapaOdgovora.get(pitanje)!!).setBackgroundColor(Color.parseColor("#DB4F3D")) }
            postaviNav(redniBr, false)
        }
        odgovoriLista.setEnabled(false)

    }

    companion object {

        fun newInstance(): FragmentPitanje = FragmentPitanje()
    }
}



