package Assign1;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A12 {
	public static void processFile(BufferedReader Reader){
		HashMap validIdentifiers = new HashMap();
		String regex ="\"(.*?)\"|([a-zA-Z]+)([0-9]+)?([a-zA-Z]+)?";
		Pattern regexPattern = Pattern.compile(regex);
		String line = null;
		Matcher regexMatcher = null;
		int identifierCounter = 0;
		try {
			while ( (line = Reader.readLine()) != null){
					regexMatcher = regexPattern.matcher(line);
				while(regexMatcher.find()){
					String matchFound = regexMatcher.group();
					System.out.println("Matches: " + matchFound);
					if(matchFound.charAt(0) != '"' && !isKeyword(matchFound)){
						if(!validIdentifiers.containsKey(matchFound)){
							validIdentifiers.put(matchFound, 1);
							identifierCounter ++;
						}	
					}	
				}
			}
			System.out.println("Number of Identifiers: " + identifierCounter);
			//writeAnswerToFile(identifierCounter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static boolean isKeyword(String input){
		ArrayList keyWords = new ArrayList<String>();
		keyWords.add("IF");
		keyWords.add("READ");
		keyWords.add("ELSE");
		keyWords.add("RETURN");
		keyWords.add("BEGIN");
		keyWords.add("END");
		keyWords.add("MAIN");
		keyWords.add("STRING");
		keyWords.add("INT");
		keyWords.add("REAL");
		keyWords.add("WRITE");
		
		if((keyWords.indexOf((String)input)) != -1){
			return true;
		}
		return false;
	}

	
	public static void writeAnswerToFile(int fileContent) throws IOException{
		String content = "identifiers:"+fileContent;
		try {
			BufferedWriter writerObject = new BufferedWriter(new FileWriter("A1.output"));
			writerObject.write(content);
			writerObject.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader readerObject = new BufferedReader(new FileReader(args[0]));
			processFile(readerObject);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
