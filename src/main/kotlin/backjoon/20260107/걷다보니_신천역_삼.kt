//  걷다보니 신천역 삼 (Large)
import kotlin.math.*
fun main(args: Array<String>) {
    val n = readln().toInt()

    if (n == 1) {
        println(0)
    } else if (n == 2) {
        println(2)
    } else {
        var v = 1.toLong()
        repeat(n-2) {
            v = v * 3 % 1000000009
        }
        println(v.toLong() * 2 % 1000000009)
    }
}



/*
자리수 2짜리는
자리수 1짜리 모든 경우에
0, 1, 2를 하나씩 붙여서 만든다.

1,000,000,009 로 나눈 나머지
* */