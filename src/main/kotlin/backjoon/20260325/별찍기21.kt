package backjoon.`20260325`//  별 찍기 - 7

fun main(args: Array<String>) {
    val n = readln().toInt()
    var sol = ""

    //처음인경우
    for (i in 0..n-1) {
        for (j in 0..n-1+i)  {
            sol += "*"
        }
        sol += "\n"
    }
    println(sol)

}