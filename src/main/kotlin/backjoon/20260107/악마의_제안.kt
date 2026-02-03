//  악마의 제안
import kotlin.math.*
fun main(args: Array<String>) {
    val (k, n) = readln().split(" ").map { it.toLong() }

    if (n == 1.toLong()) {
        println(-1)
        return
    }

    var x = (k*n+ n - 2) / (n - 1)
    println(x)
}

/*

더블은 소수점을 무한히 정확하게 표현 못해서 정확도가 있을 수 있음.
그럴 때는 정수 나눗셈

정수 나누기
a / b

정수 올림 ( b - 1 을 더해주면 댐 )
<올림은 소수점이 남으면 +1 해야 하니까
조금이라도 남으면 억지로 한 칸 넘어가게 만들려고, 미리 b-1을 더해준다>
val ceil = (a + b - 1) / b

10 / 3을 정수 올림으로 하면
(10 + 3 - 1) / 3 =  4

정수 내림
a / b

정수 반올림 ( b/2를 더해주면 댐 )
val round = (a + b/2) / b
<반올림은 소수점이 0.5 이상이면 올려야 하니까, 미리 ‘절반 값’을 더해두고 정수 나눗셈(내림)으로 처리한다.>

* */