package 코테.`12-14`

import kotlin.random.Random
import kotlin.math.roundToLong

private data class Stats(val timesMs: LongArray) {
    fun avg(): Double = timesMs.average()
    fun min(): Long = timesMs.minOrNull() ?: 0L
    fun max(): Long = timesMs.maxOrNull() ?: 0L
}

fun main() {
    val V = 100_000
    val E = 10_000_000
    val START = 1
    val WARMUP = 3
    val TRIALS = 5
    val SEED = 42

    println("=== BFS 성능 비교 ===")
    println("V=$V, E=$E, START=$START, WARMUP=$WARMUP, TRIALS=$TRIALS, SEED=$SEED")

    // 그래프 생성 (중복 간선 생겨도 OK - 비교는 visited로 검증할 거라서)
    val rng = Random(SEED)
    val temp = Array(V + 1) { mutableListOf<Int>() }

    repeat(E) {
        var a = rng.nextInt(1, V + 1)
        var b = rng.nextInt(1, V + 1)
        if (a == b) b = if (b < V) b + 1 else 1
        temp[a].add(b)
        temp[b].add(a)
    }

    val adj: List<List<Int>> = temp.map { it.sorted() }

    fun visitedCount(visited: BooleanArray): Int {
        var c = 0
        for (i in 1..V) if (visited[i]) c++
        return c
    }

    println("\n[Warmup]")
    repeat(WARMUP) { i ->
        val v1 = BooleanArray(V + 1)
        val v2 = BooleanArray(V + 1)

        bfsChain(START, adj, v1)
        bfsLoop(START, adj, v2)

        val c1 = visitedCount(v1)
        val c2 = visitedCount(v2)

        if (c1 != c2) {
            println("!! 방문 수 불일치: chain=$c1, loop=$c2")
            return
        }
        println("warmup ${i + 1}: visited=$c1")
    }

    println("\n[Measure]")
    val chainTimes = LongArray(TRIALS)
    val loopTimes = LongArray(TRIALS)

    repeat(TRIALS) { t ->
        run {
            val visited = BooleanArray(V + 1)
            val startNs = System.nanoTime()
            bfsChain(START, adj, visited)
            val endNs = System.nanoTime()
            val ms = (endNs - startNs) / 1_000_000
            chainTimes[t] = ms

            val c = visitedCount(visited) // 측정 밖(측정 후)에 계산
            println("trial ${t + 1} - chain: ${ms}ms (visited=$c)")
        }

        run {
            val visited = BooleanArray(V + 1)
            val startNs = System.nanoTime()
            bfsLoop(START, adj, visited)
            val endNs = System.nanoTime()
            val ms = (endNs - startNs) / 1_000_000
            loopTimes[t] = ms

            val c = visitedCount(visited) // 측정 밖(측정 후)에 계산
            println("trial ${t + 1} - loop : ${ms}ms (visited=$c)")
        }

        println("---")
    }

    val chainStats = Stats(chainTimes)
    val loopStats = Stats(loopTimes)

    println("\n[Summary]")
    println("chain avg=${chainStats.avg().roundToLong()}ms  min=${chainStats.min()}ms  max=${chainStats.max()}ms")
    println("loop  avg=${loopStats.avg().roundToLong()}ms  min=${loopStats.min()}ms  max=${loopStats.max()}ms")
    val ratio = chainStats.avg() / loopStats.avg()
    println("avg ratio (chain/loop) = ${"%.2f".format(ratio)}x")
}

private fun bfsChain(start: Int, adj: List<List<Int>>, visited: BooleanArray) {
    val dq = ArrayDeque<Int>()
    dq.addLast(start)
    visited[start] = true

    while (dq.isNotEmpty()) {
        val cv = dq.removeFirst()
        adj[cv]
            .filter { !visited[it] }
            .onEach { visited[it] = true }
            .forEach { dq.addLast(it) }
    }
}

private fun bfsLoop(start: Int, adj: List<List<Int>>, visited: BooleanArray) {
    val dq = ArrayDeque<Int>()
    dq.addLast(start)
    visited[start] = true

    while (dq.isNotEmpty()) {
        val cv = dq.removeFirst()
        for (nv in adj[cv]) {
            if (!visited[nv]) {
                visited[nv] = true
                dq.addLast(nv)
            }
        }
    }
}
