package e.clement.a205enroot;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Clément on 02/04/2018.
 */

public class ImagesViewHolder extends RecyclerView.ViewHolder {

    //@BindView(R.id.news_page_item_title) TextView title;
    //@BindView(R.id.news_page_item_content) TextView content;
    @BindView(R.id.gallery_image) ImageView image;
    public ImagesViewHolder(View itemView) {
        super(itemView);
        //itemView = (TextView) itemView.findViewById(R.id.news_page_item_title);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithImages(Image images){
        //this.title.setText(news.getTitle());
        //this.content.setText(news.getContent());
        Glide.with(this.image)
                .load(images.getUrl())
                .thumbnail(0.5f)
                .into(image);

    }
}
