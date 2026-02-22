package backjoon.`20260218`//  비슷한 단어

fun main() {

    val n = readln().toInt()
    val 숫자단어들 = mutableListOf<MutableList<Int>>()

    repeat(n) {
        val word = readln()
        val map = HashMap<Char,Int>()
        val 숫자단어 = mutableListOf<Int>()
        var num = 0
        for (문자 in word) {
            if(문자 !in map) {
                map[문자] = num
                num++
            }
            숫자단어.add(map[문자] ?: 0)
        }
        숫자단어들.add(숫자단어)
    }
    var sol = 0

    for (i in 숫자단어들.indices) {
        for (j in i+1..숫자단어들.lastIndex) {
            if (숫자단어들[i] == 숫자단어들[j]) {
                sol++
            }
        }
    }
    print(sol)

}


/*
아주 쉽게 말하면 “두 단어가 같은 패턴(모양)인지” 보면 돼.

단어를 왼쪽부터 보면서, 처음 등장한 알파벳이면 새 번호를 부여해.

이미 봤던 알파벳이면 예전에 부여했던 번호를 그대로 써.

이렇게 만든 “번호열”이 두 단어에서 완전히 같으면 비슷한 단어야.

예)

abca → a(0) b(1) c(2) a(0) ⇒ 0 1 2 0

zbxz → z(0) b(1) x(2) z(0) ⇒ 0 1 2 0 ✅ 같음

N ≤ 100, 길이 ≤ 50 이라서
그냥 모든 쌍(N^2) 비교해도 충분히 빨라.
* */