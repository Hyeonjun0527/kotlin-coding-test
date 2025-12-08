

/*
5번째 = 1 + 2 + 2 +  1보다 크니 1+2보다 크니 1+2+3보다 크니 아니야?
그럼. 5에서 1+2를 빼. 그러면 2이지. 2번째구나. 3번째에서 2번째구나.
그럼 3/1에서 -1/+1를 1번할게

대각번째가 2면 (2 - 1)번 -1/+1을 함.

i가 3이므로

1,1+2,1+2+3,1+2+3+4
a = 1
* */
fun main(args: Array<String>) {
    var n = readln().toInt()// 5면   
    var value = 1
    var i = 1//번째
    while (true) {
        if (n > value) {
            i++
            value = (1..i).sum()
//            println("반복 : $value, $i")
        } else {
            break
        }
    }
    var i가짝수 = (i % 2 == 0)
    var 분자 = if (i가짝수) 1 else i
    var 분모 = if (i가짝수) i else 1

//    println("1번 $분자/$분모")
//    println("value = $value, i = $i")

    val cnt = n - (value - i) - 1
//    println("cnt = $cnt")
    repeat(cnt) {
        if (i가짝수) {
            분자 += 1
            분모 -= 1
        } else {
            분자 -= 1
            분모 += 1

        }

    }
    println("$분자/$분모")

}