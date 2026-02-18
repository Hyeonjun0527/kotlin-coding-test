# 1. 기본 구조
```sql
    SELECT 컬럼명
    FROM 테이블명
    WHERE 조건
    GROUP BY 그룹컬럼
    HAVING 그룹조건
    ORDER BY 정렬컬럼
    LIMIT 개수;
```
셀렉팅 프럼 웨어 /그룹바이 해빙 /오더바이 리미트

# 2. 조건문

## 비교연산자

```SQL
=, !=, >, <, >=, <=
```

## 논리연산자

```SQL
AND, OR, NOT
```

## 범위

```SQL
WHERE 컬럼명 BETWEEN A AND B
```

## 컬럼명이 A이거나 B이거나 C

```SQL
WHERE 컬럼명 IN ('A,B,C')
```

## 문자열 포함 검색

```SQL
WHERE 컬럼명 LIKE '%컴퓨터%'
```

## MYSQL에서 컬럼명이 특정키워드랑 겹치면?

```
백틱을 사용한다
```

## 3. 집계 함수

총 개수, 총합, 평균, 최소, 최대

```SQL
COUNT(), SUM(), AVG(), MIN(), MAX()
```

## HAVING 제대로 이해하기

HAVING은 그룹바이한 결과에 적용하는거다.
그런데 중요한건 그룹마다 적용되는거다.

그래서 HAVING COUNT(*) >= 2 이런 구문이 논리적으로 가능한 것이고,
WHERE COUNT(*) >= 2 이런 구문이 불가능한 것이다.

2번 이상 구매한 그룹 찾기

`HAVING COUNT(*) >= 2`

평균이 10000 이상인 그룹 찾기

`HAVING AVG(DAILY_FEE) >= 10000`

총 구매량이 10 이상인 사용자 찾기

`HAVING SUM(SALES_AMOUNT) >= 10`

최근 구매가 4월 이후인 사용자

`HAVING MAX(SALES_DATE) >= '2022-04-01'`


## EXISTS 문


## 정규표현식

## 반올림
ROUND(값,0)

## 각 TYPE별로 3등까지 뽑기

## 서브쿼리

## 윈도우 함수

점수가 100,90,80,80,70이라고 하자.

### 윈도우 함수(동순위 미포함)(동순미)
ROW_NUMBER() 동순미. 1,2,3,4,5
### 윈도우 함수(동순위 포함)(동순포)
RANK() 동순포/등수 점프 1,2,3,3,5
DENSE_RANK() 동순포/등수 미점프 1,2,3,3,4


### 위에서 3개 (동등미)
LIMIT 3

### 위에서 3개 (동순포)

함수() OVER (PARTITION BY 그룹 ORDER BY 정렬)

## [[나이]] TOP 3 (동점미포함)
```SQL
SELECT *
FROM EMPLOYEE
ORDER BY AGE DESC
LIMIT 3
```

## [[나이]] TOP 3 (동점포함)

```SQL
SELECT *
FROM (
  SELECT C.*,
         RANK() OVER (ORDER BY DAILY_FEE DESC) AS rnk
  FROM CAR_RENTAL_COMPANY_CAR C
) X
WHERE rnk <= 3;
```

## [[주문]]한 적이 없는 회원 찾기

`EXISTS` 또는
`LEFT JOIN + IS NULL`를 사용하면 된다.

주문한 적이 없다 = 
주문, 회원 테이블에서 주문레코드를 남기지 않은 유저 ID 찾기 

주문한 적이 있다 =
주문, 회원 테이블에서 주문레코드를 남긴 유저 ID 찾기

### EXISTS

`EXISTS` 상관 서브쿼리 사용하기.

```SQL
SELECT U.USER_ID
FROM USERS U
WHERE NOT EXISTS (
    SELECT 1
    FROM ORDERS O
    WHERE U.USER_ID = O.USER_ID
)
```

### LEFT JOIN + IS NULL

조인을 해야대. 그런데 USER는 무조건 남아야대.
그래서 회원을 왼쪽테이블로,
주문을 오른쪽테이블로 하고,
LEFT JOIN 해야해.

`없다`에 대해서는 IS NULL을 사용하면 돼.

```SQL
SELECT U.USER_ID
FROM USERS U
LEFT JOIN ORDERS O
ON U.USER_ID = O.USER_ID
WHERE O.USER_ID IS NULL
```

## [[주문]]한 적이 있는 회원 찾기

`EXISTS`,`JOIN + DISTINCT`를 사용하면 된다.

```SQL
SELECT U.USER_ID
FROM USERS U
WHERE EXISTS (
  SELECT 1
  FROM ORDERS O
  WHERE O.USER_ID = U.USER_ID
);
```

아래 방식은 주문을 여러번 한 회원을 여러번 출력할 수 있음.
그리고, LEFT JOIN일 필요가 없어짐.
```SQL
SELECT U.USER_ID
FROM USERS U
LEFT JOIN ORDERS O
ON U.USER_ID = O.USER_ID
WHERE O.USER_ID IS NULL
```

그래서 아래처럼 고쳐야함.
```SQL
SELECT DISTINCT U.USER_ID
FROM USERS U
JOIN ORDERS O
  ON U.USER_ID = O.USER_ID;
```