package backjoon

fun main() {
    val t = readln().toInt()
    val sb = StringBuilder()

    repeat(t) {
        val (n, m) = readln().split(" ").map { it.toInt() }

        val graph = MutableList(n + 1)
        { mutableListOf<Int>() }

        repeat(m) {
            val (x, y) = readln()
                .split(" ").map { it.toInt() }
            graph[x].add(y)
            graph[y].add(x)
        }

        val 색깔 = IntArray(n + 1) { 0 }

        fun bfs(시작점: Int): Boolean {
            val 큐 = ArrayDeque<Int>()
            색깔[시작점] = 1
            큐.addLast(시작점)

            while (큐.isNotEmpty()) {
                val cur = 큐.removeFirst()
                for (next in graph[cur]) {
                    if (색깔[next] == 0) {
                        색깔[next] = -색깔[cur]
                        큐.addLast(next)
                    } else if (색깔[next] == 색깔[cur]) {
                        return false
                    }
                }
            }
            return true
        }

        var possible = true
        for (start in 1..n) {
            if (색깔[start] != 0) continue
            if (!bfs(start)) {
                possible = false
                break
            }
        }

        if (possible) sb.append("possible\n") else sb.append("impossible\n")
    }

    print(sb)
}
