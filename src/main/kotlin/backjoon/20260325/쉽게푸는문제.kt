package backjoon.`20260325`//  쉽게 푸는 문제

fun main(args: Array<String>) {
    var (a,b) = readln().split(" ").map { it.toInt() }
    //인덱스교정
    a--
    b--
    //
    var list = mutableListOf<Int>()
    var 넣을수 = 1
    var 반복 = 0
    while (반복 <= 999) {
        repeat(넣을수) {
            list.add(넣을수)
        }
        넣을수++
        반복++
    }

    var sum = 0
    for (i in a..b) {
        sum += list[i]
    }
    println(sum)
}