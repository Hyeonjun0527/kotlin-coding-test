import java.util.PriorityQueue

fun main() {
    data class Edge(val v: Int, val w: Int)
    val (v,e) = readln().split(" ").map{ it.toInt() }
    val s = readln().toInt()
    val dist = IntArray(v + 1){ Int.MAX_VALUE }
    val visited = BooleanArray(v + 1){ false }
    val pq = PriorityQueue<Edge>(compareBy{ it.w })
    val adj = List(v + 1){ mutableListOf<Edge>()}

    dist[s] = 0
    pq.offer(Edge(s,0))
    repeat(e) {
        val (start, end, w) = readln().split(" ").map{ it.toInt() }
        adj[start].add(Edge(end,w))
    }

    while(pq.isNotEmpty()) {
        val (cv, cad) = pq.poll()
        if (visited[cv]) continue
        visited[cv] = true

        adj[cv].forEach {
            (nv, w) ->
            val nd = cad + w
            if (dist[nv] > nd) {
                dist[nv] = nd
                pq.add(Edge(nv,nd))
            }
        }
    }
}
//cv: current vertex, cad: current accumulative distance, nv: neighbor vertex, nd: new distance
// if (dist[nv] > nd) {: 기존거리(dist[nv])보다 새로운거리(nd)가 빠르면 갱신한다.