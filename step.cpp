/*
author: 冯阳
软件工程第一次作业——个人项目第0步 
*/
#include<bits/stdc++.h>
using namespace std;
int main(){
    char c;
    FILE *fp;
    vector<char>s;
    int xiaoxie[27]={0};
    char xiaoxiezimu[26]={'a'};
    char daxiezimu[26]={'A'};
    int daxie[27]={0};
    int len=0;
    fp = fopen("test1.txt","rb");
    for(int i=1;i<26;i++){
    	xiaoxiezimu[i]=xiaoxiezimu[i-1]+1;
    	daxiezimu[i]=daxiezimu[i-1]+1;
	}
    while((c = fgetc(fp)) != EOF){
    	if(c>='a'&&c <='z'||c>='A'&&c <='Z'){
    		len++;
			switch(c){
				case 'a':xiaoxie[0]++;break;
				case 'b':xiaoxie[1]++;break;
				case 'c':xiaoxie[2]++;break;
				case 'd':xiaoxie[3]++;break;
				case'e':xiaoxie[4]++;break;
				case'f':xiaoxie[5]++;break;
				case'g':xiaoxie[6]++;break;
				case'h':xiaoxie[7]++;break;
				case'i':xiaoxie[8]++;break;
				case'j':xiaoxie[9]++;break;
				case'k':xiaoxie[10]++;break;
				case'l':xiaoxie[11]++;break;
				case'm':xiaoxie[12]++;break;
				case'n':xiaoxie[13]++;break;
				case'o':xiaoxie[14]++;break;
				case'p':xiaoxie[15]++;break;
				case'q':xiaoxie[16]++;break;
				case'r':xiaoxie[17]++;break;
				case's':xiaoxie[18]++;break;
				case't':xiaoxie[19]++;break;
				case'u':xiaoxie[20]++;break;
				case'v':xiaoxie[21]++;break;
				case'w':xiaoxie[22]++;break;
				case'x':xiaoxie[23]++;break;
				case'y':xiaoxie[24]++;break;
				case'z':xiaoxie[25]++;break;
				case'A':daxie[0]++;break;
				case'B':daxie[1]++;break;
				case'C':daxie[2]++;break;
				case'D':daxie[3]++;break;
				case'E':daxie[4]++;break;
				case'F':daxie[5]++;break;
				case'G':daxie[6]++;break;
				case'H':daxie[7]++;break;
				case'I':daxie[8]++;break;
				case'J':daxie[9]++;break;
				case'K':daxie[10]++;break;
				case'L':daxie[11]++;break;
				case'M':daxie[12]++;break;
				case'N':daxie[13]++;break;
				case'O':daxie[14]++;break;
				case'P':daxie[15]++;break;
				case'Q':daxie[16]++;break;
				case'R':daxie[17]++;break;
				case'S':daxie[18]++;break;
				case'T':daxie[19]++;break;
				case'U':daxie[20]++;break;
				case 'V':daxie[21]++;break;
				case 'W':daxie[22]++;break;
				case 'X':daxie[23]++;break;
				case 'Y':daxie[24]++;break;
				case 'Z':daxie[25]++;break;
			}
		}
	}
	for(int i=0;i<26;i++){
		cout<<xiaoxiezimu[i]<<"出现的频率为：";
		printf("%.2lf\n",1.0*xiaoxie[i]/len);
		cout<<daxiezimu[i]<<"出现的频率为：";
		printf("%.2lf\n",1.0*daxie[i]/len);
	}
	cout<<len; 
    return 0;
}
