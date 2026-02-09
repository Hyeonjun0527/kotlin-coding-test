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

    val sortedAdj: List<List<Int>> = adj.map { it.sorted() }
    val visited = BooleanArray(v + 1)

    fun dfs(cur: Int) {
        if (visited[cur]) return
        visited[cur] = true
        print("$cur ")
        for (nv in sortedAdj[cur]) {
            dfs(nv)
        }
    }

    dfs(s)
}
