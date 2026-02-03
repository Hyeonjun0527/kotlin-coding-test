
import java.util.PriorityQueue


fun main() {

    data class Edge(val to: Int, val w: Int)

    val (v, e) = readln().split(" ").map {it.toInt()}
    val s = readln().toInt()
    println("입력: 노드 수 v=$v, 간선 수 e=$e, 시작 노드 s=$s")

    // 인접 리스트
    val adj = List(v + 1) { mutableListOf<Edge>() }

    repeat(e) { idx ->
        val (start, end, w) = readln().split(" ").map { it.toInt() }
        println("간선 입력 #${idx + 1}: $start -> $end (가중치=$w)")
        adj[start].add(Edge(end, w))
    }

    val dist = IntArray(v + 1) { Int.MAX_VALUE }
    val visited = BooleanArray(v + 1) { false }

    val pq = PriorityQueue<Edge>(compareBy { it.w })

    dist[s] = 0
    pq.add(Edge(s, 0))
    println("초기화: dist[$s] = 0, PQ에 (node=$s, d=0) 추가")

    println("===== 다익스트라 시작 =====")

    while (pq.isNotEmpty()) {
        val (node, d) = pq.poll()
        println("\n[STEP] PQ에서 꺼냄: node=$node, d=$d")

        if (visited[node]) {
            println("  node=$node 는 이미 방문 처리된 노드 -> 건너뜀")
            continue
        }

        visited[node] = true
        println("  node=$node 방문 처리, 최단 거리 확정 d=$d")

        // 이 노드에서 나가는 간선들 검사
        adj[node].forEach { (next, w) ->
            println("    이웃 간선 확인: $node -> $next (w=$w)")

            val nd = d + w
            println("      후보 거리 nd = 현재거리 d($d) + 간선 w($w) = $nd")
            println("      현재 dist[$next] = ${dist[next]}")

            if (nd < dist[next]) {
                dist[next] = nd
                pq.add(Edge(next, nd))
                println("      갱신! dist[$next] = $nd 로 업데이트")
                println("      PQ에 새 상태 추가: (node=$next, d=$nd)")
            } else {
                println("      갱신 안 함 (기존 dist[$next]=${dist[next]} 가 더 짧음)")
            }
        }
    }

    println("\n===== 탐색 종료, 최종 결과 =====")

    val sb = StringBuilder()
    for (i in 1..v) {
        if (!visited[i]) {
            println("노드 $i : 도달 불가 → INF")
            sb.append("INF\n")
        } else {
            println("노드 $i : 최단 거리 = ${dist[i]}")
            sb.append(dist[i]).append('\n')
        }
    }

    println("\n===== 출력 =====")
    print(sb.toString())
}
