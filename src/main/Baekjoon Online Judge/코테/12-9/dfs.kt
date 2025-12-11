import java.util.Arrays

fun main() {

    fun dfs(v: Int, adj: List<List<Int>>, visited: BooleanArray) {
        if (visited[v]) return
        visited[v] = true
        print("$v ")
        adj[v].forEach { nv ->
            dfs(nv, adj, visited)
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

    dfs(s, adj, visited)
}
