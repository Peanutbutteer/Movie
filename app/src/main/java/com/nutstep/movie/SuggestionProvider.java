package com.nutstep.movie;

import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.annotation.Nullable;

/**
 * Created by peanutbutteer on 3/5/2016 AD.
 */
public class SuggestionProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.nutstep.movie.SuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;
    public SuggestionProvider() {
        setupSuggestions(AUTHORITY,MODE);
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, CancellationSignal cancellationSignal) {
        return super.query(uri, projection, selection, selectionArgs, sortOrder, cancellationSignal);
    }
}
