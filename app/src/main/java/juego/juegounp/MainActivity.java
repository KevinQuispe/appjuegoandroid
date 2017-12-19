package juego.juegounp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_cpu,tv_player,tv_jugadas;
    Button button;
    ImageView iv_player,iv_cpu;
    Random r;
    int cpuPoints=0, playerPoints=0;
    int veces=3;
    //player music
    MediaPlayer mp;
    Button iniciar,parar,restart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_cpu=(ImageView) findViewById(R.id.iv_cpu);
        iv_player=(ImageView) findViewById(R.id.iv_player);
        tv_cpu=(TextView) findViewById(R.id.tv_cpu);
        tv_player=(TextView) findViewById(R.id.tv_player);
        tv_jugadas=(TextView) findViewById(R.id.tv_jugadas);
        //ini random
        r=new Random();
        //play music
        mp=MediaPlayer.create(this,R.raw.walker);
        mp.start();

        parar=(Button) findViewById(R.id.btparar);
        iniciar=(Button) findViewById(R.id.btplay);
        restart=(Button) findViewById(R.id.restart);

        parar.setOnClickListener(this);
        iniciar.setOnClickListener(this);
        restart.setOnClickListener(this);

        //===================================
        //metodo Onclick Listner para la imagen rotativa de dado
        iv_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cpuThrow = r.nextInt(6) + 1;
                int playerThrow = r.nextInt(6) + 1;

                setImageCPU(cpuThrow);
                setImagePlayer(playerThrow);
                int numjugadas=r.nextInt(6)+1;

                if (cpuThrow > playerThrow) {
                    cpuPoints++;

                }
                if (playerThrow > cpuThrow) {
                    playerPoints++;
                }

                if (playerThrow == cpuThrow) {
                    Toast.makeText(MainActivity.this, "Lanza otra vez", Toast.LENGTH_SHORT).show();
                }

                    //compara el numero de intentos del cpu

                    if (cpuPoints == numjugadas || playerPoints==numjugadas){
                        if (cpuPoints > playerPoints) {
                            Toast.makeText(MainActivity.this, "GAME OVER GANO CPU", Toast.LENGTH_SHORT).show();
                            //break;
                        } else if (cpuPoints == playerPoints) {
                            Toast.makeText(MainActivity.this, "HAN EMPATADO BUEN TRABAJO", Toast.LENGTH_SHORT).show();
                            //break;

                        } else if (playerPoints > cpuPoints) {
                            Toast.makeText(MainActivity.this, "GOD JOB GANASTE ", Toast.LENGTH_SHORT).show();
                            //break;
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Sigue jugando", Toast.LENGTH_SHORT).show();

                    }
               //here setea
                tv_cpu.setText("CPU: " + cpuPoints);
                tv_player.setText("YOU: " + playerPoints);
                tv_jugadas.setText("Jugadas Ramdon: " + numjugadas);
                Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                iv_player.startAnimation(rotate);
                iv_cpu.startAnimation(rotate);

            }
        });


    }
    public void setImageCPU(int num){

        switch (num){
            case 1:
                iv_cpu.setImageResource(R.drawable.ic_1);
                break;
            case 2:
                iv_cpu.setImageResource(R.drawable.ic_2);
                break;
            case 3:
                iv_cpu.setImageResource(R.drawable.ic_3);
                break;
            case 4:
                iv_cpu.setImageResource(R.drawable.ic_4);
                break;
            case 5:
                iv_cpu.setImageResource(R.drawable.ic_5);
                break;
            case 6:
                iv_cpu.setImageResource(R.drawable.ic_6);
                break;
        }
    }
    public void setImagePlayer(int num) {

        switch (num) {
            case 1:
                iv_player.setImageResource(R.drawable.ic_1);
                break;
            case 2:
                iv_player.setImageResource(R.drawable.ic_2);
                break;
            case 3:
                iv_player.setImageResource(R.drawable.ic_3);
                break;
            case 4:
                iv_player.setImageResource(R.drawable.ic_4);
                break;
            case 5:
                iv_player.setImageResource(R.drawable.ic_5);
                break;
            case 6:
                iv_player.setImageResource(R.drawable.ic_6);
                break;
        }
    }
    @Override
    public void onClick(View view) {
        if (view.getId()==iniciar.getId()) {

            if (mp.isPlaying()){
                mp.start();
            }
            else{
                Toast.makeText(this,"Playing Music",Toast.LENGTH_SHORT).show();
            }
            mp.start();
        }
        else if(view.getId()==parar.getId()){
            mp.pause();
            Toast.makeText(this,"Stop Music",Toast.LENGTH_SHORT).show();
        }
        else if(view.getId()==restart.getId())
        {
            startActivity(getIntent());
        }

    }
}
