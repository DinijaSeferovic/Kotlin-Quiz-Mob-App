package ba.etf.rma21.projekat.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Kviz
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class KvizListAdapter(
        private var kvizovi: List<Kviz>
    ) : RecyclerView.Adapter<KvizListAdapter.KvizViewHolder>() {

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
            val pattern = "dd.MM.yyyy"
            val simpleDateFormat = SimpleDateFormat(pattern)
            val poc = simpleDateFormat.format(kvizovi[position].datumPocetka)
            holder.textKviz.text = kvizovi[position].naziv;
            holder.textPredmet.text = kvizovi[position].nazivPredmeta;
            holder.textDatum.text = poc
            holder.textBod.text = kvizovi[position].osvojeniBodovi.toString()
            holder.textVrijeme.text = kvizovi[position].trajanje.toString()

            val current = Date()
            val cal = Calendar.getInstance()
            cal.time = current


            var colorMatch =""
            if (kvizovi[position].datumPocetka>current && kvizovi[position].datumKraj>current && kvizovi[position].datumRada==null) colorMatch="zelena"
            else if ((kvizovi[position].datumRada!=null) && kvizovi[position].datumRada!!<current) colorMatch="plava"
            else if (kvizovi[position].datumPocetka>current) colorMatch="zuta"
            else if (kvizovi[position].datumKraj<current && kvizovi[position].datumRada==null) colorMatch="crvena"


            //Pronalazimo id drawable elementa na osnovu datuma
            val context: Context = holder.imageDot.getContext()
            var id: Int = context.getResources()
                    .getIdentifier(colorMatch, "drawable", context.getPackageName())
            if (id===0) id=context.getResources()
                    .getIdentifier("plava", "drawable", context.getPackageName())
            holder.imageDot.setImageResource(id)
            //holder.itemView.setOnClickListener{ onItemClicked(kvizovi[position]) }
        }
        fun updateKvizovi(kvizovi: List<Kviz>) {
            this.kvizovi = kvizovi
            notifyDataSetChanged()
        }

    }
