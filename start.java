

public class start {
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		char parameter;
		int i=0;
		String filename = args[args.length-1];
		parameter = args[i].charAt(1);
		if(args[0].charAt(0)=='-') {
			switch(parameter) {
			case 'c': step1to4.showletterf(filename);
				break;

			case 'd':
				int num = step1to4.isNumber(args[args.length-1]);
				if(args[i+1].equals("-s"))
					step1to4.showlists(args[i+2],num);
				else
					step1to4.showfileword(args[i+1],num);
				break;

			case 'f': step1to4.showwordc(filename,-1);
				break;

			case 'p':
				break;

			case 'x': step1to4.stopwords(filename,args[i+1]);
				break;

			}
		}
		else {System.out.println("参数异常");}
		long end = System.currentTimeMillis();
		System.out.println("-------------------------");
		System.out.println("程序运行时间："+(end-start)+"ms");
		System.out.println("-------------------------");
	}

}
