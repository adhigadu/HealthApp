package aditya.sensyne.hospitaluk.ui.listview

import aditya.sensyne.hospitaluk.R
import aditya.sensyne.hospitaluk.data.model.HospitalModel
import aditya.sensyne.hospitaluk.ui.SharedMainViewModel
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment(R.layout.list_fragment) {

    private val sharedMainViewModel: SharedMainViewModel by navGraphViewModels(R.id.nav_graph)

    private val hospitalsAdapter by lazy { ListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hospitalsAdapter.setItemClickListener {
            sharedMainViewModel.selectedHospital.value = it
            findNavController().navigate(
                ListFragmentDirections.actionListFragmentToDetailFragment(
                    it.OrganisationName

                )
            )
        }
        hospital_list.layoutManager = LinearLayoutManager(context)
        hospital_list.adapter = hospitalsAdapter
        sharedMainViewModel.getHospitalList()
        sharedMainViewModel.hospitals.observe(
            this,
            Observer { hospitals: List<HospitalModel> -> hospitalsAdapter.reloadList(hospitals) })
    }

}