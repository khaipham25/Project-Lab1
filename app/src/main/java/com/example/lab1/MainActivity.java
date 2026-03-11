package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1.adapter.RoomAdapter;
import com.example.lab1.model.Room;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Room> roomList = new ArrayList<>();
    private RoomAdapter roomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo dữ liệu mẫu nếu danh sách trống
        if (roomList.isEmpty()) {
            roomList.add(new Room("R01", "Phòng 101", 1500000, false, "", ""));
            roomList.add(new Room("R02", "Phòng 102", 2000000, true, "Nguyễn Văn A", "0123456789"));
        }

        // hiển thị danh sách phòng ra main
        RecyclerView recyclerViewRooms = findViewById(R.id.recyclerViewRooms);
        recyclerViewRooms.setLayoutManager(new LinearLayoutManager(this));
        
        roomAdapter = new RoomAdapter(this, roomList);
        recyclerViewRooms.setAdapter(roomAdapter);

        Button btnAddRoom = findViewById(R.id.btnAddRoom);
        btnAddRoom.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditRoomActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (roomAdapter != null) {
            roomAdapter.notifyDataSetChanged();
        }
    }
}
