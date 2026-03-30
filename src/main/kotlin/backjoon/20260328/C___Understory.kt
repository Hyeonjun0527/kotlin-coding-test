package backjoon.`20260328`//  C - Understory
import java.util.PriorityQueue
fun main(args: Array<String>) {
    val n = readln().toInt()
    var 높이들 = PriorityQueue<Long>()
    var sb = StringBuilder()
    repeat(n) {
        var (명령,높이) = readln().split(" ").map { it.toLong() }

        if (명령 == 1L) {
            높이들.add(높이)
        } else {
            while (높이들.isNotEmpty() && 높이들.peek() <= 높이) {
                높이들.poll()
            }
        }
        sb.append("${높이들.size}\n")
    }
    print(sb)
}