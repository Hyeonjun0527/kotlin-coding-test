fun main() {
    val sb = StringBuilder()
    var group = 1

    while (true) {
        val n = readln().trim().toInt()
        if (n == 0) break

        sb.append("Group ").append(group).append('\n')

        val rows = List(n) { readln().trim().split(" ") }
        val names = rows.map { it[0] }
        val papers = rows.map { it.drop(1) }

        val lines = (0 until n).map { i ->
            (0 until n - 1)
                .filter { papers[i][it] == "N" }
                .map {
                    val writer = (i + (n - it - 1)) % n
                    "${names[writer]} was nasty about ${names[i]}"
                }
        }.flatten()

        if (lines.isEmpty()) sb.append("Nobody was nasty\n")
        else sb.append(lines.joinToString("\n")).append('\n')

        sb.append('\n')
        group++
    }

    print(sb.toString())
}
