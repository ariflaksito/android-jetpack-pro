package utils;

import net.ariflaksito.mymovie.data.source.local.entity.MovieEntity;
import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;

import java.util.ArrayList;

public class FakeDataDummy {

    public static MovieEntity generateDummyMovie(){
        return new MovieEntity("475557", "Joker", "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.", "2019-10-04","udDclJoHjfjb8Ekgsd4FDteOkCU.jpg");
    }

    public static TvShowEntity generateDummyTvShow(){
        return new TvShowEntity("1412", "Arrow", "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.", "2012-10-10", "gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg");
    }

    public static ArrayList<MovieEntity> generateDummyMovies() {

        ArrayList<MovieEntity> movies = new ArrayList<>();

        movies.add(new MovieEntity("m1", "The Old Man & the Gun", "The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public. Wrapped up in the pursuit","September 28, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg"));
        movies.add(new MovieEntity("m2", "Avengers: Infinity War", "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.", "April 27, 2018","https://image.tmdb.org/t/p/w370_and_h556_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg"));
        movies.add(new MovieEntity("m3", "Spider-Man: Into the Spider-Verse", "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.","December 14, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg"));
        movies.add(new MovieEntity("m4", "Bohemian Rhapsody", "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics", "November 2, 2018","https://image.tmdb.org/t/p/w370_and_h556_bestv2/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg"));
        movies.add(new MovieEntity("m5", "Kung Fu League", "A poor comic book artist summons four legendary Kung Fu masters to learn the highest level of martial arts and help him get his girl.", "October 19, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/6cNChoxMqlTLwX8pWXPh56EjatE.jpg"));
        movies.add(new MovieEntity("m6", "Black Panther", "King T'Challa returns home from America to the reclusive, technologically advanced African nation of Wakanda to serve as his country's new leader. However, T'Challa soon finds that he is challenged for the throne", "February 16, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/uxzzxijgPIY7slzFvMotPv8wjKA.jpg"));
        movies.add(new MovieEntity("m7", "Venom", "Investigative journalist Eddie Brock attempts a comeback following a scandal, but accidentally becomes the host of Venom, a violent, super powerful alien symbiote. Soon, he must rely on his newfound powers to protect", "October 5, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"));
        movies.add(new MovieEntity("m8", "Deadpool 2", "Wisecracking mercenary Deadpool battles the evil and powerful Cable and other bad guys to save a boy's life.", "May 10, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/to0spRl1CMDvyUbOnbb4fTk3VAd.jpg"));
        movies.add(new MovieEntity("m9", "A Star Is Born", "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight.", "October 5, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg"));
        movies.add(new MovieEntity("m10", "Halloween", "Laurie Strode comes to her final confrontation with Michael Myers, the masked figure who has haunted her since she narrowly escaped his killing spree on Halloween night four decades ago.", "October 19, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/bXs0zkv2iGVViZEy78teg2ycDBm.jpg"));

        return movies;

    }

    public static MovieEntity getMovie(String movieId) {
        for (int i = 0; i < generateDummyMovies().size(); i++) {
            MovieEntity entity = generateDummyMovies().get(i);
            if (entity.getMovieId().equals(movieId)) {
                return entity;
            }
        }
        return null;
    }

    public static ArrayList<TvShowEntity> generateDummyTvShows(){

        ArrayList<TvShowEntity> tvshows = new ArrayList<>();

        tvshows.add(new TvShowEntity("t1", "Titans", "A team of young superheroes led by Nightwing (formerly Batman's first Robin) form to combat evil and other perils.", "October 12, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/eeHI5iBSCOxj4HGSOmFM6azBmwb.jpg"));
        tvshows.add(new TvShowEntity("t2", "Legacies", "In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson’s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman’s twins, Lizzie", "October 25, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/rb64COqdpRRfWOc6gWTfC7WxzXP.jpg"));
        tvshows.add(new TvShowEntity("t3", "9-1-1", "Explore the high-pressure experiences of police officers, paramedics and firefighters who are thrust into the most frightening, shocking and heart-stopping situations. These emergency responders must try to balance", "January 3, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/htX1x0gq8Y5bN9vtGzERf9nbOTK.jpg"));
        tvshows.add(new TvShowEntity("t4", "Insatiable", "A bullied teenager turns to beauty pageants as a way to exact her revenge, with the help of a disgraced coach who soon realizes he's in over his head.", "August 10, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/lHZ4xqGQlmyiFTOVtwnNpTcZgkd.jpg"));
        tvshows.add(new TvShowEntity("t5", "The Resident", "A tough, brilliant senior resident guides an idealistic young doctor through his first day, pulling back the curtain on what really happens, both good and bad, in modern-day medicine.", "January 21, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/k1Df8EkXpsEogBj1FXbLfn2mm4i.jpg"));
        tvshows.add(new TvShowEntity("t6", "Krypton", "Set two generations before the destruction of the legendary Man of Steel’s home planet, Krypton follows Superman’s grandfather — whose House of El was ostracized and shamed — as he fights to redeem his family’s", "March 21, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/uiinjmSkka6JOrk4FsZmrjlNM26.jpg"));
        tvshows.add(new TvShowEntity("t7", "Black Lightning", "Jefferson Pierce is a man wrestling with a secret. As the father of two daughters and principal of a charter high school that also serves as a safe haven for young people in a New Orleans neighborhood overrun by", "January 16, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/90QFKG1QK7szgMIB2SxFADY1pvy.jpg"));
        tvshows.add(new TvShowEntity("t8", "Killing Eve", "A security consultant hunts for a ruthless assassin. Equally obsessed with each other, they go head to head in an epic game of cat-and-mouse.", "April 8, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/cmLJFWOklp4PpUkUfeCFIKntbTH.jpg"));
        tvshows.add(new TvShowEntity("t9", "The Rookie", "Starting over isn’t easy, especially for small-town guy John Nolan who, after a life-altering incident, is pursuing his dream of being an LAPD officer. As the force’s oldest rookie, he’s met with skepticism from", "October 16, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/arPUIveaNTdlZeSrOPrgof6UvhQ.jpg"));
        tvshows.add(new TvShowEntity("t10", "The Purge", "Set in a dystopian America ruled by a totalitarian political party, the series follows several seemingly unrelated characters living in a small city. Tying them all together is a mysterious savior who’s impeccably", "September 4, 2018", "https://image.tmdb.org/t/p/w370_and_h556_bestv2/9CaS2XFd0Db42grzzVBnWcSkrbg.jpg"));

        return tvshows;

    }

    public static TvShowEntity getTvShow(String movieId) {
        for (int i = 0; i < generateDummyTvShows().size(); i++) {
            TvShowEntity entity = generateDummyTvShows().get(i);
            if (entity.getMovieId().equals(movieId)) {
                return entity;
            }
        }
        return null;
    }
}
