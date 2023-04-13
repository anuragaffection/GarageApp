package com.example.garageapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class YourCarsAdapter extends RecyclerView.Adapter<YourCarsAdapter.ViewHolder> {


    Context context;
    ArrayList<model> arrCars;


    YourCarsAdapter(Context context, ArrayList<model> arrCars){
        this.context = context;
        this.arrCars = arrCars;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.your_car, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int positionAdapter = holder.getAdapterPosition();
        holder.updateImage.setImageResource(arrCars.get(positionAdapter).image);
        holder.carMakeView.setText(arrCars.get(positionAdapter).carMake);
        holder.carModelView.setText(arrCars.get(positionAdapter).carModel);
        holder.addCarImage.setText(arrCars.get(positionAdapter).addCarImage);
        holder.deleteCar.setText(arrCars.get(positionAdapter).deleteCar);

        holder.addCarImage.setOnClickListener(v -> holder.updateImage.setImageResource(R.drawable.anuragaffection));

    }



    @Override
    public int getItemCount() {
        return arrCars.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView updateImage;
        TextView carMakeView;
        TextView carModelView;
        TextView addCarImage;
        TextView deleteCar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            updateImage = itemView.findViewById(R.id.updateImage);
            carMakeView = itemView.findViewById(R.id.carMakeView);
            carModelView = itemView.findViewById(R.id.carModelView);
            addCarImage = itemView.findViewById(R.id.addCarImage);
            deleteCar = itemView.findViewById(R.id.deleteCar);

        }
    }
}
