package backjoon.`20260312`
import java.time.LocalTime
fun main() {
    val (시작시간, 끝난시간, 스트리밍끝난시간) = readln().split(" ")
    val 입장한사람들 = HashSet<String>()
    val 출석확인된사람들 = HashSet<String>()
    while(true) {
        val 입력 = readlnOrNull() ?: break
        val (채팅시간,이름) = 입력.split(" ")

        if (채팅시간 <= 시작시간) {
            입장한사람들.add(이름)
        }
        if (끝난시간 <= 채팅시간 && 채팅시간 <= 스트리밍끝난시간) {
            if (이름 in 입장한사람들) {
                출석확인된사람들.add(이름)
            }
        }
    }

    print(출석확인된사람들.size)

}