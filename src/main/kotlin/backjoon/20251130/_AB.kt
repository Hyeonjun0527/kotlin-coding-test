import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() {
    readln()
    val sb = StringBuilder()
    while(true) {
        val line = readlnOrNull() ?: break
        if(line.isBlank()) break

        val (a,b) = line.split(" ").map {it.toInt()}

        sb.append("${a+b}\n")
    }
    print(sb)
}

/*
* 버퍼드리더, 스트링토크나이저는 사용하나 마나였음. i/o 요청을 줄이도록 스트링 빌더나 버퍼드 라이터 사용하는게 핵심.
* readln이 내부적으로 버퍼드리더 사용함. 스트링 빌더가 import할필요없음 그래서 추천
* 스트링 토크나이저, 버퍼드라이터는 import가 필요함.
* java.util.StringTokenizer
* */
//fun backjoon.main() {
//    readln()
//    val bw = BufferedWriter(OutputStreamWriter(System.out))
//    while(true) {
//        val line = readlnOrNull() ?: break
//
//        val (a,b) = line.split(" ").map {it.toInt()}
//
//        bw.write("${a+b}\n")
//    }
//    bw.flush()
//    bw.close()
//}