package ba.etf.rma21.projekat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.view.FragmentKvizovi
import ba.etf.rma21.projekat.view.FragmentPredmeti
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView

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
        }
        false
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        var odabranaGod:Int = 0
         var odabraniPred:Int=0
         var odabranaGru:Int = 0
    }

}



