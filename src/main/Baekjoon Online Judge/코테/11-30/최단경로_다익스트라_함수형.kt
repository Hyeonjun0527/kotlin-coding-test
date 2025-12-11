
v,e,s 베스
a,p 앞
dvp 디비파

import java.util.PriorityQueue

fun main() {
    data class Edge(val v: Int,
                    val w: Int)

    val (v, e) = readln().split(" ").map { it.toInt() }
    val s = readln().toInt()

    val adj = run {
        val adj = List(v + 1) { mutableListOf<Edge>() }
        List(e) {
            readln().split(" ").map { it.toInt() }
        }.forEach { (start, end, w) ->
            adj[start].add(Edge(end, w))
        }
        adj
    }

    // prev 선언 (경로 복원용)
    val (dist,prev) = run {
        val prev = IntArray(v + 1) { -1 }
        val dist = IntArray(v + 1){ Int.MAX_VALUE }
        val visited = BooleanArray(v + 1){ false }
        val pq = PriorityQueue<Edge>(compareBy{it.w})
        dist[s] = 0
        pq.offer(Edge(s,0))
        while (pq.isNotEmpty()) {
            val (cv, ccd) = pq.poll()
            if (visited[cv]) continue
            visited[cv] = true

            adj[cv]
                //그냥 단순 w는 필요없고 누적 거리 + w인 새로운 거리만 필요해서 변환
                .map { (nv, w) -> nv to (ccd + w) }
                // relax 조건
                .filter { (nv, nd) -> dist[nv] > nd }
                // 실제 갱신
                .forEach { (nv, nd) ->
                    dist[nv] = nd
                    prev[nv] = cv
                    pq.offer(Edge(nv, nd))
                }
        }
        dist to prev
    }
    println(
        dist.drop(1).joinToString("\n") {
            if (it == Int.MAX_VALUE) "INF" else it.toString()
        }
    )

}

