package backjoon.치트시트.실전문제모음.시뮬레이션

// 알고리즘 분류: 시뮬레이션

//  칠무해
import java.util.PriorityQueue
fun main(args: Array<String>) {
    val n = readln().toInt()

    val q = PriorityQueue<Double>(compareByDescending{it})
    repeat(n) {
        val item = readln().toDouble()
        if (q.size >= 7) {
            if (q.peek() > item) {
                q.poll()
                q.add(item)
            }
        } else {
            q.add(item)
        }

    }
    q.toList().sorted().forEach {
        println("%.3f".format(it))
    }
}
