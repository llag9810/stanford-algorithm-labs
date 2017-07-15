#include <stdio.h>
#include <stdlib.h>

long countSplitInversions(int *a, int *b, int size_a, int size_b, int *temp) {

    int i = 0;
    int j = 0;

    long num = 0;

    for (int count = 0; count < size_a + size_b; count++) {

        if (a[i] <= b[j] && i != size_a || j == size_b) {
            temp[count] = a[i++];
            num += j;

        } else if(a[i] > b[j] && j != size_b || i == size_a) {
            temp[count] = b[j++];
        }
    }

    for (int count = 0; count < size_a + size_b; count++) {
        a[count] = temp[count];
    }

    return num;
}

long countInversions(int *a, int n, int *temp) {

    if (n == 1) return 0;

    long x = countInversions(a, n / 2, temp);
    long y = countInversions(a + n / 2, n - n / 2, temp + n / 2);
    long z = countSplitInversions(a, a + n / 2, n / 2, n - n / 2, temp);

    return x + y + z;

}

int main() {

    int *a = (int *)malloc(100000 * sizeof(int));
    int *temp = (int *)malloc(100000 * sizeof(int));

    FILE* fp = fopen("IntegerArray.txt", "r");

    for (int i = 0; i < 100000; i++) {
        fscanf(fp, "%d\n", &a[i]);
    }

    long result = countInversions(a, 100000, temp);
    printf("%ld\n", result);
    return 0;
}