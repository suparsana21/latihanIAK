package com.example.user.latihanoop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

public class OrderActivity extends AppCompatActivity {
    TextView txtTotal;
    Button btnShare;
    int totalHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        totalHarga = getIntent().getIntExtra("totalHarga",0); //ini untuk mengambil nilai dari variable "totalHarga" dari activity sebelumnya. Bila TIPE DATA yang dimasukan BERUPA INTEGER, maka gunakan .getIntExtra("<nama_variable>");.. bila TIPE DATA String gunakan .getStringExtra.. dan seterusnya;

        txtTotal.setText("Rp "+money(totalHarga));
    btnShare.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            shareContent();
        }
    });
    }

    private void initLayout(){
        txtTotal = (TextView) findViewById(R.id.txtTotal);
        btnShare = (Button) findViewById(R.id.btnShare);
    }

    private void shareContent(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        String pesan = "[BERHASIL] Saya berhasil melakukan pemesanan dengan total Rp"+money(totalHarga);
        intent.putExtra(Intent.EXTRA_TEXT,pesan);
        intent.setType("text/plain");
        startActivity(intent);
    }

    private String money(int nominal) {
        return NumberFormat.getInstance().format(nominal);
    }
}
