@file:Suppress("unused")

package 치트시트.출력

/**
 * 코틀린 입력 파싱 + Pair/Triple/data class + buildString 예제 모음
 *
 * ▶ 실행하면 예제 입력/출력과 함께 설명이 전부 출력됨.
 * ▶ readln() 대신 샘플 문자열을 하드코딩해서 "단숨에" 돌려볼 수 있게 구성.
 */

fun main() {
    println("=== 코틀린 Pair / Triple / data class / buildString 치트시트 ===")
    println()

    // ============================
    // 0. 기본 아이디어 요약
    // ============================
    //
    //  val (a, b) = readln().split(" ").let {
    //      it[0].toInt() to it[1].toInt()
    //  }
    //
    // 여기서는 readln() 대신 샘플 문자열로 보여줌.

    run {
        val line = "3 5"
        println(line)
        val (a, b) = line.split(" ").let {
            it[0].toInt() to it[1].toInt()
        }

        println("[0] 기본 패턴 요약")
        println("  입력 문자열: \"$line\"")
        println("  split 결과: [\"3\", \"5\"]")
        println("  let 내부: it[0].toInt() to it[1].toInt() → Pair(3, 5)")
        println("  구조분해: val (a, b) = 그 Pair → a = $a, b = $b")
        println("  a + b = ${a + b}")
        println()
    }

    // =================================================
    // 1. "Int Int" 입력받기 (Pair + to)  예제
    // =================================================

    run {
        val line = "3 5"
        val (a, b) = line.split(" ").let {
            it[0].toInt() to it[1].toInt()
        }

        println("[1] Int Int 예제 (Pair + to)")
        println("  입력: \"$line\"")
        println("  코드: val (a, b) = line.split(\" \").let { it[0].toInt() to it[1].toInt() }")
        println("  결과: a = $a, b = $b")
        println("  a + b = ${a + b}")
        println()
    }

    // =================================================
    // 2. "Int Long" 입력받기 (타입이 다른 Pair)
    // =================================================

    run {
        val line = "10 10000000000"
        val (a, b) = line.split(" ").let {
            it[0].toInt() to it[1].toLong()
        }

        println("[2] Int Long 예제 (서로 다른 타입)")
        println("  입력: \"$line\"")
        println("  코드: it[0].toInt() to it[1].toLong()")
        println("  결과: a = $a (Int), b = $b (Long)")
        println()
    }

    // =================================================
    // 3. "String Int" 입력받기 (이름 + 점수 같은 패턴)
    // =================================================

    run {
        val line = "Alice 100"
        val (name, score) = line.split(" ").let {
            it[0] to it[1].toInt()
        }

        println("[3] String Int 예제 (이름 + 점수)")
        println("  입력: \"$line\"")
        println("  코드: it[0] to it[1].toInt()")
        println("  결과: name = $name (String), score = $score (Int)")
        println()
    }

    // ============================================================
    // 4. "Char Int" 입력받기 (문자 + 숫자)
    // ============================================================

    run {
        val line = "A 10"
        val tokens = line.split(" ")
        val c: Char = tokens[0][0]
        val x: Int = tokens[1].toInt()

        println("[4] Char Int 예제 (문자 + 숫자)")
        println("  입력: \"$line\"")
        println("  tokens = line.split(\" \") → $tokens")
        println("  tokens[0]의 타입   : String  (\"A\")")
        println("  tokens[0][0]의 타입: Char    ('A')")
        println("  그래서 c = tokens[0][0] → c = '$c'")
        println("  x = tokens[1].toInt() → x = $x")
        println("  요약: 리스트 인덱싱 후, 문자열 인덱싱 한 번 더 해서 it[0][0]처럼 보이는 것뿐.")
        println("       구조는 \"List<String> 안에 String, 그 안에 Char\" 1차원+1차원 임.")
        println()
    }

    // =================================================
    // 5. 3개 입력: "Int Int String" (Triple 사용)
    // =================================================

    run {
        val line = "10 20 hello"
        val (a, b, s) = line.split(" ").let {
            Triple(
                it[0].toInt(),
                it[1].toInt(),
                it[2],
            )
        }

        println("[5] Int Int String 예제 (Triple)")
        println("  입력: \"$line\"")
        println("  코드: Triple(it[0].toInt(), it[1].toInt(), it[2])")
        println("  결과: a = $a (Int), b = $b (Int), s = \"$s\" (String)")
        println("  to는 Pair까지만 지원 → 3개부터는 Triple(...) 또는 data class 사용")
        println()
    }

    // ======================================================
    // 6. 3개 이상일 때 data class 사용 (더 의미 있는 이름)
    // ======================================================

    run {
        data class Person(
            val name: String,
            val age: Int,
            val score: Int,
        )

        val line = "Bob 21 95"
        val person = line.split(" ").let {
            Person(
                name = it[0],
                age = it[1].toInt(),
                score = it[2].toInt(),
            )
        }

        val (name, age, score) = person

        println("[6] data class 예제 (이름 나이 점수)")
        println("  입력: \"$line\"")
        println("  코드: Person(name = it[0], age = it[1].toInt(), score = it[2].toInt())")
        println("  person = $person")
        println("  구조분해: val (name, age, score) = person")
        println("  결과: name = $name, age = $age, score = $score")
        println("  값이 3개 이상이면 Triple보다는 data class가 더 읽기 좋고, 의미도 살아남.")
        println()
    }

    // ==========================================================
    // 7. buildString + repeat 사용해서 N줄 출력 모으기 예제
    // ==========================================================

    run {
        // 실제 readln() 대신, 미리 입력 줄들을 리스트로 준비
        val lines = listOf(
            "1 2",
            "3 4",
            "5 6",
        )
        val n = lines.size

        val out = buildString {
            // buildString 내부의 this는 StringBuilder
            repeat(n) { idx ->
                val line = lines[idx]
                val (a, b) = line.split(" ").let {
                    it[0].toInt() to it[1].toInt()
                }
                append(a + b)
                append('\n')
            }
        }

        println("[7] buildString + repeat 예제 (N줄 (a + b) 출력)")
        println("  입력 줄들:")
        lines.forEach { println("    \"$it\"") }
        println("  각 줄에서 Int Int 파싱 후 a + b 계산, buildString으로 한 번에 문자열 생성")
        println("  최종 출력 문자열:")
        print("-----\n$out-----\n")
        println("  설명:")
        println("    - buildString { ... } 내부는 사실 StringBuilder를 감싼 DSL")
        println("    - append(...) 호출들로 문자열을 쌓고, 마지막에 toString() 결과를 반환")
        println("    - println을 반복문 안에서 많이 찍는 것보다, 이렇게 한 번에 출력하는 게 더 효율적일 수 있음")
        println()
    }

    // ==========================================================
    // 8. 정리 출력
    // ==========================================================

    println("=== 정리 ===")
    println("1) Pair + to")
    println("   - x to y === Pair(x, y)")
    println("   - val (a, b) = x to y  → a = first, b = second")
    println()
    println("2) Triple")
    println("   - Triple(a, b, c)")
    println("   - val (x, y, z) = Triple(...)")
    println()
    println("3) data class")
    println("   - 값이 3개 이상이면 data class로 의미를 부여하는 게 더 읽기 좋음")
    println()
    println("4) Char 인덱싱")
    println("   - val tokens = line.split(\" \")  // List<String>")
    println("   - val c: Char = tokens[0][0]     // 문자열의 첫 글자")
    println("   - it[0][0] 처럼 보이는 건 \"리스트 인덱스 + 문자열 인덱스\" 두 번일 뿐, 이차원 배열 아님")
    println()
    println("5) buildString")
    println("   - StringBuilder를 감싼 코틀린 함수")
    println("   - 내부 this는 StringBuilder")
    println("   - 마지막 표현식이 최종 문자열 결과가 됨")
    println()
    println("=== 끝 ===")
}
