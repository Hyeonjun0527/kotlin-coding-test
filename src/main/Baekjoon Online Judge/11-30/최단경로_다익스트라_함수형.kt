package 코테.`12-9`

import java.util.PriorityQueue

data class Edge(val v: Int, val w: Int)// w = 누적거리

fun dijkstra(
    v: Int,
    s: Int,
    adj: List<List<Edge>>
): Pair<IntArray, IntArray> {

    val dist = IntArray(v + 1) { Int.MAX_VALUE }.apply { this[s] = 0 }
    val prev = IntArray(v + 1) { -1 }
    val visited = BooleanArray(v + 1)
    val pq = PriorityQueue<Edge>(compareBy { it.w }).apply { offer(Edge(s, 0)) }

    while (pq.isNotEmpty()) {
        val (cv, ccd) = pq.poll()
        if (visited[cv]) continue
        visited[cv] = true

        adj[cv]
            .map { (nv, w) -> nv to (ccd + w) }
            .filter { (nv, nd) -> dist[nv] > nd }
            .forEach { (nv, nd) ->
                dist[nv] = nd
                prev[nv] = cv
                pq.offer(Edge(nv, nd))
            }
    }

    return dist to prev
}

fun main() {
    val (v, e) = readln().split(" ").map { it.toInt() }
    val s = readln().toInt()

    val adj = List(v + 1) { mutableListOf<Edge>() }.apply {
        repeat(e) {
            val (a, b, w) = readln().split(" ").map { it.toInt() }
            this[a].add(Edge(b, w))
        }
    }

    val (dist, prev) = dijkstra(v, s, adj)

    println(
        dist.drop(1).joinToString("\n") {
            if (it == Int.MAX_VALUE) "INF" else it.toString()
        }
    )
}
