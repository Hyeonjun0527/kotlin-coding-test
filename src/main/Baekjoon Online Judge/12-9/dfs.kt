package 코테.`12-9`
fun main() {

    fun dfs(v: Int,
            adj: List<List<Int>>,
            visited: BooleanArray) {
        if (visited[v]) return
        visited[v] = true
        print("$v ")
        adj[v].forEach { nv ->
            dfs(nv, adj, visited)
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

    val sortedAdj = adj.map { it.sorted() }

    val visited = BooleanArray(v + 1)

    dfs(s, sortedAdj, visited)
}
