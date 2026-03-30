package backjoon.치트시트.실전문제모음.시뮬레이션

// 알고리즘 분류: 시뮬레이션

fun main(args: Array<String>) {

    val (n, m) = readln().split(" ").map { it.toInt() }

    val 에르다노바들 = readln().split(" ").map { it.toInt() }.toMutableList()
    //100만까지임
    에르다노바들.sort()
    val 오리진스킬들 = readln().split(" ").map { it.toInt() }.toMutableList()
    오리진스킬들.sort()
    var 에르수 = 0
    var 오리수 = 0
    var 에르쿨돌아오는시간 = 0
    for (누른시점 in 에르다노바들) {
        if (에르쿨돌아오는시간 <= 누른시점) {
            에르수++
            에르쿨돌아오는시간 = 누른시점 + 100
        }
    }
    var 오리쿨돌아오는시간 = 0
    for (누른시점 in 오리진스킬들) {
        if (오리쿨돌아오는시간 <= 누른시점) {
            오리수++
            오리쿨돌아오는시간 = 누른시점 + 360
        }
    }

    print("${에르수} ${오리수}")


}
