fun main() {
    val n = readln().toInt()

    val points = List(n) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        x to y
    }.sortedWith(compareBy<Pair<Int, Int>> { it.second }.thenBy { it.first })

    val sb = StringBuilder()
    for ((x, y) in points) sb.append("$x $y\n")
    print(sb)
}

/*


    val points = ArrayList<Pair<Int, Int>>(n)

    repeat(n) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        points.add(x to y)
    }

    points.sortWith(compareBy<Pair<Int, Int>> { it.second }.thenBy { it.first })
* */