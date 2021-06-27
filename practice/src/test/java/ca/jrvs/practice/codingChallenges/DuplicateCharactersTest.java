package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class DuplicateCharactersTest {

  @Test
  public void findDuplicateCharacters() {
    String s1 = "A black cat";
    List<Character> characterList1 = Arrays.asList('a', 'c');
    assertEquals(characterList1, DuplicateCharacters.findDuplicateCharacters(s1));


    String s2 = "A black cat A";
    List<Character> characterList2 = Arrays.asList('A', 'a', 'c');
    assertEquals(characterList2, DuplicateCharacters.findDuplicateCharacters(s2));

    String s3 = "321 aaaa 123 bbbb";
    List<Character> characterList3 = Arrays.asList('3', '2', '1', 'a', 'b');
    assertEquals(characterList3, DuplicateCharacters.findDuplicateCharacters(s3));


    assertEquals(Arrays.asList(),  DuplicateCharacters.findDuplicateCharacters("     "));

    assertEquals(Arrays.asList(),  DuplicateCharacters.findDuplicateCharacters(""));


  }
}