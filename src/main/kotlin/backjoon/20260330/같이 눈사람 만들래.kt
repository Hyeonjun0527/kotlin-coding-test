package backjoon.`20260330`//  같이 눈사람 만들래?
import kotlin.math.*
fun main(args: Array<String>) {
    var n = readln().toInt()
    var 눈덩이지름들 = readln().split(" ").map { it.toLong() }.toMutableList()
    //만들 수 있는 두 눈사람의 키 차이 중 최솟값
    // N 개중 4개를 골라서
    //n개중 2개 택해서 a 에 넣고, 2개 택해서 b에 넣고야.
    눈덩이지름들.sort()
    var sol = Long.MAX_VALUE
    for (i in 0 until n - 3) {
        for (j in i + 3 until n) {

            val 목표값 = 눈덩이지름들[i] + 눈덩이지름들[j]
            var left = i + 1
            var right = j - 1

            while (left < right) {
//                println("left : $left right : $right")
                val 후보값 = 눈덩이지름들[left] + 눈덩이지름들[right]
                sol = minOf(sol,abs(목표값-후보값))
                if (후보값 < 목표값) {
                    //후보값을 늘려야함
                    left++
                } else {
                    right--
                }
            }
        }
    }
    println(sol)
}