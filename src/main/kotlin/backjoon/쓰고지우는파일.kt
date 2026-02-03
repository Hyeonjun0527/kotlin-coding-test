fun main() {
    fun bfs(start: Int, adj: List<List<Int>>, visited: BooleanArray) {
        val dq = ArrayDeque<Int>()

        dq.addLast(start)
        visited[start] = true

        while(dq.isNotEmpty()) {
            val cv = dq.removeFirst()
            print("$cv ")

            adj[cv]
                .filter { !visited[it] }
                .onEach { visited[it] = true }
                .forEach { dq.addLast(it) }
        }
    }

    val (v, e, s) = readln().split(" ").map {it.toInt() }
    val adj = List(v + 1) { mutableListOf<Int>() }

    repeat(e) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        adj[a].add(b)
        adj[b].add(a)
    }

    val sortedAdj = adj.map { it.sorted() }
    val visited = BooleanArray(v + 1)

    bfs(s, sortedAdj, visited)

}