package ca.jrvs.apps.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexImp implements RegexExc {

  @Override
  public boolean matchJpeg(String filename) {
    Pattern jpegFilePattern = Pattern.compile("\\S+\\.(jpg|jpeg)\\b");
    Matcher jpegFileMatcher = jpegFilePattern.matcher(filename);
    return jpegFileMatcher.matches();
  }

  @Override
  public boolean matchIp(String ip) {
    Pattern ipPattern = Pattern.compile("(\\d{1,3}\\.){3}\\d{1,3}");
    Matcher ipMatcher = ipPattern.matcher(ip);
    return ipMatcher.matches();
  }

  @Override
  public boolean isEmptyLine(String line) {
    Pattern emptyLinePattern = Pattern.compile("^[ \\t\\n]*$");
    Matcher emptyLineMatcher = emptyLinePattern.matcher(line);
    return emptyLineMatcher.matches();
  }
}
