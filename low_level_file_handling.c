#include<stdio.h>
#include <sys/stat.h> 
#include<unistd.h>
#include<fcntl.h>



int main(int argc, char* argv[]){
	int arr[1000];
	int i,f;

        /*
	for(i=0;i<1000;i++){
		arr[i] = 2*i;
	}
	
	f = open("arr_elements.txt",O_CREAT|O_WRONLY|O_TRUNC,S_IRUSR|S_IWUSR|S_IXUSR|S_IRGRP|S_IROTH); //if no create here, then no need for the third parameter
   	write(f,arr,1000*sizeof(int)); //second parameter is num of elements we need to write and it needs to be equal to the length of second param to avoid overflow and similar problems
   	close(f); 
   	*/
   	
   	f = open("arr_elements.txt",O_RDONLY);
   	
   	/*
   	lseek(f,3*sizeof(int),SEEK_SET); // move cursor 3 values from beginning of file
   	lseek(f,-3*sizeof(int),SEEK_END); // move cursor 3 values back from end of file
   	lseek(f,3*sizeof(int),SEEK_CUR); // move cursor 3 values from the current position of the cursor
   	
   	//lseek(f,4*sizeof(int),SEEK_SET);
   	//read(f,&arr[4],1000*sizeof(int));
   	//printf("%d", arr[4]);
   	*/
   
   	int n = read(f,arr,1000*sizeof(int));
   	printf("%d\n", n); // it gives 4000 (4 bytes * 1000)
   	printf("%d\t%d\t%d", arr[0],arr[10],arr[999]);
   	
   	return 0;
}
