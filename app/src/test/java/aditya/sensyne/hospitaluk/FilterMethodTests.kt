package aditya.sensyne.hospitaluk

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
    fun filterByTest() {
        val list = getListOfFakesWithDistance().toMutableList()
//        list.add(MyItemCardModel().apply { this.distance = " miles" }) //TODO This scenario is not handled

        val method = FilterMethodUtils.Companion.javaClass.getDeclaredMethod("filterBy", ArrayList::class.java, Boolean::class.java)
        method.isAccessible = true

        val sortedList = method.invoke(
            FilterMethodUtils,
            list, false) as? List<HospitalModel>

        val floats = sortedList
            ?.map { it.distance.takeWhile { it != ' ' } }
            ?.map {
                try {
                    it.toFloat()
                } catch (e: NumberFormatException) {
                    null
                }
            }
            ?.filterNotNull()

        Assert.assertTrue(floats != null)
        floats?.let { listOfFloats ->
            for (i in listOfFloats.indices) {
                try {
                    Assert.assertTrue(listOfFloats[i] < listOfFloats[i + 1])

                } catch (e: IndexOutOfBoundsException) {
                    println("End")
                }
            }
        }
    }
}
