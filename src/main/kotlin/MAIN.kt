import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main1() {
    val name = "Kotlin"
    val age = 10;

    println("Hello $name! You are $age years old.")
}

//토크나이저를 쓰지 않고 split을 쓰면 효율적이다.
fun main2() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())//토크나이저보다는 split을 사용하는게 효율적이다.

    repeat(n) { i ->
        sb.append("i = ${i + 1}, num = ${st.nextToken()}").append('\n')
    }

    print(sb)
}

// 모범 코드
fun main4() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val nums = br.readLine().split(' ').map { it.toInt() }

    repeat(n) { i ->
        println("i = ${i + 1}, num = ${nums[i]}")
    }
}


//코틀린에서는 아래처럼 스트링빌더를 사용할 필요가 없어졌다.
fun main5() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val nums = br.readLine().split(' ').map { it.toInt() }
    val sb = StringBuilder()

    repeat(n) { i ->
        sb.append("i = ${i + 1}, num = ${nums[i]}").append('\n')
    }
}

//모범 코드 2
fun main3() {
    val n = readln().toInt()        // 첫 번째 줄: n 읽기
    val nums = readln().split(' ')  // 두 번째 줄: 공백으로 구분된 숫자들 읽기

    repeat(n) { i ->
        println("i = ${i + 1}, num = ${nums[i]}")
    }
}

fun main() {
    println("1번")
    val n = readln().toInt()
    val result = readln()
        .split(' ')
        .map { it.toInt() }
        .filter { it % 2 == 0 }
        .map { it * 2 }
    result.forEach { print("$it ") }//4 8

    println("\n2번")
    val sb = StringBuilder()
    result.forEach{ sb.append("$it ") }
    println(sb.toString().trim())//4 8

    println("3번")
    listOf(1, 2, 3).forEach { num -> println(num) }

    println("4번")
    println(result.joinToString(", ","[","]"))

    println("5번")
    val sb1 = StringBuilder("---")
    println(listOf(1, 2, 3).joinToString { "${sb1.toString() + it.toString()} ---$it" })

    println("6번")
    println(sb1)
    println(1.toString())

    println(1.plus(2))
    println(2.times(3))
    println(4.div(2))

}