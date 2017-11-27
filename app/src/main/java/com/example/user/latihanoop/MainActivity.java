package com.example.user.latihanoop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    ListView listPesanan;
    TextView txtQty, txtTotalHarga, txtPesanan;
    Button btnMin, btnPlus, btnAdd, btnOrder, btnReset;
    EditText edtNamaPesanan, edtHargaPesanan;
    int totalHarga = 0;
    String dataPesanan = "Data Pesanan \n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Panggil method initLayout
        this.initLayout();

        //Deklarasi btnPlus saat di tekan
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Panggil method addQty untuk mengubah nilai txtQty
                addQty();
            }
        });

        //Deklarasi btnMin saat di tekan
        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Panggil method minQty untuk mengubah nilai txtQty
                minQty();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaPesanan = edtNamaPesanan.getText().toString();
                int hargaPesanan = Integer.valueOf(edtHargaPesanan.getText().toString());
                int qty = Integer.valueOf(txtQty.getText().toString());
                addItemList(namaPesanan,hargaPesanan,qty);
                txtTotalHarga.setText("Rp "+ money(totalHarga)); //Menampilkan harga dengan format mata uang
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAll(); //Panggil method resetAll untuk mengosongkan semua form dan mengembalikan seperti awal
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOrder();
            }
        });


    }

    //Buat Method initLayout
    private void initLayout(){
        //method initLayout berfungsi untuk menginisialisasi setiap view pada tampilan android.


        txtQty = (TextView) findViewById(R.id.txtQty);
        txtPesanan = (TextView) findViewById(R.id.txtPesanan);
        txtTotalHarga = (TextView) findViewById(R.id.txtTotalHarga);

        btnMin = (Button) findViewById(R.id.btnMin);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnOrder = (Button) findViewById(R.id.btnOrder);
        btnReset = (Button) findViewById(R.id.btnReset);

        edtNamaPesanan = (EditText) findViewById(R.id.edtNamaPesanan);
        edtHargaPesanan = (EditText) findViewById(R.id.edtHargaPesanan);
    }


    //Buat Method addQty
    private void addQty(){
        //Method addQty berfungsi untuk menambahkan jumlah qty pada tampilan android.

        String qty = txtQty.getText().toString(); // 1. Ambil value text dari txtQty
        int qtyPesanan = Integer.valueOf(qty); //2. Mengubah value text sebelumnya menjadi sebuah angka (integer) agar dapat dimanimulasi
        qtyPesanan++; // 3. Menambahkan 1 nilai kepada qtyPesanan

        txtQty.setText(String.valueOf(qtyPesanan)); //4. Memberikan nilai qtyPesanan kepada txtQty. Nilai qtyPesanan adalah nilai txtQty sebelumnya dan ditambahkan satu sesuai langkah ke 3
    }


    //Buat Method minQty
    private void minQty(){
        //Method addQty berfungsi untuk mengurangi jumlah qty pada tampilan android.

        String qty = txtQty.getText().toString(); // 1. Ambil value text dari txtQty
        int qtyPesanan = Integer.valueOf(qty); //2. Mengubah value text sebelumnya menjadi sebuah angka (integer) agar dapat dimanimulasi
        if (qtyPesanan > 0) { //3. Melakukan pengecekan apakah nilai pada txtQty lebih besar dari 0 atau tidak, untuk menghindari nilai minus
            qtyPesanan--; // 4. Jika nilai txtQty lebih besar dari 0, maka akan mengurangi 1 nilai kepada qtyPesanan
        }

        txtQty.setText(String.valueOf(qtyPesanan)); //5. Memberikan nilai qtyPesanan kepada txtQty. Nilai qtyPesanan adalah nilai txtQty sebelumnya dan dikurangkan satu sesuai langkah ke 3 - 4
    }

    //Buat method addItemList
    private void addItemList(String namaPesanan, int hargaPesanan, int qty){
        //Method ini berfungsi untuk menambahkan Daftar Pesanan
        int total = hitungHarga(hargaPesanan,qty); // memanggil method hitungHarga untuk mendapatkan hasil.
        String pesan = "- "+namaPesanan+" ( Rp "+money(hargaPesanan)+" * "+qty+") = "+money(total)+"\n"; // Pesan ini befungsi untuk menambahkan data barang pesanan.
        dataPesanan+=pesan; //text dataPesanan di tambahkan dengan pesan diatas..
        txtPesanan.setText(dataPesanan);
        resetForm();
    }

    //Buat Method hitungHarga
    private int hitungHarga(int hargaPesanan, int qty) {
        //method ini berfungsi untuk menghitung harga dengan rumus harga satuan dikalikan qty.
        int total = hargaPesanan * qty;
        totalHarga +=total;
        return total;
    }

    //Buat method money
    private String money(int nominal){
        //method ini berfungsi untuk memberikan format uang kepada value integer
        return NumberFormat.getInstance().format(nominal);
    }

    //Buat method resetForm
    private void resetForm(){
        //method ini berfungsi untuk mengosongkan form
        edtHargaPesanan.setText("");
        edtNamaPesanan.setText("");
        txtQty.setText("0");
    }

    //Buat method resetAll
    private void resetAll() {
        //method ini berfungsi untuk mengosongkan seluruh form dan list juga.
        resetForm();
        txtPesanan.setText("Data Pesanan");
        String dataPesanan = "Data Pesanan \n";
        txtTotalHarga.setText("Rp 0");
        totalHarga = 0;
    }

    //Buat method sendOrder
    private void sendOrder(){
        //method ini berfungsi untuk berpindah ke activity OrderActivity;
        Intent intent = new Intent(MainActivity.this,OrderActivity.class); //Intent berfungsi untuk berpindah.. formatnya Intent <nama_intent> = new Intent(NamaKelasIni.this,KelasTujuan.class);
        intent.putExtra("totalHarga",totalHarga); //putExtra berfungsi untuk memberikan nilai pada variable yang akan dibawa ke activity lain.. formatnya <nama_intent>.putExtra("<nama_variable>",<value>); INGAT PERHATIKAN TIPE DATA VALUENYA
        startActivity(intent); // untuk melakukan perpindahan activity
    }



}
