package backjoon.`20260224`//  거스름돈

fun main(args: Array<String>) {
    var (n,남은돈 ) = readln().split(" ").map { it.toInt() }
    val 동전들 = mutableListOf<Int>()
    repeat(n) {
        val coin = readln().toInt()
        동전들.add(coin)
    }
    동전들.sortDescending()
    var sum = 0
    for (동전 in 동전들) {
        if (남은돈 >= 동전) {
            val 횟수 = 남은돈 / 동전 // 1 /1
            남은돈 -= 횟수 * 동전
            sum += 횟수
        }
    }
    println(sum)
}