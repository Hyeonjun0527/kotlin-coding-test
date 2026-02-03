fun main() {
    val (n, m, s) = readln().split(" ").map { it.toInt() }
    val graph = MutableList(n + 1) { mutableListOf<Int>()}

    repeat(m) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
    }
    for (i in 1..n) graph[i].sort()
    val visitedDfs = BooleanArray(n + 1)
    val dfsOut = StringBuilder()

    fun dfs(v: Int) {
        visitedDfs[v] = true
        dfsOut.append("${v} ")
        graph[v].forEach {
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
    val prev = IntArray(n + 1) { -1 }

    visitedBfs[s] = true
    prev[s] = s              // 시작점은 자기 자신을 가리키게 처리
    q.addLast(s)
    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        bfsOut.append("${cur} ")
        graph[cur].forEach {
            if (visitedBfs[it]) return@forEach
            visitedBfs[it] = true
            prev[it] = cur
            q.addLast(it)
        }
    }
    if (bfsOut.isNotEmpty()) bfsOut.setLength(bfsOut.length - 1)
    println(bfsOut)
}