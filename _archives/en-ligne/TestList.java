//spackage com.journaldev.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class TestList {

	public static void main(String[] args) {
		
		System.out.println("Hello world !");
		String [] table = {"a","1","i","o","u"};
		
		List<String> liste = Arrays.asList(table);
		System.out.println(liste);
		
		Collections.sort(liste);
		System.out.println(liste);
		
		/*String[] vowels = {"a","e","i","o","u"};
		
		List<String> vowelsList = Arrays.asList(vowels);
		System.out.println(vowelsList);
		
		
				
		//using for loop to copy elements from array to list, safe for modification of list
		List<String> myList = new ArrayList<>();
		for(String s : vowels){
			myList.add(s);
		}
		System.out.println(myList);
		myList.clear(); */
	}

}