package backjoon.`20260226`

fun main() {
    val n = readln().toInt()
    val 채널들 = MutableList<String>(n) { readln() }
    var 커서 = 0
    val sb = StringBuilder()
    fun 이동(대상채널: String, 목표위치: Int) {
        var 대상채널위치 = 채널들.indexOf(대상채널)

        while (커서 < 대상채널위치) {
            sb.append("1")
            커서++
        }

        while (대상채널위치 > 목표위치) {
            sb.append("4")
            채널들[대상채널위치] = 채널들[대상채널위치 - 1]
                .also { 채널들[대상채널위치 - 1] = 채널들[대상채널위치] }
            대상채널위치--
            커서--
        }
    }

    이동("KBS1", 0)
    이동("KBS2", 1)

    print(sb)
}