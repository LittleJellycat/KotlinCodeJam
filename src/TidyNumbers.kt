import java.util.*
import kotlin.collections.ArrayList


fun findLastTidyNumber(number: Long): String {
    if (number < 10) return number.toString()
    var n = number
    val result = ArrayList<Int>()
    while (n != 0L) {
        result.add((n % 10).toInt())
        n /= 10
    }
    for (i in result.size - 1 downTo 1) {
        if (result[i] > result[i - 1]) {
            var k = i
            while (k < result.size - 1 && result[k] == result[k + 1]) {
                k++
            }
            result[k]--
            for (j in k - 1 downTo 0) {
                result[j] = 9
            }
            if (result[result.size - 1] == 0) {
                result.removeAt(result.size - 1)
            }
            return result.joinToString("").reversed()
        }
    }
    return result.joinToString("").reversed()
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val numberOfTestCases = sc.nextInt()
    val numbers = ArrayList<Long>()
    for (i in 0..numberOfTestCases - 1) {
        numbers.add(sc.nextLong())
    }
    sc.close()
    val out = numbers.map { findLastTidyNumber(it) }
    (0..numbers.size - 1).forEach { i -> println("Case #${i + 1}: " + out[i]) }
}