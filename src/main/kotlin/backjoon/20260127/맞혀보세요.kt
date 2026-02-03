
fun main(args: Array<String>) {
    val n = readln().toInt()
    val list = mutableListOf<MutableList<String>>()
    repeat(n) {
        val l = readln().split(" ")
            .toMutableList()
        list.add(l)
    }
    val target = readln()

    var 통과 = 1

    target.forEachIndexed outerr@ { i, x ->
        if (i == target.length - 1) {
            return@outerr
        }
        list.forEach { row ->
//            println("시작 ${row}")
            var 한줄통과 = 0
            row.forEachIndexed outer@ { j, y ->
                if (j == 0 || j == 1) {
                    return@outer
                }
//                println("x : ${x}, row[0] : ${row[0]}")
                if (x.toString() == row[0]) {
                    if (i + 1 <= target.length && target[i+1].toString() == y) {
//                        println(1)
                        한줄통과 = 1
                    }
                } else {
                    한줄통과 = 1
                }
            }
//            println(3)
            if (한줄통과 != 1) {
//                println("으")
                통과 = 0
            }
        }
    }
    if (통과 == 1) {
        println("yes")
    } else {
        println("no")
    }

}