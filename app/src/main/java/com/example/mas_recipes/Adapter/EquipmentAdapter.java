package com.example.mas_recipes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mas_recipes.API.Models.Equipment;
import com.example.mas_recipes.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.InstructionsStepEquipmentViewHolder> {

    Context context;
    List<Equipment> equipmentList;

    public EquipmentAdapter(Context context, List<Equipment> equipmentList) {
        this.context = context;
        this.equipmentList = equipmentList;
    }

    @NonNull
    @Override
    public InstructionsStepEquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionsStepEquipmentViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_equipmet_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsStepEquipmentViewHolder holder, int position) {
        if (equipmentList.get(position) != null) {
            holder.txt_equipment_empty.setVisibility(View.GONE);
            holder.txt_instruction_equipment_name.setText(String.valueOf(equipmentList.get(position).name));
            Picasso.get().load("https://spoonacular.com/cdn/equipment_100x100/" + equipmentList.get(position).image)
                    .into(holder.equipment_img);
        } else {
            holder.txt_equipment_empty.setVisibility(View.VISIBLE);
            holder.equipment_layout.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }


    class InstructionsStepEquipmentViewHolder extends RecyclerView.ViewHolder {
        TextView txt_instruction_equipment_name, txt_equipment_empty;
        ImageView equipment_img;
        LinearLayout equipment_layout;

        public InstructionsStepEquipmentViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_instruction_equipment_name = itemView.findViewById(R.id.txt_equipment_name);
            txt_equipment_empty = itemView.findViewById(R.id.txt_equipment_empty);
            equipment_img = itemView.findViewById(R.id.equipment_img);
            equipment_layout = itemView.findViewById(R.id.equipment_layout);
        }
    }
}