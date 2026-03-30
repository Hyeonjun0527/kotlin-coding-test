package backjoon.치트시트.실전문제모음.출력

// 알고리즘 분류: 출력

//
//fun backjoon.main(args: Array<String>) {
//    val n = readln().toInt()
//    repeat(n) {
//        val (a,b) = readln().split(" ").map {it.toInt()}
//        println(a+b)
//    }
//}

fun main() {
    readln()
    while(true) {
        val line = readlnOrNull() ?: break
        if(line.isBlank()) break

        val (a,b) = line.split(" ").map {it.toInt()}
        println(a+b)
    }
}
