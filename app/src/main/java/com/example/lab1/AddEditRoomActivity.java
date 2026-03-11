package com.example.lab1;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab1.model.Room;

public class AddEditRoomActivity extends AppCompatActivity {

    private EditText edtRoomId, edtRoomName, edtPrice, edtTenantName, edtPhone;
    private RadioButton rbAvailable, rbRented;
    private Button btnSave, btnBack;

    private int position = -1;
    private Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_room);

        edtRoomId = findViewById(R.id.edtRoomId);
        edtRoomName = findViewById(R.id.edtRoomName);
        edtPrice = findViewById(R.id.edtPrice);
        edtTenantName = findViewById(R.id.edtTenantName);
        edtPhone = findViewById(R.id.edtPhone);
        rbAvailable = findViewById(R.id.rbAvailable);
        rbRented = findViewById(R.id.rbRented);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);

        if (getIntent().hasExtra("room")) {
            room = (Room) getIntent().getSerializableExtra("room");
            position = getIntent().getIntExtra("position", -1);

            if (room != null) {
                edtRoomId.setText(room.getRoomId());
                edtRoomName.setText(room.getRoomName());
                edtPrice.setText(String.valueOf(room.getPrice()));
                edtTenantName.setText(room.getTenantName());
                edtPhone.setText(room.getPhone());

                if (room.isRented()) {
                    rbRented.setChecked(true);
                } else {
                    rbAvailable.setChecked(true);
                }
            }
        } else {
            rbAvailable.setChecked(true);
        }

        btnSave.setOnClickListener(v -> saveRoom());
        
        btnBack.setOnClickListener(v -> finish());
    }

    private void saveRoom() {
        String roomId = edtRoomId.getText().toString().trim();
        String roomName = edtRoomName.getText().toString().trim();
        String priceText = edtPrice.getText().toString().trim();
        String tenantName = edtTenantName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();

        if (TextUtils.isEmpty(roomId)) {
            edtRoomId.setError("Vui lòng nhập mã phòng");
            edtRoomId.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(roomName)) {
            edtRoomName.setError("Vui lòng nhập tên phòng");
            edtRoomName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(priceText)) {
            edtPrice.setError("Vui lòng nhập giá thuê");
            edtPrice.requestFocus();
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (Exception e) {
            edtPrice.setError("Giá thuê không hợp lệ");
            edtPrice.requestFocus();
            return;
        }

        boolean isRented = rbRented.isChecked();

        if (isRented) {
            if (TextUtils.isEmpty(tenantName)) {
                edtTenantName.setError("Vui lòng nhập tên người thuê");
                edtTenantName.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(phone)) {
                edtPhone.setError("Vui lòng nhập số điện thoại");
                edtPhone.requestFocus();
                return;
            }
        } else {
            tenantName = "";
            phone = "";
        }

        Room newRoom = new Room(roomId, roomName, price, isRented, tenantName, phone);

        if (position == -1) {
            for (Room r : MainActivity.roomList) {
                if (r.getRoomId().equalsIgnoreCase(roomId)) {
                    edtRoomId.setError("Mã phòng đã tồn tại");
                    edtRoomId.requestFocus();
                    return;
                }
            }

            MainActivity.roomList.add(newRoom);
            Toast.makeText(this, "Thêm phòng thành công", Toast.LENGTH_SHORT).show();
        } else {
            MainActivity.roomList.set(position, newRoom);
            Toast.makeText(this, "Cập nhật phòng thành công", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
