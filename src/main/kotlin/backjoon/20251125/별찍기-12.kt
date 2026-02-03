fun main() {

    //선언적 풀이를 하자.
    run {
        val n = readln().toInt()
        //나는 결과 문자열을 만들거야 라는 의미로 선언하는거 좋아.
        //패턴을 파악하는거도 좋아.

        fun line(n: Int, i: Int) =
            " ".repeat(n - i) +
                    "*".repeat(i)

        val upper = (1..n).map { line(n, it) }
        val lower = (n - 1 downTo 1).map { line(n, it) }

        print((upper + lower).joinToString("\n"))
    }

    //절차적 풀이에 가까움 안좋은 풀이
//    run {
//        val n = readln().toInt()
//        val result = buildString {
//
//            for (starCnt in 1..n) {
//                val blankCnt = n - starCnt
//
//                repeat(blankCnt) { append(" ") }
//                repeat(starCnt) { append("*") }
//                append("\n")
//            }
//
//            for (starCnt in (n - 1) downTo 1) {
//                val blankCnt = n - starCnt
//                repeat(blankCnt) { append(" ") }
//                repeat(starCnt) { append("*") }
//                append("\n")
//            }
//        }
//
//        print(result)
//    }
}