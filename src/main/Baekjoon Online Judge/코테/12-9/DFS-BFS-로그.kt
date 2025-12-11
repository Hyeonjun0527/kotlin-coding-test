import java.util.Arrays

fun main() {

    fun dfs(v: Int, adj: List<List<Int>>, visited: BooleanArray, order: MutableList<Int>) {
        println("[DFS] dfs($v) 호출")

        if (visited[v]) {
            println("  → $v 는 이미 방문됨. 종료")
            return
        }

        visited[v] = true
        order.add(v)
        println("  → 방문: visited[$v] = true")

        adj[v].forEach { nv ->
            if (!visited[nv]) {
                println("  → $v -> $nv 이동")
                dfs(nv, adj, visited, order)
            } else {
                println("  → $v -> $nv 이미 방문됨. 스킵")
            }
        }

        println("[DFS] dfs($v) 종료\n")
    }

    fun bfs(start: Int, adj: List<List<Int>>, visited: BooleanArray): List<Int> {
        val dq = ArrayDeque<Int>()
        val order = mutableListOf<Int>()

        println("[BFS] 시작: start = $start")

        dq.addLast(start)
        visited[start] = true
        println("  → 큐 삽입: $start, visited[$start] = true")

        while (dq.isNotEmpty()) {
            val cv = dq.removeFirst()
            println("\n[BFS] 현재 정점: $cv")
            order.add(cv)

            adj[cv].forEach { nv ->
                if (!visited[nv]) {
                    visited[nv] = true
                    dq.addLast(nv)
                    println("  → $cv -> $nv 방문 & 큐 삽입")
                } else {
                    println("  → $cv -> $nv 이미 방문됨. 스킵")
                }
            }
        }

        println("\n[BFS] 종료")
        return order
    }

    // 입력 처리
    val (v, e, s) = readln().split(" ").map { it.toInt() }
    val adj = MutableList(v + 1) { mutableListOf<Int>() }

    repeat(e) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        adj[a].add(b)
        adj[b].add(a)
        println("간선 추가: $a <-> $b")
    }

    for (i in 1..v) {
        adj[i].sort()
        println("정점 $i 의 인접 리스트 정렬됨: ${adj[i]}")
    }

    val visited = BooleanArray(v + 1)

    println("\n===== DFS 로그 =====")
    val dfsOrder = mutableListOf<Int>()
    dfs(s, adj, visited, dfsOrder)

    println("DFS 방문 순서: $dfsOrder\n")

    Arrays.fill(visited, false)

    println("===== BFS 로그 =====")
    val bfsOrder = bfs(s, adj, visited)

    println("BFS 방문 순서: $bfsOrder")
}
