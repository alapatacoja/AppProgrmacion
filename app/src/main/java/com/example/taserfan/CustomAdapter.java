package com.example.taserfan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taserfan.DTO.VehiculoDTO;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    List<VehiculoDTO> vehiculos;
    LayoutInflater inflater;
    private View.OnClickListener onClickListener;

    public CustomAdapter (Context context, List<VehiculoDTO> vehiculos){
        this.vehiculos = vehiculos;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.elementorv, parent, false);
        view.setOnClickListener(onClickListener);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VehiculoDTO vehiculoDTO = vehiculos.get(position);
        holder.estado.setText(vehiculoDTO.getEstado());
        holder.color.setText(vehiculoDTO.getColor());
        holder.marca.setText(vehiculoDTO.getMarca());
        holder.precio.setText(vehiculoDTO.getPreciohora());
        holder.icon.setImageResource(R.mipmap.ic_bici_foreground);
    }

    @Override
    public int getItemCount() {
        return vehiculos.size();
    }

    public void setNewData(List<VehiculoDTO> vehiculo){
        this.vehiculos = vehiculo;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView marca;
        TextView estado;
        TextView color;
        TextView precio;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iconrv);
            marca = itemView.findViewById(R.id.marca);
            estado = itemView.findViewById(R.id.estado);
            color = itemView.findViewById(R.id.colorrv);
            precio = itemView.findViewById(R.id.precio);
        }
    }
}
