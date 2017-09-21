package harper.com.dribb.view.shot_detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

class ShotImageViewHolder extends RecyclerView.ViewHolder {

    SimpleDraweeView image;

    public ShotImageViewHolder(View itemView) {
        super(itemView);
        image = (SimpleDraweeView) itemView;
    }
}
