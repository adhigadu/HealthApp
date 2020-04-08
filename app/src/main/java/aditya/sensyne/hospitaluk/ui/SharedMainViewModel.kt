package aditya.sensyne.hospitaluk.ui

import aditya.sensyne.hospitaluk.data.model.FilterBy
import aditya.sensyne.hospitaluk.data.model.HospitalModel
import aditya.sensyne.hospitaluk.data.repository.HospitalRepository
import aditya.sensyne.hospitaluk.utils.FilterMethodUtils
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

class SharedMainViewModel(val app: Application) : AndroidViewModel(app) {
    val hospitals: MediatorLiveData<List<HospitalModel>> = MediatorLiveData()
    val selectedHospital = MutableLiveData<HospitalModel>()
    private val hospitalRepository by lazy { HospitalRepository(app = app) }

    fun getHospitalList(filterBy : FilterBy = FilterBy.ShowAll) {
        hospitals.value = FilterMethodUtils.filterBy(filterBy,hospitalRepository.getHospitals().toList())
        //hospitals.value = filterBy(FilterBy.NHSHospital, value)
    }

    fun getSelectHospital():MutableLiveData<HospitalModel> {
        return selectedHospital
    }


}