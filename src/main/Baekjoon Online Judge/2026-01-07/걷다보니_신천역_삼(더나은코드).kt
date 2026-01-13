
fun main() {
    val N = readln().toInt()
    val MOD = 1_000_000_009L

    val dp = mutableListOf(0L, 0L, 2L) // 0,1,2 인덱스 준비
    //나는 N번째 인덱스의 값을 구하면 되는거임. 그러면 반복해야 되는 횟수는 N - 지금잇는개수 + 1 인거임
    //그냥 구하지 말자. while (dp.size() <= N) 가 최고임. 이러면 개수셀 필요 없다.
    //dp.size() == N + 1개 될때까지 반복하면 된다. 그거만 알면 대는거임.
    //몇번반복해야되는지 계산하자보단.....이때까지 반복하면 된다.

//    repeat(maxOf(0,N - dp.size + 1)) { N = 5이면 3번 반복해야함. 5 - 3 + 1
//    for (i in dp.size..N) {            // i = 3..N
    while (dp.size <= N) {
        dp.add(dp.last() * 3L % MOD)
    }

    println(dp[N])
}