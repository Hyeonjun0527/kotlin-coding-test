package backjoon.치트시트.실전문제모음.문자열

// 알고리즘 분류: 문자열

/*
모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용한다.
* */

fun main(args: Array<String>) {
    while (true) {
        val word = readln()
        if (word == "end") break

        var 조건1 = false//반드시 ~ 해야함 => 기본 false
        var 조건2 = true//반드시 ~ 하면 안됨 => 기본 true. 실패하는 순간에 false로
        var 조건3 = true

        if (word.contains('a') || word.contains('e') ||
            word.contains('i') || word.contains('o') ||
            word.contains('u')
        ) {
            조건1 = true
        }

        var 연속모음 = 0
        var 연속자음 = 0
        var prev:Char? = null
        for (c in word) {
            val 모음이라면 = c in setOf('a','e','i','o','u')
            if (모음이라면) {
                연속모음++
                연속자음 = 0
            } else {
                연속자음++
                연속모음 = 0
            }
            if (연속자음 == 3 || 연속모음 == 3) 조건2 = false

            if (prev != null && prev == c) {
                val 예외 = (c == 'e' || c == 'o')
                if (!예외) {
                    조건3 = false
                    break
                }
            }
            prev = c
        }

        if (조건1 && 조건2 && 조건3) {
            println("<${word}> is acceptable.")
        } else {
            println("<${word}> is not acceptable.")
        }
    }
}
