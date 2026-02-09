package backjoon.`20260208`

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

    val dist = Array(n + 1) { IntArray(n + 1) { -1 } }
    val 큐 = ArrayDeque<Int>()

    fun bfs(start: Int) {
        val d = dist[start]
        d[start] = 0
        큐.clear()
        큐.addLast(start)

        while (큐.isNotEmpty()) {
            val cur = 큐.removeFirst()
            adjList[cur]
                .asSequence()
                .filter { next -> d[next] == -1 }
                .onEach { next -> d[next] = d[cur] + 1 }
                .forEach { next -> 큐.addLast(next) }
        }
    }

    for (s in 1..n) bfs(s)

    var bestA = 1
    var bestB = 2
    var bestSum = Long.MAX_VALUE

    for (a in 1..n) {
        for (b in a + 1..n) {
            var sum = 0L
            for (x in 1..n) {
                val oneWay = min(dist[x][a], dist[x][b])
                sum += oneWay * 2L
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
