package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.viewmodel.GrupaListViewModel
import ba.etf.rma21.projekat.viewmodel.KvizListViewModel
import ba.etf.rma21.projekat.viewmodel.PredmetListViewModel

class FragmentKvizovi : Fragment() {

    private lateinit var kvizovi: RecyclerView
    private lateinit var kvizAdapter: KvizListAdapter
    private var kvizListViewModel =  KvizListViewModel()
    private var predmetListViewModel =  PredmetListViewModel()
    private var grupaListViewModel =  GrupaListViewModel()
    private lateinit var mySpinner: Spinner
    private lateinit var spinnerAdapter: ArrayAdapter<Kviz>
    private var categories = arrayOf("Svi moji kvizovi", "Svi kvizovi", "Urađeni kvizovi", "Budući kvizovi", "Prošli kvizovi")
    private var data : List<Kviz> = emptyList()

    private fun initializeViews(inflater: LayoutInflater) {

        mySpinner.adapter = ArrayAdapter(inflater.context, android.R.layout.simple_dropdown_item_1line, categories)
        kvizAdapter = KvizListAdapter(data)
        kvizovi.adapter = kvizAdapter
        //spinner selection events
        mySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, position: Int, itemID: Long) {
                if (position >= 0 && position < categories.size) {
                    getSelectedCategoryData(position, inflater)
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }


    private fun getSelectedCategoryData(categoryID: Int, inflater: LayoutInflater) {

        spinnerAdapter = ArrayAdapter(inflater.context, android.R.layout.simple_dropdown_item_1line, data)

        //filter by id
        //for (Kviz in getAll()) {
        if (categoryID == 0) {
            data= kvizListViewModel.getMojiKvizovi()
            kvizAdapter.updateKvizovi(data)
        } else if (categoryID == 1) {
            data= kvizListViewModel.getKvizovi()
            kvizAdapter.updateKvizovi(data)
        } else if (categoryID == 2) {
            data= kvizListViewModel.getGotoviKvizovi()
            kvizAdapter.updateKvizovi(data)
        } else if (categoryID == 3) {
            data= kvizListViewModel.getBuduciKvizovi()
            kvizAdapter.updateKvizovi(data)
        } else if (categoryID == 4) {
            data= kvizListViewModel.getNeodrzaniKvizovi()
            kvizAdapter.updateKvizovi(data)

            //}

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.kvizovi_fragment, container, false)
        kvizovi = view.findViewById(R.id.listaKvizova)
        mySpinner = view.findViewById(R.id.filterKvizova)

        initializeViews(inflater)
        kvizovi.setLayoutManager(GridLayoutManager(activity, 2))
        predmetListViewModel.dodajPocetniUpisaniP()
        grupaListViewModel.dodajPocetnuUpisanuG()
        kvizAdapter.updateKvizovi(kvizListViewModel.getKvizovi())

        return view
    }
/*
    private fun upisOpen(){

        val intent = Intent(this, UpisPredmet::class.java).apply {
            putExtra("defaultGodMain", intent.getIntExtra("defaultGod",0))
        }
        startActivity(intent)
    }
*/
    companion object {
        fun newInstance(): FragmentKvizovi = FragmentKvizovi()
    }
}