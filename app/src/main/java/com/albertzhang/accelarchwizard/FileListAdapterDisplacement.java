package com.albertzhang.accelarchwizard;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.io.File;
import java.util.Date;
import java.util.Random;

/**
 * Created by Albert Zhang on 18/4/2016.
 */
public class FileListAdapterDisplacement extends RecyclerView.Adapter<FileListAdapterDisplacement.ViewHolder> {

    private Context context;
    private OnItemClickListener itemClickListener;
    public String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AccelArchwizard_trackerData";

    private int prev = -1;

    Random random = new Random();

    public FileListAdapterDisplacement(Context context){
        this.context = context;
    }

    @Override
    public FileListAdapterDisplacement.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FileListAdapterDisplacement.ViewHolder holder, int position) {
        final File file = DisplacementTimeGraph.fileList.get(position);
        holder.fileName.setText(file.getName());
        holder.savingTime.setText(new Date(file.lastModified()).toString());
        String[] colorCode = context.getResources().getStringArray(R.array.IntColorCodeList);
        ((CardView)holder.itemView).setCardBackgroundColor(Color.parseColor(colorCode[select(colorCode.length)]));
    }

    public int select(int max){
        int next = -1;
        while(next==prev||next<0){
            next = random.nextInt(max);
        }
        prev = next;
        return next;
    }

    @Override
    public int getItemCount() {
        return DisplacementTimeGraph.fileList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public LinearLayout mainHolder;
        public LinearLayout fileNameHolder;
        public TextView fileName;
        public TextView savingTime;

        public ViewHolder(final View itemView){
            super(itemView);
            mainHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            fileNameHolder = (LinearLayout) itemView.findViewById(R.id.fileNameHolder);
            fileName = (TextView) itemView.findViewById(R.id.fileName);
            savingTime = (TextView) itemView.findViewById(R.id.savingTime);
            fileNameHolder.setOnClickListener(this);
            fileNameHolder.setLongClickable(true);
            fileNameHolder.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View v) {
                    final int position = getAdapterPosition();
                    final PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                    popupMenu.getMenu().add("Delete");
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if(item.equals(popupMenu.getMenu().getItem(0))){
                                File file = new File(filePath + File.separator + DisplacementTimeGraph.fileList.get(position).getName());
                                if(file.exists()){
                                    if(file.delete()){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                        builder.setIcon(R.drawable.acceleration_vector_white);
                                        builder.setTitle("File deleted");
                                        builder.setMessage("File deleted successfully: " + DisplacementTimeGraph.fileList.get(position));
                                        builder.show();
                                        DisplacementTimeGraph.fileList.remove(position);
                                        notifyItemRemoved(position);
                                    }else{
                                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                        builder.setIcon(R.drawable.acceleration_vector_white);
                                        builder.setTitle("Error");
                                        builder.setMessage("File failed to delete: "+DisplacementTimeGraph.fileList.get(position));
                                        builder.show();
                                    }
                                }else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                    builder.setIcon(R.drawable.acceleration_vector_white);
                                    builder.setTitle("Error");
                                    builder.setMessage("The file you requested may have been deleted or corrupted: "+DisplacementTimeGraph.fileList.get(position));
                                    builder.show();
                                }
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
                    return true;
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(itemView, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }


}
