import java.util.*
import kotlin.collections.ArrayList


fun findNumberOfFlippings(pancakeRows: String, flipperSize: Int): Int {
    val pancakesArray = pancakeRows.toCharArray()
    var flippingsNumber = 0
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
                    flippingsNumber++
                } else {
                    return -1
                }
            }
    return flippingsNumber
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val numberOfTestCases = sc.nextInt()
    val pancakeRows = ArrayList<Pair<String, Int>>()
    for (i in 0..numberOfTestCases - 1) {
        pancakeRows.add(sc.next() to sc.nextInt())
    }
    sc.close()
    val out = pancakeRows.map { findNumberOfFlippings(it.first, it.second) }
    (0..pancakeRows.size - 1).forEach { i -> println("Case #${i + 1}: " + if (out[i] != -1) out[i] else "IMPOSSIBLE") }
}
