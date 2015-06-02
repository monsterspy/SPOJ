import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;
 
 
public class Solution{
    
    ///////////////////////////////////////////////////////////////////////////
    static class FastScanner{
        BufferedReader s;
        StringTokenizer st;
        
        public FastScanner(InputStream InputStream){
            st = new StringTokenizer("");
            s = new BufferedReader(new InputStreamReader(InputStream));
        }
        
        public FastScanner(File f) throws FileNotFoundException{
            st = new StringTokenizer("");
            s = new BufferedReader (new FileReader(f));
        }
        
        public int nextInt() throws IOException{
            if(st.hasMoreTokens())
                return Integer.parseInt(st.nextToken());
            else{
                st = new StringTokenizer(s.readLine());
                return nextInt();
            }
        }
        
        public BigInteger big() throws IOException{
            if(st.hasMoreTokens())
                return new BigInteger(st.nextToken());
            else{
                st = new StringTokenizer(s.readLine());
                return big();
            }
        }
        
        public double nextDouble() throws IOException{
            if(st.hasMoreTokens())
                return Double.parseDouble(st.nextToken());
            else{
                st = new StringTokenizer(s.readLine());
                return nextDouble();
            }
        }
      
        public long nextLong() throws IOException{
            if(st.hasMoreTokens())
                return Long.parseLong(st.nextToken());
            else{
                st = new StringTokenizer(s.readLine());
                return nextLong();
            }
        }
        
        public String nextString() throws IOException{
            if(st.hasMoreTokens())
                return st.nextToken();
            else{
                st = new StringTokenizer(s.readLine());
                return nextString();
            }
            
        }
        public String readLine() throws IOException{
            return s.readLine();
        }
        
        public void close() throws IOException{
            s.close();
        }
        
    }
    
    ////////////////////////////////////////////////////////////////////
    //      Number Theory
    
    long pow(long a,long b,long mod){
        long x = 1; long y = a;
        while(b > 0){
            if(b % 2 == 1){
                x = (x*y);
                x %= mod;
            }
            y = (y*y);
            y %= mod;
            b /= 2;         
        }
        return x;
    }
    
    int divisor(long x,long[] a){
        long limit = x;
        int numberOfDivisors = 0;
 
        for (int i=1; i < limit; ++i) {
            if (x % i == 0) {
                limit = x / i;
                if (limit != i) {
                    numberOfDivisors++;
                }
                numberOfDivisors++;
            }
        }
        return numberOfDivisors;
    }
    
    void findSubsets(int array[]){
        long numOfSubsets = 1 << array.length; 
        for(int i = 0; i < numOfSubsets; i++){    
            @SuppressWarnings("unused")
			int pos = array.length - 1;
            int bitmask = i;
            while(bitmask > 0){     
                if((bitmask & 1) == 1)
//                    ww.print(array[pos]+" ");
                bitmask >>= 1;
                pos--;
            }
//            ww.println();
        }
    }
    
    
    public static long gcd(long a, long b){
        return b == 0 ? a : gcd(b,a%b);
    }
    
  
    public static long lcm(int a,int b, int c){
        return lcm(lcm(a,b),c);
    }
    
    public static long lcm(long a, long b){
        return (a*b/gcd(a,b));
    }
    
    public static long invl(long a, long mod) {
        long b = mod;
        long p = 1, q = 0;
        while (b > 0) {
            long c = a / b;
            long d;
            d = a;
            a = b;
            b = d % b;
            d = p;
            p = q;
            q = d - c * q;
        }
        return p < 0 ? p + mod : p;
    }
    
    ////////////////////////////////////////////////////////////////////
    
    
//     FastScanner s = new FastScanner(new File("a.pdf"));
//	 PrintWriter ww = new PrintWriter(new FileWriter("output.txt")); 
    static InputStream inputStream = System.in;
    static FastScanner s = new FastScanner(inputStream); 
    static OutputStream outputStream = System.out;
    static PrintWriter ww = new PrintWriter(new OutputStreamWriter(outputStream));
//      private static Scanner s = new Scanner(System.in);
    @SuppressWarnings("unused")
    private static int[][] states = { {-1,0} , {1,0} , {0,-1} , {0,1} };
    
      
    //////////////////////////////////////////////////////////////////// 
     
    
     
    public static void main(String[] args) throws Exception{
        new Solution().solve();
        s.close();
        ww.close();
    }
    

    ////////////////////////////////////////////////////////////////////
    
    int n;
    int t;
    int[][] time;
    int[][] toll;
    boolean[][] vis;
    int[] an = new int[2];
    
    boolean check(int x,int y){
    	if(x < n && y < n && x >= 0 && y >= 0) return true;
    	else return false;
    }
    
    void dfs(int x, int y,int tol, int tim){
    	if(tim > t || tol > an[0]) return;
    	if(y == n-1){
    		if(tim <= t){
    			if(tol < an[0]){
    				an[0] = tol;
    				an[1] = tim;
    			}
    		}
    	}
    	if(vis[x][y]) return;
    	vis[x][y] = true;
    	for(int i=0;i<n;i++){
    		int xx = y;
    		int yy = i;
    		if(xx == yy) continue;
    		if(check(xx,yy))
    			dfs(xx,yy,tol+toll[xx][yy],tim+time[xx][yy]);
    	}
    	vis[x][y] = false;
    }
    
    
    void solve() throws IOException{
		
    	while(true){	
    		n = s.nextInt();
    		t = s.nextInt();
    		
    		if(n + t == 0) break;
    		time = new int[n][n];
    		toll = new int[n][n];
    		vis = new boolean[n][n];
    		
    		an[0] = Integer.MAX_VALUE;
    		an[1] = Integer.MAX_VALUE;
    		
    		for(int i=0;i<n;i++)
    			for(int j=0;j<n;j++)
    				time[i][j] = s.nextInt();
    		
    		for(int i=0;i<n;i++)
    			for(int j=0;j<n;j++)
    				toll[i][j] = s.nextInt();
    		
    		dfs(0,0,0,0);
    		ww.println(an[0]+" "+an[1]);
    		
    	}
    	
    }
}	
	



