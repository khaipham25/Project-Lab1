package com.example.lab1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1.AddEditRoomActivity;
import com.example.lab1.MainActivity;
import com.example.lab1.R;
import com.example.lab1.model.Room;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private Context context;
    private ArrayList<Room> roomList;

    public RoomAdapter(Context context, ArrayList<Room> roomList) {
        this.context = context;
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);

        holder.txtRoomName.setText(room.getRoomName());
        holder.txtPrice.setText("Giá: " + String.format("%,.0f", room.getPrice()) + " VNĐ");

        if (room.isRented()) {
            holder.txtStatus.setText("Trạng thái: Đã thuê");
            holder.txtStatus.setTextColor(Color.RED);
        } else {
            holder.txtStatus.setText("Trạng thái: Còn trống");
            holder.txtStatus.setTextColor(Color.GREEN);
        }

        // Chức năng Sửa
        holder.btnEdit.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Intent intent = new Intent(context, AddEditRoomActivity.class);
                intent.putExtra("room", room);
                intent.putExtra("position", pos);
                context.startActivity(intent);
            }
        });

        // Chức năng Xóa
        holder.btnDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc muốn xóa " + room.getRoomName() + "?");
            builder.setPositiveButton("Xóa", (dialog, which) -> {
                int pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    MainActivity.roomList.remove(pos);
                    notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("Hủy", null);
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView txtRoomName, txtPrice, txtStatus;
        Button btnEdit, btnDelete;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRoomName = itemView.findViewById(R.id.txtRoomName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
