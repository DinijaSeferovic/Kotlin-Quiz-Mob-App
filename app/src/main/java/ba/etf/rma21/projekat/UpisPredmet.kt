package ba.etf.rma21.projekat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import ba.etf.rma21.projekat.data.repositories.GrupaRepository.Companion.upisiGrupu
import ba.etf.rma21.projekat.data.repositories.PredmetRepository.Companion.upisiPredmeti
import ba.etf.rma21.projekat.viewmodel.GrupaListViewModel
import ba.etf.rma21.projekat.viewmodel.PredmetListViewModel
import kotlin.properties.Delegates

class UpisPredmet : AppCompatActivity(){
    private lateinit var dodajButton: Button
    private lateinit var spinnerGodina: Spinner
    private lateinit var spinnerPredmet: Spinner
    private lateinit var spinnerGrupa: Spinner
    private var predmetListViewModel =  PredmetListViewModel()
    private var grupeListViewModel =  GrupaListViewModel()
    private lateinit var spinnerAdapterGod: ArrayAdapter<String>
    private lateinit var spinnerAdapterPred: ArrayAdapter<String>
    private lateinit var spinnerAdapterGru: ArrayAdapter<String>
    private var godine = arrayOf("", "1", "2", "3", "4", "5")
    private var predmeti = mutableListOf<String>("")
    private var grupe = mutableListOf<String>("")
    private var spin1: Boolean = false
    private var spin2: Boolean = false
    private var spin3: Boolean = false
    //private var defaultGodina by Delegates.notNull<Int>()

    private fun initializeViews() {

        spinnerAdapterGod = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, godine)
        spinnerGodina.adapter= spinnerAdapterGod
        spinnerGodina.setSelection(intent.getIntExtra("defaultGodMain",0))

        //spinner selection events
        spinnerGodina.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, position: Int, itemID: Long) {
                predmeti = mutableListOf<String>("")
                if (position > 0 && position < godine.size) {
                    for (p in predmetListViewModel.neupisaniPoGod(spinnerGodina.selectedItem.toString())) {
                        predmeti.add(p)
                    }
                    spinnerAdapterPred = ArrayAdapter(this@UpisPredmet, android.R.layout.simple_dropdown_item_1line, predmeti)
                    spinnerPredmet.adapter = spinnerAdapterPred
                    spin1=true
                    //defaultGodina=position
                }
                else spin1=false
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        spinnerPredmet.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, position: Int, itemID: Long) {
                grupe = mutableListOf<String>("")
                if (position > 0 && position < predmeti.size) {
                    for (g in grupeListViewModel.getGrupeZaPred(spinnerPredmet.selectedItem.toString())) {
                        grupe.add(g)
                    }
                    spinnerAdapterGru = ArrayAdapter(this@UpisPredmet, android.R.layout.simple_dropdown_item_1line, grupe)
                    spinnerGrupa.adapter = spinnerAdapterGru
                    spin2=true
                }
                else spin2=false
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        spinnerGrupa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, position: Int, itemID: Long) {
                if (position > 0 && position < predmeti.size) {
                    spin3=true
                }
                else spin3=false
                if (spin1 && spin2 && spin3) {
                    dodajButton.setEnabled(true)
                    dodajButton.setClickable(true)
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
        dodajButton = findViewById(R.id.dodajPredmetDugme)
        dodajButton.setEnabled(false)
        dodajButton.setClickable(false)

        initializeViews()

        dodajButton.setOnClickListener{
            if (spin1 && spin2 && spin3) {
                dodajButton.setClickable(true)
                dodajButton.setEnabled(true)
                upisiPredmeti(spinnerPredmet.selectedItem.toString())
                upisiGrupu(spinnerGrupa.selectedItem.toString())
                dodajOpen()
                finish()
            }
        }
    }

    private fun dodajOpen(){

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("defaultGod", spinnerGodina.selectedItemPosition)
        }
        startActivity(intent)
    }

}