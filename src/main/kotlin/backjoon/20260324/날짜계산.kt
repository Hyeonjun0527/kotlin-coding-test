package backjoon.`20260324`//  날짜 계산

fun main(args: Array<String>) {
    val (e,s,m) = readln().split(" ").map { it.toInt() }
    var 년도 = 1
    var ee = 1//현재 e
    var ss = 1
    var mm = 1
    while(true) {
        if (ee == e && ss == s && mm == m) {
            println(년도)
            break
        }

        ee++
        ss++
        mm++
        
        if (ee > 15) {
            ee = 1
        }
        if (ss > 28) {
            ss = 1
        }
        if (mm > 19) {
            mm = 1
        }
        년도++
    }
}