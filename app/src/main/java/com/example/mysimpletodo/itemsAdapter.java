package com.example.mysimpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class itemsAdapter extends RecyclerView.Adapter<itemsAdapter.ViewHolder> {

   public itemsAdapter(List<String> items) {
    }

    public interface onLongClickListener {
        void onItemLongClicked(int position);


         }

        List<String> items;
        onLongClickListener longClickListener;

        public itemsAdapter(List<String> items, onLongClickListener longClickListener) {
            this.items = items;
            this.longClickListener = longClickListener;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(todoView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            String item = items.get(position);
            holder.bind(item);

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView tvItem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvItem = itemView.findViewById(android.R.id.text1);

            }

            public void bind(String item) {
                tvItem.setText(item);
                tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        longClickListener.onItemLongClicked(getAdapterPosition());
                        //check for this line again

                        return true;

                    }
                });
            }
        }
    }
