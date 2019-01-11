package com.furkanturkmen.recipe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecipeResult {
    @SerializedName("count")
    @Expose
    int count;
    @SerializedName("recipes")
    @Expose
    ArrayList<Recipe> recipes;
}