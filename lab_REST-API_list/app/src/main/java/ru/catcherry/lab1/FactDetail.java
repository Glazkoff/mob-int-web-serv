package ru.catcherry.lab1;

import com.google.gson.annotations.SerializedName;

public class FactDetail {

    @SerializedName("_id")
    String _id;

    @SerializedName("text")
    String text;

    @SerializedName("type")
    String type;

    @SerializedName("upvotes")
    String upvotes;
}
