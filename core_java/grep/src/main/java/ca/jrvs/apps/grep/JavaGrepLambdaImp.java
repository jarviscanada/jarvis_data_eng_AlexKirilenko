package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.BasicConfigurator;

public class JavaGrepLambdaImp extends JavaGrepImp{

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }
    BasicConfigurator.configure();

    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try {
      javaGrepLambdaImp.process();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  JavaGrepLambdaImp() {
    super();
  }

  @Override
  public void process() throws IOException {
    List<String> processedLines =  listFiles(getRootPath())
        .stream()
        .map(this::readLines)
        .flatMap(Collection::stream)
        .filter(this::containsPattern)
        .collect(Collectors.toList());

    // writing is done in batch not to invoke the method in the stream for each line
    writeToFile(processedLines);
  }

  @Override
  public List<File> listFiles(String rootDir) throws IOException {
    return Files
        .walk(Paths.get(rootDir))
        .filter(Files::isRegularFile)
        .map(Path::toFile)
        .collect(Collectors.toList());
  }

  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException {
    List<String> lines = new LinkedList<>();
    try {
      lines = Files
          .lines(inputFile.toPath())
          .collect(Collectors.toList());
    } catch (IOException e) {
      throw new IllegalArgumentException(
          String.format("File %s cannot be read", inputFile.getPath()), e);
    }
    return lines;
  }

  @Override
  public boolean containsPattern(String line) {
    return super.containsPattern(line);
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    PrintWriter printWriter = new PrintWriter(Paths.get(getOutFile()).toFile());
    lines.stream().forEach(printWriter::println);
  }
}
