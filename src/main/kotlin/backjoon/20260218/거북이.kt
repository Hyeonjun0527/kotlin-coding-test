package backjoon.`20260218`//  거북이

fun main(args: Array<String>) {
    val n = readln().toInt()
    val sb = StringBuilder()
    var dx = arrayOf(0,1,0,-1)
    var dy = arrayOf(1,0,-1,0)
    repeat(n) {
        val orderList = readln()
        var dir = 0
        var x = 0
        var y = 0
        var minX = 0
        var minY = 0
        var maxX = 0
        var maxY = 0

        fun 사각형계산() {
            maxX = maxOf(maxX, x)
            minX = minOf(minX, x)
            maxY = maxOf(maxY, y)
            minY = minOf(minY, y)
        }
        for (order in orderList) {
            when(order) {
                'L'-> dir = (dir + 3) % 4//4 -> 0,1,2,3
                'R'-> dir = (dir + 1) % 4
                'F'-> {
                    x += dx[dir]
                    y += dy[dir]
                    사각형계산()
                }
                'B' -> {
                    x -= dx[dir]
                    y -= dy[dir]
                    사각형계산()
                }
            }
        }
        val width = maxX - minX
        val height = maxY - minY
        sb.append(width*height).append("\n")
    }
    print(sb)
}