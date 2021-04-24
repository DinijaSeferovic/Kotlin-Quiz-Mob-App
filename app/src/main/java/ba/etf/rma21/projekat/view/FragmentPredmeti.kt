package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.repositories.GrupaRepository
import ba.etf.rma21.projekat.data.repositories.PredmetRepository
import ba.etf.rma21.projekat.viewmodel.GrupaListViewModel
import ba.etf.rma21.projekat.viewmodel.PredmetListViewModel
import ba.etf.rma21.projekat.viewmodel.SaveStateViewModel


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
    private var predmeti = mutableListOf<String>("")
    private var grupe = mutableListOf<String>("")
    private var spin1: Boolean = false
    private var spin2: Boolean = false
    private var spin3: Boolean = false


    private fun initializeViews(inflater: LayoutInflater) {

        spinnerAdapterGod = ArrayAdapter(inflater.context, android.R.layout.simple_dropdown_item_1line, godine)
        spinnerGodina.adapter= spinnerAdapterGod
        spinnerGodina.setSelection(MainActivity.viewModel.getGod())


        //spinner selection events
        spinnerGodina.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, position: Int, itemID: Long) {
                predmeti = mutableListOf<String>("")

                if (position > 0 && position < godine.size) {
                    for (p in predmetListViewModel.neupisaniPoGod(spinnerGodina.selectedItem.toString())) {
                        predmeti.add(p)

                    }
                    spinnerAdapterPred = ArrayAdapter(inflater.context, android.R.layout.simple_dropdown_item_1line, predmeti)
                    spinnerPredmet.adapter = spinnerAdapterPred
                    spinnerPredmet.setSelection(MainActivity.viewModel.getPred())
                    spin1=true
                    MainActivity.viewModel.setGod(position)
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
                    spinnerAdapterGru = ArrayAdapter(inflater.context, android.R.layout.simple_dropdown_item_1line, grupe)
                    spinnerGrupa.adapter = spinnerAdapterGru
                    spinnerGrupa.setSelection(MainActivity.viewModel.getGru())
                    spin2=true
                    MainActivity.viewModel.setPred(position)

                }
                else spin2=false

            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        spinnerGrupa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, position: Int, itemID: Long) {
                MainActivity.viewModel.setGru(position)
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
            if (spin1 && spin2 && spin3) {
                dodajButton.setClickable(true)
                dodajButton.setEnabled(true)
                PredmetRepository.upisiPredmeti(spinnerPredmet.selectedItem.toString())
                GrupaRepository.upisiGrupu(spinnerGrupa.selectedItem.toString())
                MainActivity.viewModel.setGru(0)
                MainActivity.viewModel.setGod(0)
                MainActivity.viewModel.setPred(0)

            }
            var bundle: Bundle = Bundle()
            bundle.putString("posGru", spinnerGrupa.selectedItem.toString())
            bundle.putString("posPred", spinnerPredmet.selectedItem.toString())
            porukaFragment.setArguments(bundle)
            dodajOpen(porukaFragment)
        }
        return view
    }



    companion object {
        fun newInstance(): FragmentPredmeti = FragmentPredmeti()
    }
}