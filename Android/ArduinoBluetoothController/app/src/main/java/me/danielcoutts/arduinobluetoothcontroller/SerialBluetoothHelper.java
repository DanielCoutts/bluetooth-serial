package me.danielcoutts.arduinobluetoothcontroller;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Helper for sending strings of data to a serial Bluetooth device.
 */
public class SerialBluetoothHelper {

    private static final String TAG = "SerialBluetoothHelper";

    private static final UUID SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private String macAddress;

    private Activity activity;

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;
    private OutputStream outputStream;

    public SerialBluetoothHelper(Activity activity, String macAddress) {
        this.activity = activity;
        this.macAddress = macAddress;

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothIsSupported()) {
            enableBluetooth();
        }
    }

    private boolean bluetoothIsSupported() {
        if (bluetoothAdapter == null) {
            Log.e(TAG, "[ERROR] Bluetooth not supported on this device");
            return false;
        } else {
            return true;
        }
    }

    private void enableBluetooth() {
        if (!bluetoothAdapter.isEnabled()) {
            Log.i(TAG, "turning on Bluetooth...");
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(intent, 1);
        }
    }

    public void connect() {
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(macAddress);

        try {
            bluetoothSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Log.e(TAG, "[ERROR] could not connect: " + e.getMessage());
        }

        bluetoothAdapter.cancelDiscovery();

        Log.d(TAG, "connecting...");
        try {
            bluetoothSocket.connect();
            Log.d(TAG, "connection successful");
        } catch (IOException e) {
            try {
                bluetoothSocket.close();
            } catch (IOException e2) {
                Log.e(TAG, "[ERROR] connection error");
            }
        }

        try {
            outputStream = bluetoothSocket.getOutputStream();
        } catch (IOException e) {
            Log.e(TAG, "[ERROR] could not create output stream: " + e.getMessage());
        }
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        return device.createRfcommSocketToServiceRecord(SPP_UUID);
    }

    public void disconnect() {
        if (outputStream != null) {
            try {
                outputStream.flush();
            } catch (IOException e) {
                Log.e(TAG, "[ERROR] unable to flush output stream: " + e.getMessage());
            }
        }

        try {
            bluetoothSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "[ERROR] unable to close bluetooth connection: " + e.getMessage());
        }
    }

    public void sendData(String dataString) {
        if (isConnected()) {
            byte[] buffer = dataString.getBytes();

            Log.i(TAG, "sending data: \"" + dataString + "\"");

            try {
                outputStream.write(buffer);
            } catch (IOException e) {
                Log.e(TAG, "[ERROR] unable to transmit data: " + e.getMessage());
            }
        }
    }

    private boolean isConnected() {
        if (bluetoothAdapter != null && bluetoothSocket != null && bluetoothSocket.isConnected()) {
            return true;
        } else {
            Log.e(TAG, "bluetooth not connected (make sure you have called 'connect' before attempting to send data)");
            return false;
        }
    }
}
