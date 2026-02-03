//  운동장 회전초밥
import kotlin.math.*

fun main(args: Array<String>) {
    val (n,t) = readln().split(" ").map { it.toInt() }
    val (r,d,theta, w) = readln().split(" ").map { it.toDouble() }

    val r1 = r - 5.toDouble()/2*(w * 0.01) //정민반지름
    val r2 = r - 3.toDouble()/2*(w * 0.01) //정화반지름
    val r3 = r - 1.toDouble()/2*(w * 0.01) //은채반지름

    fun f10(x: Double) = "%.10f".format(x)

    val rad = theta * PI / 180.0

    val 정민_속력 = (r1*rad + d + 2 * r1 * cos(rad/2)) * 2 * n / t
    val 정화_속력 = (r2*rad + d + 2 * r2 * cos(rad/2)) * 2 * n / t
    val 은채_속력 = (r3*rad + d + 2 * r3 * cos(rad/2)) * 2 * n / t

    println(f10(은채_속력 - 정민_속력))
    println(f10(은채_속력 - 정화_속력))

}