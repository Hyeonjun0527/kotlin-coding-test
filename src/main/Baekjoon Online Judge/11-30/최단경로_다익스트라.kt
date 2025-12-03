/*

다익스트라
1) 지금까지 발견한 곳 중에서 제일 가까운 곳부터 먼저 가고 최단거리 확정한다.(최단거리 확정 : visited[cur]=true 다신 안들르기로 한거니 확정한거.)
2) 거기서 더 싸게 갈 수 있는 새로운 곳들을 살짝 더 계산해본다.
3) 이걸 계속 반복하면 초기 위치에서 모든 곳까지의 최단 거리가 구해진다.

제일 짧은 길부터 간다는 원칙이 있으니 우선순위 큐가 딱 맞음. O(log N)으로 바로 꺼내줌.

베스 디비파(v,e,s,d,v,p,a)
vertex 노드
edge 에지
s 시작점
dist 노드당 최단거리배열
visited 노드당 방문배열
pq 가중치 우선순위 큐
adj 인접 리스트

nd : new distance 새로 계산된 거리 = 현재 노드를 거쳐서 next로 가면 총 비용이 얼마냐?

while
// 1) 지금까지 후보 중 제일 짧은 길 하나 확정
// 2) 그 확정된 노드에서 갈 수 있는 새 경로들 계산
// 3) 더 싸면 갱신하고 후보에 넣어서 다음 탐색 준비

지금까지 후보 == 우선순위 큐에 들어간 요소들
갈 수 있는 새 경로 == 이웃경로

dist[nv] > nd
dist[nv] : 이웃 노드까지 가는 누적 최소거리(현재까지 구한 최선)
nv : neighbor vertex, next vertex
nd : new distance
* */
import java.util.PriorityQueue

fun main() {
    data class Edge(val v: Int, val w: Int)
    // vertex, edge, start
    val (v, e) = readln().split(" ").map {it.toInt()}
    val s = readln().toInt()

    val dist = IntArray(v + 1) { Int.MAX_VALUE }
    val visited = BooleanArray(v + 1) { false }
    val pq = PriorityQueue<Edge>(compareBy { it.w })
    val adj = List(v + 1) { mutableListOf<Edge>() }

    dist[s] = 0
    pq.add(Edge(s, 0))

    repeat(e) {
        val (start, end, w) = readln().split(" ").map { it.toInt() }
        adj[start].add(Edge(end, w))
    }

    while (pq.isNotEmpty()) {
        val (cur, d) = pq.poll()
        if (visited[cur]) continue
        visited[cur] = true
        // 현재 노드에서 나가는 간선들 순회
        adj[cur].forEach { (nv, w) ->
            val nd = d + w
            if (dist[nv] > nd) {
                dist[nv] = nd
                pq.add(Edge(nv, nd))
            }
        }
    }

    val sb =StringBuilder()

    for (d in dist) {
        if (d == Int.MAX_VALUE) sb.append("INF\n")
        else sb.append("${d}\n")
    }

//    for (i in 1 until v + 1) {
//        if (!visited[i]) sb.append("INF\n")
//        else sb.append("${dist[i]}\n")
//    }
    print(sb.toString())
}
