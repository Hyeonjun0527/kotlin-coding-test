//BFS – BOJ 1260 DFS와 BFS (가장 대표 기초)

fun main() {
    val (n, m, s) = readln().split(" ").map { it.toInt() }

    val graph = Array(n + 1) { mutableListOf<Int>() }
    val visited = BooleanArray(n + 1) { false }

    repeat(m) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
    }

    graph.forEach { it.sort() }

    val q = ArrayDeque<Int>()
    visited[s] = true
    q.addLast(s)

    while (q.isNotEmpty()) {
        val cur = q.removeFirst()
        println(cur)
        for (next in graph[cur]) {
            if (!visited[next]) {
                visited[next] = true
                q.addLast(next)
            }
        }
    }
}
