package 코테.`12-9`

fun main() {
    val (v, e, s) = readln().split(" ").map { it.toInt() }

    val adj = List(v + 1) { mutableListOf<Int>() }.apply {
        repeat(e) {
            val (a, b) = readln().split(" ").map { it.toInt() }
            this[a].add(b)
            this[b].add(a)
        }
    }

    // 정점 번호 작은 것부터 방문하도록 정렬
    val sortedAdj: List<List<Int>> = adj.map { it.sorted() }

    val visited = BooleanArray(v + 1)

    fun bfs(start: Int) {
        val dq = ArrayDeque<Int>()
        dq.addLast(start)
        visited[start] = true

        while (dq.isNotEmpty()) {
            val cv = dq.removeFirst()
            print("$cv ")

            for (nv in sortedAdj[cv]) {
                if (!visited[nv]) {
                    visited[nv] = true
                    dq.addLast(nv)
                }
            }
        }
    }

    bfs(s)
}
