#include <stdio.h>

void swap(int *a, int *b) {

    int temp = *a;
    *a = *b;
    *b = temp;
}


void insertionSort(int *a, int n)
{
    for(int i = 1; i < n; i++) {
        int j = i;
        while(j > 0 && (a[j] < a[j - 1])) {
            swap(&a[j], &a[j - 1]);
            j--;
        }
    }
}

int select(int *a, int k, int n) {

    if (n <= 1) return a[0];

    for (int i = 0; i < n / 5; i++) {

        insertionSort(&a[5 * i], 5);
        swap(&a[i], &a[5 * i + 2]);
    }

    select(a, n / 10, n / 5);

    int pivot = a[0];

    int i = 0;
    int j = n - 1;

    for (;;) {

        while (a[++i] < pivot);
        while (a[--j] > pivot);

        if(i >= j) break;

        swap(&a[i], &a[j]);
    }

    i--;
    swap(&a[0], &a[i]);

    if (k == i) return a[i];
    else if(k < i) return select(a, k, i);
    else return select(a + i + 1, k - i - 1, n - i - 1);
}

int main(void) {

    int a[] = {3, 5, 8, 7, 11, 4, 1, 0, 22, 35, 16};
    int k = select(a, 5, 11);
    printf("%d", k);

}