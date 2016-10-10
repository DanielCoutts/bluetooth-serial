package me.danielcoutts.arduinobluetoothcontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Basic implementation of SerialBluetoothHelper to send serial Bluetooth
 * data to an HC-06 Bluetooth device connected to an Arduino Uno.
 *
 * Created by Daniel Coutts. https://github.com/DanielCoutts
 */
public class MainActivity extends AppCompatActivity {

    private static final String MAC_ADDRESS = "20:15:12:08:63:79";

    private SerialBluetoothHelper serialBluetoothHelper;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        serialBluetoothHelper = new SerialBluetoothHelper(this, MAC_ADDRESS);
        serialBluetoothHelper.connect();

        toolbar.setTitle("Control Panel");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        serialBluetoothHelper.disconnect();
    }

    @OnClick(R.id.on_1)
    void button1On() {
        serialBluetoothHelper.sendData("0");
    }

    @OnClick(R.id.off_1)
    void button1Off() {
        serialBluetoothHelper.sendData("8");
    }

    @OnClick(R.id.on_2)
    void button2On() {
        serialBluetoothHelper.sendData("1");
    }

    @OnClick(R.id.off_2)
    void button2Off() {
        serialBluetoothHelper.sendData("9");
    }

    @OnClick(R.id.on_3)
    void button3On() {
        serialBluetoothHelper.sendData("2");
    }

    @OnClick(R.id.off_3)
    void button3Off() {
        serialBluetoothHelper.sendData("A");
    }

    @OnClick(R.id.on_4)
    void button4On() {
        serialBluetoothHelper.sendData("3");
    }

    @OnClick(R.id.off_4)
    void button4Off() {
        serialBluetoothHelper.sendData("B");
    }

    @OnClick(R.id.on_5)
    void button5On() {
        serialBluetoothHelper.sendData("4");
    }

    @OnClick(R.id.off_5)
    void button5Off() {
        serialBluetoothHelper.sendData("C");
    }

    @OnClick(R.id.on_6)
    void button6On() {
        serialBluetoothHelper.sendData("5");
    }

    @OnClick(R.id.off_6)
    void button6Off() {
        serialBluetoothHelper.sendData("D");
    }

    @OnClick(R.id.on_7)
    void button7On() {
        serialBluetoothHelper.sendData("6");
    }

    @OnClick(R.id.off_7)
    void button7Off() {
        serialBluetoothHelper.sendData("E");
    }

    @OnClick(R.id.on_8)
    void button8On() {
        serialBluetoothHelper.sendData("7");
    }

    @OnClick(R.id.off_8)
    void button8Off() {
        serialBluetoothHelper.sendData("F");
    }

    @OnClick(R.id.on_all)
    void buttonAllOn() {
        serialBluetoothHelper.sendData("01234567");
    }

    @OnClick(R.id.off_all)
    void buttonAllOff() {
        serialBluetoothHelper.sendData("89ABCDEF");
    }
}
