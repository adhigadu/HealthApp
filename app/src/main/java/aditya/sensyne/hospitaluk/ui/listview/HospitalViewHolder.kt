package aditya.sensyne.hospitaluk.ui.listview

import aditya.sensyne.hospitaluk.R
import aditya.sensyne.hospitaluk.data.model.HospitalModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class HospitalViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.list_item, parent, false
    )
) {

    fun bind(hospitalModel: HospitalModel) {
        itemView.name.text = hospitalModel.OrganisationName
        itemView.idtv.text = hospitalModel.OrganisationID.toString()
        itemView.website.text = hospitalModel.Website
    }

}
