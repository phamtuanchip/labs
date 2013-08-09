package lab10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexdemo
{



  public static void sample4(){

    String REGEX = "a*b";
    String INPUT = "aabfooaabfooabfoob";
    String REPLACE = "-";

    Pattern p = Pattern.compile(REGEX);
    // get a matcher object
    Matcher m = p.matcher(INPUT);
    StringBuffer sb = new StringBuffer();
    while(m.find()){
      m.appendReplacement(sb,REPLACE);
    }
    m.appendTail(sb);
    System.out.println(sb.toString());

  }

  public static void sample3(){

    String REGEX = "foo";
    String INPUT = "fooooooooooooooooo";
    Pattern pattern;
    Matcher matcher;


    pattern = Pattern.compile(REGEX);
    matcher = pattern.matcher(INPUT);

    System.out.println("Current REGEX is: "+REGEX);
    System.out.println("Current INPUT is: "+INPUT);

    System.out.println("lookingAt(): "+matcher.lookingAt());
    System.out.println("matches(): "+matcher.matches());


  }

  public static void sample2(){
    String REGEX = "\\bcat\\b";
    String INPUT =
        "cat cat cat cattie cat";
    Pattern p = Pattern.compile(REGEX);
    Matcher m = p.matcher(INPUT); // get a matcher object
    int count = 0;

    while(m.find()) {
      count++;
      System.out.println("Match number "+count);
      System.out.println("start(): "+m.start());
      System.out.println("end(): "+m.end());
    }
  }

  public static void sample1(){
    // String to be scanned to find the pattern.
    String line = "This order was places for QT3000! OK?";
    String pattern = "(.*)(\\d+)(.*)";

    // Create a Pattern object
    Pattern r = Pattern.compile(pattern);

    // Now create matcher object.
    Matcher m = r.matcher(line);
    if (m.find( )) {
      System.out.println("Found value: " + m.group(0) );
      System.out.println("Found value: " + m.group(1) );
      System.out.println("Found value: " + m.group(2) );
    } else {
      System.out.println("NO MATCH");
    }
  }

  public static void main( String args[] ){
    sample1();
    sample2();
    sample3();
    sample4();

  }
}