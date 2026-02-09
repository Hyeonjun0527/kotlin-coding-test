package backjoon.`20260205`//  아무래도이문제는A번난이도인것같다

fun main(args: Array<String>) {
    val t = readln().toInt()
    repeat(t) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        println("yes")
    }
}

/*
소인수분해 어케하지
 6 = 3 2
10 = 5 2
1~a로 나눠보자. %item == 0 이면 담아. 그렇게 소인수 구해. 그리고
* */