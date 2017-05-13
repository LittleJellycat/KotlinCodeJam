import java.util.*

private fun findLastTidyNumber(number: Long): String {
    if (number < 10) return number.toString()
    val result = generateSequence(number) { it / 10 }.takeWhile { it > 0 }.map { it % 10 }.toMutableList()
    for (i in result.size - 1 downTo 1) {
        if (result[i] > result[i - 1]) {
            val k = result.lastIndexOf(result[i])
            result[k]--
            for (j in k - 1 downTo 0) {
                result[j] = 9
            }
            if (result[result.size - 1] == 0L) {
                result.removeAt(result.size - 1)
            }
            break
        }
    }
    return result.joinToString("").reversed()
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val numbers = (1..sc.nextInt()).map { sc.nextLong() }.toList()
    sc.close()
    val result = numbers.map { findLastTidyNumber(it) }
    result.forEachIndexed { i, it -> println("Case #${i + 1}: $it") }
}