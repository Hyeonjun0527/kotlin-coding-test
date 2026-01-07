//  바이러스

fun main(args: Array<String>) {
    //7개 6쌍 에지 개수
    val n = readln().toInt()
    val e = readln().toInt()

    val adjList = MutableList(n + 1) { mutableListOf<Int>() }
    val visited = BooleanArray(n + 1)
    repeat(e) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        adjList[a].add(b)
        adjList[b].add(a)
    }

    var cnt = 0
    fun dfs(v: Int) {
        visited[v] = true
        cnt++
        adjList[v].forEach {
            if (visited[it]) return@forEach
            dfs(it)
        }
    }
    dfs(1)
    print(cnt - 1)
}