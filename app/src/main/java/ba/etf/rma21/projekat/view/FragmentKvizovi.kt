package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.viewmodel.GrupaListViewModel
import ba.etf.rma21.projekat.viewmodel.KvizListViewModel
import ba.etf.rma21.projekat.viewmodel.PredmetListViewModel
import ba.etf.rma21.projekat.viewmodel.SendDataViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
    private lateinit var viewModel: SendDataViewModel

    private fun initializeViews(inflater: LayoutInflater) {

        mySpinner.adapter = ArrayAdapter(inflater.context, android.R.layout.simple_dropdown_item_1line, categories)
        kvizAdapter = KvizListAdapter(data) { kviz -> showKviz(kviz) }
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
            kvizListViewModel.getMojiKvizovi(onSuccess = ::onSuccessUp, onError = ::onError)
            /*data= kvizListViewModel.getMojiKvizovi()
            kvizAdapter.updateKvizovi(data)*/
        } else if (categoryID == 1) {
            kvizListViewModel.getKvizovi(onSuccess = ::onSuccessSvi, onError = ::onError)
            /*data= kvizListViewModel.getKvizovi()
            kvizAdapter.updateKvizovi(data)*/
        } else if (categoryID == 2) {
            kvizListViewModel.getGotoviKvizovi(onSuccess = ::onSuccessUp, onError = ::onError)
            /*data= kvizListViewModel.getGotoviKvizovi()
            kvizAdapter.updateKvizovi(data)*/
        } else if (categoryID == 3) {
            kvizListViewModel.getBuduciKvizovi(onSuccess = ::onSuccessUp, onError = ::onError)
            /*data= kvizListViewModel.getBuduciKvizovi()
            kvizAdapter.updateKvizovi(data)*/
        } else if (categoryID == 4) {
            kvizListViewModel.getNeodrzaniKvizovi(onSuccess = ::onSuccessUp, onError = ::onError)
            /*data= kvizListViewModel.getNeodrzaniKvizovi()
            kvizAdapter.updateKvizovi(data)*/

            //}

        }
    }

    fun onSuccessSvi(kvizovi:List<Kviz>, predmeti:MutableMap<Kviz, List<Predmet>>){
        val toast = Toast.makeText(context, "Kvizovi pronadjeni", Toast.LENGTH_SHORT)
        toast.show()
        GlobalScope.launch (Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                kvizAdapter.updateKvizovi(kvizovi)
                kvizAdapter.updatePredmeti(predmeti)

            }
        }
    }

    fun onSuccessUp(kvizovi:List<Kviz>, predmeti:MutableMap<Kviz, List<Predmet>>){
        val toast = Toast.makeText(context, "Kvizovi pronadjeni", Toast.LENGTH_SHORT)
        toast.show()
        GlobalScope.launch (Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                kvizAdapter.updateKvizovi(kvizovi)
                kvizAdapter.updatePredmeti(predmeti)

            }
        }
    }

    fun onSuccessPocni(){

        GlobalScope.launch (Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val toast = Toast.makeText(context, "Kviz zapocet", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    fun onSuccessGrupa(b: Boolean){
        GlobalScope.launch (Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                if (b) {
                    val toast = Toast.makeText(context, "Pocetni Upisan", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }
        }
    }

    fun onError() {
        val toast = Toast.makeText(context, "Greska", Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.kvizovi_fragment, container, false)
        (activity as MainActivity).switchNavigation("main")
        kvizovi = view.findViewById(R.id.listaKvizova)
        mySpinner = view.findViewById(R.id.filterKvizova)

        viewModel= ViewModelProvider(requireActivity()).get(SendDataViewModel::class.java)

        initializeViews(inflater)
        kvizovi.setLayoutManager(GridLayoutManager(activity, 2))
        GlobalScope.launch (Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                grupaListViewModel.dodajPocetniUpisani(onSuccess = ::onSuccessGrupa, onError = ::onError)
                kvizListViewModel.getMojiKvizovi(onSuccess = ::onSuccessUp, onError = ::onError)
                kvizListViewModel.zapocniKviz(3, onSuccess = ::onSuccessPocni, onError = ::onError)
            }
        }


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
    private fun showKviz(kviz: Kviz) {
        val pokusajFragment = FragmentPokusaj.newInstance()
        kvizListViewModel.zapocniKviz(kviz.id, onSuccess = ::onSuccessPocni, onError = ::onError)
        viewModel.sendDataKviz(kviz)
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, pokusajFragment)
        transaction?.addToBackStack(null)
        transaction?.commit()


    }



    companion object {
        fun newInstance(): FragmentKvizovi = FragmentKvizovi()

    }
}