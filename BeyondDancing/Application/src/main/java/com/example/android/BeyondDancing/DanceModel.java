package com.example.android.BeyondDancing;

import android.content.Intent;
import android.net.Uri;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by jackp on 2018-07-08.
 */

public class DanceModel extends Observable {
    private static final DanceModel DancingModel = new DanceModel();

    static DanceModel getInstance() {
        return DancingModel;
    }

    private Uri video1uri;
    private Uri video2uri;
    private Uri clienturi;
    private Uri serveruri;
    private int select;
    private int CurrentScreen;
    private boolean issignedin;

    DanceModel() {
        CurrentScreen = 0;
        video1uri = null;
        video2uri = null;
        clienturi = null;
        serveruri =null;
        select = 0;
        issignedin =false;

    }
    public int numbvideo(){
        int num =0;
        if(video1uri != null){
            num = num +1;

        }
        if(video2uri != null){
            num = num +1;
        }
        return num;
    }
    public void signin(){
        issignedin =true;

    }
    public void signout(){
        issignedin =false;

    }

    public boolean signedin(){
        return issignedin;
    }
    public void setServerUri(Uri u){
        serveruri = u;
    }
    public void addUri(int type) {
        if (type == 0) {
            if (select == 0) {
                video1uri = serveruri;
            } else {
                video2uri = serveruri;
            }
            select = (select + 1) % 2;
        } else if (type == 1) {
            if (select == 0) {
                video1uri = clienturi;
            } else {
                video2uri = serveruri;
            }
        }
    }

    public void setClientUri(Uri u) {
        video2uri = u;
    }

    public Uri getUri(int videoindex) {
        switch (videoindex) {
            case 1:
                return video1uri;
            case 2:
                return video2uri;

        }
        return null;
    }
    public boolean fitforcompare(){
        if(video1uri !=null && video2uri !=null) {
            return true;
        }else{
            return false;
        }
    }
    public Uri getServerUri(){
        return serveruri;
    }
    public void resetUri(){
        video1uri = null;
        video2uri = null;
        clienturi = null;
        serveruri = null;
        select = 0;
    }
    public void clearUri(){
        switch (select) {
            case  0:
                video1uri =null;
             case 1:
                video2uri =null;

        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Observable Methods
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Helper method to make it easier to initialize all observers
     */
    public void initObservers()
    {
        setChanged();
        notifyObservers();
    }
    /**
     * Deletes an observer from the set of observers of this object.
     * Passing <CODE>null</CODE> to this method will have no effect.
     *
     * @param o the observer to be deleted.
     */
    @Override
    public synchronized void deleteObserver(Observer o)
    {
        super.deleteObserver(o);
    }

    /**
     * Adds an observer to the set of observers for this object, provided
     * that it is not the same as some observer already in the set.
     * The order in which notifications will be delivered to multiple
     * observers is not specified. See the class comment.
     *
     * @param o an observer to be added.
     * @throws NullPointerException if the parameter o is null.
     */
    @Override
    public synchronized void addObserver(Observer o)
    {
        super.addObserver(o);
    }

    /**
     * Clears the observer list so that this object no longer has any observers.
     */
    @Override
    public synchronized void deleteObservers()
    {
        super.deleteObservers();
    }

    /**
     * If this object has changed, as indicated by the
     * <code>hasChanged</code> method, then notify all of its observers
     * and then call the <code>clearChanged</code> method to
     * indicate that this object has no longer changed.
     * <p>
     * Each observer has its <code>update</code> method called with two
     * arguments: this observable object and <code>null</code>. In other
     * words, this method is equivalent to:
     * <blockquote><tt>
     * notifyObservers(null)</tt></blockquote>
     *
     * @see Observable#clearChanged()
     * @see Observable#hasChanged()
     * @see Observer#update(Observable, Object)
     */
    @Override
    public void notifyObservers()
    {
        super.notifyObservers();
    }
}
