package com.codean.mybatispesantren.services;

public interface PresensiService {

    public void register(int idSantri, int idKegiatan, String status);
    public void cancelRegister(int idSantri, int idKegiatan, String status);
}
