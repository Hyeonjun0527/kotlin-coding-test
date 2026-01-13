
//연결리스트 방식 노드 에지 시작
fun main() {
    val (n, m, s) = readln().split(" ").map { it.toInt() }

    val graph = MutableList (n + 1) { mutableListOf<Int>() }

    repeat(m) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
    }

    // 방문할 수 있는 정점이 여러 개면 작은 번호부터
    for (i in 1..n) graph[i].sort()

    // 1) DFS (재귀)
    val visitedDfs = BooleanArray(n + 1)
    val dfsOut = StringBuilder()

    //방문, 출력→ 이웃 순회→ 안 갔으면 들어가
    fun dfs(v: Int) {
        visitedDfs[v] = true
        dfsOut.append("${v} ")
        graph[v].forEach {
            if (visitedDfs[it]) return@forEach
            dfs(it)
        }
    }

    dfs(s)
    if (dfsOut.isNotEmpty()) dfsOut.setLength(dfsOut.length - 1) // 마지막 공백 제거
    println(dfsOut)

    // 2) BFS (큐) bfs는 이웃노드를 싹다 순차적으로 탐색할거라고 약속하니까 레벨순서
    val visitedBfs = BooleanArray(n + 1)
    val bfsOut = StringBuilder()
    val q = ArrayDeque<Int>()

    visitedBfs[s] = true
    q.addLast(s)

    while (q.isNotEmpty()) {
        val cur = q.removeFirst()
        bfsOut.append("${cur} ")
        graph[cur].forEach {
            if (visitedBfs[it]) return@forEach
            visitedBfs[it] = true
            q.addLast(it)
        }
    }

    if (bfsOut.isNotEmpty()) bfsOut.setLength(bfsOut.length - 1) // 마지막 공백 제거
    println(bfsOut.toString())
}
