package com.codean.keuangan.util;

public class PaginationHelper {
    public static int calculateTotalPages(long totalElement, int pageSize) {
        // Menghitung total halaman dengan pembulatan
        int totalPage = (int) (totalElement / pageSize);
        if (totalElement % pageSize != 0) {
            totalPage++;
        }
        return totalPage;
    }
}