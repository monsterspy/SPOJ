import java.util.Scanner;

public class Solve{
	
	Scanner s = new Scanner(System.in);
	public static void main(String[] args){
		new Solve().solve();
	}
	
	class Node{
		int left, right, value;
		public Node(int left,int right){
			this.left = left;
			this.right = right;
		}
		public void merge(Node leftChild, Node rightChild){
			if(leftChild == null) value = rightChild.value;
			else if(rightChild == null) value = leftChild.value;
			else value = Math.min(leftChild.value, rightChild.value);
		}	
	}
	
	class SegmentTree{
		
		int n;
		Node[] tree;
		public SegmentTree(int[] arr){
			n = arr.length;
			int power =  (int) Math.floor(Math.log(arr.length));
			int len = (int) ((int) 2 * Math.pow(2,power));	
			tree = new Node[100];
			built(1,0,arr.length-1,arr);
		}
		private void built(int node, int l ,int r, int[] arr){
			
			if(l == r){
				tree[node] = new Node(l,r);
				tree[node].value = arr[l];
				return;
			}
			int leftChild = 2*node;
			int rightChild = leftChild+1;
			int middle = (l+r)/2;
			built(leftChild,l,middle,arr);
			built(rightChild,middle+1,r,arr);
			tree[node] = new Node(l,r);
			tree[node].merge(tree[leftChild], tree[rightChild]);
		}
		private Node query(int node, int l ,int r, int i, int j){
			
			if(l >= i && r <= j)
				return tree[node];
			else if(i > r || j < l) return null;
			else{
				int leftChild = 2*node;
				int rightChild = leftChild+1;
				int mid = (l+r)/2;
				Node a = query(leftChild,l,mid,i,j);
				Node b = query(rightChild,mid+1,r,i,j);
				Node temp = new Node(-1,-1);
				temp.merge(a, b);
				return temp;
			}
		}
		public int query(int i, int j){
			Node result = query(1,0,n-1,i,j);
			return result.value;
		}
		
		private void update(int node, int l,int r, int i, int v){
			if(i == l || i == r) tree[node].value = v;
			else if(i < l || i > r) return;
			else{
				int leftChild = 2*node;
				int rightChild = leftChild+1;
				int mid = (l+r)/2;
				update(leftChild,l,mid,i,v);
				update(rightChild,mid+1,r,i,v);
				tree[node].merge(tree[leftChild], tree[rightChild]);
			}
			
		}
		public void update(int i, int value){
			update(1,0,n-1,i,value);
		}
	}
	
	void solve(){
		int[] arr = {1,11,45,23,4,77};
		SegmentTree st = new SegmentTree(arr);
		System.out.println(st.query(0,5));
		st.update(1, 55);
		System.out.println(st.query(0,5));
	}

}
