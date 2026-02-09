package backjoon.치트시트.수학// 목표: n을 3의 배수 3개로 분해하는 "중복조합" 개수
// (a, b, c) 모두 3의 배수, a <= b <= c, a+b+c = n

fun main() {
    val n = readln().toLong()

    // a,b,c가 모두 3의 배수면 n도 3의 배수여야 함
    if (n % 3L != 0L) {
        println(0)
        return
    }

    // a=3x, b=3y, c=3z 로 바꾸면
    // 3(x+y+z)=n  =>  x+y+z = m (m=n/3)
    val m = n / 3L

    var cnt = 0L

    // 중복(순서) 제거를 위해 x <= y <= z 로만 센다.
    //
    // x의 최대는 왜 m/3 이냐?
    // y>=x, z>=x 이므로 최소합이 x+x+x = 3x.
    // 근데 x+y+z = m 이니까 3x <= m => x <= m/3
    for (x in 1L..(m / 3L)) {

        // 이제 y의 범위를 잡자.
        // z는 식으로 결정됨: z = m - x - y
        //
        // 조건 1) y >= x  (x <= y <= z로 세기 위해)
        // 조건 2) y <= z  (중복 제거의 핵심)
        //
        // 조건 2)를 z = m - x - y에 대입하면:
        // y <= m - x - y
        // 2y <= m - x
        // y <= (m - x) / 2
        //
        // 즉, y는 최대가 floor((m-x)/2)까지만 가능하다.
        val maxY = (m - x) / 2L

        // y는 x부터 maxY까지 가능.
        // 만약 maxY < x면 가능한 y가 0개라서 스킵.
        if (maxY >= x) {
            // 가능한 y 개수 = (maxY - x + 1)
            // 각 y마다 z는 자동으로 하나로 결정되고,
            // y <= z를 만족하도록 maxY를 잡았기 때문에 중복 없이 딱 센다.
            cnt += (maxY - x + 1L)
        }
    }

    println(cnt)
}
