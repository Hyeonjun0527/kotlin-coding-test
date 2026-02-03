import java.util.PriorityQueue
import kotlin.math.abs

//  주차장
/*
1. 빈공ㅇ간 검사. 2없으면 입구에서 기다려. 3. 빈공간 생기면 주차.
4. 빈공간여러개면 번호 작은순부터 할당. 큐로 관리해. 주차료는 차량무게,주차공간의 요금
200,100,300,800 이거 주차 공간 번호임.

입력이 1부터면, 배열도 1부터 쓰면 편하다
출차 때문에 “차 → 주차칸” 매핑이 필수다



* */
fun main(args: Array<String>) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val 주차공간 = IntArray(n + 1) {
        if (it == 0) 0 else readln().toInt()
    }
    val 차목록 = IntArray(m + 1) {
        if (it == 0) 0 else readln().toInt()
    }
    val actionList = IntArray(2*m) {
        readln().toInt()
    }

    val 차랑별주차칸 = IntArray(m + 1)
    var 수입 = 0
    val pq = PriorityQueue<Int>()
    for (i in 1..n) pq.add(i)

    val dq = ArrayDeque<Int>()
    actionList.forEach { x ->
        val 차번호 = abs(x)
        if (x > 0) {
            if (pq.isNotEmpty()) {
                val 칸번호 = pq.poll()
                차랑별주차칸[차번호] = 칸번호
            } else {
                dq.addLast(차번호)
            }

        } else {
            val 빠진칸번호 = 차랑별주차칸[차번호]
            차랑별주차칸[차번호] = 0
            수입 += 주차공간[빠진칸번호] * 차목록[차번호]
            pq.add(빠진칸번호)
            if (dq.isNotEmpty()) {
                val 기다린_차번호 = dq.removeFirst()
                val 칸번호 = pq.poll()
                차랑별주차칸[기다린_차번호] = 칸번호
            }
        }
    }
    println(수입)
}