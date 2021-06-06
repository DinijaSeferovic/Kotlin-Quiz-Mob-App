package ba.etf.rma21.projekat.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.viewmodel.KvizListViewModel
import ba.etf.rma21.projekat.viewmodel.PredmetListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class KvizListAdapter(
        private var kvizovi: List<Kviz>,
        private val onItemClicked: (k:Kviz) -> Unit): RecyclerView.Adapter<KvizListAdapter.KvizViewHolder>() {
        private var predmetiZaKviz = mutableMapOf<Kviz, List<Predmet>>()
        private var kvizListViewModel =  KvizListViewModel()
        private var predmetListViewModel =  PredmetListViewModel()
        private  var poceti: KvizTaken? = null

        inner class KvizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageDot: ImageView = itemView.findViewById(R.id.imageDot)
            val textKviz: TextView = itemView.findViewById(R.id.textKviz)
            val textPredmet: TextView = itemView.findViewById(R.id.textPredmet)
            val textDatum: TextView = itemView.findViewById(R.id.textDatum)
            val textBod: TextView = itemView.findViewById(R.id.textBod)
            val textVrijeme: TextView = itemView.findViewById(R.id.textVrijeme)
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KvizViewHolder {
            val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
            return KvizViewHolder(view)
        }
        override fun getItemCount(): Int = kvizovi.size

        override fun onBindViewHolder(holder: KvizViewHolder, position: Int) {
            GlobalScope.launch (Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    kvizListViewModel.getPocetiKviz(kvizovi[position], onSuccess = ::onSuccessKviz, onError = ::onError)
                    //predmetListViewModel.getPredmeteZaKviz(kvizovi[position], onSuccess = ::onSuccessPredmet, onError = ::onError)
                }
            }

            val current = Date()
            val cal = Calendar.getInstance()
            cal.time = current

            var colorMatch =""

            if (poceti!=null) {
                if (!kvizovi[position].datumPocetka.after(current) && poceti!!.datumRada == null) colorMatch = "zelena"
                else if ((poceti != null && poceti!!.datumRada != null) && (poceti!!.datumRada!!.before(current) || poceti!!.datumRada==current)) colorMatch = "plava"
                else if (kvizovi[position].datumPocetka.after(current)) colorMatch = "zuta"
                else if (kvizovi[position].datumKraj != null && kvizovi[position].datumKraj!!.before(current) && poceti!!.datumRada == null) colorMatch = "crvena"
            }
            else {
                colorMatch = "zelena"
            }
            val pattern = "dd.MM.yyyy"
            val simpleDateFormat = SimpleDateFormat(pattern)
            var prikazDatuma =""
            if (colorMatch=="zuta") prikazDatuma=simpleDateFormat.format(kvizovi[position].datumPocetka)
            else if (colorMatch=="zelena" || colorMatch=="crvena") prikazDatuma=""
            else if (colorMatch=="plava") prikazDatuma=simpleDateFormat.format(poceti!!.datumRada)


            var nazivPredmeta=""
            var listaPredmeta = predmetiZaKviz.get(kvizovi[position])!!
            for (p in listaPredmeta) {
                nazivPredmeta+=p.naziv
                if (listaPredmeta.size>1 && p!=listaPredmeta[listaPredmeta.size-1]) nazivPredmeta+=", "
            }


            holder.textKviz.text = kvizovi[position].naziv
            holder.textPredmet.text = nazivPredmeta
            holder.textDatum.text = prikazDatuma
            if (poceti!=null) holder.textBod.text = poceti!!.osvojeniBodovi.toString()
            holder.textVrijeme.text = kvizovi[position].trajanje.toString()
            holder.itemView.setOnClickListener{ onItemClicked(kvizovi[position]) }


            //Pronalazimo id drawable elementa na osnovu datuma
            val context: Context = holder.imageDot.getContext()
            var id: Int = context.getResources()
                    .getIdentifier(colorMatch, "drawable", context.getPackageName())
            if (id===0) id=context.getResources()
                    .getIdentifier("zuta", "drawable", context.getPackageName())
            holder.imageDot.setImageResource(id)
            //holder.itemView.setOnClickListener{ onItemClicked(kvizovi[position]) }
        }

        fun updateKvizovi(kvizovi: List<Kviz>) {
            this.kvizovi = kvizovi
            notifyDataSetChanged()
        }

        fun updatePredmeti(predmeti:MutableMap<Kviz, List<Predmet>>) {
            this.predmetiZaKviz = predmeti
        }

        fun onSuccessKviz(kviz: KvizTaken) {
            GlobalScope.launch (Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    dajPoceti(kviz)
                }
            }
        }



        fun dajPoceti(kviz: KvizTaken) {
            val current = Date()
            val cal = Calendar.getInstance()
            cal.time = current
            kviz.datumRada=current
            poceti=kviz
        }



        fun onError() {

        }

        }



