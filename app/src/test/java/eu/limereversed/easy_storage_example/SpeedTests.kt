package eu.limereversed.easy_storage_example

import eu.limereversed.easy_storage_example.product.Product
import org.junit.Test

import org.junit.Assert.*
import kotlin.system.measureTimeMillis

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

class PrestandaUnitTest {

    @Test
    fun loopList_showTime() {

        val list = mutableListOf<Product>()
        var value: Product? = null
        val addTime = measureTimeMillis {

            for (num in 1..1000) {
                list += Product(num.toLong(), num, num)
            }
        }

        val loopTime = measureTimeMillis {
            for (item in list) {
                value = item
            }
        }
        println(value)
        println("Took $addTime ms to add numbers to the list.")
        println("Took $loopTime ms to loop through the list.")
    }

    @Test
    fun loopMap_showTime() {
        val map = mutableMapOf<Int, Product>()
        var value: Product? = null
        val addTime = measureTimeMillis {

            for (num in 1..1000) {
                map.put(num, Product(num.toLong(), num, num))
            }
        }

        val loopTime = measureTimeMillis {
            for (item in map) {
                value = item.value
            }
        }
        println(value?.serialNumber)
        println("Took $addTime ms to add numbers to the map.")
        println("Took $loopTime ms to loop through the map.")
    }

    @Test
    fun crossReflistAndMap() {
        val list = mutableListOf<Int>()

        for (num in 1..1000) {
            list += num
        }

        val map = mutableMapOf<Int, Product>()
        for (num in list) {
            map.put(num, Product(num.toLong(), num, num))
        }

        var value: Product? = null
        val crossRefTime = measureTimeMillis {
            for (id in list) {
                value = map[id]
            }
        }

        println(value)
        println("Took $crossRefTime ms to loop through list and get items from map.")
    }
}