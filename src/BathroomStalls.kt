import java.util.*


private fun findLastStallConfiguration(numberOfStalls: Long, numberOfPeople: Long): Pair<Long, Long> {
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
    val stallsToPeopleList = (1..sc.nextInt()).map { sc.nextLong() to sc.nextLong() }.toList()
    sc.close()
    val result = stallsToPeopleList.map { findLastStallConfiguration(it.first, it.second) }
    result.forEachIndexed { i, it -> println("Case #${i + 1}: ${it.first} ${it.second}") }
}