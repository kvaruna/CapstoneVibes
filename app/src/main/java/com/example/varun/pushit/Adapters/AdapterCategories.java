package com.example.varun.pushit.Adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.varun.pushit.Listners.OnTapListener;
import com.example.varun.pushit.R;
import com.example.varun.pushit.Utils.ImageLoader;

import java.util.ArrayList;

public class AdapterCategories extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    // Create variables to store data
    private final ArrayList<String> mCategoryIds;
    private final ArrayList<String> mCategoryNames;
    private final ArrayList<String> mCategoryImages;
    private final ArrayList<String> mTotalWorkouts;

    // Create listener object
    private OnTapListener onTapListener;

    // Create Context and ImageLoader objects
    private Context mContext;
    private ImageLoader mImageLoader;

    // Create view and LayoutInflater objects
    private View mHeaderView;
    private LayoutInflater mInflater;

    // Constructor to set classes objects
    public AdapterCategories(Context context, View headerView)
    {
        this.mCategoryIds = new ArrayList<>();
        this.mCategoryNames = new ArrayList<>();
        this.mCategoryImages = new ArrayList<>();
        this.mTotalWorkouts = new ArrayList<>();

        mContext = context;

        mHeaderView = headerView;

        mInflater = LayoutInflater.from(context);

        // Get image width and height sizes from dimens.xml
        int mImageWidth = mContext.getResources().getDimensionPixelSize(R.dimen.thumb_width);
        int mImageHeight = mContext.getResources().getDimensionPixelSize(R.dimen.thumb_height);

        mImageLoader = new ImageLoader(mContext, mImageWidth, mImageHeight);

    }

    @Override
    public int getItemCount() {
        // If header has been set, add +1 to arraylist variables size
        if (mHeaderView == null) {
            return mCategoryIds.size();
        } else {
            return mCategoryIds.size();
        }
    }

//    @Override
//    public int getItemViewType(int position) {
//        return VIEW_TYPE_ITEM;
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position)
    {
        if(viewHolder instanceof ItemViewHolder){
            ((ItemViewHolder) viewHolder).mItemContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onTapListener != null)
                        onTapListener.onTapView(mCategoryIds.get(viewHolder.getAdapterPosition()), mCategoryNames.get(viewHolder.getAdapterPosition()));
                }
            });

            ((ItemViewHolder) viewHolder).mTxtWorkoutName.setText(mCategoryNames.get(viewHolder.getAdapterPosition()));

            // Get total workout
            int count = Integer.parseInt(mTotalWorkouts.get(viewHolder.getAdapterPosition()));

            // If total workout is more than one then add 's'
            if(count > 1){
                ((ItemViewHolder) viewHolder).mTxtWorkoutNumber.setText(count+" "+mContext.getResources().getString(R.string.workouts));
            }else{
                ((ItemViewHolder) viewHolder).mTxtWorkoutNumber.setText(count+" "+mContext.getResources().getString(R.string.workout));
            }

            // Set image to ImageView
            int image = mContext.getResources().getIdentifier(mCategoryImages.get(viewHolder.getAdapterPosition()), "drawable", mContext.getPackageName());

            // Load image lazily
            mImageLoader.loadBitmap(image, ((ItemViewHolder) viewHolder).mImgCategoryImage);
        }


    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View view) {
            super(view);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        // Create view objects
        private ImageView mImgCategoryImage;
        private TextView mTxtWorkoutName, mTxtWorkoutNumber;
        private ConstraintLayout mItemContainer;

        public ItemViewHolder(View view) {
            super(view);
            // Connect view objects with view ids in xml
            mImgCategoryImage   = (ImageView) view.findViewById(R.id.imgThumbnail);
            mTxtWorkoutName     = (TextView) view.findViewById(R.id.txtPrimaryText);
            mTxtWorkoutNumber   = (TextView) view.findViewById(R.id.txtSecondaryText);
            mItemContainer      = (ConstraintLayout) view.findViewById(R.id.item_container);
        }
    }

    // Method to set data to recyclerview item
    public void updateList(
            ArrayList<String> categoryIds,
            ArrayList<String> categoryNames,
            ArrayList<String> categoryImages,
            ArrayList<String> totalWorkouts) {

        this.mCategoryIds.clear();
        this.mCategoryIds.addAll(categoryIds);

        this.mCategoryNames.clear();
        this.mCategoryNames.addAll(categoryNames);

        this.mCategoryImages.clear();
        this.mCategoryImages.addAll(categoryImages);

        this.mTotalWorkouts.clear();
        this.mTotalWorkouts.addAll(totalWorkouts);

        this.notifyDataSetChanged();
    }

    // Method to set listener to handle item click
    public void setOnTapListener(OnTapListener onTapListener)
    {
        this.onTapListener = onTapListener;
    }


}