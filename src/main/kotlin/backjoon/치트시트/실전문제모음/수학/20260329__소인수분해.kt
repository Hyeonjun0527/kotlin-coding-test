package backjoon.치트시트.실전문제모음.수학

// 알고리즘 분류: 수학

fun main() {
    val 테스트개수 = readln().toInt()
    val 최대값 = 100_000

    data class 인수정보(
        val 소인수: Int,
        val 횟수: Int
    )

    // 소수아님[?] = false는 먼저 소수라고 가정하지만 후보일뿐 정해지지 않음.
    // 하지만, 로직끝나고도 false면 소수아닌거 전부 제거했으니 소수이다.
    fun 소수목록구하기(최대값: Int): List<Int> {
        val 소수아님 = BooleanArray(최대값 + 1)
        소수아님[0] = true
        소수아님[1] = true

        var 수 = 2//일단 수는 소수라고 가정
        while (수 * 수 <= 최대값) {
            if (!소수아님[수]) {
                var 배수 = 수 * 수
                while (배수 <= 최대값) {
                    소수아님[배수] = true
                    배수 += 수
                }
            }
            수++
        }

        val 소수목록 = mutableListOf<Int>()
        for (i in 2..최대값) {
            if (!소수아님[i]) {
                소수목록.add(i)
            }
        }
        return 소수목록
    }

    fun 소인수분해하기(원본수: Int, 소수목록: List<Int>): List<인수정보> {
        var 현재수 = 원본수
        val 결과목록 = mutableListOf<인수정보>()

        for (소수 in 소수목록) {
            if (소수 * 소수 > 현재수) break

            var 횟수 = 0
            while (현재수 % 소수 == 0) {
                현재수 /= 소수
                횟수++
            }

            if (횟수 > 0) {
                결과목록.add(인수정보(소수, 횟수))
            }
        }

        if (현재수 > 1) {
            결과목록.add(인수정보(현재수, 1))
        }

        return 결과목록
    }

    val 소수목록 = 소수목록구하기(최대값)
    val 정답 = StringBuilder()

    repeat(테스트개수) {
        val 수 = readln().toInt()
        val 분해결과 = 소인수분해하기(수, 소수목록)

        for (인수정보 in 분해결과) {
            정답.append(인수정보.소인수)
                .append(' ')
                .append(인수정보.횟수)
                .append('\n')
        }
    }

    print(정답)
}
