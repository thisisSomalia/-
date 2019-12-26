
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class step1to4 {
	static File file;
	static Scanner scanner;

	//	初始化scanner对象，指向输入文件
	public static void fileread(String filename)throws Exception{
		file = new File(filename);
		scanner = new Scanner(file,"gbk");
	}

	//  截取-n参数后面的数字，如果没有-n参数，返回-1
	public static int isNumber(String s) {

		if(s.charAt(0)=='-') {
			String n = s.substring(1,s.length());
			int num = Integer.parseInt(n);
			return num;
		}
		return -1;
	}

	//  对应参数-c，显示字母频率
	public static void showletterf(String filename) throws Exception {
		int[] abc = new int[26];
		ArrayList<Alpha> letter = new ArrayList<Alpha>();
		int sum=0;  //统计单词总数
		char[] line = new char[1024];   //txt文件中的一行
		char c='a';
		fileread(filename);			//读取文件
		while(scanner.hasNext()){
			line = scanner.nextLine().toCharArray();
			for(int i=0;i<line.length;i++){
				if((line[i]>='A'&&line[i]<='Z')){
					int k = line[i]-'A';
					abc[k]++;
					sum++;
				}
				if((line[i]>='a'&&line[i]<='z')){
					int t = line[i]-'a';
					abc[t]++;
					sum++;
				}
			}
		}
		for(int i=0;i<26;i++){
			double f = 1.0*abc[i]/sum;
			Alpha temp = new Alpha();
			temp.c = c;
			temp.count = abc[i];
			temp.f = f*100;
			letter.add(temp);
			c++;
		}

		//排序
		Collections.sort(letter,(Alpha o1,Alpha o2) -> o2.count-o1.count );

		//输出
		System.out.println("文件："+filename);
		Iterator<Alpha> iterator = letter.iterator();
		while(iterator.hasNext()) {
			Alpha test = iterator.next();
			System.out.printf("%c出现次数：%d  频率:%.2f%c",test.c,test.count,test.f,'%');
			System.out.println();
		}
		scanner.close();
	}

	//  对应参数-f，显示单词数量，并排序输出
	public static void showwordc(String filename,int num) throws Exception {

		fileread(filename);
		HashMap<String, Integer > hashMap=new HashMap<String,Integer>();
		while(scanner.hasNextLine()){
			String line=scanner.nextLine();
			String[] lineWords=line.split("\\W+");//用非单词符来做分割，分割出来的就是一个个单词
			Set<String> wordSet=hashMap.keySet();

			for(int i=0;i<lineWords.length;i++){
				String str = lineWords[i].toLowerCase();
				char c;
				try {c = str.charAt(0);} catch(Exception e) {c = '0';}
				if(c>='a'&&c<='z'&&(str.matches("[\\d\\p{Lower}]+"))) {
					if(wordSet.contains(str)){
						Integer number=hashMap.get(str);
						number++;
						hashMap.put(str, number);
					}
					else {
						hashMap.put(str, 1);
					}
				}
			}
		}
		//排序
		Set<Map.Entry<String, Integer>> entrymap = hashMap.entrySet();
		ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(entrymap);
		Collections.sort(list,(Entry<String, Integer> o1, Entry<String, Integer> o2) -> {
			if(o2.getValue()==o1.getValue()) {
				return o1.getKey().compareTo(o2.getKey());
			}
			else return o2.getValue()-o1.getValue();
		});
		//输出
		System.out.println("文件："+filename);
		Iterator<Map.Entry<String,Integer>> iterator = list.iterator();
		while(iterator.hasNext()&&(num>0||num==-1)) {
			Map.Entry<String, Integer> test = iterator.next();
			System.out.println(test.getKey()+":"+test.getValue());
			if(num!=-1) num--;
		}
		scanner.close();
	}

	//  对应参数-d，对当前目录文件执行-f操作
	public static void showfileword(String dir,int num) throws Exception {
		File dirFile = new File(dir);
		File[] listFile = dirFile.listFiles();
		for(File file:listFile) {
			if(!file.isDirectory())
				showwordc(file.toString(),num);
		}
	}

	//  对应参数-d -s,递归执行-d操作
	public static void showlists(String dir,int num) throws Exception {
		File dirFile = new File(dir);
		File[] listFile = dirFile.listFiles();
		for(File file:listFile) {
			if(file.isDirectory()) {
				showlists(file.toString(),num);
			}
			else {
				showwordc(file.toString(),num);
			}
		}
	}

	//  对应参数-x,跳过制定文件stopfile中的单词，执行-f操作
	public static void stopwords(String filename,String stopfile)throws Exception{
		File stop = new File(stopfile);
		Scanner stopscanner = new Scanner(stop);
		Set<String> stopwords = new HashSet<String>() ; //将要跳过的单词保存在stopwords集合中
		fileread(filename);
		while(stopscanner.hasNextLine())
		{
			String lines=stopscanner.nextLine();
			String[] lineWordss=lines.split("\\W+");//用非单词符来做分割，分割出来的就是一个个单词
			for(String s:lineWordss){
				if(!stopwords.contains(s)){
					stopwords.add(s);
				}
			}
		}
		HashMap<String, Integer > hashMap=new HashMap<String,Integer>();
		while(scanner.hasNextLine()){
			String line=scanner.nextLine();
			String[] lineWords=line.split("\\W+");

			Set<String> wordSet=hashMap.keySet();
			for(int i=0;i<lineWords.length;i++){
				String str = lineWords[i].toLowerCase();
				char c;
				try {c = str.charAt(0);} catch(Exception e) {c = '0';}
				if(c>='a'&&c<='z'&&(lineWords[i].toLowerCase().matches("[\\d\\p{Lower}]+"))) {
					if(!stopwords.contains(str)) {
						if(wordSet.contains(str)){
							Integer number=hashMap.get(str);
							number++;
							hashMap.put(str, number);
						}
						else {
							hashMap.put(str, 1);
						}
					}
				}
			}
		}
		//排序
		Set<Map.Entry<String, Integer>> entrymap = hashMap.entrySet();
		ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(entrymap);
		Collections.sort(list,(Entry<String, Integer> o1, Entry<String, Integer> o2) -> {
			if(o2.getValue()==o1.getValue()) {
				return o1.getKey().compareTo(o2.getKey());
			}
			else return o2.getValue()-o1.getValue();
		});

		//输出
		Iterator<Map.Entry<String,Integer>> iterator = list.iterator();
		while(iterator.hasNext()){
			Map.Entry<String, Integer> test = iterator.next();
			System.out.println(test.getKey()+":"+test.getValue());
		}

		stopscanner.close();
		scanner.close();
	}

}
