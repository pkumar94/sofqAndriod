package com.softenger.sofq.util.helper;

import android.support.transition.Fade;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentHelper {


    public static FragmentHelper newInstance(){
        return new FragmentHelper();
    }
    public void replaceFragment(FragmentManager fragmentManager , Fragment fragment, Boolean addToBackstack, int container) {

        setAnimation(fragment);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(container, fragment, fragment.getClass().getSimpleName());
        if(addToBackstack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();
    }

    public void addFragment(FragmentManager fragmentManager, Fragment fragment, Boolean addToBackstack, int container) {

        setAnimation(fragment);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(container, fragment, fragment.getClass().getSimpleName());
        if(addToBackstack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();
    }

    //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setAnimation(Fragment fragment){
        Fade exitFade = new Fade();
        Long FADE_DEFAULT_TIME = 100l;
        Long MOVE_DEFAULT_TIME = 10l;

        exitFade.setDuration(FADE_DEFAULT_TIME);
        fragment.setExitTransition(exitFade);

        Fade enterFade = new Fade();
        enterFade.setStartDelay(MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME);
        enterFade.setDuration(FADE_DEFAULT_TIME);
        fragment.setEnterTransition(enterFade);
    }
}
