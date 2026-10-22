//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
#include <bits/stdc++.h>
//--END FIXED PART----------------------------

//--LIBRARIES - @STUDENT: INCLUDE YOUR OWN LIBRARIES HERE:


//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
using namespace std;

FILE *fi=NULL, *fo=NULL;

int checkIfFileExists(const char * filename)
{
    FILE *file;
    if (file = fopen(filename, "r"))
    {
        fclose(file);
        return 1;
    }
    return 0;
}

void readFile(int argc, char *argv[]) {
    if (argc == 3) {
        if(checkIfFileExists(argv[1]))
        {
            fi = freopen(argv[1], "r", stdin);
        }
        fo = freopen(argv[2], "w", stdout);
    }
}

void closeFile(FILE *f) {
    if (f != NULL)
        fclose(f);
}
//--END FIXED PART----------------------------

//--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:


//--FUNCTIONS - @STUDENT: YOUR FUNCTIONS CODE ARE HERE:


//--FIXED PART - DO NOT EDIT ANY THINGS HERE
int main(int argc, char *argv[]) {
    readFile(argc, argv);
//--END FIXED PART----------------------------
    
    //INPUT  - @STUDENT: ADD YOUR CODE FOR INPUT  HERE:



    //OUTPUT - @STUDENT: ADD YOUR CODE FOR OUTPUT HERE:



//--FIXED PART - DO NOT EDIT ANY THINGS HERE
    closeFile(fi);
    closeFile(fo);
    return(0);
}
//--END FIXED PART----------------------------
