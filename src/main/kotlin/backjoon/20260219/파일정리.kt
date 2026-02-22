package backjoon.`20260219`//  파일 정리

fun main(args: Array<String>) {
    val n = readln().toInt()
    val map = HashMap<String,Int>()
    repeat(n) {
        val (_,확장자) = readln().split(".")
        map[확장자] = (map[확장자] ?: 0) + 1
    }
//    val list = map.entries.toMutableList()
//    list.sortBy { it.key }
    val list = map.entries.sortedBy { it.key }

    for ((k, v) in list) {
        println("$k $v")
    }

//    for ((k, v) in map.toSortedMap()) {
//        println("$k $v")
//    }
}