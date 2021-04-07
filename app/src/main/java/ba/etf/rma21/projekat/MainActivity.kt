package ba.etf.rma21.projekat

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.KvizRepository.Companion.getAll
import ba.etf.rma21.projekat.data.repositories.KvizRepository.Companion.getMyKvizes
import ba.etf.rma21.projekat.view.KvizListAdapter
import ba.etf.rma21.projekat.viewmodel.KvizListViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var kvizovi: RecyclerView
    private lateinit var kvizAdapter: KvizListAdapter
    private var kvizListViewModel =  KvizListViewModel()
    private lateinit var mySpinner: Spinner
    private lateinit var spinnerAdapter: ArrayAdapter<Kviz>
    var categories = arrayOf("Svi moji kvizovi", "Svi kvizovi", "Urađeni kvizovi", "Budući kvizovi", "Prošli kvizovi")

    private fun initializeViews() {

        mySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categories)
        kvizAdapter = KvizListAdapter(listOf())
        kvizovi.adapter = kvizAdapter
        //spinner selection events
        mySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, position: Int, itemID: Long) {
                if (position >= 0 && position < categories.size) {
                    getSelectedCategoryData(position)
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }


    private fun getSelectedCategoryData(categoryID: Int) {
        //arraylist to hold selected cosmic bodies
        val data = ArrayList<Kviz>()
        spinnerAdapter = if (categoryID == 1) {
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, getAll())

        } else {
            //filter by id
            for (Kviz in getAll()) {
                if (categoryID==0) {
                    for (k in getMyKvizes()) data.add(k)
                }
            }
            //instatiate adapter a
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, data)
        }
        //set the adapter to GridView
        //kvizovi.adapter = spinnerAdapter
    }

    /*
    when activity is created, setContentView then initializeViews.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        kvizovi = findViewById(R.id.listaKvizova)
        mySpinner = findViewById(R.id.filterKvizova)
        initializeViews()
        kvizovi.setLayoutManager(GridLayoutManager(this, 2))


        kvizAdapter.updateKvizovi(kvizListViewModel.getKvizovi())

    }
}



