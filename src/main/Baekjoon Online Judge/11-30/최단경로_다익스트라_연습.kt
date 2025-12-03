
import java.util.PriorityQueue

/*

다익스트라
지금까지 발견한 길 중에서 제일 짧은 길부터 먼저 간다.
거기서 갈 수 있는 새로운 길들을 살짝 더 계산해본다.
이걸 계속 반복하면 모든 곳에 대한 최단 거리가 구해진다.

지금까지 후보중 제일 짧은 길 하나 확정
거기서 갈 수 있는 새 경로들 계산
더 싸면 갱신하고 후보에 넣어서 다음 탐색 준비

* */

fun main() {
    data class Edge(val v:Int, val w:Int)
    val (v, e) = readln().split(" ").map{ it.toInt() }
    val s = readln().toInt()

// dist 노드당 최단거리배열
// visited 노드당 방문배열
// pq 가중치 우선순위 큐
// adj 인접 리스트
    val dist = IntArray(v + 1) { Int.MAX_VALUE}
    val visited = BooleanArray(v + 1) { false }
    val pq = PriorityQueue<Edge>(compareBy { it.w })
    val adj = List(v + 1) { mutableListOf<Edge>() }

    dist[s] = 0
    pq.add(Edge(s, 0))

    repeat(e) {
        val (sv, ev, w) = readln().split(" ").map { it.toInt() }
        adj[sv].add(Edge(ev, w))
    }


    while (pq.isNotEmpty()) {
        // 지금까지 후보 중 제일 짧은 길 하나 확정
        val (cur, d) = pq.poll()
        if (visited[cur]) continue
        visited[cur] = true

        // 그 확정된 노드에서 갈 수 있는 새 경로를 계산
        adj[cur].forEach{ (nv, w) ->
            val nd = d + w

            //더 싸면 갱신하고 후보에 넣어서 다음 탐색 준비
            if (dist[nv] > nd) {
                dist[nv] = nd
                pq.add(Edge(nv, nd))
            }
        }
    }

}