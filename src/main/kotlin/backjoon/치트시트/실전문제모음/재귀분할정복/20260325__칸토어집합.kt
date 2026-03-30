package backjoon.치트시트.실전문제모음.재귀분할정복

// 알고리즘 분류: 재귀분할정복

fun main(args: Array<String>) {

    while(true) {
        val input = readlnOrNull()
        if (input != null) {
            val n = input.toInt()
            var size = 1
            repeat(n) {
                size *= 3
            }
            //사이즈가 size인 문자열 생성 or 배열생성

            var list = MutableList(size) {" "}

            fun 재귀(s:Int, e:Int) {
                var 크기 = e - s + 1
                if (s < e) {
                    val 중간1 = s + 크기 / 3 - 1
                    val 중간2 = s + 크기 / 3 * 2 - 1
                    재귀(s,중간1)
                    재귀(중간2 + 1,e)
                } else {
                    list[s] = "-"
                }
            }
            재귀(0,size-1)
            println(list.joinToString(""))
        } else {
            return
        }
    }
}
