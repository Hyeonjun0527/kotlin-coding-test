//  숨바꼭질 시간복잡도 모르겟음

fun main(args: Array<String>) {
    val (N,K) = readln().split(" ").map { it.toInt() }
    val q = ArrayDeque<Int>()
    val cntList = IntArray(100001) { 0 }
    q.addLast(N)
    cntList[N] = 0
    while (q.isNotEmpty()) {
        val cur = q.removeFirst()
        if (cur == K) break

        val adjList = mutableListOf(cur - 1, cur + 1, cur * 2)
        adjList.forEach out@{
            if (it !in 0..100000) return@out
            if (cntList[it] != 0) return@out
            cntList[it] = cntList[cur] + 1
            q.addLast(it)
        }
    }
    print(cntList[K])
}