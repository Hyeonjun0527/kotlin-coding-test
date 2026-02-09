package backjoon.치트시트.DFS와BFS
fun main() {
    val (n, m, s) = readln().split(" ").map { it.toInt() }

    val adjList = MutableList(n+1) { mutableListOf<Int>() }

    repeat(m) {
        val (a,b) = readln().split(" ").map { it.toInt() }
        adjList[a].add(b)
        adjList[b].add(a)
    }

    for (i in 1..n) adjList[i].sort()

    val visitedDfs = BooleanArray(n + 1)
    val dfsOut = StringBuilder()

    fun dfs(v: Int) {
        visitedDfs[v] = true
        dfsOut.append("${v} ")
        adjList[v].forEach {
            if (visitedDfs[it]) return@forEach
            dfs(it)
        }
    }
    dfs(s)
    if (dfsOut.isNotEmpty()) dfsOut.setLength(dfsOut.length - 1)
    println(dfsOut)

    val visitedBfs = BooleanArray(n + 1)
    val bfsOut = StringBuilder()
    val q = ArrayDeque<Int>()

    visitedBfs[s] = true
    q.addLast(s)

    while (q.isNotEmpty()) {
        val cur = q.removeFirst()
        bfsOut.append("${cur} ")
        adjList[cur].forEach {
            if (visitedBfs[it]) return@forEach
            visitedBfs[it] = true
            q.addLast(it)
        }
    }

}