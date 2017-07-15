#include <iostream>
#include <cstdio>
#include <cstring>

void bignum(char *num1, char *num2)
{
    int length1 = strlen(num1);
    int length2 = strlen(num2);
    int i, l;
    char *res = (char *)malloc(sizeof(char)*(length1 + length2)); //开辟相应内存
    memset(res, 0, sizeof(char)*(length1 + length2));
    for (i = length1 - 1; i >= 0; i--)
        for (l = length2 - 1; l >= 0; l--)
        {
            res[i + l + 1] += (num1[i] - '0')*(num2[l] - '0');
            res[i + l] += res[i + l + 1] / 10;    //马上进行进位
            res[i + l + 1] %= 10;
        }
    int count = 0;
    while (res[count] == 0)  //由于保存的结果是从右向左的，所以要消除左部分的0；
    {
        count++;
    }
    char* ret = (char *)malloc(sizeof(char)*(length1 + length2 + 2));
    memset(ret, 0, sizeof(char)*(length1 + length2 + 2));
    for (l = 0, i = count; i < length1 + length2; l++, i++)  //非0部分赋给ret
    {
        ret[l] = res[i] + '0';
    }
    printf("Ret=%s\n", ret);
    free(res);
    free(ret);
}

int main() {
    bignum("3141592653589793238462643383279502884197169399375105820974944592", "2718281828459045235360287471352662497757247093699959574966967627");
    return 0;
}