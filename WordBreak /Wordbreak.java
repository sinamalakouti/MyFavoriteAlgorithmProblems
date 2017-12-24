import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Wordbreak {
static ArrayList<String> result = new ArrayList<String>();
	public static class BreakRecorder {
		public boolean flag;
		public List<String> set;
		public BreakRecorder() {
			
			this.flag = false;
			this.set = new ArrayList();
		}
		
		
	}
    public static List<String> wordBreak(String s, Set<String> wordDict) {

    	int length = s.length();
    	BreakRecorder[] result = new BreakRecorder[length + 1];
    	for (int i = 0; i < length + 1; i++) {
    		result[i] = new BreakRecorder();
    
    	}
    	result[0].flag = true;
    	for (int i = 1; i <= length; i++) {
    		for (int j = 0; j < i; j++) {
    			if (result[j].flag && wordDict.contains(s.substring(j, i))) {
    				result[i].flag = true;
    				result[i].set.add(s.substring(j, i));
    			}
    		}
    	}
    	List<String> lists = new ArrayList();
    	generate(lists, new ArrayList<String>(), result, length);
    	return lists;
    }
    
    public static void generate(List<String> lists, List<String> list, BreakRecorder[] breakRecorders, int index) {
    	if (index <= 0) {
    		lists.add(list.toString().replaceAll("\\[|\\]", "").replaceAll(",",""));
    	} else if (breakRecorders[index].flag) {
    		for (String word: breakRecorders[index].set) {
    			list.add(0, word);
    			generate(lists, list, breakRecorders, index - word.length());
    			
    			list.remove(0);
    		}
    	}
    }
    
    public static void worBreak2(String str, HashSet<String> set , int start ,  int index , int end, String sentence , boolean shouldadd) {
    	
    		while ( index <= end ){
    			
    			
    			
    			String temp = str.substring(start, index);

    			if ( promissing(temp, set))
    			{
    				if ( end == index )
    					shouldadd = true;
    				String copy = sentence; 
    				sentence += temp + " ";

    				if ( index == end)
    					shouldadd = true;
    				worBreak2(str, set, index ,  index + 1  ,end, sentence, shouldadd);
    				shouldadd = false;
    				sentence = copy;
    				
    				
    			}else {
    				
    				if ( index == end )
    					shouldadd = false;
    			}
    			
    			
    			
    			index ++; 
    		}
    		
    		if ( index > end && shouldadd){
    			String tmp  = sentence.substring(0,sentence.length()-1);
    			result.add(tmp);
    			sentence = "";
    			
    		}else {
    			if  (index > end)
    				sentence = "";
    		}
    		
    			
    		
    		
    		
    	}
    	
    	
    	
    
    public static boolean promissing ( String str , HashSet<String> set){
    	
    	if  ( set.contains(str))
    		return true; 
    	else return false; 
    		
    }
        
    public static void main(String[] args) {
    	
    	Scanner scan = new Scanner(System.in);
    	
    	String str = scan.nextLine();

    	int i = 0;
    	HashSet<String> set = new HashSet<String>();
    	int n = scan.nextInt();
//    	String s  = scan.next();
//    	System.out.println(s);
//    	String [] arr = s.split(",");
    	for (i =0 ; i < n ; i++){
    		set.add(scan.next());
    	}
//    	System.out.println(set);
//    	worBreak2(str, set, 0, 0, str.length(), "",false);
//    	System.out.println(result);
    	result = (ArrayList<String>) wordBreak(str, set);
    	Collections.sort(result);
    	for (  i =0 ; i < result.size() ; i++ )
    		System.out.println(result.get(i) );

	}
}