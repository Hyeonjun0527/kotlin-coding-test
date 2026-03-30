package backjoon.치트시트.실전문제모음.그래프

// 알고리즘 분류: 그래프

import java.util.PriorityQueue

fun main(args: Array<String>) {
    //n-1은 넥서스. 0~n-2는 1이면 상대에게 보여서 못감

    val (n, m) = readln().split(" ").map { it.toInt() }
    //정점수 간선수
    var 갈수없다여부 = readln().split(" ").map { it.toInt() }
    data class 정점(var 번호:Int, var 시간:Long)
    var graph = MutableList(n) { mutableListOf<정점>() }
    repeat(m) {
        val (a,b,t) = readln().split(" ").map { it.toInt() }
        graph[a].add(정점(b,t.toLong()))
        graph[b].add(정점(a,t.toLong()))
    }
    var visited = MutableList(n) { false }
    //최소시간
    var pq = PriorityQueue<정점>(compareBy { it.시간 })
    var 최단거리 = MutableList(n) { Long.MAX_VALUE }
    fun 다익스트라(s:Int) {
        pq.add(정점(s,0))
        최단거리[s] = 0

        while (pq.isNotEmpty()) {
            var 현재정점 = pq.poll()
            if (현재정점.시간 != 최단거리[현재정점.번호]) {
                continue
            }
            visited[현재정점.번호] = true

            for (다음정점 in graph[현재정점.번호]) {
                if (visited[다음정점.번호]) {
                    continue
                }
                if (다음정점.번호 <= n - 2 && 갈수없다여부[다음정점.번호] == 1) {
                    continue
                }
                if (현재정점.시간 + 다음정점.시간 < 최단거리[다음정점.번호]) {

                    최단거리[다음정점.번호] = 현재정점.시간 + 다음정점.시간
                    pq.add(정점(다음정점.번호,최단거리[다음정점.번호]))
//                    println(pq)
//                    println("현재정점: ${현재정점}")
//                    println("다음정점 : ${다음정점}")
//                    println(dist[다음정점.정점번호])
//                    println()
                }
            }
//            println("=========")
        }
    }
    다익스트라(0)
    if (최단거리[n-1] == Long.MAX_VALUE) {
        println(-1)
    } else {
        println(최단거리[n-1])
    }


}
