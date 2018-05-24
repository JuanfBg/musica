package co.edu.konradlorenz.cardview;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import co.edu.konradlorenz.cardview.R;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by jiacontrerasp on 02/04/18.
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Activity mContext;
    private List<Album> albumList;
    private Context applicationContext;
    private Transition transition;
    public static final long DURACION = 1000;
int previousPosition = -1;


    public Context getApplicationContext() {

        return this.applicationContext;
    }





    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;
        private View elementView;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            elementView = view;

        }
    }


    public AlbumsAdapter(Activity mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Album album = albumList.get(position);
//        YoYo.with(Techniques.FadeIn).playOn(holder.cardView);

        if(position> previousPosition ){
            AnimationUtil.animate(holder, true);
        }else{
            AnimationUtil.animate(holder,false);

        }

        previousPosition= position;
                holder.elementView.setOnClickListener(new View.OnClickListener() {


                    @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, SeasonsActivity.class);
                intent.putExtra("IMG", album.getThumbnail());

                Slide slide =new Slide ();
                slide.setDuration(DURACION);
                transition = new Slide(Gravity.LEFT);
                mContext.getWindow().setExitTransition(slide);
                mContext.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(mContext,view,"").toBundle());


            }

        });
        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfCaps() + " " + mContext.getString(R.string.Chapter));

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);

            }
        });
    }
    public void animateCircularReveal(View view) {
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }








    /**
     * Mostrar menú emergente al tocar en 3 puntos
     */
    private void showPopupMenu(View view) {
        // inflate menu

        PopupMenu popup = new PopupMenu(mContext, view);

        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();


    }


    /**
     * Evento Click listener para el menú emergente
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:


                    Intent intent2 = new Intent(mContext, EpisodeActivity.class);
                    Explode explode =new Explode ();

                    explode.setDuration(DURACION);
                   // transition = new Slide(Gravity.TOP);
                    mContext.getWindow().setExitTransition(explode);
                    mContext.startActivity(intent2, ActivityOptionsCompat.makeSceneTransitionAnimation(mContext).toBundle());





                    mContext.startActivity(intent2);
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext,mContext.getString(R.string.action_play_next), Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(mContext, prueba.class);

                    mContext.startActivity(intent3);
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
