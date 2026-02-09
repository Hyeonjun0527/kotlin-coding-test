package backjoon.`20260206`//  2진수 8진수

fun main(args: Array<String>) {
    val sb = StringBuilder()
    var str = readln()
    if (str.length % 3 != 0) {
        val cnt = str.length % 3
        repeat(3 - cnt) {
            str = "0" + str
        }
    }
//    println(list)

    var cnt = 0//읽는횟수 3번마다 초기화하려고.
    val list2 = arrayOf(4,2,1)//곱하는수
    var tempNum = 0
    for (i in 0 until str.length) {
        tempNum += str[i].digitToInt() * list2[(i % 3)]
        cnt++
        if (cnt % 3 == 0) {
            sb.append(tempNum)
            tempNum = 0
            cnt = 0
        }
    }
    print(sb)
}