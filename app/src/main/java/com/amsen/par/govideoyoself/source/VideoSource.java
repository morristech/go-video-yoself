package com.amsen.par.govideoyoself.source;

import android.net.Uri;

import com.amsen.par.govideoyoself.api.VideoApi;
import com.amsen.par.govideoyoself.base.rx.event.EventStream;
import com.amsen.par.govideoyoself.model.VideoStatus;
import com.amsen.par.govideoyoself.persistence.MockStorage;
import com.amsen.par.govideoyoself.persistence.Storage;

import java.util.List;

import rx.Observable;

/**
 * Source = Single Source of Truth. VideoSource is the single source/sink for all VideoStatus related actions.
 * Keeps a cache and manage the API.
 * 
 * @author Pär Amsen 2016
 */
public class VideoSource {
    private Storage<VideoStatus> cache;
    private VideoApi api;

    public VideoSource(EventStream eventStream) {
        cache = new MockStorage();
        api = new VideoApi(eventStream);
    }

    public Observable<List<VideoStatus>> get() {
        return Observable.just(cache.getAll());
    }

    public Observable<Boolean> put(int id, Uri uri) {
        return api.post(id, uri)
                .first()
                .doOnNext(e -> cache.get(id).setCompleted(true));
    }
}
