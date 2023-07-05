# logtracer
Spring AOP와 log4j를 활용한 로그 추적기 구현

## 1. How to use
로그 추적이 필요한 메소드에 아래와 같이 Annotation을 붙여서 사용할 수 있다.

```java
@Trace // add this on class method which you want to "trace".
String doMethod() {
  ...
}
```

## 2. Example

@Trace를 붙이면 아래와 같이 출력된다.

```
[trace] ==> hello starts.
[trace] ==|==> doService starts.
[do helloService] normal
[trace] <==|== doService ends. times=22ms 
[trace] <== hello ends. times=80ms 
```
