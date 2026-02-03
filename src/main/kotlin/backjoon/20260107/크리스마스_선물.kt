//  크리스마스 선물
import java.util.*

fun main(args: Array<String>) {
    val pq = PriorityQueue<Int>(compareByDescending { it })
    val n = readln().toInt()
    val sb = StringBuilder()
    repeat(n) {
        val line = readln().trim()
        if (line == "0") {//0인거임
            if (pq.isNotEmpty()) {
                val num = pq.poll()
                sb.append("${num}\n")
            } else {
                sb.append("-1\n")
            }
        } else {
            var list = line.trim().split(" ").map { it.toInt()}
            val 개수 = list[0]//이게 개수
            list = list.drop(1)
            list.forEach {
                pq.add(it)
            }
        }
    }
    print(sb)
}


/*
*
*
*
* */