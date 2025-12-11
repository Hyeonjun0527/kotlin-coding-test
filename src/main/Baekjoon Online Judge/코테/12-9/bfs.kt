import java.util.Arrays

fun main() {

    fun bfs(start: Int, adj: List<List<Int>>, visited: BooleanArray) {
        val dq = ArrayDeque<Int>()

        dq.addLast(start)
        visited[start] = true

        while (dq.isNotEmpty()) {
            val cv = dq.removeFirst()
            print("$cv ")

            adj[cv]
                .filter { nv -> !visited[nv] }
                .onEach { nv -> visited[nv] = true }
                .forEach { nv -> dq.addLast(nv) }
        }
    }

    val (v, e, s) = readln().split(" ").map { it.toInt() }

    val adj = MutableList(v + 1) { mutableListOf<Int>() }

    repeat(e) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        adj[a].add(b)
        adj[b].add(a)
    }

    // 정점 번호 작은 것부터 방문하도록 정렬
    for (i in 1..v) adj[i].sort()

    val visited = BooleanArray(v + 1)

    bfs(s, adj, visited)
}
