package ba.etf.rma21.projekat

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ba.etf.rma21.projekat.data.dataKvizovi
import ba.etf.rma21.projekat.data.repositories.GrupaRepository
import ba.etf.rma21.projekat.data.repositories.PredmetRepository
import ba.etf.rma21.projekat.view.FragmentKvizovi
import ba.etf.rma21.projekat.view.FragmentPokusaj
import ba.etf.rma21.projekat.view.FragmentPokusaj.Companion.navigacijaPitanja
import ba.etf.rma21.projekat.view.FragmentPoruka
import ba.etf.rma21.projekat.view.FragmentPredmeti
import ba.etf.rma21.projekat.viewmodel.SaveStateViewModel
import ba.etf.rma21.projekat.viewmodel.SendDataViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var navMenu: Menu
    private lateinit var viewModel: SendDataViewModel


    //Listener za click
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.kvizovi -> {
                val kvizoviFragment = FragmentKvizovi.newInstance()
                openFragment(kvizoviFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.predmeti -> {
                val predmetiFragment = FragmentPredmeti.newInstance()
                openFragment(predmetiFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.predajKviz -> {
                val porukaFragment = FragmentPoruka.newInstance()
                var rezultatMeni="Rezultat:"
                saveViewModel.setPorukaFragment("predaj")

                var bundle: Bundle = Bundle()
                bundle.putString("kviz", viewModel.dataKviz.value!!.naziv)
                bundle.putString("tacnost", viewModel.tacnost.value.toString())
                porukaFragment.setArguments(bundle)
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.framePitanje, porukaFragment)
                transaction.addToBackStack(null)
                transaction.commit()
                rezultatMeni+= viewModel.tacnost.value.toString()
                navigacijaPitanja.menu.add(0,viewModel.navigacija.value!!.size+1,viewModel.navigacija.value!!.size+1,rezultatMeni)
                val s = SpannableString(rezultatMeni)
                s.setSpan(ForegroundColorSpan(Color.WHITE), 0, s.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                navigacijaPitanja.menu.getItem(viewModel.navigacija.value!!.size-1).setTitle(s)
                navigacijaPitanja.menu.getItem(viewModel.navigacija.value!!.size-1).setEnabled(false)
                editKviz()

                return@OnNavigationItemSelectedListener true
            }

            R.id.zaustaviKviz -> {
                onBackPressed()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun editKviz() {
        var current = Date()
        var cal = Calendar.getInstance()
        cal.time = current
        dataKvizovi()[dataKvizovi().indexOf(viewModel.dataKviz.value!!)].osvojeniBodovi = (viewModel.tacnost.value!!/100).toFloat()
        dataKvizovi()[dataKvizovi().indexOf(viewModel.dataKviz.value!!)].datumRada = current
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(SendDataViewModel::class.java)
        /*kvizovi = findViewById(R.id.listaKvizova)
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
        */

        bottomNavigation= findViewById(R.id.bottomNav)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottomNavigation.selectedItemId= R.id.kvizovi
        switchNavigation("main")

    }


    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        bottomNavigation.setSelectedItemId(R.id.kvizovi)

    }

    companion object {
        var saveViewModel: SaveStateViewModel = SaveStateViewModel()
    }

    fun switchNavigation(trenutniView: String) {
        if (trenutniView.equals("main")) {
            bottomNavigation.menu.findItem(R.id.predajKviz).isVisible = false
            bottomNavigation.menu.findItem(R.id.zaustaviKviz).isVisible = false
            bottomNavigation.menu.findItem(R.id.kvizovi).isVisible = true
            bottomNavigation.menu.findItem(R.id.predmeti).isVisible = true
        }
        if (trenutniView.equals("pokusaj")) {
            bottomNavigation.menu.findItem(R.id.kvizovi).isVisible = false
            bottomNavigation.menu.findItem(R.id.predmeti).isVisible = false
            bottomNavigation.menu.findItem(R.id.predajKviz).isVisible = true
            bottomNavigation.menu.findItem(R.id.zaustaviKviz).isVisible = true
        }
    }

}



