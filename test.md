## 시간 복잡도 분석

- 두 개의 입력을 절반씩 쪼갠 뒤, 세 번 재귀 호출
- 재귀 호출을 한번이나 두번만 하던 지금까지와는 다르게 분석해야 함
    - 병합 단계 + 기저 사례  두 부분으로 나눔.

// a += b * (10^k)를 구현한다. 
void addTo(vector<int>&a, const vector<int>&b, int k);
// a -= b 를 구현한다. a >= b를 가정한다.
void subFrom(vector<int>&a, const vector<int>&b);
// 두 긴 정수의 곱을 반환한다.
vector <int> karatsuba(cosnt vector<int>&a, const vector<int>&b){
    int an = a.size(), bn = b.size();
    // a가 b보다 짧을 경우 둘을 바꾼다.
    if(an < bn) return karatsuba(b,a);
    //기저 사례: a나 b가 비어 있는 경우
    if(an == 0 || bn ==0) return vector<int>();
    if(an <= 50) return multiply(a,b);
    int half = an / 2;
    // a와 b를 밑에서 half 자리와 나머지로 분리한다.
    vector<int> a0(a.begin(), a.begin() + half);
    vector<int> a1(a.begin() + half, a.end());
    vector<int> b0(b.begin(), b.begin() + min<int>(b.size(), half));
    vector<int> b1(b.begin(), min<int>(b.size(), half), b.end());
    //z2 = a1 * b1
    vector<int> z2 = karatsuba(a1, b1);
    //z0 = a0 * b0
    vector<int> z0 = karatsuba(a0, b0);
    //a0 = a0 + a1; b0 = b0 + b1
    addTo(a0, a1, 0); addTo(b0, b1, 0);
    //z1 = (a0 * b0) - z0 -z2;
    vector<int> z1 = karatsuba(a0, b0);
    subFrom(z1, z0);
    subFrom(z1, z2);
    //ret = z0 + z1 * 10^half + z2 * 10^(half*2)
    vector<int> ret;
    addTo(ret, z0, 0);
    addTo(ret, z1, half);
    addTo(ret, z2, half + half);
    return ret;
}

- 기저 사례 계산
    - 자릿 수 n이 2의 거듭 제곱 2^k 라고 하면, 재귀 호출의 깊이는 k
    - 한번 쪼갤 때마다 해야 할 곱셈의 수가 세 배씩 늘어나기 때문에 마지막 단계에는 3^k개의 부분 문제가 있는데, 
마지막 단계에서는 두수 모두 한 자리니까 곱셈 한 번이면 충분
    - 따라서 곱셈의 수는 O(3^k)
    - n = 2^k 라고 가정했으니 k = logN
- 기저 사례 시간 복잡도: O(3^k) = (3^(logN)) = O(n^log3)
    - log3 = 1.585 정도이기 때문에 카라츠바 알고리즘이 O(n^2)보다 훨씬 적은 곱셈을 필요로 함
    

- 병합 단계 계산
    - 단계가 내려갈 때마다 숫자의 길이는 절반, 부분 문제 개수는 세 배 증가,
    - i 번째 단계에서 필요한 연산 수는 (3/2)^i * n 
    - 모든 단계의 전체 연산 수 -> n * 시그마(0~n)(3/2)^i
    - 입력의 크기가 작을 경우 O(n^2)보다 느리므로 주의.
    
- 2^N * 2^N 흑백 그림 압축
    - 전부 하얀색: w, 전부 검은색: b
    - 아니면 그림을 가로세로 2등분, 4조각 내서 각각을 쿼드 트리
    - 그 결과는 x(왼쪽위)(오른쪽위)(왼쪽아래)(오른쪽아래) 이런식으로 결과가 xwwwb 가 됨 

# 쿼드 트리 뒤집기
- 쿼드 트리: 대량의 자표 데이터를 메모리 안에 압축해 저장하기 위한 기법 중 하나
    - 주어진 공간을 항상 4개로 분할해 재귀적으로 표현



- 무식하게 풀기: 쿼드 트리 압축 플어 실제 이미지 얻고 상하 반전, 다시 쿼드 트리 압축
    - 당연히 비추
- 큰 입력에 대해서도 동작하는 효율적인 알고리즘을 처음부터 새로 만들기
- 작은 입력에 대해서만 동작하는 단순한 알고리즘으로부터 시작해서 최적화해 나가기
    - 2개 중 확실히 더 쉬운 것은 단순한 알고리즘부터 시작하는 것.

## 쿼드 트리 압축 풀기
- 기저사례: s의 첫글 자가 w나 b인 경우
- 첫 글자 x이면 s의 나머지 부분을 넷으로 쪼개 재귀 호출
char decompressed[MAX_SIZE][MAX_SIZE];
// s 를 압축 해제해서 decompressed[y..y + size - 1][x..x + size - 1]구간에 쓴다.
void decompress(const string &s, int y, int x, int size);

## 압축 문자열 분할하기
- s의 나머지 부분을 넷으로 쪼개기가 
