package com.example.android.miwok;

/**
 * Created by eribas on 04/05/2017.
 */

public class Word {
    private static final int NO_IMAGE_PROVIDED = -1;

    private String defaultTranslation, miwokTranslation;
    private int imageResourceId = NO_IMAGE_PROVIDED;
    private int audioResourceId;

    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId ){
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.audioResourceId = audioResourceId;
    }

    public Word(int imageResourceId, String defaultTranslation, String miwokTranslation, int audioResourceId){
        this.imageResourceId = imageResourceId;
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.audioResourceId = audioResourceId;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public int getImageResourceId(){
        return  imageResourceId;
    }

    public int getAudioResourceId() {
        return audioResourceId;
    }

    public boolean hasImage(){
        return imageResourceId != NO_IMAGE_PROVIDED;
    }

}
