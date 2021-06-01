package ca.jrvs.apps.practice;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Test;

public class LambdaStreamImplTest {

  @Test
  public void createStrStream() {
    LambdaStreamExc lse = new LambdaStreamImpl();
    Stream<String> strings = lse.createStrStream("one", "two", "three", "four");
    assertEquals(Arrays.asList("one", "two", "three", "four"), strings.collect(Collectors.toList()));
  }

  @Test
  public void toUpperCase() {
    LambdaStreamExc lse = new LambdaStreamImpl();
    Stream<String> strings = lse.toUpperCase("one", "twO", "THREE", "Four");
    assertEquals(Arrays.asList("ONE", "TWO", "THREE", "FOUR"), strings.collect(Collectors.toList()));
  }

  @Test
  public void filter() {
    LambdaStreamExc lse = new LambdaStreamImpl();
    Stream<String> strings = lse
        .filter(
            lse.createStrStream("ZERO", "one", "Two", "THREE", "four", "five")
            , ".*[A-Z]+.*"); // one or more capitalized letters
    assertEquals(Arrays.asList("one", "four", "five"), strings.collect(Collectors.toList()));
  }

  @Test
  public void createIntStream() {
    LambdaStreamExc lse = new LambdaStreamImpl();
    int[] data = new int[] {5,10,3,25,18,33,1,9};
    IntStream intStream = lse.createIntStream(data);
    assertArrayEquals(data, intStream.toArray());
  }

  @Test
  public void toList() {
    LambdaStreamExc lse = new LambdaStreamImpl();
    Stream<String> strings = lse.createStrStream("one", "two", "three", "four");
    assertEquals(Arrays.asList("one", "two", "three", "four"), lse.toList(strings));
  }

  @Test
  public void testToList() {
    LambdaStreamExc lse = new LambdaStreamImpl();
    int[] data = new int[] {5,10,3,25,18,33,1,9};
    IntStream intStream = lse.createIntStream(data);
    assertEquals(Arrays.stream(data).boxed().collect(Collectors.toList()), lse.toList(intStream));
  }

  @Test
  public void testCreateIntStream() {
    LambdaStreamExc lse = new LambdaStreamImpl();
    int[] data = new int[] {4,5,6,7,8,9,10,11,12};
    IntStream intStream = lse.createIntStream(4, 12);
    assertArrayEquals(data, intStream.toArray());
  }

  @Test
  public void squareRootIntStream() {
    LambdaStreamExc lse = new LambdaStreamImpl();
    int[] data = new int[] {4,5,6,7,8,9,10,11,12};
    IntStream intStream = lse.createIntStream(4, 12);
    DoubleStream doubleStream = lse.squareRootIntStream(intStream);
    double delta = 1e-6; //tolerance
    assertArrayEquals(Arrays.stream(data).boxed().mapToDouble(Math::sqrt).toArray(), doubleStream.toArray(), delta);
  }

  @Test
  public void getOdd() {
    LambdaStreamExc lse = new LambdaStreamImpl();
    IntStream intStream = lse.createIntStream(4, 12);
    IntStream oddStream = lse.getOdd(intStream);
    int[] data = new int[] {5,7,9,11};
    assertArrayEquals(data, oddStream.toArray());

  }


  @Test
  public void printMessages() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    LambdaStreamExc lse = new LambdaStreamImpl();
    String prefix = ">";
    String suffix = "<";
    Consumer<String> consumer = lse.getLambdaPrinter(prefix, suffix);
    String[] messages = {"one", "two", "three"};
    lse.printMessages(messages, consumer);
    assertEquals(">one<\n>two<\n>three<\n", outContent.toString());

    System.setOut(System.out);
  }

  @Test
  public void printOdd() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    LambdaStreamExc lse = new LambdaStreamImpl();
    String prefix = ">";
    String suffix = "<";
    Consumer<String> consumer = lse.getLambdaPrinter(prefix, suffix);

    IntStream intStream = lse.createIntStream(4, 12);
    int[] data = new int[] {5,7,9,11};

    lse.printOdd(intStream, consumer);
    assertEquals(">5<\n>7<\n>9<\n>11<\n", outContent.toString());

    System.setOut(System.out);
  }

  @Test
  public void flatNestedInt() {
    LambdaStreamExc lse = new LambdaStreamImpl();
    List<Integer> list1 = Arrays.asList(1,2,3);
    List<Integer> list2 = Arrays.asList(30,20,10);
    List<Integer> list3 = Arrays.asList(5,5,5);
    List<Integer> result = Arrays.asList(1,2,3,30,20,10,5,5,5);

    Stream<List<Integer>> nestedStream = Stream.of(list1, list2, list3);

    assertEquals(result, lse.flatNestedInt(nestedStream).collect(Collectors.toList()));
  }
}