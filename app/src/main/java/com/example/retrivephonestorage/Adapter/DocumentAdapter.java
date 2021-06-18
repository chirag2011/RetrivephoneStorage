package com.example.retrivephonestorage.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.retrivephonestorage.Fragment.FirstFragment;
import com.example.retrivephonestorage.R;
import com.example.retrivephonestorage.Interface.onPadfSelectListener;

import java.io.File;
import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder> {

    private Context context;
    private List<File> pdfFiles;
    private onPadfSelectListener listener;

    public DocumentAdapter(Context context, List<File> pdfFiles, onPadfSelectListener listener) {
        this.context = context;
        this.pdfFiles = pdfFiles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtname.setText(pdfFiles.get(position).getName());
        holder.txtname.setSelected(true);

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPdfSelected(pdfFiles.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pdfFiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtname;
        public CardView cardview;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtname = itemView.findViewById(R.id.xPdfTextview);
            cardview = itemView.findViewById(R.id.xPdfCardview);
            imageView = itemView.findViewById(R.id.xPdfImageview);
        }
    }
}
