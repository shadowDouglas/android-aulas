package br.com.wolneyhqf.android.aulas.threads;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.wolneyhqf.android.aulas.threads.util.MensagemHandler;

public class HandlerActivity extends AppCompatActivity {

    public Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        handler = new MensagemHandler(this);

        Button buttonDispararMensagem = (Button) findViewById(R.id.button_msg);
        buttonDispararMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Message msg = new Message();

                msg.what = MensagemHandler.MSG_01;

                handler.sendMessageDelayed(msg, 3000);

            }
        });


        Button buttonDispararMensagemRunnable = (Button) findViewById(R.id.button_msg_runnable);
        buttonDispararMensagemRunnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(HandlerActivity.this, "A Mensagem Chegou! (Executada no Runnable)", Toast.LENGTH_SHORT).show();
                    }
                }, 3000);


            }
        });

    }
}