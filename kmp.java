import java.util.*;
 
public class Coder{
 
	public static int[] prefix_(String text){
 
		int[] prefix = new int[text.length()];
		int len = text.length();
		int k = 0;
 
		for(int i=1;i<len;i++){
 
			while(k > 0  && text.charAt(i) != text.charAt(k))
				k = prefix[k-1];
			if(text.charAt(i) == text.charAt(k))
				k++;
			prefix[i] = k;
		}
		return prefix;
	}
 
	public static int KMP(String text, String pattern, int[] p){
 
		int k = 0;
		int len = text.length();
		int m = pattern.length();
		int i=0;
		for(i=0;i<len;i++){
 
			while(k > 0 && text.charAt(i) != pattern.charAt(k))
				k = p[k-1];
			if(text.charAt(i) == pattern.charAt(k))
				k++;
			if(m == k)
				return i-m;
 
		}
		return -1;
	}
 
 
	public static void main(String[] args){
 
		Scanner s = new Scanner(System.in);
		String text = s.next();
		String pattern = s.next();
		int[] prefix = prefix_(pattern);
		System.out.println(KMP(text,pattern,prefix)+2);
 
	}
 
 
 
}
