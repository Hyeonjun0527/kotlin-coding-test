import kotlin.math.abs

//BOJ_15686_Chicken


fun main() {

    data class Pos(val y: Int, val x: Int)

    val (n, m) = readln().split(" ").map { it.toInt() }

    val city = List(n) { readln().split(" ").map { it.toInt() } }

    val houses = mutableListOf<Pos>()
    val chickens = mutableListOf<Pos>()

    for (y in 0 until n) {
        for (x in 0 until n) {
            when (city[y][x]) {
                1 -> houses += Pos(y, x)
                2 -> chickens += Pos(y, x)
            }
        }
    }

    // combinations
    val indices = chickens.indices.toList()
    val combs = combinations(indices, m)

    fun dist(h: Pos, c: Pos) = abs(h.y - c.y) + abs(h.x - c.x)

    val answer = combs.minOf { comb ->
        houses.sumOf { h ->
            comb.minOf { ci -> dist(h, chickens[ci]) }
        }
    }

    println(answer)
}

fun <T> combinations(list: List<T>, k: Int): List<List<T>> {
    if (k == 0) return listOf(emptyList())
    if (list.isEmpty()) return emptyList()
    val head = list.first()
    val tail = list.drop(1)
    return combinations(tail, k - 1).map { listOf(head) + it } +
            combinations(tail, k)
}
