import java.util.ArrayDeque

// 전역 그래프 + 방문 배열
private lateinit var graph: Array<MutableList<Int>>
private lateinit var visited: BooleanArray

private fun bfs(start: Int) {
    val q = ArrayDeque<Int>()

    println("=== BFS 시작 ===")
    println("시작 정점: $start")
    println()

    visited[start] = true
    q.addLast(start)

    println("[초기 상태]")
    println("visited: ${visited.contentToString()}")
    println("queue  : $q")
    println()

    var step = 1

    while (q.isNotEmpty()) {
        println("----- step $step -----")

        val cur = q.removeFirst()
        println("큐에서 꺼낸 정점(cur): $cur")
        println("현재 큐 상태        : $q")

        println("→ graph[$cur] 에 연결된 이웃들: ${graph[cur]}")

        for (next in graph[cur]) {
            print("  - 이웃 next = $next : ")

            if (!visited[next]) {
                println("아직 방문 안 함 → 방문 처리 후 큐에 추가")
                visited[next] = true
                q.addLast(next)

                println("    visited: ${visited.contentToString()}")
                println("    queue  : $q")
            } else {
                println("이미 방문함 → 무시")
            }
        }

        println("===== step $step 끝 =====")
        println()
        step++
    }

    println("=== BFS 종료 ===")
    println("최종 visited: ${visited.contentToString()}")
}

// backjoon.main: 입력 + 그래프 구성
fun main() {
    val (n, m, s) = readln().split(" ").map { it.toInt() }

    graph = Array(n + 1) { mutableListOf() }
    visited = BooleanArray(n + 1)

    println("=== 입력받은 값 ===")
    println("정점 개수 n = $n, 간선 개수 m = $m, 시작 정점 s = $s")
    println()

    println("=== 간선 입력 시작 ===")
    repeat(m) { idx ->
        val (a, b) = readln().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
        println("간선 ${idx + 1}: $a - $b 추가")
    }
    println("=== 간선 입력 끝 ===")
    println()

    // 정점 번호 작은 순으로 방문하려고 정렬
    graph.forEachIndexed { i, adj ->
        if (i == 0) return@forEachIndexed
        adj.sort()
    }

    println("=== 인접 리스트(graph) 상태 ===")
    for (i in 1..n) {
        println("$i : ${graph[i]}")
    }
    println("============================")
    println()

    bfs(s)
}
