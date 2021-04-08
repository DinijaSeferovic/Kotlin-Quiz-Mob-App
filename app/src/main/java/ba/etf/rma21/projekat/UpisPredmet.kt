package ba.etf.rma21.projekat

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.GrupaRepository.Companion.getGroupsByPredmet
import ba.etf.rma21.projekat.data.repositories.PredmetRepository.Companion.getAll
import ba.etf.rma21.projekat.data.repositories.PredmetRepository.Companion.getPredmetByGod
import ba.etf.rma21.projekat.view.KvizListAdapter

class UpisPredmet : AppCompatActivity(){
    private lateinit var upisButton: Button
    private lateinit var spinnerGodina: Spinner
    private lateinit var spinnerPredmet: Spinner
    private lateinit var spinnerGrupa: Spinner
    /*private lateinit var spinnerAdapterGod: ArrayAdapter<String>
    private lateinit var spinnerAdapterPred: ArrayAdapter<Predmet>
    private lateinit var spinnerAdapterGru: ArrayAdapter<Grupa>*/
    var godine = arrayOf("", "1", "2", "3", "4", "5")
    private fun initializeViews() {

        spinnerGodina.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, godine)
        spinnerPredmet.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arrayOf("")/*getPredmetByGod(spinnerGodina.selectedItem as String*/)
        spinnerGrupa.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arrayOf("")/*getGroupsByPredmet(spinnerPredmet.selectedItem as String*/)
        //kvizAdapter = KvizListAdapter(listOf())
        /*spinnerGodina.adapter = spinnerAdapterGod
        spinnerPredmet.adapter = spinnerAdapterPred
        spinnerGrupa.adapter = spinnerAdapterGru*/
        //spinner selection events
        spinnerGodina.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, position: Int, itemID: Long) {
                if (position >= 0 && position < godine.size) {

                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        spinnerPredmet.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, position: Int, itemID: Long) {
                if (position >= 0 && position < getPredmetByGod(spinnerGodina.selectedItem as String).size) {

                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        spinnerGrupa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, position: Int, itemID: Long) {
                if (position >= 0 && position < getGroupsByPredmet(spinnerPredmet.selectedItem as String).size) {

                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.upis_activity)
        spinnerGodina = findViewById(R.id.odabirGodina)
        spinnerPredmet = findViewById(R.id.odabirPredmet)
        spinnerGrupa = findViewById(R.id.odabirGrupa)
        upisButton = findViewById(R.id.dodajPredmetDugme)
        initializeViews()
    }

}