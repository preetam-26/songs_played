package com.playsongs.com;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SongsPlayed {
	
	private int capacity;

    private int currentSize;

    private Map<String, ArrayList<UserObjects>> songs;

    private Deque<UserObjects> recentSongs;

    public SongsPlayed(int initialCapacity){
        capacity=initialCapacity;
        currentSize=0;
        songs=new HashMap<>();
        recentSongs=new LinkedList<>();
    }

    /**
     * User adding songs to the recently played song list
     */
    public void addSong(String name, String user){
        if (!songs.containsKey(user)){
            songs.put(user, new ArrayList<UserObjects>());
        }
        evictLeastRecentlyPlayed();

        UserObjects song=new UserObjects(name, user);

        songs.get(user).add(song);
        recentSongs.addFirst(song);
        currentSize++;
    }

    /**
     * This function returns the songs by removing the least recently played songs
     */
    private void evictLeastRecentlyPlayed(){
        if(currentSize>=capacity){
        	UserObjects leastRecentlyPlayed =recentSongs.removeLast();
            songs.get(leastRecentlyPlayed.getUser()).remove(leastRecentlyPlayed);
            if(songs.get(leastRecentlyPlayed.getUser()).isEmpty()){
                songs.remove(leastRecentlyPlayed.getUser());
            }
            currentSize--;
        }
    }

    /**
     * This function returns recently played songs
     */
    public List<String> getRecentlyPlayedSongs(String user){
        if(songs.containsKey(user)){
            ArrayList<UserObjects> userSongs = songs.get(user);
            ArrayList<String> recentlyPlayedSongs=new ArrayList<>();
            for(UserObjects song:userSongs){
                recentlyPlayedSongs.add(song.getName());
            }
            return recentlyPlayedSongs;
        }else {
            return new ArrayList<>();
        }
    }

}
