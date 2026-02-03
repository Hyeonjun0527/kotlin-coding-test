fun main() {
    val (R, C) = readln().split(" ").map { it.toInt() }

    val map = Array(R) { readln().toCharArray() }

    val result = IntArray(5)

    for (i in 0 until R - 1) {
        for (j in 0 until C - 1) {

            var crash = 0
            var blocked = false

            val cells = arrayOf(
                map[i][j],
                map[i + 1][j],
                map[i][j + 1],
                map[i + 1][j + 1]
            )

            for (c in cells) {
                if (c == '#') {
                    blocked = true
                    break
                }
                if (c == 'X') crash++
            }

            if (!blocked) {
                result[crash]++
            }
        }
    }

    for (i in 0..4) {
        println(result[i])
    }
}
