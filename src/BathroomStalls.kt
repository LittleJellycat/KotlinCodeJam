import java.util.*
import kotlin.collections.ArrayList


fun findLastStallConfiguration(numberOfStalls: Long, numberOfPeople: Long): Pair<Long, Long> {
    val configurationsMap = TreeMap<Long, Long>()
    configurationsMap[numberOfStalls] = 1
    var count = 0L
    while (true) {
        val currentEntry = configurationsMap.lastEntry()
        val current = currentEntry.key
        val left = current / 2
        val right = (current - 1) / 2
        count += currentEntry.value
        if (count >= numberOfPeople) {
            return left to right
        }
        configurationsMap.remove(current)
        configurationsMap.merge(left, currentEntry.value, { x, y -> x + y })
        configurationsMap.merge(right, currentEntry.value, { x, y -> x + y })
    }
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val numberOfTestCases = sc.nextInt()
    val stallsToPeopleList = ArrayList<Pair<Long, Long>>()
    for (i in 0..numberOfTestCases - 1) {
        stallsToPeopleList.add(sc.nextLong() to sc.nextLong())
    }
    sc.close()
    val out = stallsToPeopleList.map { findLastStallConfiguration(it.first, it.second) }
    (0..stallsToPeopleList.size - 1).forEach { i -> println("Case #${i + 1}: ${out[i].first} ${out[i].second}") }
}