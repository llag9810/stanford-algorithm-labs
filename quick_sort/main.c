#include <stdio.h>
#include <stdlib.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

int quickSort(int a[], int n) {

    if (n <= 1) return 0;

    int i = 1;
    int j = 1;


    int mid = (n % 2 == 0) ? (n / 2 - 1) : (n / 2);

    if (n >= 3) {

        if (a[0] < a[mid] && a[mid] < a[n - 1] || a[n - 1] < a[mid] && a[mid] < a[0]) {
            swap(&a[0], &a[mid]);
        } else if (a[mid] < a[0] && a[0] < a[n - 1] || a[n - 1] < a[0] && a[0] < a[mid]);
        else {
            swap(&a[n - 1], &a[0]);
        }
    }

    if (n == 2) a[0] > a[1] ? swap(&a[0], &a[1]) : 0;

    int pivot = a[0];

    printf("pivot = %d      ", pivot);

    for (j = 1; j < n; j++) {

        if (a[j] < pivot) {
            swap(&a[i], &a[j]);
            i++;
        }
    }
    swap(&a[0], &a[i - 1]);


    for (int i = 0; i < n && i != i - 1; i++) {
        printf("%d ", a[i]);
    }

    puts("");

    int p = quickSort(a, i - 1);
    int q = quickSort(a + i, n - i);

    return n - 1 + p + q;
}


int main() {

    int *a = (int *) malloc(10000 * sizeof(int));

    FILE *fp = fopen("QuickSort.txt", "r");

    for (int i = 0; i < 10000; i++) {
        fscanf(fp, "%d\n", &a[i]);
    }

    //int a[] = {57, 97, 17, 31, 54, 98, 87, 27, 89, 81, 18, 70, 3, 34, 63, 100, 46, 30, 99, 10, 33, 65, 96, 38, 48, 80, 95, 6, 16, 19, 56, 61, 1, 47, 12, 73, 49, 41, 37, 40, 59, 67, 93, 26, 75, 44, 58, 66, 8, 55, 94, 74, 83, 7, 15, 86, 42, 50, 5, 22, 90, 13, 69, 53, 43, 24, 92, 51, 23, 39, 78, 85, 4, 25, 52, 36, 60, 68, 9, 64, 79, 14, 45, 2, 77, 84, 11, 71, 35, 72, 28, 76, 82, 88, 32, 21, 20, 91, 62, 29};

    int res = quickSort(a, 10000);

    for (int i = 0; i < 10000; i++)
        printf("%d ", a[i]);

    printf("\n%d", res);
    return 0;

}