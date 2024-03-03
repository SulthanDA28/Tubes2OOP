package com.himehime.lib;

public interface FileLoader {
    /*load file */
    public void loadFile(String filename);
    /*cek apakah file didukung plugin ini*/
    public Boolean isSupported(String filename);
    /*dapatkan jumlah baris*/
    public int getRowCount();
    /*dapatkan jumlah kolom*/
    public int getColCount();
    /*dapatkan isi sel tertentu*/
    public String getCell(int row, int col);
}