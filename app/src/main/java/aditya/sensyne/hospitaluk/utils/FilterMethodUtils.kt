package aditya.sensyne.hospitaluk.utils

import aditya.sensyne.hospitaluk.data.model.FilterBy
import aditya.sensyne.hospitaluk.data.model.HospitalModel

class FilterMethodUtils{
    companion object{
        fun filterBy(filterBy: FilterBy, list: List<HospitalModel>): List<HospitalModel> {
            return when (filterBy) {
                FilterBy.NHSHospital -> list.filter { s -> s.Sector.contains("nhs", ignoreCase = true) }
                FilterBy.IndependentHospital -> list.filter { s ->
                    s.Sector.contains(
                        "independent",
                        ignoreCase = true
                    )
                }
                FilterBy.HasFax -> list.filter { s -> s.Fax.length > 2 }
                FilterBy.ShowAll -> list
                else -> null
            } as List<HospitalModel>
        }
    }
}