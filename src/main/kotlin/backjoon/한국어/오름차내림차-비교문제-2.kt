package backjoon.한국어

data class 학생(
    val name: String,
    val k: Int,
    val e: Int,
    val m: Int
)

fun main() {
    val students = listOf(
        학생("홍", 90, 70, 80),
        학생("김", 90, 80, 70),
        학생("이", 85, 90, 90),
        학생("최", 90, 70, 80),
        학생("박", 90, 70, 80)
    )

    // ▼ 한국어로 더 자연스러워진 코드 ▼
    val sorted = students.정렬하기(
        내림차순<학생> { it.k }        // 1. 국어 ↓ (큰 점수 먼저)
            .그다음_오름차순 { it.e }   // 2. 영어 ↑
            .그다음_내림차순 { it.m }   // 3. 수학 ↓
            .그다음_오름차순 { it.name } // 4. 이름 ↑
    )

    println("=== 정렬 결과 ===")
    sorted.하나씩_실행 { println(it) }
}
