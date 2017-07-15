#include <stdio.h>
#include <stdlib.h>

void merge(int a[], int b[], int size_a, int size_b, int temp[]) {

{{{



    int i = 0;
    int j = 0;

    for (int count = 0; count < size_a + size_b; count++) {

        if (a[i] < b[j] && i < size_a || j == size_b)
            temp[count] = a[i++];
        else if (a[i] >= b[j] && j < size_b || i == size_a)
            temp[count] = b[j++];
    }

    for (int count = 0; count < size_a + size_b; count++) {
        a[count] = temp[count];
    }

}
    int i = 0;
    int j = 0;

    for (int count = 0; count < size_a + size_b; count++) {

        if (a[i] < b[j] && i < size_a || j == size_b)
            temp[count] = a[i++];
        else if (a[i] >= b[j] && j < size_b || i == size_a)
            temp[count] = b[j++];
    }

    for (int count = 0; count < size_a + size_b; count++) {
        a[count] = temp[count];
    }

}


void mergeSort(int a[], int n, int temp[]) {

    if (n == 1) return;

    mergeSort(a, n / 2, temp);
    mergeSort(a + n / 2, n - n / 2, temp + n / 2);

    merge(a, a + n / 2, n / 2, n - n / 2, temp);
}


int main() {

    int a[] = {3, 5, 8, 6, 7, 4, 2, 1};
    int *temp = (int *)malloc(sizeof(a));

    mergeSort(a, 8, temp);

    for (int i = 0; i < sizeof(a) / sizeof(int); i++) {
        printf("%d ", temp[i]);
    }

    return 0;
}