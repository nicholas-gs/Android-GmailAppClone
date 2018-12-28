package com.example.user.gmailappclone.Helper;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.user.gmailappclone.Adapter.PrimaryRVAdapter;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private RecyclerItemTouchHelperListener listener;

    public interface RecyclerItemTouchHelperListener{
       void onSwiped(RecyclerView.ViewHolder viewHolder,int direction, int position);
    }

    //Constructor
    public RecyclerItemTouchHelper(RecyclerItemTouchHelperListener listener,int dragDir,int swipeDir){
        super(dragDir,swipeDir);
        this.listener = listener;
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        listener.onSwiped(viewHolder,i,viewHolder.getAdapterPosition());
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if(viewHolder != null){
            View foregroundLayout = ((PrimaryRVAdapter.MyViewHolder)viewHolder).getForegroundContainer();
            getDefaultUIUtil().onSelected(foregroundLayout);
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View foregroundLayout = ((PrimaryRVAdapter.MyViewHolder)viewHolder).getForegroundContainer();
        getDefaultUIUtil().onDraw(c,recyclerView,foregroundLayout,dX,dY,actionState,isCurrentlyActive);
    }


    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        View foregroundLayout = ((PrimaryRVAdapter.MyViewHolder)viewHolder).getForegroundContainer();
        getDefaultUIUtil().clearView(foregroundLayout);
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }
}
