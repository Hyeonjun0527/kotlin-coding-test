package backjoon.치트시트.실전문제모음.수학

// 알고리즘 분류: 수학

fun main(args: Array<String>) {
    val 색상종류수 = readln().toInt()//m 안씀
    val list = readln().split(" ").map { it.toInt() }
    val k = readln().toInt()

    var sum = 0
    for (it in list) {
        sum += it
    }
    var 분자 = 0.0
    var 분모 = 1.0
    var 값 = 1.0

    for (i in list.indices) {
        for (j in 0..k-1) {
            값 *= if ((list[i] - j) <= 0) 0 else (list[i] - j)
//            println(값)
        }
        분자 += 값
        값 = 1.0
    }
//    println(분자)

    for (j in 0..k-1) {
        분모 *= (sum - j)
    }
//    println(분모)

    print(분자/분모)

}
