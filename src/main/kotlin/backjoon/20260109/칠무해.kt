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