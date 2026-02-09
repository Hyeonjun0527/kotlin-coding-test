package backjoon.`20260207`//  24

fun main(args: Array<String>) {
    val (a,b,c) = readln().split(":").map { it.toInt() }
    val (a2,b2,c2) = readln().split(":").map { it.toInt() }
    val 초 = a * 3600 + b * 60 + c
    val 초2 = a2 * 3600 + b2 * 60 + c2
    var 결과초 = 초2 - 초
    if (결과초 < 0) {
        결과초 += 24 * 3600
    }
    var 시간 = 0
    if (결과초 >= 3600) {
        시간 = 결과초 / 3600//
        결과초 = 결과초 - 시간*3600
    }

    var 분 = 0
    if (결과초 >= 60) {
        분 = 결과초 / 60
        결과초 = 결과초 - 분*60
    }

    /*
    *   val hh = diff / 3600
        diff %= 3600
        val mm = diff / 60
        val ss = diff % 60
    *
    * */

    fun two(n: Int): String {
        return if (n < 10) "0$n" else n.toString()
    }
    print(two(시간)+":"+two(분)+":"+two(결과초))

//    print("%02d:%02d:%02d".format(시간, 분, 결과초))

}

/*
3600초가 몇 덩어리냐
남은 초에서 60초가 몇 덩어리냐
60으로 나눈 나머지

%02d
%d : 정수 출력
2 : 최소 폭을 2칸으로 맞춤
0 : 왼쪽 빈칸을 0으로 채움
그래서 %02d = “정수를 2자리로, 앞에 0 붙여서”
* */