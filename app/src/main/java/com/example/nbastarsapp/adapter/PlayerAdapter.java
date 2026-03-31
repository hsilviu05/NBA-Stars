package com.example.nbastarsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nbastarsapp.R;
import com.example.nbastarsapp.model.Player;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    public interface OnPlayerClickListener {
        void onPlayerClick(Player player);
    }

    private Context context;
    private List<Player> playerList;
    private OnPlayerClickListener listener;

    public PlayerAdapter(Context context, List<Player> playerList, OnPlayerClickListener listener) {
        this.context = context;
        this.playerList = playerList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_player, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = playerList.get(position);

        holder.txtName.setText(player.getName());
        holder.txtSubtitle1.setText(player.getTeam());
        holder.txtSubtitle2.setText(player.getPosition());

        int imageResId = context.getResources().getIdentifier(
                player.getImageName(),
                "drawable",
                context.getPackageName()
        );

        holder.imgPlayer.setImageResource(imageResId);

        holder.itemView.setOnClickListener(v -> listener.onPlayerClick(player));
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public void updateList(List<Player> newList) {
        playerList = newList;
        notifyDataSetChanged();
    }

    static class PlayerViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPlayer;
        TextView txtName, txtSubtitle1, txtSubtitle2;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPlayer = itemView.findViewById(R.id.imgPlayer);
            txtName = itemView.findViewById(R.id.txtName);
            txtSubtitle1 = itemView.findViewById(R.id.txtSubtitle1);
            txtSubtitle2 = itemView.findViewById(R.id.txtSubtitle2);
        }
    }
}