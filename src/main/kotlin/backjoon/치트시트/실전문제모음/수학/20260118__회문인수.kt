package backjoon.치트시트.실전문제모음.수학

// 알고리즘 분류: 수학

fun main(args: Array<String>) {
    val t = readln().toInt()
    repeat(t) {
        val n = readln().toInt()

        val result = if ((2..64).any {
            var x = n
            val list = mutableListOf<Int>()
            while(x > 0) {
                list.add(x % it)
                x /= it
            }
            (0 until list.size/2).all { i ->
                list[i] == list[list.lastIndex - i]
            }
        }) 1 else 0
        println(result)
    }
}
