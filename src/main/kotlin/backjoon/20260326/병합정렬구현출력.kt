package backjoon.`20260326`

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val 배열 = readln().split(" ").map { it.toInt() }.toIntArray()
    val 임시배열 = IntArray(n + 1)

    var 저장횟수 = 0
    var 정답 = -1
    var 단계 = 1

    fun 현재배열문자열(): String {
        return 배열.joinToString(prefix = "[", postfix = "]")
    }

    fun 구간문자열(시작: Int, 끝: Int): String {
        val 리스트 = mutableListOf<Int>()
        for (i in 시작..끝) {
            리스트.add(배열[i])
        }
        return 리스트.joinToString(prefix = "[", postfix = "]")
    }

    fun 임시배열문자열(끝인덱스: Int): String {
        val 리스트 = mutableListOf<Int>()
        for (i in 1 until 끝인덱스) {
            리스트.add(임시배열[i])
        }
        return 리스트.joinToString(prefix = "[", postfix = "]")
    }

    fun 병합(시작: Int, 중간: Int, 끝: Int) {
        println()
        println("🤝=====단계${단계++}:병합시작=====")
        println("📍병합대상구간:($시작,$중간,$끝)")
        println("⬅️왼쪽구간:${구간문자열(시작, 중간)}")
        println("➡️오른쪽구간:${구간문자열(중간 + 1, 끝)}")

        var 왼쪽 = 시작
        var 오른쪽 = 중간 + 1
        var 임시위치 = 1

        while (왼쪽 <= 중간 && 오른쪽 <= 끝) {
            if (배열[왼쪽] <= 배열[오른쪽]) {
                println("🔍배열[$왼쪽]=${배열[왼쪽]} <= 배열[$오른쪽]=${배열[오른쪽]} → ${배열[왼쪽]}선택")
                임시배열[임시위치++] = 배열[왼쪽++]
            } else {
                println("🔍배열[$왼쪽]=${배열[왼쪽]} > 배열[$오른쪽]=${배열[오른쪽]} → ${배열[오른쪽]}선택")
                임시배열[임시위치++] = 배열[오른쪽++]
            }
            println("📦현재임시배열:${임시배열문자열(임시위치)}")
        }

        while (왼쪽 <= 중간) {
            println("📦왼쪽나머지저장:${배열[왼쪽]}")
            임시배열[임시위치++] = 배열[왼쪽++]
            println("📦현재임시배열:${임시배열문자열(임시위치)}")
        }

        while (오른쪽 <= 끝) {
            println("📦오른쪽나머지저장:${배열[오른쪽]}")
            임시배열[임시위치++] = 배열[오른쪽++]
            println("📦현재임시배열:${임시배열문자열(임시위치)}")
        }

        println("📝원본배열에다시복사시작")
        var i = 시작
        var t = 1
        while (i <= 끝) {
            배열[i] = 임시배열[t]
            저장횟수++

            println("📝배열[$i] ← 임시배열[$t](${임시배열[t]})")
            println("🔢저장횟수:$저장횟수")

            if (저장횟수 == k) {
                정답 = 임시배열[t]
                println("🎯k번째저장발생!정답=$정답")
            }

            println("🧾현재배열상태:${현재배열문자열()}")
            i++
            t++
        }

        println("✅=====병합끝=====")
    }

    fun 병합정렬(시작: Int, 끝: Int, 깊이: Int) {
        val 들여쓰기 = "│ ".repeat(깊이)

        println("${들여쓰기}📞병합정렬($시작,$끝)호출")

        if (시작 < 끝) {
            val 중간 = (시작 + 끝) / 2
            println("${들여쓰기}✂️중간=$중간")
            println("${들여쓰기}⬅️왼쪽먼저:병합정렬($시작,$중간)")
            병합정렬(시작, 중간, 깊이 + 1)

            println("${들여쓰기}➡️오른쪽다음:병합정렬(${중간 + 1},$끝)")
            병합정렬(중간 + 1, 끝, 깊이 + 1)

            println("${들여쓰기}🤝이제병합($시작,$중간,$끝)")
            병합(시작, 중간, 끝)
        } else {
            println("${들여쓰기}✅원소1개라서종료")
        }
    }

    println("🚀초기배열:${현재배열문자열()}")
    병합정렬(0, n - 1, 0)
    println()
    println("🏁최종정답:$정답")
}