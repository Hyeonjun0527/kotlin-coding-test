package backjoon.치트시트.실전문제모음.시뮬레이션

// 알고리즘 분류: 시뮬레이션

fun main(args: Array<String>) {
    val inputList = ArrayList<Int>()
    repeat(28) {
        inputList.add(readln().toInt())
    }
    val sol = (1..30).filter {it !in inputList}
    sol.forEach { println(it) }
}
