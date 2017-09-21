package harper.com.dribb.view.shot_list;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import harper.com.dribb.R;
import harper.com.dribb.model.Shot;
import harper.com.dribb.utils.ImageUtils;
import harper.com.dribb.utils.ModelUtils;
import harper.com.dribb.view.base.BaseViewHolder;
import harper.com.dribb.view.base.InfiniteAdapter;
import harper.com.dribb.view.shot_detail.ShotActivity;
import harper.com.dribb.view.shot_detail.ShotFragment;

class ShotListAdapter extends InfiniteAdapter<Shot> {

    private final ShotListFragment shotListFragment;

    public ShotListAdapter(@NonNull ShotListFragment shotListFragment,
                           @NonNull List<Shot> data,
                           @NonNull LoadMoreListener loadMoreListener) {
        super(shotListFragment.getContext(), data, loadMoreListener);
        this.shotListFragment = shotListFragment;
    }

    @Override
    protected BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext())
                                  .inflate(R.layout.list_item_shot, parent, false);
        return new ShotViewHolder(view);
    }

    @Override
    protected void onBindItemViewHolder(BaseViewHolder holder, int position) {
        ShotViewHolder shotViewHolder = (ShotViewHolder) holder;

        final Shot shot = getData().get(position);
        shotViewHolder.cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShotActivity.class);
                intent.putExtra(ShotFragment.KEY_SHOT,
                                ModelUtils.toString(shot, new TypeToken<Shot>(){}));
                intent.putExtra(ShotActivity.KEY_SHOT_TITLE, shot.title);
                shotListFragment.startActivityForResult(intent, ShotListFragment.REQ_CODE_SHOT);
            }
        });

        shotViewHolder.likeCount.setText(String.valueOf(shot.likes_count));
        shotViewHolder.bucketCount.setText(String.valueOf(shot.buckets_count));
        shotViewHolder.viewCount.setText(String.valueOf(shot.views_count));

        ImageUtils.loadShotImage(shot, shotViewHolder.image);
    }
}
