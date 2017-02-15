package com.example.user.vkadvert.interfaces;

import android.view.View;

import rx.Subscription;

/**
 * Created by user on 07.02.2017.
 */
public interface ViewSubscriptionManager {
    void createSubscripion();

    void addSubscription(Subscription subscription);

    void unsubscribeAndDestroy();

    View.OnClickListener onClickListener();

    View getRootView();
}
