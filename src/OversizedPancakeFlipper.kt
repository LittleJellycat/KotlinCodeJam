import java.util.*
import kotlin.collections.ArrayList


private fun findNumberOfFlips(pancakeRow: String, flipperSize: Int): Int {
    val pancakesArray = pancakeRow.toCharArray()
    var flipNumber = 0
    (0..pancakesArray.size - 1)
            .asSequence()
            .filter { pancakesArray[it] == '-' }
            .forEach {
                if (it + flipperSize - 1 < pancakesArray.size) {
                    for (j in it..it + flipperSize - 1) {
                        if (pancakesArray[j] == '+') {
                            pancakesArray[j] = '-'
                        } else {
                            pancakesArray[j] = '+'
                        }
                    }
                    flipNumber++
                } else {
                    return -1
                }
            }
    return flipNumber
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val pancakeRows = (1..sc.nextInt()).map { sc.next() to sc.nextInt() }.toList()
    sc.close()
    val result = pancakeRows.map { findNumberOfFlips(it.first, it.second) }
    (0..pancakeRows.size - 1).forEach { i -> println("Case #${i + 1}: " + if (result[i] != -1) result[i] else "IMPOSSIBLE") }
}
