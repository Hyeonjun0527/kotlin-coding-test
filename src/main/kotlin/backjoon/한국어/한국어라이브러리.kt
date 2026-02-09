package backjoon.한국어

import java.util.ArrayDeque
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.collections.first
import kotlin.collections.last
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// =================================================================
// ▼ [K-코딩] 마법의 정의 모음집 ▼
// =================================================================

// ----------------------
// 1. 정렬 (Sorting)
// ----------------------
// sortedWith
fun <T> Iterable<T>.정렬하기(comparator: Comparator<in T>): List<T> = this.sortedWith(comparator)

// compareByDescending
fun <T> 내림차순(selector: (T) -> Comparable<*>?) = compareByDescending(selector)
// compareBy
fun <T> 오름차순(selector: (T) -> Comparable<*>?) = compareBy(selector)

// thenByDescending
fun <T> Comparator<T>.그다음_내림차순(selector: (T) -> Comparable<*>?) = this.thenByDescending(selector)
// thenBy
fun <T> Comparator<T>.그다음_오름차순(selector: (T) -> Comparable<*>?) = this.thenBy(selector)


// ----------------------
// 2. 입력 (Input)
// ----------------------
// readln (안전하게 readLine으로 대체 가능하지만, 요청하신 대로 readln 유지)
fun 한줄_읽기(): String = readln()


// ----------------------
// 3. 변환 & 타입 (Conversion)
// ----------------------
// toInt
fun String.정수로(): Int = this.toInt()
// toLong
fun String.긴_숫자로(): Long = this.toLong()
// map
fun <T, R> Iterable<T>.변환하기(transform: (T) -> R): List<R> = this.map(transform)


// ----------------------
// 4. 리스트 조작 (List Operations)
// ----------------------
// filter
fun <T> Iterable<T>.골라내기(predicate: (T) -> Boolean): List<T> = this.filter(predicate)
// forEach
fun <T> Iterable<T>.하나씩_실행(action: (T) -> Unit) = this.forEach(action)
// fold (누적하기)
fun <T, R> Iterable<T>.누적하기(초기값: R, 계산식: (R, T) -> R): R = this.fold(초기값, 계산식)
// find
fun <T> Iterable<T>.찾기(predicate: (T) -> Boolean): T? = this.find(predicate)
// contains
fun <T> Iterable<T>.포함하나요(element: T): Boolean = this.contains(element)
// distinct
fun <T> Iterable<T>.중복제거(): List<T> = this.distinct()
// count
fun <T> Collection<T>.개수(): Int = this.size
// sumOf
fun <T> Iterable<T>.합계(selector: (T) -> Int): Int = this.sumOf(selector)


// ----------------------
// 5. 문자열 조작 (String Operations)
// ----------------------
// split
fun String.자르기(구분자: String): List<String> = this.split(구분자)
// joinToString
fun <T> Iterable<T>.문자로_합치기(구분자: String = ", "): String = this.joinToString(구분자)
// replace
fun String.바꾸기(찾을말: String, 바꿀말: String): String = this.replace(찾을말, 바꿀말)


// ----------------------
// 6. 수학 & 유틸 (Math & Utils)
// ----------------------
fun 최대(a: Int, b: Int) = max(a, b)
fun 최소(a: Int, b: Int) = min(a, b)
fun 절댓값(a: Int) = abs(a)
// println
fun 한줄_출력(값: Any?) = println(값)


// ----------------------
// 7. 큐 & 스택 (Queue & Data Structure)
// ----------------------
// 큐 생성 (ArrayDeque)
fun <T> 큐_생성(): ArrayDeque<T> = ArrayDeque()

// add (큐에 넣기)
fun <T> ArrayDeque<T>.넣기(item: T) = this.add(item)
// removeFirst (큐에서 꺼내기)
fun <T> ArrayDeque<T>.앞에서_꺼내기(): T = this.removeFirst()
// removeLast (스택처럼 꺼내기)
fun <T> ArrayDeque<T>.뒤에서_꺼내기(): T = this.removeLast()
// isEmpty
fun <T> ArrayDeque<T>.비었나요(): Boolean = this.isEmpty()
// first
fun <T> ArrayDeque<T>.맨앞_요소(): T = this.first()
// last
fun <T> ArrayDeque<T>.맨뒤_요소(): T = this.last()

// ----------------------
// 8. 맵 & 셋 (Map & Set)
// ----------------------
// 가변맵 생성 (MutableMap)
fun <K, V> 가변맵_생성(): MutableMap<K, V> = mutableMapOf()
// 해시맵 (HashMap)
fun <K, V> 해시맵_생성(): HashMap<K, V> = HashMap()

// 맵에 넣기 (가변맵만 가능)
fun <K, V> MutableMap<K, V>.넣기(key: K, value: V) { this[key] = value }
// 맵에서 꺼내기 (일반 맵도 가능)
fun <K, V> Map<K, V>.꺼내기(key: K): V? = this[key]
// 꺼내거나 기본값
fun <K, V> Map<K, V>.꺼내거나_기본값(key: K, defaultValue: V): V = this.getOrDefault(key, defaultValue)
// 키 확인
fun <K, V> Map<K, V>.키_있나요(key: K): Boolean = this.containsKey(key)


// 가변셋 생성 (MutableSet)
fun <T> 가변셋_생성(): MutableSet<T> = mutableSetOf()
// 해시셋 (HashSet)
fun <T> 해시셋_생성(): HashSet<T> = HashSet()

// 셋에 넣기 (가변셋만 가능)
fun <T> MutableSet<T>.넣기(item: T) = this.add(item)
// 셋에 있나요? (일반 셋도 가능)
fun <T> Set<T>.있나요(item: T): Boolean = this.contains(item)


// ----------------------
// 9. 배열 & 리스트 생성 (Array & List Creation)
// ----------------------
// 1. [정수 전용] 2차원 배열 (Array<IntArray>) - 속도 빠름, 0으로 초기화
fun 이차원_정수배열_생성(행: Int, 열: Int): Array<IntArray> = Array(행) { IntArray(열) }

// 2. [체크 전용] 2차원 배열 (Array<BooleanArray>) - 방문 체크용, false로 초기화
fun 이차원_체크배열_생성(행: Int, 열: Int): Array<BooleanArray> = Array(행) { BooleanArray(열) }

// 3. [범용] 2차원 배열 (모든 타입 가능) - 초기값을 넣어줘야 함
// 예: 이차원_가변배열_생성(5, 5, "빈칸") -> 5x5 String 배열 생성
//fun <T> 이차원_가변배열_생성(행: Int, 열: Int, 초기값: T): Array<Array<T>> = Array(행) { Array(열) { 초기값 } }

// 가변리스트 생성 (ArrayList)
fun <T> 가변리스트_생성(): MutableList<T> = mutableListOf()
// 정수 배열
fun 정수배열_생성(크기: Int): IntArray = IntArray(크기)
// 방문 체크 배열
fun 방문체크_생성(크기: Int): BooleanArray = BooleanArray(크기)


// ----------------------
// 10. 우선순위 큐 (Priority Queue)
// ----------------------
// 힙(Heap) 생성 - 기본은 작은 숫자가 먼저 나옴 (오름차순)
fun <T> 우선순위큐_생성(): PriorityQueue<T> = PriorityQueue()
// 정렬 기준을 직접 정해서 생성
fun <T> 우선순위큐_생성(comparator: Comparator<T>): PriorityQueue<T> = PriorityQueue(comparator)

// offer (넣기)
fun <T> PriorityQueue<T>.넣기(item: T) = this.offer(item)
// poll (꺼내기)
fun <T> PriorityQueue<T>.꺼내기(): T = this.poll()
// peek (확인하기)
fun <T> PriorityQueue<T>.맨앞_확인(): T = this.peek()
// isEmpty
fun <T> PriorityQueue<T>.비었나요(): Boolean = this.isEmpty()