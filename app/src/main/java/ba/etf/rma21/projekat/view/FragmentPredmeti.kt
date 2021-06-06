package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.MainActivity.Companion.saveViewModel
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.GrupaRepository
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import ba.etf.rma21.projekat.viewmodel.GrupaListViewModel
import ba.etf.rma21.projekat.viewmodel.PredmetListViewModel
import ba.etf.rma21.projekat.viewmodel.SaveStateViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FragmentPredmeti : Fragment() {
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
    private var dohvaceniPredmeti = emptyList<Predmet>()
    private var dohvaceneGrupe = emptyList<Grupa>()
    private var spin1: Boolean = false
    private var spin2: Boolean = false
    private var spin3: Boolean = false


    private fun initializeViews(inflater: LayoutInflater) {

        spinnerAdapterGod = ArrayAdapter(inflater.context, android.R.layout.simple_dropdown_item_1line, godine)
        spinnerGodina.adapter= spinnerAdapterGod
        spinnerGodina.setSelection(saveViewModel.getGod())


        //spinner selection events
        spinnerGodina.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, position: Int, itemID: Long) {
                var godina: Int = 0
                if (spinnerGodina.selectedItem.toString() != "")  godina = spinnerGodina.selectedItem.toString().toInt()

                predmetListViewModel.neupisaniPoGod(godina, onSuccess = ::onSuccessPredmet, onError = ::onError)

                if (position > 0 && position < godine.size) {

                    spinnerPredmet.setSelection(saveViewModel.getPred())
                    spin1 = true
                    saveViewModel.setGod(position)
                } else spin1 = false
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }


        spinnerPredmet.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, position: Int, itemID: Long) {

                grupeListViewModel.getGrupeZaPred(dohvaceniPredmeti.find { p -> p.naziv.equals(spinnerPredmet.selectedItem.toString()) }!!.id, onSuccess = ::onSuccessGrupa, onError = ::onError)

                if (position > 0 && position < dohvaceniPredmeti.size) {


                    spinnerGrupa.setSelection(saveViewModel.getGru())
                    spin2=true
                    saveViewModel.setPred(position)

                }
                else spin2=false

            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        spinnerGrupa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, position: Int, itemID: Long) {
                saveViewModel.setGru(position)
                if (position > 0 && position < dohvaceneGrupe.size) {
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

    private fun dodajOpen(porukaFragment: FragmentPoruka){

        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, porukaFragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.predmeti_fragment, container, false)
        (activity as MainActivity).switchNavigation("main")
        spinnerGodina = view.findViewById(R.id.odabirGodina)
        spinnerPredmet = view.findViewById(R.id.odabirPredmet)
        spinnerGrupa = view.findViewById(R.id.odabirGrupa)
        dodajButton = view.findViewById(R.id.dodajPredmetDugme)
        dodajButton.setEnabled(false)
        dodajButton.setClickable(false)


        initializeViews(inflater)

        dodajButton.setOnClickListener{
            val porukaFragment = FragmentPoruka.newInstance()
            saveViewModel.setPorukaFragment("upisi")
            if (spin1 && spin2 && spin3) {
                dodajButton.setClickable(true)
                dodajButton.setEnabled(true)
                predmetListViewModel.upisiPredmet(dohvaceniPredmeti.find { p -> p.naziv.equals(spinnerPredmet.selectedItem.toString()) }!!.id, onSuccess = ::onSuccessPredmetUpis, onError = ::onError)
                grupeListViewModel.upisiUGrupu(dohvaceneGrupe.find{ g -> g.naziv.equals(spinnerGrupa.selectedItem.toString())}!!.id, onSuccess = ::onSuccessGrupaUpis, onError = ::onError)
                saveViewModel.setGru(0)
                saveViewModel.setGod(0)
                saveViewModel.setPred(0)

            }
            var bundle: Bundle = Bundle()
            bundle.putString("posGru", spinnerGrupa.selectedItem.toString())
            bundle.putString("posPred", spinnerPredmet.selectedItem.toString())
            porukaFragment.setArguments(bundle)
            dodajOpen(porukaFragment)
        }
        return view
    }

    fun onSuccessPredmet(p: List<Predmet>) {
        GlobalScope.launch (Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                dajDohvacenePredmete(p)
                spinnerAdapterPred = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, dohvaceniPredmeti.map { p -> p.naziv })
                spinnerPredmet.adapter = spinnerAdapterPred
                spinnerPredmet.setSelection(saveViewModel.getPred())
                spin1 = true

            }
        }
    }

    fun onSuccessPredmetUpis(p: Boolean) {
        GlobalScope.launch (Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val toast = Toast.makeText(context, "Predmet upisan", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    fun onSuccessGrupa(g: List<Grupa>) {
        GlobalScope.launch (Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                dajDohvaceneGrupe(g)
                spinnerAdapterGru = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, dohvaceneGrupe.map { g -> g.naziv })
                spinnerGrupa.adapter = spinnerAdapterGru
                spinnerGrupa.setSelection(saveViewModel.getGru())
                spin2=true
            }
        }
    }

    fun onSuccessGrupaUpis(g: Boolean) {
        GlobalScope.launch (Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val toast = Toast.makeText(context, "Grupa upisana", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    fun onError() {

    }

    fun dajDohvacenePredmete(p: List<Predmet>) {
        dohvaceniPredmeti=p
    }

    fun dajDohvaceneGrupe(g: List<Grupa>) {
        dohvaceneGrupe=g
    }

    companion object {
        fun newInstance(): FragmentPredmeti = FragmentPredmeti()
    }
}