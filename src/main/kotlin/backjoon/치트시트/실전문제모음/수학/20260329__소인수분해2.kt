package backjoon.치트시트.실전문제모음.수학

// 알고리즘 분류: 수학

fun main() {
    val 테스트개수 = readln().toInt()

    data class 인수정보(
        val 소인수: Int,
        val 횟수: Int
    )

    fun 소인수분해하기(원본수: Int): List<인수정보> {
        var 현재수 = 원본수
        var 나누는수 = 2
        val 결과목록 = mutableListOf<인수정보>()

        while (나누는수 * 나누는수 <= 현재수) {
            var 횟수 = 0

            while (현재수 % 나누는수 == 0) {
                현재수 /= 나누는수
                횟수++
            }

            if (횟수 > 0) {
                결과목록.add(인수정보(나누는수, 횟수))
            }

            나누는수++
        }

        if (현재수 > 1) {
            결과목록.add(인수정보(현재수, 1))
        }

        return 결과목록
    }

    val 정답 = StringBuilder()

    repeat(테스트개수) {
        val 수 = readln().toInt()
        val 분해결과 = 소인수분해하기(수)

        for (인수정보 in 분해결과) {
            정답.append(인수정보.소인수)
                .append(' ')
                .append(인수정보.횟수)
                .append('\n')
        }
    }

    print(정답)
}
