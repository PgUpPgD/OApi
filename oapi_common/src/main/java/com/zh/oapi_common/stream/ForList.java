package com.zh.oapi_common.stream;

import lombok.Data;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Data
public class ForList {

    private String type;
    private Integer id;
    private Integer index;

    public ForList(){}
    public ForList(String type, int id) {
        this.type = type;
        this.id = id;
    }
    public ForList(int id, String type) {
        this.id = id;
        this.type = type;
    }
    public ForList(int id, String type, int index) {
        this.id = id;
        this.type = type;
    }

//    public String getType() {
//        System.out.println(type);
//        return type;
//    }

    public static void main(String[] args) {

        ForList forList = new ForList("s1", 1);
        ForList forList1 = new ForList("s1", 3);
        ForList forList2 = new ForList("s1", 2);
        ForList forList3 = new ForList("s2", 4);

        List<ForList> list = new ArrayList<>();
        list.add(forList);
        list.add(forList1);
        list.add(forList2);
        list.add(forList3);

        System.out.println("----------1.基本示例----------");

        List<Integer> collect = list.stream().
                filter(t -> t.getType().equals("s1"))  //筛选条件
                .sorted(Comparator.comparing(ForList::getId))  //排序字段
                .map(ForList::getId).  //结果集
                collect(toList());   //输出
        System.out.println(collect);

        int sum = list.stream()
                .filter(w -> w.getType().equals("s1"))
                .mapToInt(w -> w.getId())
                .sum();
        System.out.println(sum);

        System.out.println("----------2.流构造常用方法----------");

        //构造流的几种常见方法
        // 1. Individual values
        Stream stream = Stream.of("a", "b", "c");
        stream.forEach(System.out::println);

        // 2. Arrays
        String[] strArray = new String[]{"a", "b", "c"};
        Stream stream1 = Stream.of(strArray);
        Stream stream2 = Arrays.stream(strArray);

        // 3. Collections
        List<String> list1 = Arrays.asList(strArray);
        Stream stream3 = list.stream();

        System.out.println("----------3.数值流构造----------");

        //数值流的构造
        //对于基本数据类型，目前有三种对应的包装类型Stream:
        //InStream,LongStream,DoubleStream.
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);

        System.out.println("----------4.流转换为其它数据结构----------");

        // 1. Array
//        String[] strArray1 = stream.toArray(String[]::new);
        // 2. Collection
//        List<String> list3 = stream.collect(Collectors.toList());
//        List<String> list4 = stream.collect(Collectors.toCollection(ArrayList::new));
//        Set set1 = stream.collect(Collectors.toSet());
//        Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
        // 3. String
//        String str = stream.collect(Collectors.joining()).toString();

        System.out.println("----------5.流的操作----------");

        //Intermediate
        //map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
        //Terminal
        //forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
        //Short-circuiting
        //anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit

        System.out.println("----------6.转换大写----------");

        List<String> wordList = new ArrayList<>();
        wordList.add("abc");
        List<String> abc = wordList.stream().
                map(String::toUpperCase).
                collect(toList());
        System.out.println(abc);

        System.out.println("----------7.平方数----------");

        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().
                map(n -> n * n).
                collect(toList());
        System.out.println(squareNums);

        System.out.println("----------8.一对多----------");

        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());  //flatMap 把对象按照条件重组成新的流
        List<Integer> collect1 = outputStream.collect(toList());
        System.out.println(collect1);

        System.out.println("----------9.留下偶数----------");

        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens = Stream.of(sixNums)
                .filter(n -> n % 2 == 0)
                .toArray(Integer[]::new);
        Integer a = evens[0];
        Stream evens1 = Stream.of(evens);
        List<Integer> evenss = (List<Integer>) evens1.collect(toList());
        System.out.println(a + "----" + evenss);

        System.out.println("----------10.把单词挑出来----------");

        String str = "word reader lines KEY KILL";
        byte[] bytes = str.getBytes();
        ByteArrayInputStream stream4 = new ByteArrayInputStream(bytes);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream4));
        List<String> output = reader.lines().
                flatMap(line -> Stream.of(line.split(" "))).
                filter(word -> word.length() > 0).
                collect(Collectors.toList());
        System.out.println(output);

        System.out.println("----------11.打印姓名 forEach和pre-java8的对比----------");

        // Java 8
        list.stream()
                .filter(p -> p.getType().equals("s1"))
                .forEach(p -> System.out.println(p.getId()));
        // Pre-Java 8
        for (ForList p : list) {
            if (p.getType().equals("s1")) {
                System.out.println(p.getId());
            }
        }
        //一般认为，forEach 和常规 for 循环的差异不涉及到性能，它们仅仅是函数式风格与传统 Java 风格的差别。
        //注意，forEach 是 terminal 操作，因此它执行后，Stream 的元素就被“消费”掉了，
        // 你无法对一个 Stream 进行两次 terminal 运算

        System.out.println("----------12.peek对每个元素执行操作并返回一个新的Stream----------");

        Stream.of("one", "two", "three", "four", "five")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        System.out.println("----------13.Optional的两个用例----------");

        String strA = "a", strB = null, strC = "";
        // Java 8
        Optional.ofNullable(strC).ifPresent(System.out::println);  //输出空
        // Java 8
        Optional.ofNullable(strC).map(String::length).orElse(-1);

        System.out.println("----------14.reduce的用例----------");

        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println(concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println(minValue);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(3, Integer::sum);
        System.out.println(sumValue);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println(sumValue);
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);
        System.out.println(concat);

        Integer[] ar = {1, 2, 3, 4};
        int sumValueTest = Stream.of(ar).reduce(3, Integer::sum);
        System.out.println(sumValueTest);

        System.out.println("----------15.limit 和 skip 对运行次数的影响----------");

        List<ForList> forLists = new ArrayList();
        forLists.add(new ForList(1,"1"));
        forLists.add(new ForList(1,"2"));
        forLists.add(new ForList(1,"3"));
        forLists.add(new ForList(1,"4"));
        forLists.add(new ForList(1,"5"));
        forLists.add(new ForList(1,"6"));
        forLists.add(new ForList(1,"7"));
        forLists.add(new ForList(1,"8"));
        forLists.add(new ForList(1,"9"));
        forLists.add(new ForList(1,"10"));
        forLists.add(new ForList(1,"11"));
        forLists.add(new ForList(1,"12"));
        forLists.add(new ForList(1,"13"));
        forLists.add(new ForList(1,"14"));

        List<String> personList2 = forLists.stream().
                map(ForList::getType).limit(15).skip(12).collect(Collectors.toList());
        System.out.println(personList2);
        //例page = 2; limit = 3；
        List<ForList> personList3 = forLists.stream()  //优化后
                .limit(3 * 5)
                .skip(3 * (5 - 1))
                .sorted((p1, p2) -> p1.getId().compareTo(p2.getId()))
                .collect(Collectors.toList());
        System.out.println(personList3);

        System.out.println("----------16.找出最长一行的长度----------");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\唐恒超\\Documents\\B15012015_唐恒超_轻轨车厢顶板铝型材挤压工艺及模具设计\\开题报告.doc"));
            int longest = br.lines().
                    mapToInt(String::length).
                    max().
                    getAsInt();
            br.close();
            System.out.println(longest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("----------17.找出全文的单词，转小写，并排序----------");

        List<String> words = reader.lines().
                flatMap(line -> Stream.of(line.split(" "))).
                filter(word -> word.length() > 0).
                map(String::toLowerCase).
                distinct().
                sorted().
                collect(Collectors.toList());
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(words);

        System.out.println("----------18.使用 Match----------");

        boolean isAllAdult = forLists.stream().
                allMatch(p -> p.getId() > 18);
        System.out.println("All are adult? " + isAllAdult);
        boolean isThereAnyChild = forLists.stream().
                anyMatch(p -> p.getId() < 18);
        System.out.println("Any child? " + isThereAnyChild);

        System.out.println("----------19.生成 10 个随机整数----------");

        Random seed = new Random();  //不够随机
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random).limit(10).forEach(System.out::println);
        //Another way
        IntStream.generate(() -> (int) (System.nanoTime() % 10)).
                limit(10).forEach(System.out::println);

        System.out.println("----------20.自实现 Supplier----------");

        Stream.generate(new PersonSupplier()).
                limit(10).
                forEach(p -> System.out.println(p.getId() + ", " + p.getIndex()));



    }

}
