package ru.catcherry.lab1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FactsList {
    @SerializedName("all")
    List<FactDetail> all;
}
