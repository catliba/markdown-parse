// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next ) )[
        int currentIndex = 0;
        int linkIndex = 0;
        while(currentIndex < markdown.length()) {
            System.out.println(currentIndex);
            int nextOpenBracket = markdown.indexOf("[", currentIndex); 
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket); 
            if (nextOpenBracket == -1 || nextCloseBracket == -1) {
                break;
            }
            int openParen = markdown.indexOf("(", nextCloseBracket); 
            int closeParen = markdown.indexOf(")", openParen);//0
            if (nextOpenBracket != 0 && markdown.charAt(nextOpenBracket-1) != '!'){
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                linkIndex++;
            }
            if (nextCloseBracket + 1 != openParen) {
                toReturn.remove(linkIndex-1);
                linkIndex--;
            }
            currentIndex = closeParen + 1;
            System.out.println(currentIndex);
        }
        
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}