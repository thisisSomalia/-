/*
author: 冯阳
readme: 在DOS命令下step.exe -d
软件工程第二次作业——个人项目第0步 
*/
#include<bits/stdc++.h>
#include<io.h>
using namespace std;
int main(int argc,char** argv){
    ifstream order(argv[1]);
    ifstream input(argv[2]);
    ofstream output("./fy_step0_output.txt");
    string type="";
    for(int i=0;i<strlen(argv[1]);i++)
    	type+=argv[1][i];
    string s;
    map<char,size_t> mapp;
    map<char,size_t> ::iterator it;
    size_t sum=0;
    if(type.size()==2){
    	cout<<type<<endl;
    	if(type=="-c"){
    		while(getline(input,s)){
    			transform(s.begin(),s.end(),s.begin(),::tolower);
    			for(int i=0;i<s.length();i++)
    				if(isalpha(s[i])) ++mapp[s[i]];
			}
			for(it=mapp.begin();it!=mapp.end();it++)
				output<<it->first<<"的频率为："<<it->second<<endl;
		}
		else if(type=="-d"){
			_finddata_t file;
			long lf;
			char file_dir[55]="";
			strcat(file_dir,argv[2]);
			strcat(file_dir,"\\*.txt");
			int k=1;
			long HANDLE;
			k=HANDLE=_findfirst(file_dir,&file);
			cout<<file_dir<<endl<<endl;
			while(k!=-1){//success 
				sum=0;
				output<<file.name<<endl;
				char filee[55]="";
				strcat(filee,argv[2]);
				strcat(filee,"\\");
				strcat(filee,file.name);
				ifstream e(filee);
				while(getline(e,s)){
					transform(s.begin(),s.end(),s.begin(), ::tolower);
		    		for(int i=0; i<s.size(); i++){
		    	   		if(isalpha(s[i]))	mapp[s[i]]++;
		    	  	}
				}
				for(it=mapp.begin();it!=mapp.end();it++)
					sum+=(it->second);
				for(it=mapp.begin();it!=mapp.end();it++)
			  		output<<it->first<<"的频率为："<<(100.0*(it->second)/sum)<<"%"<<endl;	
				output<<endl<<endl;
				k = _findnext( HANDLE, &file );	
			}
			_findclose( HANDLE );
		}
	}
	input.close();
	output.close();
    return 0;
}
