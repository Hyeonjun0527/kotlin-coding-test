package backjoon.치트시트.실전문제모음.정렬

// 알고리즘 분류: 정렬

import java.util.PriorityQueue
fun main(args: Array<String>) {
    val n = readln().toInt()

    val 카드뭉치우선순위큐 = PriorityQueue<Int>()
    repeat(n) {
        val 카드묶음수 = readln().toInt()
        카드뭉치우선순위큐.add(카드묶음수)
    }
    var sum = 0

    if (n == 1) {
        print(0)
        return
    }

    while(카드뭉치우선순위큐.isNotEmpty()) {
        if (카드뭉치우선순위큐.size == 1) {
            카드뭉치우선순위큐.remove()
            continue
        }
        val 카드1 = 카드뭉치우선순위큐.remove()
        val 카드2 = 카드뭉치우선순위큐.remove()
        val 뭉친카드 = 카드1 + 카드2
        sum += 뭉친카드
        카드뭉치우선순위큐.add(뭉친카드)
    }
    print(sum)
}
