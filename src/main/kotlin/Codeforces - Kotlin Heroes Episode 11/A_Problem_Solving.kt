
//fun 11-15.backjoon.main(args: Array<String>) {
//    val t = readln().toInt()
//    repeat(t) {
//        val n = readln().toInt()
//        val list = readln().split(" ").map { it.toInt()}
//        var max_value = -1
//        for (i in 0..list.size-2) {
//            if (max_value < list[i]) {
//                max_value = list[i]
//            }
//        }
//        if (list[list.size-1] - max_value == 1) {
//            println("${max_value}")
//        } else {
//            println("Ambiguous")
//        }
//    }
//}

//dropLast contains a.last() 이용해서 풀기
//solve() 함수로 풀기

fun solve() {
    readln()
    val a = readln().split(' ').map { it.toInt() }
    println(if (a.dropLast(1).contains(a.last() - 1)) (a.last() - 1).toString() else "Ambiguous")
}

fun main() {
    val t = readln().toInt()
    repeat (t) {
        solve()
    }
}

////a.last, a.subList, max,
//
//fun backjoon.main() {
//    fun readInt() = readln().toInt()
//    repeat(readInt()) {
//        val n = readInt()
//        val a = readln().split(' ').map { it.toInt() }
//        if (a.last() == a.subList(0, n- 1).max() + 1) {
//            println(a.last() - 1)
//        }else {
//            println("Ambiguous")
//        }
//    }
//}