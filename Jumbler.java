import java.util.*;
public class Jumbler {
static int count,limit,digi_ctrs[];
static long key,list[];

static int facto(int n){
	if(n<=0)
		return 1;
	else
		return n*facto(n-1);
}
static char[] swap(char[] str,int x, int y){
	char temp=str[x];
	str[x]=str[y];
	str[y]=temp;
	return str;
}
static void calcPermute(char[] str){
	digi_ctrs=new int[10];
	for(int i=0;i<str.length;i++)
		digi_ctrs[(int)str[i]-48]++;
	count=0;
	limit=facto(str.length);
	for(int i=0;i<10;i++)
		limit/=facto(digi_ctrs[i]);
	list=new long[limit];
}
static void strPermute(char[] str,int j){
	int n=str.length;
	if(count>=limit)
		return;
	else if(j==n-1){
		if(count>0){
			Arrays.sort(list);
			key=Integer.parseInt(new String(str));
		    int pos=Arrays.binarySearch(list, key);
		    if(pos>0&&pos<list.length&&list[pos]==key)
		    	return;
		}
		list[limit-1-(count++)]=Integer.parseInt(new String(str));
		System.out.println(str);
	}
	else{
		for(int i=j;i<n;i++){
			str=swap(str,i,j);
			strPermute(str,j);
			str=swap(str,i,j);
		}
	}
}
public static void main(String[] a){
	Scanner sc=new Scanner(System.in);
	char[] s=sc.nextLine().toCharArray();
	calcPermute(s);
	strPermute(s,0);
	sc.close();
}
}