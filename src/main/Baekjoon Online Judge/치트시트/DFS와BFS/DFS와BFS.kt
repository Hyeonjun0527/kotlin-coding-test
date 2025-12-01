//BFS – BOJ 1260 DFS와 BFS (가장 대표 기초)

private lateinit var graph: Array<MutableList<Int>>
private lateinit var visited: BooleanArray

private fun bfs(start: Int) {
    val q = ArrayDeque<Int>()

    visited[start] = true
    q.addLast(start)

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

fun main() {
    val (n, m, s) = readln().split(" ").map { it.toInt() }

    graph = Array(n + 1) { mutableListOf() }
    visited = BooleanArray(n + 1)

    repeat(m) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
    }

    graph.forEach { it.sort() }

    bfs(s)
}
