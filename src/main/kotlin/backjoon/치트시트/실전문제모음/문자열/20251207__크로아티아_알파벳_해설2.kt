package backjoon.치트시트.실전문제모음.문자열

// 알고리즘 분류: 문자열

fun main() {
    val patterns = listOf("c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=")

    val result = patterns.fold(readln()) { acc, it ->
        acc.replace(it, "X")
    }

    println(result.length)
}
