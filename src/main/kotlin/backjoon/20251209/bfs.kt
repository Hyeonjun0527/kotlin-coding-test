package 코테.`12-9`
fun main() {

    fun bfs(start: Int,
            adj: List<List<Int>>,
            visited: BooleanArray) {
        val dq = ArrayDeque<Int>().apply {
            this.addLast(start)
        }

        visited[start] = true

        while (dq.isNotEmpty()) {
            val cv = dq.removeFirst()
            print("$cv ")

            adj[cv].asSequence()
                .filter { nv -> !visited[nv] }
                .onEach { nv -> visited[nv] = true }
                .forEach { nv -> dq.addLast(nv) }
        }
    }

    val (v, e, s) = readln().split(" ").map { it.toInt() }

    val adj = List(v + 1) { mutableListOf<Int>() }.apply {
        List(e) {
            readln().split(" ").map { it.toInt() }
        }.forEach { (a, b) ->
            this[a].add(b)
            this[b].add(a)
        }
    }

    // 정점 번호 작은 것부터 방문하도록 정렬
    val sortedAdj = adj.map { it.sorted() }

    val visited = BooleanArray(v + 1)

    bfs(s, sortedAdj, visited)
}
