
fun main(args: Array<String>) {
    val l = readln().toInt()
    val n = readln().toInt()

    var 기대되는값 = 0
    var 기대되는후보 = 0
    val cake = MutableList(l+1) { 0 }
    for (person in 1..n) {
        //0이고, 0아니면 들어간거임.
        val (s, e) = readln().split(" ").map { it.toInt() }

        for (j in s..e) {
            if (cake[j] == 0) cake[j] = person
        }

        if (기대되는값 < e - s + 1) {
            기대되는값 = e - s + 1
            기대되는후보 = person
        }
    }
    println(기대되는후보)
//    println(cake)
    var ans = 0
    var maxv = 0
    val cnt_list = MutableList(n + 1) { 0 }
    cake.forEach {
        if (it != 0) cnt_list[it]++
    }

    for (i in 1..n) {
        if (cnt_list[i] > maxv) {
            maxv = cnt_list[i]
            ans = i
        }
    }

//    println(cake)
//    println(cnt_list)
    println(ans)

}