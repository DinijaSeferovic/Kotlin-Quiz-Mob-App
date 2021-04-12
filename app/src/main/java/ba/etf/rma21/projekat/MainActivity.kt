package ba.etf.rma21.projekat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.view.KvizListAdapter
import ba.etf.rma21.projekat.viewmodel.GrupaListViewModel
import ba.etf.rma21.projekat.viewmodel.KvizListViewModel
import ba.etf.rma21.projekat.viewmodel.PredmetListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var kvizovi: RecyclerView
    private lateinit var kvizAdapter: KvizListAdapter
    private var kvizListViewModel =  KvizListViewModel()
    private var predmetListViewModel =  PredmetListViewModel()
    private var grupaListViewModel =  GrupaListViewModel()
    private lateinit var mySpinner: Spinner
    private lateinit var spinnerAdapter: ArrayAdapter<Kviz>
    private lateinit var upisButton : FloatingActionButton
    var categories = arrayOf("Svi moji kvizovi", "Svi kvizovi", "Urađeni kvizovi", "Budući kvizovi", "Prošli kvizovi")
    var data : List<Kviz> = emptyList()

    private fun initializeViews() {

        mySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categories)
        kvizAdapter = KvizListAdapter(data)
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

        spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, data)

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        kvizovi = findViewById(R.id.listaKvizova)
        mySpinner = findViewById(R.id.filterKvizova)
        upisButton = findViewById(R.id.upisDugme)

        initializeViews()
        kvizovi.setLayoutManager(GridLayoutManager(this, 2))
        predmetListViewModel.dodajPocetniUpisaniP()
        grupaListViewModel.dodajPocetnuUpisanuG()
        kvizAdapter.updateKvizovi(kvizListViewModel.getKvizovi())

        upisButton.setOnClickListener{
            upisOpen()
        }
    }

    private fun upisOpen(){

        val intent = Intent(this, UpisPredmet::class.java).apply {
            putExtra("defaultGodMain", intent.getIntExtra("defaultGod",0))
        }
        startActivity(intent)
    }
}



