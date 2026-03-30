package backjoon.치트시트.실전문제모음.수학

// 알고리즘 분류: 수학

fun main(args: Array<String>) {
    val n = readln().toInt()
    repeat(n) {
        val inputs = readln().split(" ")
        var num = inputs[0].toDouble()
        inputs.drop(1).forEach {
            when (it) {
                "@" -> num *= 3
                "%" -> num += 5
                "#" -> num -= 7
            }
        }
        println("%.2f".format(num))
    }
}
