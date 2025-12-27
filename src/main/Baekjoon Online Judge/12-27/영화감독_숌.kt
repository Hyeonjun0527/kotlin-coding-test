//  영화감독 숌

fun main(args: Array<String>) {
    val n = readln().trim().toInt()
    var cnt = 0
    var num = 665
    while (cnt < n) {
        num++
        if (num.toString().contains("666")) {
            cnt++
        }
    }
    print(num)
}