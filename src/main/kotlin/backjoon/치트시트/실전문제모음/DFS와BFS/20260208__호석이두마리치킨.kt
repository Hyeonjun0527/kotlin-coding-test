package backjoon.치트시트.실전문제모음.DFS와BFS

// 알고리즘 분류: DFS와BFS

import kotlin.math.min
import java.util.ArrayDeque

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    val adjList = Array(n + 1) { mutableListOf<Int>() }
    repeat(m) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        adjList[a].add(b)
        adjList[b].add(a)
    }

    // dist[s][v] = s에서 v까지 최단거리(간선 수), 가중치=1 이라 BFS로 구함

    /*
    dist는 점 i 에서 j까지의 거리표
    dist[start] (== d)는? start에서 각 노드까지의 거리표(1차원)”
    d[v]는 start에서 v까지 최단거리
    * */

    val dist = Array(n + 1) { IntArray(n + 1) { -1 } }
    val 큐 = ArrayDeque<Int>()

    // 시작점에서 모든 다른 점의 최단거리를 구하는 방법이군
    fun bfs(start: Int) {
        val d = dist[start]
        d[start] = 0
        큐.clear()
        큐.addLast(start)

        while (큐.isNotEmpty()) {
            val cur = 큐.removeFirst()
            for (next in adjList[cur]) {
                if (d[next] != -1) continue
                d[next] = d[cur] + 1
                큐.addLast(next)
            }
        }
    }

    // 모든 시작점에서 BFS 1번씩 -> all-pairs 최단거리 완성
    for (s in 1..n) bfs(s)

    var bestA = 1
    var bestB = 2
    var bestSum = Long.MAX_VALUE

    for (a in 1..n) {
        for (b in a + 1..n) {
            var sum = 0L
            for (x in 1..n) {
                val oneWay = min(dist[x][a], dist[x][b])
                sum += oneWay * 2L // 왕복
            }

            if (
                sum < bestSum ||
                (sum == bestSum && a < bestA) ||
                (sum == bestSum && a == bestA && b < bestB)
            ) {
                bestSum = sum
                bestA = a
                bestB = b
            }
        }
    }

    print("$bestA $bestB $bestSum")
}
