package com.example.afinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class NoteAdapter extends FirestoreRecyclerAdapter<Note, NoteAdapter.NoteHolder> {
    private OnItemClickListener listener;
    private OnItemLongClickListener listen;

    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull Note model) {
        holder.referenceType.setText(model.getReferenceType());
        holder.price.setText(model.getRentAmount());
        holder.referenceFrom.setText(model.getReferencefrom());
        holder.address.setText(model.getPropertyAddress());
        holder.bhk.setText(model.getBhk());
        holder.propertyType.setText(model.getProperty_Type());
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,
                parent, false);
        return new NoteHolder(v);
    }


     void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }


    public void clear() {
        notifyDataSetChanged();
    }


     void setOnItemLongClickListener(OnItemLongClickListener listen) {
        this.listen = listen;
    }

     void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(DocumentSnapshot documentSnapshot, int position);
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        TextView referenceFrom, price, address, referenceType, bhk, propertyType;


        private NoteHolder(final View itemView) {
            super(itemView);
            propertyType = itemView.findViewById(R.id.property_Type);
            referenceFrom = itemView.findViewById(R.id.referenceFrom);
            price = itemView.findViewById(R.id.price);
            address = itemView.findViewById(R.id.address);
            referenceType = itemView.findViewById(R.id.referenceType);
            bhk = itemView.findViewById(R.id.Bhk);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position1 = getAdapterPosition();
                    if (position1 != RecyclerView.NO_POSITION && listen != null) {
                        listen.onItemLongClick(getSnapshots().getSnapshot(position1), position1);
                    }
                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

}