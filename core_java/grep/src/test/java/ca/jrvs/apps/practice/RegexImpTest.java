package ca.jrvs.apps.practice;

import org.junit.*;

public class RegexImpTest {

  private RegexExc regexExcTested;

  @Before
  public void setUp() {
    regexExcTested = new RegexImp();
  }

  @Test
  public void matchJpeg() {
    Assert.assertTrue(regexExcTested.matchJpeg("abc.jpg"));
    Assert.assertTrue(regexExcTested.matchJpeg("zxc321.jpeg"));

    Assert.assertFalse(regexExcTested.matchJpeg(".jpg"));
    Assert.assertFalse(regexExcTested.matchJpeg(".jpeg"));
    Assert.assertFalse(regexExcTested.matchJpeg("asd2.jpgg"));
    Assert.assertFalse(regexExcTested.matchJpeg("jpeg"));
    Assert.assertFalse(regexExcTested.matchJpeg("jpg"));
  }

  @Test
  public void matchIp() {
    Assert.assertTrue(regexExcTested.matchIp("192.16.0.1"));
    Assert.assertTrue(regexExcTested.matchIp("182.168.100.100"));
    Assert.assertTrue(regexExcTested.matchIp("001.001.01.1"));
    Assert.assertTrue(regexExcTested.matchIp("1.1.1.1"));

    Assert.assertFalse(regexExcTested.matchIp("192.168"));
    Assert.assertFalse(regexExcTested.matchIp("192#168#0#1"));
  }

  @Test
  public void isEmptyLine() {
    Assert.assertTrue(regexExcTested.isEmptyLine(""));
    Assert.assertTrue(regexExcTested.isEmptyLine("   "));
    Assert.assertTrue(regexExcTested.isEmptyLine("\t   \t"));
    Assert.assertTrue(regexExcTested.isEmptyLine("   \t"));

    Assert.assertFalse(regexExcTested.isEmptyLine(" 1"));
    Assert.assertFalse(regexExcTested.isEmptyLine("\t1"));
    Assert.assertFalse(regexExcTested.isEmptyLine("1"));
    Assert.assertFalse(regexExcTested.isEmptyLine("    1    "));
  }
}