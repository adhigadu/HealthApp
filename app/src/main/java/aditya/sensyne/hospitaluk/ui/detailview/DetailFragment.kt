package aditya.sensyne.hospitaluk.ui.detailview

import aditya.sensyne.hospitaluk.R
import aditya.sensyne.hospitaluk.ui.SharedMainViewModel
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment(R.layout.detail_fragment) {
    private val args: DetailFragmentArgs by navArgs()
    private val sharedMainViewModel: SharedMainViewModel by navGraphViewModels(R.id.nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hospitalName = args.hospitalName
        tvname.text = hospitalName
        tv1.text = sharedMainViewModel.getSelectHospital().value?.Address1
        tv2.text = sharedMainViewModel.getSelectHospital().value?.Address2
        tv3.text = sharedMainViewModel.getSelectHospital().value?.Address3
        tv4.text = sharedMainViewModel.getSelectHospital().value?.Email
        tv5.text = sharedMainViewModel.getSelectHospital().value?.Fax
        tv6.text = sharedMainViewModel.getSelectHospital().value?.IsPimsManaged.toString()
        tv7.text = sharedMainViewModel.getSelectHospital().value?.OrganisationCode
        tv8.text = sharedMainViewModel.getSelectHospital().value?.OrganisationName
        tv9.text = sharedMainViewModel.getSelectHospital().value?.OrganisationStatus
        tv10.text = sharedMainViewModel.getSelectHospital().value?.OrganisationType

    }

}
