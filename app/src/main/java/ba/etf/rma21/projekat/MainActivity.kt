package ba.etf.rma21.projekat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.view.KvizListAdapter
import ba.etf.rma21.projekat.viewmodel.KvizListViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var kvizovi: RecyclerView
    private lateinit var kvizAdapter: KvizListAdapter
    private var kvizListViewModel =  KvizListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        kvizovi = findViewById(R.id.listaKvizova)

        kvizovi.setLayoutManager(GridLayoutManager(this, 2))

//        kvizAdapter = KvizListAdapter(listOf())
//        kvizovi.adapter = kvizAdapter
        kvizAdapter = KvizListAdapter(listOf())
        kvizovi.adapter = kvizAdapter
        kvizAdapter.updateKvizovi(kvizListViewModel.getKvizovi())

    }
}

