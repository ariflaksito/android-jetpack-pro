package net.ariflaksito.mymovie.data.source.local.room;

import android.content.Context;

import net.ariflaksito.mymovie.data.source.local.entity.MovieEntity;
import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = { MovieEntity.class, TvShowEntity.class},
        version = 5,
        exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();

    private static volatile MovieDatabase INSTANCE;

    public static MovieDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MovieDatabase.class, "movies.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }



}
