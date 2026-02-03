
import 한국어.하나씩_실행

data class Student(
    val name: String,
    val k: Int,
    val e: Int,
    val m: Int
)

fun main() {
    val students = listOf(
        Student("홍", 90, 70, 80),
        Student("김", 90, 80, 70),
        Student("이", 85, 90, 90),
        Student("최", 90, 70, 80),
        Student("박", 90, 70, 80)
    )

    // ▼ 한국어로 더 자연스러워진 코드 ▼
    val sorted = students.sortedWith(
        compareByDescending<Student> { it.k }        // 1. 국어 ↓ (큰 점수 먼저)
            .thenBy { it.e }   // 2. 영어 ↑
            .thenByDescending { it.m }   // 3. 수학 ↓
            .thenByDescending { it.name } // 4. 이름 ↑
    )

    println("=== 정렬 결과 ===")
    sorted.하나씩_실행 { println(it) }
}
