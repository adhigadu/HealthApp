package aditya.sensyne.hospitaluk

import aditya.sensyne.hospitaluk.data.model.FilterBy
import aditya.sensyne.hospitaluk.data.model.HospitalModel
import aditya.sensyne.hospitaluk.utils.FilterMethodUtils
import junit.framework.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FilterMethodTests {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun filterNHSOnly() {
        val list = getListOfHospitals()

         val filteredList= FilterMethodUtils.filterBy(FilterBy.NHSHospital,list = list)

        Assert.assertTrue(filteredList != null)
        filteredList.let {
            Assert.assertTrue(it.size==3)
        }
    }
    @Test
    fun filterIndependentOnly() {
        val list = getListOfHospitals()

        val filteredList= FilterMethodUtils.filterBy(FilterBy.IndependentHospital,list = list)

        Assert.assertTrue(filteredList != null)
        filteredList.let {
            Assert.assertTrue(it.size==6)
        }
    }
    fun getListOfHospitals(): List<HospitalModel> {
        val list = mutableListOf<HospitalModel>()
        list.add(HospitalModel(Sector = "NHS"))
        list.add(HospitalModel(Sector = "NHS"))
        list.add(HospitalModel(Sector = "NHS"))
        list.add(HospitalModel(Sector = "Independent"))
        list.add(HospitalModel(Sector = "Independent"))
        list.add(HospitalModel(Sector = "Independent"))
        list.add(HospitalModel(Sector = "Independent"))
        list.add(HospitalModel(Sector = "Independent"))
        list.add(HospitalModel(Sector = "Independent"))
        return list
    }

}
