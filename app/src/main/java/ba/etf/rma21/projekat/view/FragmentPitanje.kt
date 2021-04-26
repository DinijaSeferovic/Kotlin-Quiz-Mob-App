package ba.etf.rma21.projekat.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.MainActivity.Companion.saveViewModel
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.viewmodel.SendDataViewModel


class FragmentPitanje : Fragment(){
    private lateinit var textPitanja: TextView
    private lateinit var odgovoriLista: ListView
    private lateinit var viewModel: SendDataViewModel
    private lateinit var pitanje: Pitanje
    private lateinit var mapaOdgovora: HashMap<Pitanje, Int>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.pokusaj_fragment, container, false)
        (activity as MainActivity).switchNavigation("pokusaj")

        textPitanja= view!!.findViewById(R.id.textPitanja)
        odgovoriLista= view!!.findViewById(R.id.odgovoriLista)

        viewModel = ViewModelProvider(requireActivity()).get(SendDataViewModel::class.java)
        pitanje= viewModel.dataPitanje.value!!

        textPitanja.text= pitanje.tekst

        val odgovoriAdapter: ArrayAdapter<*>
        odgovoriAdapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, pitanje.opcije)
        odgovoriLista.adapter = odgovoriAdapter

        mapaOdgovora= saveViewModel.getDataOdgovor()

        if (mapaOdgovora.contains(pitanje)) {
            odgovoriLista.setItemChecked(saveViewModel.getDataOdgovor().get(pitanje)!!, true)
            odgovoriLista.setSelection(saveViewModel.getDataOdgovor().get(pitanje)!!)

        }

        odgovoriLista.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedOdgovor = parent.getItemAtPosition(position)
            if (selectedOdgovor.toString().equals(pitanje.opcije[pitanje.tacan])) {

                odgovoriLista.get(position).setBackgroundColor(Color.parseColor("#3DDC84"))
            }
            else {
                view.setBackgroundColor(Color.parseColor("#DB4F3D"))
                odgovoriLista.get(pitanje.tacan).setBackgroundColor(Color.parseColor("#3DDC84"))
            }
            odgovoriLista.setEnabled(false)
            mapaOdgovora.put(pitanje, position)
            saveViewModel.saveDataOdgovor(mapaOdgovora)

        }


        return view
    }




    companion object {
        fun newInstance(): FragmentPitanje = FragmentPitanje()
    }
}


