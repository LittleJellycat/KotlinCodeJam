import java.lang.Math.abs
import java.util.*

private fun placeRooks(rooks: List<Pair<Int, Int>>, fieldSize: Int): List<Pair<Int, Int>> {
    val (rows, columns) = rooks.unzip()
    val freeRows = (1..fieldSize) - rows
    val freeColumns = (1..fieldSize) - columns
    return freeRows.zip(freeColumns)
}

private fun placeBishops(bishops: List<Pair<Int, Int>>, fieldSize: Int): List<Pair<Int, Int>> {
    val (rows, columns) = bishops.map { (it.first - it.second) to (it.first + it.second - fieldSize - 1) }.unzip()
    val freeRows = ((-fieldSize + 1..fieldSize - 1) - rows).sortedByDescending { abs(it) }.toMutableList()
    val freeColumns = ((-fieldSize + 1..fieldSize - 1) - columns).toMutableList()
    return placeBishops(freeRows, freeColumns, fieldSize)
            .map { (it.first + it.second + fieldSize + 1) / 2 to (it.second - it.first + fieldSize + 1) / 2 }
}

private fun placeBishops(rows: MutableList<Int>, columns: MutableList<Int>, fieldSize: Int): List<Pair<Int, Int>> {
    if (rows.isEmpty()) return emptyList()
    val result = mutableListOf<Pair<Int, Int>>()
    val i = rows.first()
    val j = columns.filter { (it + (fieldSize - 1 + i)) % 2 == 0 }
            .sortedByDescending { abs(i) + abs(it) }
            .find { abs(i) + abs(it) < fieldSize }
    if (j != null) {
        result.add(i to j)
        columns.remove(element = j)
    }
    rows.removeAt(0)
    result.addAll(placeBishops(rows, columns, fieldSize))
    return result
}

private fun placeModels(bishops: List<Pair<Int, Int>>, rooks: List<Pair<Int, Int>>, fieldSize: Int):
        Triple<Int, Int, List<Triple<Char, Int, Int>>> {
    var placedRooks = placeRooks(rooks, fieldSize)
    var placedBishops = placeBishops(bishops, fieldSize)
    val queens = (placedRooks + rooks).intersect(placedBishops + bishops) -
            (rooks.intersect(bishops))
    val score = bishops.size + placedBishops.size + rooks.size + placedRooks.size
    val placed = (placedBishops + placedRooks).distinct().size
    placedRooks -= queens
    placedBishops -= queens
    return Triple(score, placed,
            placedRooks.map { Triple('x', it.first, it.second) } +
            placedBishops.map { Triple('+', it.first, it.second) } +
            queens.map { Triple('o', it.first, it.second) })
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    for (i in 1..sc.nextInt()) {
        val fieldSize = sc.nextInt()
        val (rooks, bishops) = (1..sc.nextInt())
                .map { Triple(sc.next(), sc.nextInt(), sc.nextInt()) }
                .fold(mutableListOf<Pair<Int, Int>>() to mutableListOf<Pair<Int, Int>>()) {
                    (rooks, bishops), (ch, x, y) ->
                    if (ch != "x") {
                        bishops.add(x to y)
                    }
                    if (ch != "+") {
                        rooks.add(x to y)
                    }
                    rooks to bishops
                }
        val (score, placed, models) = placeModels(bishops, rooks, fieldSize)
        println("Case #$i: $score $placed")
        models.forEach { println("${it.first} ${it.second} ${it.third}") }
    }
    sc.close()
}