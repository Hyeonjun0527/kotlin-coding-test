package backjoon.치트시트.실전문제모음.greedy

// 알고리즘 분류: greedy

fun main(args: Array<String>) {
    val n = readln().toInt()

    var cnt = n / 5
    var 봉지수 = 0
    while (cnt != -1) {
        var x = n
        x -= cnt * 5
        if (x % 3 == 0) {
            //3으로 나누어 떨어지면 통과
            봉지수 = cnt + x / 3
            println(봉지수)
            return
        }
        //봉지수 계산 필요
        cnt--
    }
    println(-1)
}
