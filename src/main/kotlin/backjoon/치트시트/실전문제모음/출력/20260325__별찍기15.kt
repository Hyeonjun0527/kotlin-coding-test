package backjoon.치트시트.실전문제모음.출력

// 알고리즘 분류: 출력

fun main(args: Array<String>) {
    val n = readln().toInt()
    var sol = ""

    //처음인경우
    for (i in 0..n-1) {
        var line = CharArray(n+i) { ' ' }
        line[line.size-1] = '*'

        line[line.size-1-2*i] = '*'

        sol += line.concatToString()
        sol += "\n"
    }
    println(sol)
}
