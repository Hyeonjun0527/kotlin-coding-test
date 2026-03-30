package backjoon.치트시트.실전문제모음.브루트포스

// 알고리즘 분류: 브루트포스

//  영화감독 숌

fun main(args: Array<String>) {
    val n = readln().trim().toInt()
    var cnt = 0
    var num = 665
    while (cnt < n) {
        num++
        if (num.toString().contains("666")) {
            cnt++
        }
    }
    print(num)
}
