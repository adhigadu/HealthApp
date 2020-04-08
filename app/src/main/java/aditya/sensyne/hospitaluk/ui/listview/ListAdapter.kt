package aditya.sensyne.hospitaluk.ui.listview

import aditya.sensyne.hospitaluk.data.model.HospitalModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListAdapter : RecyclerView.Adapter<HospitalViewHolder>() {

    private val list: MutableList<HospitalModel> = mutableListOf()

    private var itemClickListener: ((HospitalModel) -> Unit)? = null

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        val hospitalItem = list[position]
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(hospitalItem)
        }
        holder.bind(hospitalItem)
    }

    override fun getItemCount(): Int = list.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HospitalViewHolder(inflater, parent)
    }


    fun setItemClickListener(listener: (hospital: HospitalModel) -> Unit) {
        itemClickListener = listener
    }

    fun reloadList(hospitals: List<HospitalModel>) {
        list.clear()
        list.addAll(hospitals)
        notifyDataSetChanged()
    }
}