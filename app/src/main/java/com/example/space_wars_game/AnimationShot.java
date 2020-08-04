package com.example.space_wars_game;


import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class AnimationShot {

    public static void startAnimationFight(final ImageView imageGun, final  ImageView imageGunShot)
    {
        imageGunShot.setVisibility(View.VISIBLE);
        Animation alpha = new AlphaAnimation(0f, 1f);


        alpha.setDuration(300);
        alpha.setFillAfter(true);
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                shotDissapear(imageGun);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                shotAnimationEnd(imageGun);
                GunAnimationEnd(imageGunShot);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imageGunShot.startAnimation(alpha);
    }
    public static void shotDissapear(final ImageView image)
    {

        Animation alpha = new AlphaAnimation(1f, 0f);




        alpha.setDuration(300);
        alpha.setFillAfter(true);
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image.startAnimation(alpha);
    }
    public static void shotAnimationEnd(final ImageView image)
    {

        Animation alpha = new AlphaAnimation(0f, 1f);




        alpha.setDuration(300);
        alpha.setFillAfter(true);
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image.startAnimation(alpha);
    }
    public static void GunAnimationEnd(final ImageView image)
    {

        Animation alpha = new AlphaAnimation(1f, 0f);




        alpha.setDuration(300);
        alpha.setFillAfter(true);
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image.startAnimation(alpha);
    }
}
